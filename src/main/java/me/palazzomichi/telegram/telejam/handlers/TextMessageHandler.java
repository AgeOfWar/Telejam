package me.palazzomichi.telegram.telejam.handlers;

import me.palazzomichi.telegram.telejam.objects.TextMessage;

/**
 * Interface that handles text messages received from a bot.
 */
@FunctionalInterface
public interface TextMessageHandler {
  
  /**
   * Handles an incoming text message.
   *
   * @param message new incoming message
   * @throws Throwable if a throwable is thrown
   */
  void onTextMessage(TextMessage message) throws Throwable;
  
}
