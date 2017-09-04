package me.palazzomichi.telegram.telejam.objects.messages;

import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

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
                                Chat chat,
                                User forwardMessageSender,
                                Chat forwardMessageChat,
                                Long forwardMessageId,
                                Long forwardMessageDate,
                                Message replyToMessage,
                                Long editDate,
                                String authorSignature,
                                String forwardSignature) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
  }

}
