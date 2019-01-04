package io.github.ageofwar.telejam.examples.pressthebutton;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.inline.*;
import io.github.ageofwar.telejam.methods.AnswerInlineQuery;

import java.io.IOException;

public class PressTheButtonInlineQueryHandler implements InlineQueryHandler {
  
  private final Bot bot;
  private final PressTheButtonStyle style;
  
  public PressTheButtonInlineQueryHandler(Bot bot, PressTheButtonStyle style) {
    this.bot = bot;
    this.style = style;
  }
  
  @Override
  public void onInlineQuery(InlineQuery inlineQuery) throws IOException {
    AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery()
        .inlineQuery(inlineQuery)
        .cacheTime(0)
        .results(
            newInlineQueryResult()
        );
    bot.execute(answerInlineQuery);
  }
  
  private InlineQueryResult newInlineQueryResult() {
    return new InlineQueryResultArticle(
        "new_lobby",
        style.getInlineQueryTitle(),
        style.getLobbyMessage(),
        style.getLobbyKeyboard(),
        style.getInlineQueryDescription()
    );
  }
  
}
