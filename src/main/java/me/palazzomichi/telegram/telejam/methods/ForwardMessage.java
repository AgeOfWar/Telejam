package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.Message;

/**
 * Use this method to forward messages of any kind.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class ForwardMessage extends JsonTelegramMethod<Message> {

  public static final String NAME = "forwardMessage";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String FROM_CHAT_ID_FIELD = "from_chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String MESSAGE_ID_FIELD = "message_id";

  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  @SerializedName(DISABLE_NOTIFICATION_FIELD)
  private Boolean disableNotification;

  /**
   * Unique identifier for the chat where the original message
   * was sent (or channel username in the format @channelusername).
   */
  @SerializedName(FROM_CHAT_ID_FIELD)
  private String fromChatId; // String or long

  /**
   * Message identifier in the chat specified in {@link #fromChatId}.
   */
  @SerializedName(MESSAGE_ID_FIELD)
  private Long messageId;


  public ForwardMessage chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public ForwardMessage chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public ForwardMessage chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public ForwardMessage fromChat(String fromChatId) {
    this.fromChatId = fromChatId;
    return this;
  }

  public ForwardMessage fromChat(long fromChatId) {
    this.fromChatId = Long.toString(fromChatId);
    return this;
  }

  public ForwardMessage fromChat(Chat fromChat) {
    this.fromChatId = Long.toString(fromChat.getId());
    return this;
  }

  public ForwardMessage disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public ForwardMessage disableNotification() {
    this.disableNotification = true;
    return this;
  }

  public ForwardMessage message(Long messageId) {
    this.messageId = messageId;
    return this;
  }

  public ForwardMessage message(Message message) {
    this.chatId = Long.toString(message.getChat().getId());
    this.messageId = message.getId();
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Message> getReturnType(JsonElement response) {
    return Message.class;
  }

}
