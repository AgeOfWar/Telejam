package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * Use this method to unban a previously kicked user in a supergroup or channel.
 * The user will not return to the group or channel automatically,
 * but will be able to join via link, etc.
 * The bot must be an administrator for this to work.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class UnbanChatMember extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "unbanChatMember";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String USER_ID_FIELD = "user_id";

  /**
   * Unique identifier for the target group or username of the
   * target supergroup or channel (in the format @channelusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

  /**
   * Unique identifier of the target user.
   */
  @SerializedName(USER_ID_FIELD)
  private Long userId;


  public UnbanChatMember chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public UnbanChatMember chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public UnbanChatMember chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public UnbanChatMember user(Long userId) {
    this.userId = userId;
    return this;
  }

  public UnbanChatMember user(User user) {
    this.userId = user.getId();
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
