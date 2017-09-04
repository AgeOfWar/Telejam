package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Location;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.LocationMessage;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.replymarkups.ReplyMarkup;

/**
 * Use this method to send point on the map.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendLocation extends JsonTelegramMethod<LocationMessage> {

  public static final String NAME = "sendLocation";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String LATITUDE_FIELD = "latitude";
  static final String LONGITUDE_FIELD = "longitude";

  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>)
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  @SerializedName(DISABLE_NOTIFICATION_FIELD)
  private Boolean disableNotification;

  /**
   * If the message is a reply, ID of the original message.
   */
  @SerializedName(REPLY_TO_MESSAGE_ID_FIELD)
  private Long replyToMessageId;

  /**
   * Additional interface options.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private ReplyMarkup replyMarkup;

  /**
   * Latitude of location.
   */
  @SerializedName(LATITUDE_FIELD)
  private Float latitude;

  /**
   * Longitude of location.
   */
  @SerializedName(LONGITUDE_FIELD)
  private Float longitude;


  public SendLocation chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public SendLocation chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public SendLocation chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public SendLocation disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendLocation disableNotification() {
    this.disableNotification = true;
    return this;
  }

  public SendLocation replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    return this;
  }

  public SendLocation replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }

  public SendLocation replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  public SendLocation latitude(Float latitude) {
    this.latitude = latitude;
    return this;
  }

  public SendLocation longitude(Float longitude) {
    this.longitude = longitude;
    return this;
  }

  public SendLocation location(Location location) {
    this.latitude = location.getLatitude();
    this.longitude = location.getLongitude();
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends LocationMessage> getReturnType(JsonElement response) {
    return LocationMessage.class;
  }

}
