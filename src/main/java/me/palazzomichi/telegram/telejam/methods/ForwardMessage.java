package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.Forward;
import me.palazzomichi.telegram.telejam.objects.Message;

import java.lang.reflect.Type;
import java.util.Map;

import static me.palazzomichi.telegram.telejam.json.Json.genericTypeOf;
import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to forward messages of any kind.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class ForwardMessage implements TelegramMethod<Forward<?>> {

  public static final String NAME = "forwardMessage";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String FROM_CHAT_ID_FIELD = "from_chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String MESSAGE_ID_FIELD = "message_id";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  private Boolean disableNotification;

  /**
   * Unique identifier for the chat where the original message
   * was sent.
   */
  private Long fromChatId; // String or long
  
  /**
   * Channel username (in the format @channelusername)
   * where the original message was sent.
   */
  private String fromChatUsername;

  /**
   * Message identifier in the chat specified in {@link #fromChatId}.
   */
  private Long messageId;
  
  
  public ForwardMessage chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    return this;
  }
  
  public ForwardMessage chat(Long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    return this;
  }
  
  public ForwardMessage chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
    return this;
  }

  public ForwardMessage fromChat(String fromChatUsername) {
    this.fromChatUsername = fromChatUsername;
    this.fromChatId = null;
    return this;
  }

  public ForwardMessage fromChat(long fromChatId) {
    this.fromChatId = fromChatId;
    this.fromChatUsername = null;
    return this;
  }

  public ForwardMessage fromChat(Chat fromChat) {
    this.fromChatId = fromChat.getId();
    this.fromChatUsername = null;
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
    this.fromChatId = message.getChat().getId();
    this.messageId = message.getId();
    this.fromChatUsername = null;
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
        FROM_CHAT_ID_FIELD, fromChatId != null ? fromChatId : fromChatUsername,
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        MESSAGE_ID_FIELD, messageId
    );
  }
  
  @Override
  public Type getReturnType() {
    return genericTypeOf(Forward.class, Message.class);
  }
  
}
