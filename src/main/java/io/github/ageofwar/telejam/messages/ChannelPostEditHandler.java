package io.github.ageofwar.telejam.messages;

import io.github.ageofwar.telejam.updates.EditedChannelPostUpdate;
import io.github.ageofwar.telejam.updates.Update;
import io.github.ageofwar.telejam.updates.UpdateHandler;

/**
 * Interface that handles edited channel posts received from a bot.
 */
@FunctionalInterface
public interface ChannelPostEditHandler extends UpdateHandler {
  
  /**
   * Handles an incoming channel post edit.
   *
   * @param message  new incoming edit
   * @param editDate the date of the edit
   * @throws Throwable if a throwable is thrown
   */
  void onChannelPostEdit(Message message, long editDate) throws Throwable;
  
  @Override
  default void onUpdate(Update update) throws Throwable {
    if (update instanceof EditedChannelPostUpdate) {
      Message message = ((EditedChannelPostUpdate) update).getEditedChannelPost();
      onChannelPostEdit(message, message.getEditDate().getAsLong());
    }
  }
  
}
