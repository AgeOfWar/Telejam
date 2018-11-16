package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to change the description of a supergroup or a channel.
 * The bot must be an administrator in the chat for this to work and must
 * have the appropriate admin rights.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class SetChatDescription implements TelegramMethod<Boolean> {

  public static final String NAME = "setChatDescription";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DESCRIPTION_FIELD = "description";
  
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;

  /**
   * New chat description, 0-255 characters.
   */
  private String description;
  
  
  public SetChatDescription chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SetChatDescription chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SetChatDescription chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public SetChatDescription description(String description) {
    this.description = description;
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
        DESCRIPTION_FIELD, description
    );
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
