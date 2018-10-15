package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.User;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to kick a user from a group, a supergroup or a channel.
 * In the case of supergroups and channels, the user will not be able to
 * return to the group on their own using invite links, etc., unless unbanned first.
 * The bot must be an administrator in the chat for this to work and must
 * have the appropriate admin rights.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class KickChatMember implements TelegramMethod<Boolean> {

  public static final String NAME = "kickChatMember";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String USER_ID_FIELD = "user_id";
  static final String UNTIL_DATE_FIELD = "until_date";
  
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
   * Date when the user will be unbanned, unix time.
   * If user is banned for more than 366 days or less than 30 seconds
   * from the current time they are considered to be banned forever.
   */
  private Long untilDate;
  
  
  public KickChatMember chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public KickChatMember chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public KickChatMember chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public KickChatMember user(Long userId) {
    this.userId = userId;
    return this;
  }

  public KickChatMember user(User user) {
    userId = user.getId();
    return this;
  }

  public KickChatMember untilDate(Long untilDate) {
    this.untilDate = untilDate;
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
        UNTIL_DATE_FIELD, untilDate
    );
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
