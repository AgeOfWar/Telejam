package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.updates.EditedChannelPostUpdate;
import me.palazzomichi.telegram.telejam.objects.updates.Update;

/**
 * Represents an operation that accepts a message and returns no
 * result. Used usually to handle updates received from a bot.
 *
 * @author Michi Palazzo
 */
public interface EditedChannelPostHandler extends UpdateHandler {

  /**
   * Performs this operation on the given edited channel post.
   *
   * @param message the message
   */
  void accept(Message message);

  @Override
  default void accept(Update update) throws Throwable {
    if (update instanceof EditedChannelPostUpdate) {
      Message message = ((EditedChannelPostUpdate) update).getEditedChannelPost();
      accept(message);
    }
  }

}
