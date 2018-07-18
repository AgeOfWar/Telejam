package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to unpin a message in a supergroup chat.
 * The bot must be an administrator in the chat for this to
 * work and must have the appropriate admin rights.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class UnpinChatMessage implements TelegramMethod<Boolean> {

  public static final String NAME = "unpinChatMessage";

  static final String CHAT_ID_FIELD = "chat_id";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  
  public UnpinChatMessage chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    return this;
  }
  
  public UnpinChatMessage chat(Long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    return this;
  }
  
  public UnpinChatMessage chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
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
  public Class<? extends Boolean> getReturnType() {
    return Boolean.class;
  }

}
