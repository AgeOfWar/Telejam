package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.inline.ChosenInlineResult;
import me.palazzomichi.telegram.telejam.objects.inline.InlineQuery;
import me.palazzomichi.telegram.telejam.objects.updates.ChosenInlineResultUpdate;
import me.palazzomichi.telegram.telejam.objects.updates.InlineQueryUpdate;
import me.palazzomichi.telegram.telejam.objects.updates.Update;

/**
 * Represents an operation that accepts an inline query or chosen inline results and
 * returns no result. Used usually to handle updates received from a bot.
 *
 * @author Michi Palazzo
 */
public interface InlineQueryHandler extends UpdateHandler {

  /**
   * Performs this operation on the given inline query.
   *
   * @param inlineQuery the inline query
   */
  void accept(InlineQuery inlineQuery) throws Throwable;

  /**
   * Performs this operation on the given chosen inline result.
   *
   * @param chosenInlineResult the chosen inline result
   */
  default void accept(ChosenInlineResult chosenInlineResult) throws Throwable {
  }

  @Override
  default void accept(Update update) throws Throwable {
    if (update instanceof InlineQueryUpdate) {
      InlineQuery inlineQuery = ((InlineQueryUpdate) update).getInlineQuery();
      accept(inlineQuery);
    } else if (update instanceof ChosenInlineResultUpdate) {
      ChosenInlineResult chosenInlineResult = ((ChosenInlineResultUpdate) update).getChosenInlineResult();
      accept(chosenInlineResult);
    }
  }

}
