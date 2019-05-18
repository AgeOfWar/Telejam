package io.github.ageofwar.telejam.inline;

import io.github.ageofwar.telejam.updates.ChosenInlineResultUpdate;
import io.github.ageofwar.telejam.updates.InlineQueryUpdate;
import io.github.ageofwar.telejam.updates.Update;
import io.github.ageofwar.telejam.updates.UpdateHandler;

/**
 * Interface that handles inline queries received from a bot.
 */
@FunctionalInterface
public interface InlineQueryHandler extends UpdateHandler {
  
  /**
   * Handles an incoming inline query from the bot.
   *
   * @param inlineQuery new incoming message
   * @throws Throwable if a throwable is thrown
   */
  void onInlineQuery(InlineQuery inlineQuery) throws Throwable;
  
  /**
   * Handles an incoming chosen inline result from the bot.
   *
   * @param chosenInlineResult new incoming chosen inline result
   * @throws Throwable if a throwable is thrown
   */
  default void onChosenInlineResult(ChosenInlineResult chosenInlineResult) throws Throwable {
  }
  
  @Override
  default void onUpdate(Update update) throws Throwable {
    if (update instanceof InlineQueryUpdate) {
      onInlineQuery(((InlineQueryUpdate) update).getInlineQuery());
    } else if (update instanceof ChosenInlineResultUpdate) {
      onChosenInlineResult(((ChosenInlineResultUpdate) update).getChosenInlineResult());
    }
  }
  
}
