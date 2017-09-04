package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * Use this method to get up to date information about the chat
 * (current name of the user for one-on-one conversations,
 * current username of a user, group or channel, etc.).
 * Returns a Chat object on success.
 *
 * @author Michi Palazzo
 */
public class GetChat extends JsonTelegramMethod<Chat> {

  public static final String NAME = "getChat";

  static final String CHAT_ID_FIELD = "chat_id";

  /**
   * Unique identifier for the target chat or username of the
   * target supergroup or channel (in the format @channelusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long


  public GetChat chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public GetChat chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public GetChat chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Chat> getReturnType(JsonElement response) {
    return Chat.class;
  }

}
