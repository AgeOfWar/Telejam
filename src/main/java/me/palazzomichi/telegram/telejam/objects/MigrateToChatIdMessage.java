package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

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
  private final long newChatId;


  public MigrateToChatIdMessage(long id,
                                User sender,
                                long date,
                                Chat chat,
                                long newChatId) {
    super(id, sender, date, chat, null, null, null);
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
