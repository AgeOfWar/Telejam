package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.ChatMember;
import me.palazzomichi.telegram.telejam.objects.User;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to get information about a member of a chat.
 * Returns a ChatMember object on success.
 *
 * @author Michi Palazzo
 */
public class GetChatMember implements TelegramMethod<ChatMember> {

  public static final String NAME = "getChatMember";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String USER_ID_FIELD = "user_id";
  
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
  
  
  public GetChatMember chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    return this;
  }
  
  public GetChatMember chat(Long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    return this;
  }
  
  public GetChatMember chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
    return this;
  }

  public GetChatMember user(Long userId) {
    this.userId = userId;
    return this;
  }

  public GetChatMember user(User user) {
    this.userId = user.getId();
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
        USER_ID_FIELD, userId
    );
  }
  
  @Override
  public Class<? extends ChatMember> getReturnType() {
    return ChatMember.class;
  }

}
