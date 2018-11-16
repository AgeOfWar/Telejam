package io.github.ageofwar.telejam.callbacks;

/**
 * Interface that handles callback data received from a bot.
 */
@FunctionalInterface
public interface CallbackDataHandler {
  
  /**
   * Handles an incoming callback game.
   *
   * @param callbackQuery new incoming callback data
   * @param name          name of the callback
   * @param args          args of the callback
   * @throws Throwable if a throwable is thrown
   */
  void onCallbackData(CallbackQuery callbackQuery, String name, String args)
      throws Throwable;
  
  /**
   * Returns a new CallbackDataHandler that filters callbacks with the specified name.
   *
   * @param name the name of the callbacks to handle
   * @return a new CallbackDataHandler that filters command with the specified name
   */
  default CallbackDataHandler withName(String name) {
    return (callbackQuery, callbackName, callbackArgs) -> {
      if (callbackName.equals(name)) {
        onCallbackData(callbackQuery, callbackName, callbackArgs);
      }
    };
  }
  
}
