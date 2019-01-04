package io.github.ageofwar.telejam.examples.pressthebutton;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.LongPollingBot;
import io.github.ageofwar.telejam.inline.InputTextMessageContent;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.github.ageofwar.telejam.json.Json.fromJson;

public class PressTheButtonBot extends LongPollingBot {
  
  public static void main(String... args) throws IOException {
    if (args.length != 1) {
      System.err.println("Pass the bot token as unique program argument.");
      System.exit(1);
    }
    String token = args[0];
    Bot bot = Bot.fromToken(token);
    Map<String, PressTheButton> games = new HashMap<>();
    PressTheButtonStyle style = getPressTheButtonStyle();
    new PressTheButtonBot(bot, games, style).run();
  }
  
  private static PressTheButtonStyle getPressTheButtonStyle() throws IOException {
    String path = "./style.json";
    try {
      return PressTheButtonStyle.fromFile(path);
    } catch (FileNotFoundException e) {
      PressTheButtonStyle style = new PressTheButtonStyle(
          "\uD83D\uDD35",
          "\u26ab\ufe0f",
          "\uD83D\uDD34",
          "Press the button",
          null,
          new InputTextMessageContent(new Text("Press to start!"), null),
          fromJson("{\"inline_keyboard\": [[{\"text\":\"start!\", \"callback_data\":\"start 3 3\"}]]}", InlineKeyboardMarkup.class),
          Text.bold("Press the right button!"),
          Text.bold("The winner is {0}!")
      );
      PressTheButtonStyle.save(path, style);
      return style;
    }
  }
  
  public PressTheButtonBot(Bot bot, Map<String, PressTheButton> games, PressTheButtonStyle style) {
    super(bot);
    PressTheButtonMessageUpdater messageUpdater = new PressTheButtonMessageUpdater(bot, games, style);
    events.registerInlineQueryHandler(new PressTheButtonInlineQueryHandler(bot, style));
    events.registerCallbackDataHandler(new PressTheButtonStartCallbackHandler(bot, games, messageUpdater).withName("start"));
    events.registerCallbackDataHandler(new PressTheButtonButtonCallbackHandler(bot, games, messageUpdater).withName("button"));
  }
  
}
