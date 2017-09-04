package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.Message;

/**
 * Use this method to delete a message, including service messages, with the following limitations:
 * <ul>
 * <li>A message can only be deleted if it was sent less than 48 hours ago.</li>
 * <li>Bots can delete outgoing messages in groups and supergroups.</li>
 * <li>Bots granted can_post_messages permissions can delete outgoing messages in channels.</li>
 * <li>If the bot is an administrator of a group, it can delete any message there.</li>
 * <li>If the bot has can_delete_messages permission in a supergroup or a channel, it can delete any message there.</li>
 * </ul>
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class DeleteMessage extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "deleteMessage";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";

  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

  /**
   * Identifier of the message to delete.
   */
  @SerializedName(MESSAGE_ID_FIELD)
  private Long messageId;


  public DeleteMessage chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public DeleteMessage chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public DeleteMessage chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public DeleteMessage message(Long messageId) {
    this.messageId = messageId;
    return this;
  }

  public DeleteMessage message(Message message) {
    this.chatId = Long.toString(message.getChat().getId());
    this.messageId = message.getId();
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
