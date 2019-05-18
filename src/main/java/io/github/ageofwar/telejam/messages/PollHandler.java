package io.github.ageofwar.telejam.messages;

import io.github.ageofwar.telejam.updates.PollUpdate;
import io.github.ageofwar.telejam.updates.Update;
import io.github.ageofwar.telejam.updates.UpdateHandler;

/**
 * Interface that handles poll updates received from a bot.
 */
@FunctionalInterface
public interface PollHandler extends UpdateHandler {
  
  /**
   * Handles an incoming poll update.
   *
   * @param poll the new poll state
   * @throws Throwable if a throwable is thrown
   */
  void onPollUpdate(Poll poll) throws Throwable;
  
  @Override
  default void onUpdate(Update update) throws Throwable {
    if (update instanceof PollUpdate) {
      onPollUpdate(((PollUpdate) update).getPoll());
    }
  }
  
}
