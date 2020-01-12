package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.users.User;

import java.lang.reflect.Type;
import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to set a custom title for an administrator in
 * a supergroup promoted by the bot.
 * Returns True on success.
 */
public class SetChatAdministratorCustomTitle implements TelegramMethod<Boolean> {
  
  public static final String NAME = "setChatAdministratorCustomTitle";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String USER_ID_FIELD = "user_id";
  static final String CUSTOM_TITLE_FIELD = "custom_title";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  /**
   * Unique identifier of the target user.
   */
  private Long userId;
  
  /**
   * New custom title for the administrator; 0-16 characters, emoji are not allowed.
   */
  private String customTitle;
  
  
  public SetChatAdministratorCustomTitle chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SetChatAdministratorCustomTitle chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SetChatAdministratorCustomTitle chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }
  
  public SetChatAdministratorCustomTitle user(Long userId) {
    this.userId = userId;
    return this;
  }
  
  public SetChatAdministratorCustomTitle user(User user) {
    userId = user.getId();
    return this;
  }
  
  public SetChatAdministratorCustomTitle customTitle(String customTitle) {
    this.customTitle = customTitle;
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
        USER_ID_FIELD, userId,
        CUSTOM_TITLE_FIELD, customTitle
    );
  }
  
  @Override
  public Type getReturnType() {
    return Boolean.class;
  }
  
}
