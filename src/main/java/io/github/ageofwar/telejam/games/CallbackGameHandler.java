package io.github.ageofwar.telejam.games;

import io.github.ageofwar.telejam.callbacks.CallbackQuery;

/**
 * Interface that handles callback games received from a bot.
 */
@FunctionalInterface
public interface CallbackGameHandler {
  
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
  
}
