package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound).
 * On success, the sent Message is returned. Bots can currently send animation files of
 * up to 50 MB in size, this limit may be changed in the future.
 */
public class SendAnimation implements TelegramMethod<AnimationMessage> {
  
  public static final String NAME = "sendAnimation";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String ANIMATION_FIELD = "animation";
  static final String DURATION_FIELD = "duration";
  static final String WIDTH_FIELD = "width";
  static final String HEIGHT_FIELD = "height";
  static final String THUMBNAIL_FIELD = "thumbnail";
  static final String CAPTION_FIELD = "caption";
  static final String PARSE_MODE_FIELD = "parse_mode";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  /**
   * Animation to send.
   */
  private String animation;
  
  /**
   * Duration of sent animation in seconds.
   */
  private Integer duration;
  
  /**
   * Animation width.
   */
  private Integer width;
  
  /**
   * Animation height.
   */
  private Integer height;
  
  /**
   * Thumbnail of the file sent.
   */
  private String thumbnail;
  
  /**
   * Animation caption, 0-200 characters.
   */
  private Text caption;
  
  /**
   * Sends the message silently.
   * Users will receive a notification with no sound.
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
   * Animation not present in Telegram servers.
   */
  private UploadFile newAnimation;
  
  /**
   * Thumbnail not present in Telegram servers.
   */
  private UploadFile newThumbNail;
  
  
  public SendAnimation chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    return this;
  }
  
  public SendAnimation chat(Long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    return this;
  }
  
  public SendAnimation chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
    return this;
  }
  
  public SendAnimation disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }
  
  public SendAnimation disableNotification() {
    this.disableNotification = true;
    return this;
  }
  
  public SendAnimation replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    this.chatId = message.getChat().getId();
    this.chatUsername = null;
    return this;
  }
  
  public SendAnimation replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }
  
  public SendAnimation replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }
  
  public SendAnimation animation(String animation) {
    newAnimation = null;
    this.animation = animation;
    return this;
  }
  
  public SendAnimation animation(UploadFile newAnimation) {
    this.newAnimation = newAnimation;
    animation = null;
    return this;
  }
  
  public SendAnimation caption(String caption) {
    this.caption = new Text(caption);
    return this;
  }
  
  public SendAnimation caption(Text caption) {
    this.caption = caption;
    return this;
  }
  
  public SendAnimation duration(Integer duration) {
    this.duration = duration;
    return this;
  }
  
  public SendAnimation width(Integer width) {
    this.width = width;
    return this;
  }
  
  public SendAnimation height(Integer height) {
    this.height = height;
    return this;
  }
  
  public SendAnimation size(Integer width, Integer height) {
    this.width = width;
    this.height = height;
    return this;
  }
  
  public SendAnimation thumbnail(String thumbnail) {
    newThumbNail = null;
    this.thumbnail = thumbnail;
    return this;
  }
  
  public SendAnimation thumbnail(UploadFile newThumbNail) {
    this.newThumbNail = newThumbNail;
    thumbnail = null;
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
        ANIMATION_FIELD, animation,
        DURATION_FIELD, duration,
        WIDTH_FIELD, width,
        HEIGHT_FIELD, height,
        THUMBNAIL_FIELD, thumbnail,
        CAPTION_FIELD, caption,
        PARSE_MODE_FIELD, "HTML",
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId,
        REPLY_MARKUP_FIELD, replyMarkup
    );
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(
        ANIMATION_FIELD, newAnimation,
        THUMBNAIL_FIELD, newThumbNail
    );
  }
  
  @Override
  public Class<? extends AnimationMessage> getReturnType() {
    return AnimationMessage.class;
  }
  
}
