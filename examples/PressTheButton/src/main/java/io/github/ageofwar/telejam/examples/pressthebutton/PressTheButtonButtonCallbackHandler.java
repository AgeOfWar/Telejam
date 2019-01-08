package io.github.ageofwar.telejam.examples.pressthebutton;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.callbacks.CallbackDataHandler;
import io.github.ageofwar.telejam.callbacks.CallbackQuery;
import io.github.ageofwar.telejam.examples.pressthebutton.game.Point;
import io.github.ageofwar.telejam.examples.pressthebutton.game.PressTheButton;
import io.github.ageofwar.telejam.methods.AnswerCallbackQuery;
import io.github.ageofwar.telejam.users.User;

import java.io.IOException;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class PressTheButtonButtonCallbackHandler implements CallbackDataHandler {
  
  private final Bot bot;
  private final PressTheButtonMessageUpdater messageUpdater;
  
  private final Map<String, PressTheButton> games;
  
  public PressTheButtonButtonCallbackHandler(Bot bot,
                                             Map<String, PressTheButton> games,
                                             PressTheButtonMessageUpdater messageUpdater) {
    this.bot = bot;
    this.games = games;
    this.messageUpdater = messageUpdater;
  }
  
  @Override
  public void onCallbackData(CallbackQuery callbackQuery, String name, String arg) throws IOException {
    String inlineMessageId = callbackQuery.getInlineMessageId().orElseThrow(AssertionError::new);
    User sender = callbackQuery.getSender();
    PressTheButton game = games.get(inlineMessageId);
    if (!(game == null || game.isTerminated())) {
      String[] args = arg.split(" ");
      Point point = new Point(parseInt(args[0]), parseInt(args[1]));
      if (!game.isPressed(point)) {
        game.press(sender, point);
        messageUpdater.update(inlineMessageId);
      }
    }
    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery()
        .callbackQuery(callbackQuery);
    bot.execute(answerCallbackQuery);
  }
  
}
