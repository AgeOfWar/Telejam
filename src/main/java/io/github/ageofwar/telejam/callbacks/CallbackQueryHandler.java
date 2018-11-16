package io.github.ageofwar.telejam.callbacks;

/**
 * Interface that handles callback queries received from a bot.
 */
@FunctionalInterface
public interface CallbackQueryHandler {
  
  /**
   * Handles an incoming callback query.
   *
   * @param callbackQuery new incoming callback query
   * @throws Throwable if a throwable is thrown
   */
  void onCallbackQuery(CallbackQuery callbackQuery) throws Throwable;
  
}
