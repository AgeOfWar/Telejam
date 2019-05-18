package io.github.ageofwar.telejam.messages;

import io.github.ageofwar.telejam.updates.MessageUpdate;
import io.github.ageofwar.telejam.updates.Update;
import io.github.ageofwar.telejam.updates.UpdateHandler;

/**
 * Interface that handles messages received from a bot.
 */
@FunctionalInterface
public interface MessageHandler extends UpdateHandler {
  
  /**
   * Handles an incoming message.
   *
   * @param message new incoming message
   * @throws Throwable if a throwable is thrown
   */
  void onMessage(Message message) throws Throwable;
  
  @Override
  default void onUpdate(Update update) throws Throwable {
    if (update instanceof MessageUpdate) {
      onMessage(((MessageUpdate) update).getMessage());
    }
  }

}
