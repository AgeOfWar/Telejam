package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * Use this method to unpin a message in a supergroup chat.
 * The bot must be an administrator in the chat for this to
 * work and must have the appropriate admin rights.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class UnpinChatMessage extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "unpinChatMessage";

  static final String CHAT_ID_FIELD = "chat_id";

  /**
   * Unique identifier for the target chat or username of the
   * target supergroup (in the format @supergroupusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long


  public UnpinChatMessage chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public UnpinChatMessage chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public UnpinChatMessage chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
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
