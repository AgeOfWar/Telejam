package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

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
  private Message pinnedMessage;


  public MessagePinnedMessage(long id,
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
                              Message pinnedMessage) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
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
