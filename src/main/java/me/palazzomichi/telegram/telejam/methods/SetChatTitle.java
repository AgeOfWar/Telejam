package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to set a new profile photo for the chat.
 * Photos can't be changed for private chats.
 * The bot must be an administrator in the chat for this
 * to work and must have the appropriate admin rights.
 * Returns True on success.
 *
 * Note: In regular groups (non-supergroups), this method will only work
 * if the ‘All Members Are Admins’ setting is off in the target group.
 *
 * @author Michi Palazzo
 */
public class SetChatTitle implements TelegramMethod<Boolean> {

  public static final String NAME = "setChatTitle";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String TITLE_FIELD = "title";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;

  /**
   * New chat title, 1-255 characters.
   */
  private String title;
  
  
  public SetChatTitle chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SetChatTitle chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SetChatTitle chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public SetChatTitle title(String title) {
    this.title = title;
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
        TITLE_FIELD, title
    );
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
