package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Location;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.messages.VenueMessage;
import me.palazzomichi.telegram.telejam.objects.replymarkups.ReplyMarkup;

/**
 * Use this method to send information about a venue.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendVenue extends JsonTelegramMethod<VenueMessage> {

  public static final String NAME = "sendVenue";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String LATITUDE_FIELD = "latitude";
  static final String LONGITUDE_FIELD = "longitude";
  static final String TITLE_FIELD = "title";
  static final String ADDRESS_FIELD = "address";
  static final String FOURSQUARE_ID_FIELD = "foursquare_id";

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
   * Latitude of the venue.
   */
  @SerializedName(LATITUDE_FIELD)
  private Float latitude;

  /**
   * Longitude of the venue.
   */
  @SerializedName(LONGITUDE_FIELD)
  private Float longitude;

  /**
   * Name of the venue.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Address of the venue.
   */
  @SerializedName(ADDRESS_FIELD)
  private String address;

  /**
   * Foursquare identifier of the venue.
   */
  @SerializedName(FOURSQUARE_ID_FIELD)
  private String foursquareId;


  public SendVenue chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public SendVenue chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public SendVenue chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public SendVenue disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendVenue disableNotification() {
    this.disableNotification = true;
    return this;
  }

  public SendVenue replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    this.chatId = Long.toString(message.getChat().getId());
    return this;
  }

  public SendVenue replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }

  public SendVenue replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  public SendVenue latitude(Float latitude) {
    this.latitude = latitude;
    return this;
  }

  public SendVenue longitude(Float longitude) {
    this.longitude = longitude;
    return this;
  }

  public SendVenue location(Location location) {
    this.latitude = location.getLatitude();
    this.longitude = location.getLongitude();
    return this;
  }

  public SendVenue title(String title) {
    this.title = title;
    return this;
  }

  public SendVenue address(String address) {
    this.address = address;
    return this;
  }

  public SendVenue foursquareId(String foursquareId) {
    this.foursquareId = foursquareId;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends VenueMessage> getReturnType(JsonElement response) {
    return VenueMessage.class;
  }

}
