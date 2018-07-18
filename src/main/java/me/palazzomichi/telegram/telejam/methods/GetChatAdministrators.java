package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.ChatMember;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to get a list of administrators in a chat.
 * On success, returns an Array of ChatMember objects that
 * contains information about all chat administrators except other bots.
 * If the chat is a group or a supergroup and no administrators
 * were appointed, only the creator will be returned.
 *
 * @author Michi Palazzo
 */
public class GetChatAdministrators implements TelegramMethod<ChatMember[]> {

  public static final String NAME = "getChatAdministrators";

  static final String CHAT_ID_FIELD = "chat_id";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  
  public GetChatAdministrators chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    return this;
  }
  
  public GetChatAdministrators chat(Long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    return this;
  }
  
  public GetChatAdministrators chat(Chat chat) {
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
  public Class<? extends ChatMember[]> getReturnType() {
    return ChatMember[].class;
  }

}
