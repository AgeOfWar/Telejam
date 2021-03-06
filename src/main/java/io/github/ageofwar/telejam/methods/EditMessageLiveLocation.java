package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.callbacks.CallbackQuery;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.messages.Location;
import io.github.ageofwar.telejam.messages.LocationMessage;
import io.github.ageofwar.telejam.messages.Message;

import java.io.Serializable;
import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to edit live location messages sent by the bot or via the bot (for inline bots).
 * A location can be edited until its live period expires or editing is explicitly disabled by a
 * call to {@link StopMessageLiveLocation}.
 * On success, if the edited message was sent by the bot, the edited Message is returned,
 * otherwise True is returned.
 *
 * @author Michi Palazzo
 */
public class EditMessageLiveLocation implements TelegramMethod<Serializable> {
  
  public static final String NAME = "editMessageLiveLocation";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";
  static final String LATITUDE_FIELD = "latitude";
  static final String LONGITUDE_FIELD = "longitude";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  
  /**
   * Required if {@link #inlineMessageId} and {@link #chatUsername} are not specified.
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Required if {@link #inlineMessageId} and {@link #chatId} are not specified.
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Identifier of the sent message.
   */
  private Long messageId;
  
  /**
   * Required if {@link #chatId} and {@link #messageId} are not specified.
   * Identifier of the inline message
   */
  private String inlineMessageId;
  
  /**
   * Latitude of new location.
   */
  private Float latitude;
  
  /**
   * Longitude of new location
   */
  private Float longitude;
  
  /**
   * A new inline keyboard.
   */
  private InlineKeyboardMarkup replyMarkup;
  
  
  public EditMessageLiveLocation chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageLiveLocation chat(long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageLiveLocation chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageLiveLocation message(Long messageId) {
    this.messageId = messageId;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageLiveLocation message(Message message) {
    chatId = message.getChat().getId();
    messageId = message.getId();
    chatUsername = null;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageLiveLocation inlineMessage(String inlineMessageId) {
    this.inlineMessageId = inlineMessageId;
    chatId = null;
    chatUsername = null;
    messageId = null;
    return this;
  }
  
  public EditMessageLiveLocation callbackQuery(CallbackQuery callbackQuery) {
    inlineMessageId = callbackQuery.getInlineMessageId().orElse(null);
    chatId = callbackQuery.getMessage().map(Message::getChat).map(Chat::getId).orElse(null);
    messageId = callbackQuery.getMessage().map(Message::getId).orElse(null);
    chatUsername = null;
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
    latitude = location.getLatitude();
    longitude = location.getLongitude();
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
  public Map<String, Object> getParameters() {
    return mapOf(
        CHAT_ID_FIELD, chatId != null ? chatId : chatUsername,
        MESSAGE_ID_FIELD, messageId,
        INLINE_MESSAGE_ID_FIELD, inlineMessageId,
        LATITUDE_FIELD, latitude,
        LONGITUDE_FIELD, longitude,
        REPLY_MARKUP_FIELD, replyMarkup
    );
  }
  
  @Override
  public Class<? extends Serializable> getReturnType() {
    return inlineMessageId == null ? LocationMessage.class : Boolean.class;
  }
  
}
