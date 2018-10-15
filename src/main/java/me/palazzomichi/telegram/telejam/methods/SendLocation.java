package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.*;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to send point on the map.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendLocation implements TelegramMethod<LocationMessage> {

  public static final String NAME = "sendLocation";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String LATITUDE_FIELD = "latitude";
  static final String LONGITUDE_FIELD = "longitude";
  static final String LIVE_PERIOD_FIELD = "live_period";
  
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
   * Latitude of location.
   */
  private Float latitude;

  /**
   * Longitude of location.
   */
  private Float longitude;
  
  /**
   * Period in seconds for which the location will be updated,
   * should be between 60 and 86400.
   */
  private Integer livePeriod;
  
  
  public SendLocation chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SendLocation chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SendLocation chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public SendLocation disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendLocation disableNotification() {
    disableNotification = true;
    return this;
  }

  public SendLocation replyToMessage(Message message) {
    replyToMessageId = message.getId();
    chatId = message.getChat().getId();
    chatUsername = null;
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
    latitude = location.getLatitude();
    longitude = location.getLongitude();
    return this;
  }
  
  public SendLocation livePeriod(Integer livePeriod) {
    this.livePeriod = livePeriod;
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
        LIVE_PERIOD_FIELD, livePeriod
    );
  }
  
  @Override
  public Class<? extends LocationMessage> getReturnType() {
    return LocationMessage.class;
  }

}
