package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.Message;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to delete a message, including service messages, with the following limitations:
 * <ul>
 * <li>A message can only be deleted if it was sent less than 48 hours ago.</li>
 * <li>Bots can delete outgoing messages in groups and supergroups.</li>
 * <li>Bots granted can_post_messages permissions can delete outgoing messages in channels.</li>
 * <li>If the bot is an administrator of a group, it can delete any message there.</li>
 * <li>If the bot has can_delete_messages permission in a supergroup or a channel, it can delete any message there.</li>
 * </ul>
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class DeleteMessage implements TelegramMethod<Boolean> {

  public static final String NAME = "deleteMessage";

  static final String CHAT_ID_FIELD = "chat_id";
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
   * Identifier of the message to delete.
   */
  private Long messageId;
  
  
  public DeleteMessage chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public DeleteMessage chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public DeleteMessage chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public DeleteMessage message(Long messageId) {
    this.messageId = messageId;
    return this;
  }

  public DeleteMessage message(Message message) {
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
        MESSAGE_ID_FIELD, messageId
    );
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
