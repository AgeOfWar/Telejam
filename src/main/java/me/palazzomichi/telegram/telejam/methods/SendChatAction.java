package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * Use this method when you need to tell the user that something is happening on the bot's side.
 * The status is set for 5 seconds or less (when a message arrives from your bot,
 * Telegram clients clear its typing status).
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class SendChatAction extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "sendChatAction";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String ACTION_FIELD = "action";

  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>)
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

  /**
   * Type of action to broadcast.
   * Choose one, depending on what the user is about to receive:
   * <code>typing</code> for text messages,
   * <code>upload_photo</code> for photos,
   * <code>record_video</code> or <code>upload_video</code> for videos,
   * <code>record_audio</code> or <code>upload_audio</code> for audio files,
   * <code>upload_document</code> for general files,
   * <code>find_location</code> for location data,
   * <code>record_video_note</code> or <code>upload_video_note</code> for video notes.
   */
  @SerializedName(ACTION_FIELD)
  private String action;


  public SendChatAction chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public SendChatAction chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public SendChatAction chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public SendChatAction action(String action) {
    this.action = action;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Boolean> getReturnType(JsonElement response) {
    return boolean.class;
  }

}
