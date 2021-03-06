package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.messages.Message;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to pin a message in a supergroup.
 * The bot must be an administrator in the chat for
 * this to work and must have the appropriate admin rights.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class PinChatMessage implements TelegramMethod<Boolean> {

  public static final String NAME = "pinChatMessage";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;

  /**
   * Identifier of a message to pin.
   */
  private Long messageId;

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  private Boolean disableNotification;
  
  
  public PinChatMessage chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public PinChatMessage chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public PinChatMessage chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public PinChatMessage disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public PinChatMessage disableNotification() {
    disableNotification = true;
    return this;
  }

  public PinChatMessage message(Long messageId) {
    this.messageId = messageId;
    return this;
  }

  public PinChatMessage message(Message message) {
    chatId = message.getChat().getId();
    messageId = message.getId();
    chatUsername = null;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(
        CHAT_ID_FIELD, chatId != null ? chatId : chatUsername,
        MESSAGE_ID_FIELD, messageId,
        DISABLE_NOTIFICATION_FIELD, disableNotification
    );
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
