package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.updates.EditedMessageUpdate;
import me.palazzomichi.telegram.telejam.objects.updates.Update;

/**
 * Represents an operation that accepts a message and returns no
 * result. Used usually to handle updates received from a bot.
 *
 * @author Michi Palazzo
 */
public interface EditedMessageHandler extends UpdateHandler {

  /**
   * Performs this operation on the given edited message.
   *
   * @param message the message
   */
  void accept(Message message) throws Throwable;

  @Override
  default void accept(Update update) throws Throwable {
    if (update instanceof EditedMessageUpdate) {
      Message message = ((EditedMessageUpdate) update).getMessage();
      accept(message);
    }
  }

}
