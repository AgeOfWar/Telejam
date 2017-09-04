package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.ChatMember;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * Use this method to get information about a member of a chat.
 * Returns a ChatMember object on success.
 *
 * @author Michi Palazzo
 */
public class GetChatMember extends JsonTelegramMethod<ChatMember> {

  public static final String NAME = "getChatMember";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String USER_ID_FIELD = "user_id";

  /**
   * Unique identifier for the target chat or username of the
   * target supergroup or channel (in the format @channelusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

  /**
   * Unique identifier of the target user.
   */
  @SerializedName(USER_ID_FIELD)
  private Long userId;


  public GetChatMember chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public GetChatMember chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public GetChatMember chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
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
  public Class<? extends ChatMember> getReturnType(JsonElement response) {
    return ChatMember.class;
  }

}
