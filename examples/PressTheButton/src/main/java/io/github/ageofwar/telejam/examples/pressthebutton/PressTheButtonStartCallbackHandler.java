package io.github.ageofwar.telejam.examples.pressthebutton;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.callbacks.CallbackDataHandler;
import io.github.ageofwar.telejam.callbacks.CallbackQuery;

import java.io.IOException;
import java.util.Map;

public class PressTheButtonStartCallbackHandler implements CallbackDataHandler {
  
  private final Bot bot;
  private final Map<String, PressTheButton> games;
  private final PressTheButtonMessageUpdater messageUpdater;
  
  public PressTheButtonStartCallbackHandler(Bot bot,
                                            Map<String, PressTheButton> games,
                                            PressTheButtonMessageUpdater messageUpdater) {
    this.bot = bot;
    this.games = games;
    this.messageUpdater = messageUpdater;
  }
  
  @Override
  public void onCallbackData(CallbackQuery callbackQuery, String name, String arg) throws IOException {
    String inlineMessageId = callbackQuery.getInlineMessageId().orElseThrow(AssertionError::new);
    String[] args = arg.split(" ");
    int width = Integer.parseInt(args[0]);
    int height = Integer.parseInt(args[1]);
    PressTheButtonGameSettings settings = new PressTheButtonGameSettings(width, height);
    games.put(inlineMessageId, PressTheButton.fromSettings(settings));
    messageUpdater.update(inlineMessageId);
  }
  
}
