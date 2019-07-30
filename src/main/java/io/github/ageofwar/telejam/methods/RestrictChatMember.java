package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.chats.ChatPermissions;
import io.github.ageofwar.telejam.users.User;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to restrict a user in a supergroup.
 * The bot must be an administrator in the supergroup for
 * this to work and must have the appropriate admin rights.
 * Pass True for all boolean parameters to lift restrictions from a user.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class RestrictChatMember implements TelegramMethod<Boolean> {

  public static final String NAME = "restrictChatMember";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String USER_ID_FIELD = "user_id";
  static final String UNTIL_DATE_FIELD = "until_date";
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
   * Unique identifier of the target user.
   */
  private Long userId;

  /**
   * Date when restrictions will be lifted for the user, unix time.
   * If user is restricted for more than 366 days or less than 30 seconds
   * from the current time, they are considered to be restricted forever.
   */
  private Long untilDate;

  /**
   * New user permissions.
   */
  private ChatPermissions permissions;
  
  
  public RestrictChatMember chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public RestrictChatMember chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public RestrictChatMember chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public RestrictChatMember user(Long userId) {
    this.userId = userId;
    return this;
  }

  public RestrictChatMember user(User user) {
    userId = user.getId();
    return this;
  }

  public RestrictChatMember untilDate(Long untilDate) {
    this.untilDate = untilDate;
    return this;
  }
  
  public RestrictChatMember permissions(ChatPermissions permissions) {
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
        USER_ID_FIELD, userId,
        UNTIL_DATE_FIELD, untilDate,
        PERMISSIONS_FIELD, permissions
    );
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
