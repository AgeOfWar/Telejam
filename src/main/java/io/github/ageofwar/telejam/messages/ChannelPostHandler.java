package io.github.ageofwar.telejam.messages;

/**
 * Interface that handles channel posts received from a bot.
 */
@FunctionalInterface
public interface ChannelPostHandler {
  
  /**
   * Handles an incoming channel post.
   *
   * @param message new incoming message
   * @throws Throwable if a throwable is thrown
   */
  void onChannelPost(Message message) throws Throwable;
  
}
