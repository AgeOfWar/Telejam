package io.github.ageofwar.telejam.messages;

/**
 * Interface that handles messages received from a bot.
 */
@FunctionalInterface
public interface MessageHandler {
  
  /**
   * Handles an incoming message.
   *
   * @param message new incoming message
   * @throws Throwable if a throwable is thrown
   */
  void onMessage(Message message) throws Throwable;
  
}
