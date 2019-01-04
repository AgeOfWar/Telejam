package io.github.ageofwar.telejam.examples.pressthebutton;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.github.ageofwar.telejam.inline.InputTextMessageContent;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.text.Text;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static io.github.ageofwar.telejam.json.Json.fromJson;
import static io.github.ageofwar.telejam.json.Json.toPrettyJson;

public final class PressTheButtonStyle {
  
  private final String buttonNotClicked;
  private final String buttonClicked;
  private final String winButton;
  
  private final String inlineQueryTitle;
  private final String inlineQueryDescription;
  private final InputTextMessageContent lobbyMessage;
  private final InlineKeyboardMarkup lobbyKeyboard;
  
  @JsonAdapter(TextTypeAdapter.class)
  private final Text gameMessage;
  
  @JsonAdapter(TextTypeAdapter.class)
  private final Text winMessage;
  
  
  public PressTheButtonStyle(String buttonNotClicked,
                             String buttonClicked,
                             String winButton,
                             String inlineQueryTitle,
                             String inlineQueryDescription,
                             InputTextMessageContent lobbyMessage,
                             InlineKeyboardMarkup lobbyKeyboard,
                             Text gameMessage,
                             Text winMessage) {
    this.buttonNotClicked = buttonNotClicked;
    this.buttonClicked = buttonClicked;
    this.winButton = winButton;
    this.inlineQueryTitle = inlineQueryTitle;
    this.inlineQueryDescription = inlineQueryDescription;
    this.lobbyMessage = lobbyMessage;
    this.lobbyKeyboard = lobbyKeyboard;
    this.gameMessage = gameMessage;
    this.winMessage = winMessage;
  }
  
  public static PressTheButtonStyle fromFile(String path) throws IOException {
    try (FileReader reader = new FileReader(path)) {
      return fromJson(reader, PressTheButtonStyle.class);
    }
  }
  
  public static void save(String path, PressTheButtonStyle style) throws IOException {
    File file = new File(path);
    if (!file.exists()) {
      file.getParentFile().mkdirs();
      file.createNewFile();
    }
    try (FileWriter writer = new FileWriter(path)) {
      toPrettyJson(style, writer);
    }
  }
  
  public String getButtonNotClicked() {
    return buttonNotClicked;
  }
  
  public String getButtonClicked() {
    return buttonClicked;
  }
  
  public String getWinButton() {
    return winButton;
  }
  
  public String getInlineQueryTitle() {
    return inlineQueryTitle;
  }
  
  public String getInlineQueryDescription() {
    return inlineQueryDescription;
  }
  
  public InputTextMessageContent getLobbyMessage() {
    return lobbyMessage;
  }
  
  public InlineKeyboardMarkup getLobbyKeyboard() {
    return lobbyKeyboard;
  }
  
  public Text getGameMessage() {
    return gameMessage;
  }
  
  public Text getWinMessage() {
    return winMessage;
  }
  
  private static class TextTypeAdapter extends TypeAdapter<Text> {
    
    @Override
    public void write(JsonWriter writer, Text text) throws IOException {
      writer.value(text.toHtmlString());
    }
    
    @Override
    public Text read(JsonReader reader) throws IOException {
      return Text.parseHtml(reader.nextString());
    }
    
  }
  
}
