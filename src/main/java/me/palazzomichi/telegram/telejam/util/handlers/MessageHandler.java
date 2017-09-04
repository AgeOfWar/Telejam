package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.updates.MessageUpdate;
import me.palazzomichi.telegram.telejam.objects.updates.Update;

/**
 * Represents an operation that accepts a message and returns no
 * result. Used usually to handle updates received from a bot.
 *
 * @author Michi Palazzo
 */
public interface MessageHandler extends UpdateHandler {

  /**
   * Performs this operation on the given message.
   *
   * @param message the message
   */
  void accept(Message message);

  @Override
  default void accept(Update update) {
    if (update instanceof MessageUpdate) {
      Message message = ((MessageUpdate) update).getMessage();
      accept(message);
    }
  }

}
