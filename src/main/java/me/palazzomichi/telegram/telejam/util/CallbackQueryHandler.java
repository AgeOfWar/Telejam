package me.palazzomichi.telegram.telejam.util;

import me.palazzomichi.telegram.telejam.objects.CallbackQuery;

/**
 * Interface that handles callback queries received from a bot.
 */
public interface CallbackQueryHandler {
  
  /**
   * Handles an incoming callback query.
   *
   * @param callbackQuery new incoming callback query
   * @throws Throwable if a throwable is thrown
   */
  void onCallbackQuery(CallbackQuery callbackQuery) throws Throwable;
  
}
