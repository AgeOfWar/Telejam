package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.messages.VideoNoteMessage;
import me.palazzomichi.telegram.telejam.objects.replymarkups.ReplyMarkup;
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
   * Video width and height.
   */
  private Integer size;

  /**
   * Duration of sent video in seconds.
   */
  private Integer duration;

  /**
   * True if the video is not present in Telegram servers.
   */
  private boolean newVideo;


  public SendVideoNote chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public SendVideoNote chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public SendVideoNote chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
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
    this.chatId = Long.toString(message.getChat().getId());
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
    newVideo = false;
    this.video = video;
    return this;
  }

  public SendVideoNote video(File video) {
    newVideo = true;
    this.video = video.getPath();
    return this;
  }

  public SendVideoNote video(Path video) {
    newVideo = true;
    this.video = video.toString();
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
  public Class<? extends VideoNoteMessage> getReturnType(JsonElement response) {
    return VideoNoteMessage.class;
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

      if (size != null)
        builder.addTextBody(SIZE_FIELD, size.toString());

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

      if (size != null)
        params.add(
            new BasicNameValuePair(SIZE_FIELD, size.toString())
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
