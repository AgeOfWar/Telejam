package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.ChatMember;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * Use this method to get a list of administrators in a chat.
 * On success, returns an Array of ChatMember objects that
 * contains information about all chat administrators except other bots.
 * If the chat is a group or a supergroup and no administrators
 * were appointed, only the creator will be returned.
 *
 * @author Michi Palazzo
 */
public class GetChatAdministrators extends JsonTelegramMethod<ChatMember[]> {

  public static final String NAME = "getChatAdministrators";

  static final String CHAT_ID_FIELD = "chat_id";

  /**
   * Unique identifier for the target chat or username of the
   * target supergroup or channel (in the format @channelusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long


  public GetChatAdministrators chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public GetChatAdministrators chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public GetChatAdministrators chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends ChatMember[]> getReturnType(JsonElement response) {
    return ChatMember[].class;
  }

}
