package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to send video files, Telegram clients support mp4
 * videos (other formats may be sent as Document).
 * On success, the sent Message is returned.
 * Bots can currently send video files of up to 50 MB in size,
 * this limit may be changed in the future.
 *
 * @author Michi Palazzo
 */
public class SendVideo implements TelegramMethod<VideoMessage> {
  
  public static final String NAME = "sendVideo";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String VIDEO_FIELD = "video";
  static final String WIDTH_FIELD = "width";
  static final String HEIGHT_FIELD = "height";
  static final String CAPTION_FIELD = "caption";
  static final String PARSE_MODE_FIELD = "parse_mode";
  static final String DURATION_FIELD = "duration";
  
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
   * Video file to send.
   */
  private String video;
  
  /**
   * Video width.
   */
  private Integer width;
  
  /**
   * Video height.
   */
  private Integer height;
  
  /**
   * Video caption, 0-200 characters.
   */
  private Text caption;
  
  /**
   * Duration of sent video in seconds.
   */
  private Integer duration;
  
  /**
   * Video not present in Telegram servers.
   */
  private UploadFile newVideo;
  
  
  public SendVideo chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    return this;
  }
  
  public SendVideo chat(Long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    return this;
  }
  
  public SendVideo chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
    return this;
  }
  
  public SendVideo disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }
  
  public SendVideo disableNotification() {
    this.disableNotification = true;
    return this;
  }
  
  public SendVideo replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    this.chatId = message.getChat().getId();
    this.chatUsername = null;
    return this;
  }
  
  public SendVideo replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }
  
  public SendVideo replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }
  
  public SendVideo video(String video) {
    newVideo = null;
    this.video = video;
    return this;
  }
  
  public SendVideo video(UploadFile newVideo) {
    video = null;
    this.newVideo = newVideo;
    return this;
  }
  
  public SendVideo caption(String caption) {
    this.caption = new Text(caption);
    return this;
  }
  
  public SendVideo caption(Text caption) {
    this.caption = caption;
    return this;
  }
  
  public SendVideo duration(Integer duration) {
    this.duration = duration;
    return this;
  }
  
  public SendVideo width(Integer width) {
    this.width = width;
    return this;
  }
  
  public SendVideo height(Integer height) {
    this.height = height;
    return this;
  }
  
  public SendVideo size(Integer width, Integer height) {
    this.width = width;
    this.height = height;
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
        VIDEO_FIELD, video,
        WIDTH_FIELD, width,
        HEIGHT_FIELD, height,
        CAPTION_FIELD, caption.toHtmlString(),
        PARSE_MODE_FIELD, "HTML",
        DURATION_FIELD, duration
    );
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(VIDEO_FIELD, newVideo);
  }
  
  @Override
  public Class<? extends VideoMessage> getReturnType() {
    return VideoMessage.class;
  }
  
}
