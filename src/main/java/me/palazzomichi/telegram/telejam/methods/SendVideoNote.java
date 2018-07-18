package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.*;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * As of v.4.0, Telegram clients support rounded square mp4 videos of up to 1 minute long.
 * Use this method to send video messages.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendVideoNote implements TelegramMethod<VideoNoteMessage> {
  
  public static final String NAME = "sendVideoNote";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String VIDEO_FIELD = "video_note";
  static final String SIZE_FIELD = "length";
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
   * Video width and height.
   */
  private Integer size;
  
  /**
   * Duration of sent video in seconds.
   */
  private Integer duration;
  
  /**
   * Video not present in Telegram servers.
   */
  private UploadFile newVideo;
  
  
  public SendVideoNote chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    return this;
  }
  
  public SendVideoNote chat(Long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    return this;
  }
  
  public SendVideoNote chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
    return this;
  }
  
  public SendVideoNote disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }
  
  public SendVideoNote disableNotification() {
    this.disableNotification = true;
    return this;
  }
  
  public SendVideoNote replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    this.chatId = message.getChat().getId();
    this.chatUsername = null;
    return this;
  }
  
  public SendVideoNote replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }
  
  public SendVideoNote replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }
  
  public SendVideoNote video(String video) {
    newVideo = null;
    this.video = video;
    return this;
  }
  
  public SendVideoNote video(UploadFile newVideo) {
    video = null;
    this.newVideo = newVideo;
    return this;
  }
  
  public SendVideoNote duration(Integer duration) {
    this.duration = duration;
    return this;
  }
  
  public SendVideoNote size(Integer size) {
    this.size = size;
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
        SIZE_FIELD, size,
        DURATION_FIELD, duration
    );
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(VIDEO_FIELD, newVideo);
  }
  
  @Override
  public Class<? extends VideoNoteMessage> getReturnType() {
    return VideoNoteMessage.class;
  }
  
}
