package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;

/**
 * This object represents a pinned message message.
 *
 * @author Michi Palazzo
 */
public class MessagePinnedMessage extends Message {
  
  static final String PINNED_MESSAGE_FIELD = "pinned_message";
  
  /**
   * Specified message was pinned.
   * <p>Note that the Message object in this field will not
   * contain further reply_to_message fields even if it is itself a reply.</p>
   */
  @SerializedName(PINNED_MESSAGE_FIELD)
  private final Message pinnedMessage;
  
  
  public MessagePinnedMessage(long id,
                              User sender,
                              long date,
                              Chat chat,
                              Message pinnedMessage) {
    super(id, sender, date, chat, null, null, null);
    this.pinnedMessage = Objects.requireNonNull(pinnedMessage);
  }
  
  
  /**
   * Getter for property {@link #pinnedMessage}.
   *
   * @return value for property {@link #pinnedMessage}
   */
  public Message getPinnedMessage() {
    return pinnedMessage;
  }
  
}
