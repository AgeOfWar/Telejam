package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * Use this method to get the number of members in a chat.
 * Returns Integer on success.
 *
 * @author Michi Palazzo
 */
public class GetChatMembersCount extends JsonTelegramMethod<Integer> {

  public static final String NAME = "getChatMembersCount";

  static final String CHAT_ID_FIELD = "chat_id";

  /**
   * Unique identifier for the target chat or username of the
   * target supergroup or channel (in the format @channelusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long


  public GetChatMembersCount chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public GetChatMembersCount chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public GetChatMembersCount chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Integer> getReturnType(JsonElement response) {
    return Integer.class;
  }

}
