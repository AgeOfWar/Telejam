package io.github.ageofwar.telejam.messages;

/**
 * Interface that handles poll updates received from a bot.
 */
@FunctionalInterface
public interface PollHandler {
  
  /**
   * Handles an incoming poll update.
   *
   * @param poll the new poll state
   * @throws Throwable if a throwable is thrown
   */
  void onPollUpdate(Poll poll) throws Throwable;
  
}
