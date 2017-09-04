package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * Use this method to change the description of a supergroup or a channel.
 * The bot must be an administrator in the chat for this to work and must
 * have the appropriate admin rights.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class SetChatDescription extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "setChatDescription";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DESCRIPTION_FIELD = "description";


  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

  /**
   * New chat description, 0-255 characters.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private String description;


  public SetChatDescription chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public SetChatDescription chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public SetChatDescription chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public SetChatDescription description(String description) {
    this.description = description;
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
