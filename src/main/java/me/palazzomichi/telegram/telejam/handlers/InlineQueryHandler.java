package me.palazzomichi.telegram.telejam.handlers;

import me.palazzomichi.telegram.telejam.objects.ChosenInlineResult;
import me.palazzomichi.telegram.telejam.objects.InlineQuery;

/**
 * Interface that handles inline queries received from a bot.
 */
@FunctionalInterface
public interface InlineQueryHandler {
  
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
  
}
