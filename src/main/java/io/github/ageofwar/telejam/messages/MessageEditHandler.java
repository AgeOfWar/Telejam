package io.github.ageofwar.telejam.messages;

/**
 * Interface that handles edited messages received from a bot.
 */
@FunctionalInterface
public interface MessageEditHandler {
  
  /**
   * Handles an incoming message edit.
   *
   * @param message  new incoming edit
   * @param editDate the date of the edit
   * @throws Throwable if a throwable is thrown
   */
  void onMessageEdit(Message message, long editDate) throws Throwable;
  
}
