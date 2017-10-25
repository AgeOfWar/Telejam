package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * This object represents a migrate to chat id message.
 *
 * @author Michi Palazzo
 */
public class MigrateToChatIdMessage extends Message {

  static final String MIGRATE_TO_CHAT_ID_FIELD = "migrate_to_chat_id";

  /**
   * The new chat id.
   */
  @SerializedName(MIGRATE_TO_CHAT_ID_FIELD)
  private long newChatId;


  public MigrateToChatIdMessage(long id,
                                User sender,
                                long date,
                                Chat chat,
                                Message replyToMessage,
                                Long editDate,
                                String authorSignature,
                                long newChatId) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.newChatId = newChatId;
  }


  /**
   * Getter for property {@link #newChatId}.
   *
   * @return value for property {@link #newChatId}
   */
  public long getNewChatId() {
    return newChatId;
  }

}
