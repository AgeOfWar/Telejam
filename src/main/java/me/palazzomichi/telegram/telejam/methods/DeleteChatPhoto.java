package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to delete a chat photo.
 * Photos can't be changed for private chats.
 * The bot must be an administrator in the chat for this
 * to work and must have the appropriate admin rights.
 * Returns True on success.
 *
 * In regular groups (non-supergroups), this method will only work if
 * the ‘All Members Are Admins’ setting is off in the target group.
 *
 * @author Michi Palazzo
 */
public class DeleteChatPhoto implements TelegramMethod<Boolean> {

  public static final String NAME = "deleteChatPhoto";

  static final String CHAT_ID_FIELD = "chat_id";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  
  public DeleteChatPhoto chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public DeleteChatPhoto chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public DeleteChatPhoto chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(CHAT_ID_FIELD, chatId != null ? chatId : chatUsername);
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
