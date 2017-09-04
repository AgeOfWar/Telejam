package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Date;

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
public class KickChatMember extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "kickChatMember";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String USER_ID_FIELD = "user_id";
  static final String UNTIL_DATE_FIELD = "until_date";

  /**
   * Unique identifier for the target group or username of the
   * target supergroup or channel (in the format @channelusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId;

  /**
   * Unique identifier of the target user.
   */
  @SerializedName(USER_ID_FIELD)
  private Long userId;

  /**
   * Date when the user will be unbanned, unix time.
   * If user is banned for more than 366 days or less than 30 seconds
   * from the current time they are considered to be banned forever.
   */
  @SerializedName(UNTIL_DATE_FIELD)
  private Long untilDate;


  public KickChatMember chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public KickChatMember chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public KickChatMember chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public KickChatMember user(Long userId) {
    this.userId = userId;
    return this;
  }

  public KickChatMember user(User user) {
    this.userId = user.getId();
    return this;
  }

  public KickChatMember untilDate(Long untilDate) {
    this.untilDate = untilDate;
    return this;
  }

  public KickChatMember untilDate(Date untilDate) {
    this.untilDate = untilDate.getTime();
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Boolean> getReturnType(JsonElement response) {
    return Boolean.class;
  }

}
