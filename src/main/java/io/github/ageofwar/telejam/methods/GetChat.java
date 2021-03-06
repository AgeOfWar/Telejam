package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to get up to date information about the chat
 * (current name of the user for one-on-one conversations,
 * current username of a user, group or channel, etc.).
 * Returns a Chat object on success.
 *
 * @author Michi Palazzo
 */
public class GetChat implements TelegramMethod<Chat> {

  public static final String NAME = "getChat";

  static final String CHAT_ID_FIELD = "chat_id";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  
  public GetChat chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public GetChat chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public GetChat chat(Chat chat) {
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
  public Class<? extends Chat> getReturnType() {
    return Chat.class;
  }

}
