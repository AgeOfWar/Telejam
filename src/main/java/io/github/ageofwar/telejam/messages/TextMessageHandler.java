package io.github.ageofwar.telejam.messages;

/**
 * Interface that handles text messages received from a bot.
 */
@FunctionalInterface
public interface TextMessageHandler extends MessageHandler {
  
  /**
   * Handles an incoming text message.
   *
   * @param message new incoming message
   * @throws Throwable if a throwable is thrown
   */
  void onTextMessage(TextMessage message) throws Throwable;
  
  @Override
  default void onMessage(Message message) throws Throwable {
    if (message instanceof TextMessage) {
      onTextMessage((TextMessage) message);
    }
  }
  
}
