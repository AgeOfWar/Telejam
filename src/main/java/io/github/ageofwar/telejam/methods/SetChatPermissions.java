package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.chats.ChatPermissions;

import java.lang.reflect.Type;
import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

public class SetChatPermissions implements TelegramMethod<Boolean> {
  public static final String NAME = "setChatPermissions";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String PERMISSIONS_FIELD = "permissions";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  /**
   * New default chat permissions.
   */
  private ChatPermissions permissions;
  
  
  public SetChatPermissions chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SetChatPermissions chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SetChatPermissions chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }
  
  public SetChatPermissions permissions(ChatPermissions permissions) {
    this.permissions = permissions;
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
        PERMISSIONS_FIELD, permissions
    );
  }
  
  @Override
  public Type getReturnType() {
    return Boolean.class;
  }
}
