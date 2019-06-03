package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.users.User;

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
  private final long oldChatId;
  
  
  public MigrateFromChatIdMessage(long id,
                                  User sender,
                                  long date,
                                  Chat chat,
                                  long oldChatId) {
    super(id, sender, date, chat, null, null, null, null);
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
