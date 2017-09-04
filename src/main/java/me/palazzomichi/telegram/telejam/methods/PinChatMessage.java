package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.Message;

/**
 * Use this method to pin a message in a supergroup.
 * The bot must be an administrator in the chat for
 * this to work and must have the appropriate admin rights.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class PinChatMessage extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "pinChatMessage";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";

  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

  /**
   * Identifier of a message to pin.
   */
  @SerializedName(MESSAGE_ID_FIELD)
  private Long messageId;

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  @SerializedName(DISABLE_NOTIFICATION_FIELD)
  private Boolean disableNotification;


  public PinChatMessage chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public PinChatMessage chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public PinChatMessage chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public PinChatMessage disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public PinChatMessage disableNotification() {
    this.disableNotification = true;
    return this;
  }

  public PinChatMessage message(Long messageId) {
    this.messageId = messageId;
    return this;
  }

  public PinChatMessage message(Message message) {
    this.chatId = Long.toString(message.getChat().getId());
    this.messageId = message.getId();
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Boolean> getReturnType(JsonElement response) {
    return Boolean.class;
  }

}
