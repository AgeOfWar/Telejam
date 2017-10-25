package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Location;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

import java.io.Serializable;

/**
 * Use this method to edit live location messages sent by the bot or via the bot (for inline bots).
 * A location can be edited until its live period expires or editing is explicitly disabled by a
 * call to {@link StopMessageLiveLocation}.
 * On success, if the edited message was sent by the bot, the edited Message is returned,
 * otherwise True is returned.
 *
 * @author Michi Palazzo
 */
public class EditMessageLiveLocation extends JsonTelegramMethod<Serializable> {
  
  public static final String NAME = "editMessageLiveLocation";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";
  static final String LATITUDE_FIELD = "latitude";
  static final String LONGITUDE_FIELD = "longitude";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  
  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Unique identifier for the target chat or username
   * of the target channel (in the format @channelusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long
  
  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Identifier of the sent message.
   */
  @SerializedName(MESSAGE_ID_FIELD)
  private Long messageId;
  
  /**
   * Required if {@link #chatId} and {@link #messageId} are not specified.
   * Identifier of the inline message
   */
  @SerializedName(INLINE_MESSAGE_ID_FIELD)
  private String inlineMessageId;
  
  /**
   * Latitude of new location.
   */
  @SerializedName(LATITUDE_FIELD)
  private Float latitude;
  
  /**
   * Longitude of new location
   */
  @SerializedName(LONGITUDE_FIELD)
  private Float longitude;
  
  /**
   * A new inline keyboard.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;
  
  
  public EditMessageLiveLocation chat(String chatId) {
    this.chatId = chatId;
    this.inlineMessageId = null;
    return this;
  }
  
  public EditMessageLiveLocation chat(long chatId) {
    this.chatId = Long.toString(chatId);
    this.inlineMessageId = null;
    return this;
  }
  
  public EditMessageLiveLocation chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    this.inlineMessageId = null;
    return this;
  }
  
  public EditMessageLiveLocation message(Long messageId) {
    this.messageId = messageId;
    this.inlineMessageId = null;
    return this;
  }
  
  public EditMessageLiveLocation message(Message message) {
    this.chatId = Long.toString(message.getChat().getId());
    this.messageId = message.getId();
    this.inlineMessageId = null;
    return this;
  }
  
  public EditMessageLiveLocation inlineMessage(String inlineMessageId) {
    this.inlineMessageId = inlineMessageId;
    this.chatId = null;
    this.messageId = null;
    return this;
  }
  
  public EditMessageLiveLocation latitude(Float latitude) {
    this.latitude = latitude;
    return this;
  }
  
  public EditMessageLiveLocation longitude(Float longitude) {
    this.longitude = longitude;
    return this;
  }
  
  public EditMessageLiveLocation location(Location location) {
    if (location == null) {
      this.latitude = null;
      this.longitude = null;
    } else {
      this.latitude = location.getLatitude();
      this.longitude = location.getLongitude();
    }
    return this;
  }
  
  public EditMessageLiveLocation replyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }
  
  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Class<? extends Serializable> getReturnType(JsonElement response) {
    if (response == null) {
      return Serializable.class;
    }
    return response.isJsonPrimitive() ? Boolean.class : Message.class;
  }
  
}
