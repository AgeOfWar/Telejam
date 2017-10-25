package me.palazzomichi.telegram.telejam.objects.messages;

import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * This object represents a chat created message.
 *
 * @author Michi Palazzo
 */
public class ChatCreatedMessage extends Message {

  static final String GROUP_CHAT_CREATED_FIELD = "group_chat_created";
  static final String SUPERGROUP_CHAT_CREATED_FIELD = "supergroup_chat_created";
  static final String CHANNEL_CHAT_CREATED_FIELD = "channel_chat_created";

  public ChatCreatedMessage(long id,
                            User sender,
                            long date,
                            Chat chat,
                            Message replyToMessage,
                            Long editDate,
                            String authorSignature) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
  }

}
