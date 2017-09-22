package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.messages.VideoMessage;
import me.palazzomichi.telegram.telejam.objects.replymarkups.ReplyMarkup;
import me.palazzomichi.telegram.telejam.util.text.Text;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
  static final String DURATION_FIELD = "duration";

  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>)
   */
  private String chatId; // String or long

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
  private String caption;

  /**
   * Duration of sent video in seconds.
   */
  private Integer duration;

  /**
   * True if the video is not present in Telegram servers.
   */
  private boolean newVideo;

  public SendVideo chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public SendVideo chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public SendVideo chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
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
    this.chatId = Long.toString(message.getChat().getId());
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
    newVideo = false;
    this.video = video;
    return this;
  }

  public SendVideo video(File video) {
    newVideo = true;
    this.video = video.getPath();
    return this;
  }

  public SendVideo video(Path video) {
    newVideo = true;
    this.video = video.toString();
    return this;
  }

  public SendVideo caption(String caption) {
    this.caption = caption;
    return this;
  }

  public SendVideo caption(Text caption) {
    this.caption = caption.toString();
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
  public Class<? extends VideoMessage> getReturnType(JsonElement response) {
    return VideoMessage.class;
  }

  @Override
  public HttpEntity getHttpEntity() {
    if (newVideo) {
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();

      if (chatId != null)
        builder.addTextBody(CHAT_ID_FIELD, chatId);

      if (disableNotification != null)
        builder.addTextBody(DISABLE_NOTIFICATION_FIELD, disableNotification.toString());

      if (replyToMessageId != null)
        builder.addTextBody(REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId.toString());

      if (replyMarkup != null)
        builder.addTextBody(REPLY_MARKUP_FIELD, replyMarkup.toString());

      if (video != null)
        builder.addBinaryBody(VIDEO_FIELD, new File(video));

      if (width != null)
        builder.addTextBody(WIDTH_FIELD, width.toString());

      if (height != null)
        builder.addTextBody(HEIGHT_FIELD, height.toString());

      if (caption != null)
        builder.addTextBody(CAPTION_FIELD, caption);

      if (duration != null)
        builder.addTextBody(DURATION_FIELD, duration.toString());

      return builder.build();
    } else {
      List<NameValuePair> params = new ArrayList<>();

      if (chatId != null)
        params.add(
            new BasicNameValuePair(CHAT_ID_FIELD, chatId)
        );

      if(disableNotification != null)
        params.add(
            new BasicNameValuePair(DISABLE_NOTIFICATION_FIELD, disableNotification.toString())
        );

      if(replyToMessageId != null)
        params.add(
            new BasicNameValuePair(REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId.toString())
        );

      if(replyMarkup != null)
        params.add(
            new BasicNameValuePair(REPLY_MARKUP_FIELD, replyMarkup.toString())
        );

      if (video != null)
        params.add(
            new BasicNameValuePair(VIDEO_FIELD, video)
        );

      if (width != null)
        params.add(
            new BasicNameValuePair(WIDTH_FIELD, width.toString())
        );

      if (height != null)
        params.add(
            new BasicNameValuePair(HEIGHT_FIELD, height.toString())
        );

      if (caption != null)
        params.add(
            new BasicNameValuePair(CAPTION_FIELD, caption)
        );

      if (duration != null)
        params.add(
            new BasicNameValuePair(DURATION_FIELD, duration.toString())
        );

      return EntityBuilder.create()
          .setParameters(params)
          .build();
    }
  }

}
