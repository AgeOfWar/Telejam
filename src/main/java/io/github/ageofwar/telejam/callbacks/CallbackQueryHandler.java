package io.github.ageofwar.telejam.callbacks;

import io.github.ageofwar.telejam.updates.CallbackQueryUpdate;
import io.github.ageofwar.telejam.updates.Update;
import io.github.ageofwar.telejam.updates.UpdateHandler;

/**
 * Interface that handles callback queries received from a bot.
 */
@FunctionalInterface
public interface CallbackQueryHandler extends UpdateHandler {
  
  /**
   * Handles an incoming callback query.
   *
   * @param callbackQuery new incoming callback query
   * @throws Throwable if a throwable is thrown
   */
  void onCallbackQuery(CallbackQuery callbackQuery) throws Throwable;
  
  @Override
  default void onUpdate(Update update) throws Throwable {
    if (update instanceof CallbackQueryUpdate) {
      onCallbackQuery(((CallbackQueryUpdate) update).getCallbackQuery());
    }
  }
  
}
