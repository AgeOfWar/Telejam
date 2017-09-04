package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * Use this method to export an invite link to a supergroup or a channel.
 * The bot must be an administrator in the chat for this to work and must
 * have the appropriate admin rights.
 * Returns exported invite link as String on success.
 *
 * @author Michi Palazzo
 */
public class ExportChatInviteLink extends JsonTelegramMethod<String> {

  public static final String NAME = "exportChatInviteLink";

  static final String CHAT_ID_FIELD = "chat_id";

  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long


  public ExportChatInviteLink chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public ExportChatInviteLink chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public ExportChatInviteLink chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends String> getReturnType(JsonElement response) {
    return String.class;
  }

}
