package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;

/**
 * This object represents a message.
 *
 * @author Michi Palazzo
 */
public abstract class Message implements TelegramObject {

  static final String ID_FIELD = "message_id";
  static final String SENDER_FIELD = "from";
  static final String DATE_FIELD = "date";
  static final String CHAT_FIELD = "chat";
  static final String FORWARD_MESSAGE_SENDER_FIELD = "forward_from";
  static final String FORWARD_MESSAGE_CHAT_FIELD = "forward_from_chat";
  static final String FORWARD_MESSAGE_ID_FIELD = "forward_from_message_id";
  static final String FORWARD_MESSAGE_DATE_FIELD = "forward_date";
  static final String REPLY_TO_MESSAGE_FIELD = "reply_to_message";
  static final String EDIT_DATE_FIELD = "edit_date";
  static final String AUTHOR_SIGNATURE_FIELD = "author_signature";
  static final String FORWARD_SIGNATURE_FIELD = "forward_signature";

  /**
   * Unique message identifier inside this chat.
   */
  @SerializedName(ID_FIELD)
  private long id;

  /**
   * Sender, can be empty for messages sent to channels.
   */
  @SerializedName(SENDER_FIELD)
  private User sender;

  /**
   * Date the message was sent in Unix time.
   */
  @SerializedName(DATE_FIELD)
  private long date;

  /**
   * Conversation the message belongs to.
   */
  @SerializedName(CHAT_FIELD)
  private Chat chat;

  /**
   * For forwarded messages, sender of the original message.
   */
  @SerializedName(FORWARD_MESSAGE_SENDER_FIELD)
  private User forwardMessageSender;

  /**
   * For messages forwarded from a channel, information about the original channel.
   */
  @SerializedName(FORWARD_MESSAGE_CHAT_FIELD)
  private Chat forwardMessageChat;

  /**
   * For forwarded channel posts, identifier of the original message in the channel.
   */
  @SerializedName(FORWARD_MESSAGE_ID_FIELD)
  private Long forwardMessageId;

  /**
   * For forwarded messages, date the original message was sent in Unix time.
   */
  @SerializedName(FORWARD_MESSAGE_DATE_FIELD)
  private Long forwardMessageDate;

  /**
   * For replies, the original message.
   * <p>Note that the Message object in this field
   * will not contain further replyToMessage
   * even if it itself is a reply.</p>
   */
  @SerializedName(REPLY_TO_MESSAGE_FIELD)
  private Message replyToMessage;

  /**
   * Date the message was last edited in Unix time.
   */
  @SerializedName(EDIT_DATE_FIELD)
  private Long editDate;

  /**
   * Optional.
   * Signature of the post author for messages in channels.
   */
  @SerializedName(AUTHOR_SIGNATURE_FIELD)
  private String authorSignature;

  /**
   * For messages forwarded from channels, signature of the post author if present.
   */
  @SerializedName(FORWARD_SIGNATURE_FIELD)
  private String forwardSignature;


  public Message(long id,
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
    this.id = id;
    this.sender = sender;
    this.date = date;
    this.chat = Objects.requireNonNull(chat);
    this.forwardMessageSender = forwardMessageSender;
    this.forwardMessageChat = forwardMessageChat;
    this.forwardMessageId = forwardMessageId;
    this.forwardMessageDate = forwardMessageDate;
    this.replyToMessage = replyToMessage;
    this.editDate = editDate;
    this.authorSignature = authorSignature;
    this.forwardSignature = forwardSignature;
  }


  /**
   * Returns whether or not this message is a forward.
   *
   * @return whether or not this message is a forward
   */
  public boolean isForward() {
    return forwardMessageId != null;
  }

  /**
   * Getter for property {@link #id}.
   *
   * @return value for property {@link #id}
   */
  public long getId() {
    return id;
  }

  /**
   * Getter for property {@link #sender}.
   *
   * @return value for property {@link #sender}
   */
  public User getSender() {
    return sender;
  }

  /**
   * Getter for property {@link #date}.
   *
   * @return value for property {@link #date}
   */
  public long getDate() {
    return date;
  }

  /**
   * Getter for property {@link #chat}.
   *
   * @return value for property {@link #chat}
   */
  public Chat getChat() {
    return chat;
  }

  /**
   * Getter for property {@link #forwardMessageSender}.
   *
   * @return value for property {@link #forwardMessageSender}
   */
  public User getForwardMessageSender() {
    return forwardMessageSender;
  }

  /**
   * Getter for property {@link #forwardMessageChat}.
   *
   * @return value for property {@link #forwardMessageChat}
   */
  public Chat getForwardMessageChat() {
    return forwardMessageChat;
  }

  /**
   * Getter for property {@link #forwardMessageId}.
   *
   * @return value for property {@link #forwardMessageId}
   */
  public Long getForwardMessageId() {
    return forwardMessageId;
  }

  /**
   * Getter for property {@link #forwardMessageDate}.
   *
   * @return value for property {@link #forwardMessageDate}
   */
  public Long getForwardMessageDate() {
    return forwardMessageDate;
  }

  /**
   * Getter for property {@link #replyToMessage}.
   *
   * @return optional value for property {@link #replyToMessage}
   */
  public Optional<Message> getReplyToMessage() {
    return Optional.ofNullable(replyToMessage);
  }

  /**
   * Getter for property {@link #editDate}.
   *
   * @return optional value for property {@link #editDate}
   */
  public OptionalLong getEditDate() {
    return editDate == null ? OptionalLong.empty() : OptionalLong.of(editDate);
  }

  /**
   * Getter for property {@link #authorSignature}.
   *
   * @return optional value for property {@link #authorSignature}
   */
  public Optional<String> getAuthorSignature() {
    return Optional.ofNullable(authorSignature);
  }

  /**
   * Getter for property {@link #forwardSignature}.
   *
   * @return optional value for property {@link #forwardSignature}
   */
  public Optional<String> getForwardSignature() {
    return Optional.ofNullable(forwardSignature);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Message) {
      Message m = (Message) obj;
      return m.getId() == id && m.getChat().getId() == chat.getId();
    }
    return false;
  }

}
