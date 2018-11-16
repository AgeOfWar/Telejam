package io.github.ageofwar.telejam.messages;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.users.User;

/**
 * This object represents a delete chat photo message.
 *
 * @author Michi Palazzo
 */
public class DeleteChatPhotoMessage extends Message {
  
  static final String DELETE_CHAT_PHOTO_FIELD = "delete_chat_photo";
  
  public DeleteChatPhotoMessage(long id,
                                User sender,
                                long date,
                                Chat chat) {
    super(id, sender, date, chat, null, null, null);
  }
  
}
