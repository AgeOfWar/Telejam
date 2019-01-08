package io.github.ageofwar.telejam.examples.pressthebutton;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.examples.pressthebutton.game.Point;
import io.github.ageofwar.telejam.examples.pressthebutton.game.PressTheButton;
import io.github.ageofwar.telejam.inline.CallbackDataInlineKeyboardButton;
import io.github.ageofwar.telejam.inline.InlineKeyboardButton;
import io.github.ageofwar.telejam.methods.EditMessageText;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.text.Text;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

public class PressTheButtonMessageUpdater {
  
  private final Bot bot;
  private final Map<String, PressTheButton> games;
  private final PressTheButtonStyle style;
  
  public PressTheButtonMessageUpdater(Bot bot,
                                      Map<String, PressTheButton> games,
                                      PressTheButtonStyle style) {
    this.bot = bot;
    this.games = games;
    this.style = style;
  }
  
  private static Text formatText(Text text, Object... args) {
    String htmlWinMessage = text.toHtmlString();
    return Text.parseHtml(MessageFormat.format(htmlWinMessage, args));
  }
  
  public void update(String inlineMessageId) throws IOException {
    PressTheButton game = games.get(inlineMessageId);
    if (game.isTerminated()) {
      EditMessageText editMessageText = new EditMessageText()
          .inlineMessage(inlineMessageId)
          .text(formatText(style.getWinMessage(), game.getWinner().getName()))
          .replyMarkup(newKeyboard(game));
      bot.execute(editMessageText);
    } else {
      EditMessageText editMessageText = new EditMessageText()
          .inlineMessage(inlineMessageId)
          .text(style.getGameMessage())
          .replyMarkup(newKeyboard(game));
      bot.execute(editMessageText);
    }
  }
  
  private InlineKeyboardMarkup newKeyboard(PressTheButton game) {
    int width = game.getWidth();
    int height = game.getHeight();
    boolean[][] buttonsClicked = game.getButtonsClickState();
    InlineKeyboardButton[][] buttons = new InlineKeyboardButton[width][height];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (buttonsClicked[x][y]) {
          if (game.getRightButtonPosition().equals(new Point(x, y))) {
            buttons[x][y] = new CallbackDataInlineKeyboardButton(style.getWinButton(), "button " + x + " " + y);
          } else {
            buttons[x][y] = new CallbackDataInlineKeyboardButton(style.getButtonClicked(), "button " + x + " " + y);
          }
        } else {
          buttons[x][y] = new CallbackDataInlineKeyboardButton(style.getButtonNotClicked(), "button " + x + " " + y);
        }
      }
    }
    return new InlineKeyboardMarkup(buttons);
  }
  
}
