package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * Use this method to delete a chat photo.
 * Photos can't be changed for private chats.
 * The bot must be an administrator in the chat for this
 * to work and must have the appropriate admin rights.
 * Returns True on success.
 *
 * In regular groups (non-supergroups), this method will only work if
 * the ‘All Members Are Admins’ setting is off in the target group.
 *
 * @author Michi Palazzo
 */
public class DeleteChatPhoto extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "deleteChatPhoto";

  static final String CHAT_ID_FIELD = "chat_id";

  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long


  public DeleteChatPhoto chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public DeleteChatPhoto chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public DeleteChatPhoto chat(Chat chat) {
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
