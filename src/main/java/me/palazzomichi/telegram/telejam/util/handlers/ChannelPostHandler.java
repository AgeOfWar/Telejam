package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.updates.ChannelPostUpdate;
import me.palazzomichi.telegram.telejam.objects.updates.Update;

/**
 * Represents an operation that accepts a message and returns no
 * result. Used usually to handle updates received from a bot.
 *
 * @author Michi Palazzo
 */
public interface ChannelPostHandler extends UpdateHandler {
  
  /**
   * Performs this operation on the given channel post.
   *
   * @param message the message
   * @throws Throwable if a throwable is thrown
   */
  void accept(Message message) throws Throwable;
  
  @Override
  default void accept(Update update) throws Throwable {
    if (update instanceof ChannelPostUpdate) {
      Message message = ((ChannelPostUpdate) update).getChannelPost();
      accept(message);
    }
  }
  
}
