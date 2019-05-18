package io.github.ageofwar.telejam.games;

import io.github.ageofwar.telejam.callbacks.CallbackQuery;
import io.github.ageofwar.telejam.callbacks.CallbackQueryHandler;

/**
 * Interface that handles callback games received from a bot.
 */
@FunctionalInterface
public interface CallbackGameHandler extends CallbackQueryHandler {
  
  /**
   * Handles an incoming callback game.
   *
   * @param callbackQuery new incoming callback game
   * @param gameShortName short name of a Game to be returned,
   *                      serves as the unique identifier for the game
   * @throws Throwable if a throwable is thrown
   */
  void onCallbackGame(CallbackQuery callbackQuery, String gameShortName)
      throws Throwable;
  
  @Override
  default void onCallbackQuery(CallbackQuery callbackQuery) throws Throwable {
    if (callbackQuery.getGameShortName().isPresent()) {
      String gameShortName = callbackQuery.getGameShortName().get();
      onCallbackGame(callbackQuery, gameShortName);
    }
  }
  
}
