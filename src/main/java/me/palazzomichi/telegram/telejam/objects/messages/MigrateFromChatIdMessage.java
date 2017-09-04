package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * This object represents a migrate from chat id message.
 *
 * @author Michi Palazzo
 */
public class MigrateFromChatIdMessage extends Message {

  static final String MIGRATE_FROM_CHAT_ID_FIELD = "migrate_from_chat_id";

  /**
   * The old chat id.
   */
  @SerializedName(MIGRATE_FROM_CHAT_ID_FIELD)
  private long oldChatId;


  public MigrateFromChatIdMessage(long id,
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
                                  String forwardSignature,
                                  long oldChatId) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.oldChatId = oldChatId;
  }


  /**
   * Getter for property {@link #oldChatId}.
   *
   * @return value for property {@link #oldChatId}
   */
  public long getOldChatId() {
    return oldChatId;
  }

}
