package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.messages.Location;
import io.github.ageofwar.telejam.replymarkups.ReplyMarkup;
import io.github.ageofwar.telejam.messages.Venue;
import io.github.ageofwar.telejam.messages.*;
import io.github.ageofwar.telejam.chats.Chat;


import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to send information about a venue.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendVenue implements TelegramMethod<VenueMessage> {

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
  static final String FOURSQUARE_TYPE_FIELD = "foursquare_type";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  private Boolean disableNotification;

  /**
   * If the message is a reply, ID of the original message.
   */
  private Long replyToMessageId;

  /**
   * Additional interface options.
   */
  private ReplyMarkup replyMarkup;

  /**
   * Latitude of the venue.
   */
  private Float latitude;

  /**
   * Longitude of the venue.
   */
  private Float longitude;

  /**
   * Name of the venue.
   */
  private String title;

  /**
   * Address of the venue.
   */
  private String address;

  /**
   * Foursquare identifier of the venue.
   */
  private String foursquareId;
  
  /**
   * Foursquare type of the venue.
   */
  private String foursquareType;
  
  
  public SendVenue chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SendVenue chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SendVenue chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public SendVenue disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendVenue disableNotification() {
    disableNotification = true;
    return this;
  }

  public SendVenue replyToMessage(Message message) {
    replyToMessageId = message.getId();
    chatId = message.getChat().getId();
    chatUsername = null;
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
    latitude = location.getLatitude();
    longitude = location.getLongitude();
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
  
  public SendVenue foursquareType(String foursquareType) {
    this.foursquareType = foursquareType;
    return this;
  }
  
  public SendVenue venue(Venue venue) {
    latitude = venue.getLocation().getLatitude();
    longitude = venue.getLocation().getLongitude();
    title = venue.getTitle();
    address = venue.getAddress();
    foursquareId = venue.getFoursquareId().orElse(null);
    foursquareType = venue.getFoursquareType().orElse(null);
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(
        CHAT_ID_FIELD, chatId != null ? chatId : chatUsername,
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId,
        REPLY_MARKUP_FIELD, replyMarkup,
        LATITUDE_FIELD, latitude,
        LONGITUDE_FIELD, longitude,
        TITLE_FIELD, title,
        ADDRESS_FIELD, address,
        FOURSQUARE_ID_FIELD, foursquareId,
        FOURSQUARE_TYPE_FIELD, foursquareType
    );
  }
  
  @Override
  public Class<? extends VenueMessage> getReturnType() {
    return VenueMessage.class;
  }

}
