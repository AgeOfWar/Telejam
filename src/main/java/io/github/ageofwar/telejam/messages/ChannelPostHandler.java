package io.github.ageofwar.telejam.messages;

import io.github.ageofwar.telejam.updates.ChannelPostUpdate;
import io.github.ageofwar.telejam.updates.Update;
import io.github.ageofwar.telejam.updates.UpdateHandler;

/**
 * Interface that handles channel posts received from a bot.
 */
@FunctionalInterface
public interface ChannelPostHandler extends UpdateHandler {
  
  /**
   * Handles an incoming channel post.
   *
   * @param message new incoming message
   * @throws Throwable if a throwable is thrown
   */
  void onChannelPost(Message message) throws Throwable;
  
  @Override
  default void onUpdate(Update update) throws Throwable {
    if (update instanceof ChannelPostUpdate) {
      onChannelPost(((ChannelPostUpdate) update).getChannelPost());
    }
  }
  
}
