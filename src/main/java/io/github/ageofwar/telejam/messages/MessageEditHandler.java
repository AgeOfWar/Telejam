package io.github.ageofwar.telejam.messages;

import io.github.ageofwar.telejam.updates.EditedMessageUpdate;
import io.github.ageofwar.telejam.updates.Update;
import io.github.ageofwar.telejam.updates.UpdateHandler;

/**
 * Interface that handles edited messages received from a bot.
 */
@FunctionalInterface
public interface MessageEditHandler extends UpdateHandler {
  
  /**
   * Handles an incoming message edit.
   *
   * @param message  new incoming edit
   * @param editDate the date of the edit
   * @throws Throwable if a throwable is thrown
   */
  void onMessageEdit(Message message, long editDate) throws Throwable;
  
  @Override
  default void onUpdate(Update update) throws Throwable {
    if (update instanceof EditedMessageUpdate) {
      Message message = ((EditedMessageUpdate) update).getMessage();
      onMessageEdit(message, message.getEditDate().getAsLong());
    }
  }
  
}
