package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.AudioMessage;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.replymarkups.ReplyMarkup;
import me.palazzomichi.telegram.telejam.util.text.Text;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Use this method to send audio files, if you want Telegram clients to
 * display them in the music player. Your audio must be in the .mp3 format.
 * On success, the sent Message is returned.
 * Bots can currently send audio files of up to 50 MB in size,
 * this limit may be changed in the future.
 * For sending voice messages, use the sendVoice method instead.
 *
 * @author Michi Palazzo
 * @see SendVoice
 */
public class SendAudio implements TelegramMethod<AudioMessage> {
  
  public static final String NAME = "sendAudio";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String AUDIO_FIELD = "audio";
  static final String CAPTION_FIELD = "caption";
  static final String DURATION_FIELD = "duration";
  static final String PERFORMER_FIELD = "performer";
  static final String TITLE_FIELD = " title";
  
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
   * Audio file to send.
   */
  private String audio;
  
  /**
   * Audio caption, 0-200 characters.
   */
  private String caption;
  
  /**
   * Duration of the audio in seconds.
   */
  private Integer duration;
  
  /**
   * Performer.
   */
  private String performer;
  
  /**
   * Track name.
   */
  private String title;
  
  /**
   * Audio not present in Telegram servers.
   */
  private InputStream newAudio;
  
  
  public SendAudio chat(String chatId) {
    this.chatId = chatId;
    return this;
  }
  
  public SendAudio chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }
  
  public SendAudio chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }
  
  public SendAudio disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }
  
  public SendAudio disableNotification() {
    this.disableNotification = true;
    return this;
  }
  
  public SendAudio replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    this.chatId = Long.toString(message.getChat().getId());
    return this;
  }
  
  public SendAudio replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }
  
  public SendAudio replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }
  
  public SendAudio audio(String audio) {
    newAudio = null;
    this.audio = audio;
    return this;
  }
  
  public SendAudio audio(InputStream newAudio) {
    audio = null;
    this.newAudio = newAudio;
    return this;
  }
  
  public SendAudio audio(File newAudio) throws FileNotFoundException {
    audio = null;
    this.newAudio = new FileInputStream(newAudio);
    return this;
  }
  
  public SendAudio audio(Path newAudio) throws IOException {
    audio = null;
    this.newAudio = Files.newInputStream(newAudio);
    return this;
  }
  
  public SendAudio caption(String caption) {
    this.caption = caption;
    return this;
  }
  
  public SendAudio caption(Text caption) {
    this.caption = caption.toString();
    return this;
  }
  
  public SendAudio duration(Integer duration) {
    this.duration = duration;
    return this;
  }
  
  public SendAudio performer(String performer) {
    this.performer = performer;
    return this;
  }
  
  public SendAudio title(String title) {
    this.title = title;
    return this;
  }
  
  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Class<? extends AudioMessage> getReturnType(JsonElement response) {
    return AudioMessage.class;
  }
  
  @Override
  public HttpEntity getHttpEntity() {
    if (newAudio != null) {
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      
      if (chatId != null)
        builder.addTextBody(CHAT_ID_FIELD, chatId);
      
      if (disableNotification != null)
        builder.addTextBody(DISABLE_NOTIFICATION_FIELD, disableNotification.toString());
      
      if (replyToMessageId != null)
        builder.addTextBody(REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId.toString());
      
      if (replyMarkup != null)
        builder.addTextBody(REPLY_MARKUP_FIELD, replyMarkup.toString());
      
      builder.addBinaryBody(AUDIO_FIELD, newAudio);
      
      if (caption != null)
        builder.addTextBody(CAPTION_FIELD, caption);
      
      if (duration != null)
        builder.addTextBody(DURATION_FIELD, duration.toString());
      
      if (performer != null)
        builder.addTextBody(PERFORMER_FIELD, performer);
      
      if (title != null)
        builder.addTextBody(TITLE_FIELD, title);
      
      return builder.build();
    } else {
      List<NameValuePair> params = new ArrayList<>();
      
      if (chatId != null)
        params.add(
            new BasicNameValuePair(CHAT_ID_FIELD, chatId)
        );
      
      if (disableNotification != null)
        params.add(
            new BasicNameValuePair(DISABLE_NOTIFICATION_FIELD, disableNotification.toString())
        );
      
      if (replyToMessageId != null)
        params.add(
            new BasicNameValuePair(REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId.toString())
        );
      
      if (replyMarkup != null)
        params.add(
            new BasicNameValuePair(REPLY_MARKUP_FIELD, replyMarkup.toString())
        );
      
      if (audio != null)
        params.add(
            new BasicNameValuePair(AUDIO_FIELD, audio)
        );
      
      if (caption != null)
        params.add(
            new BasicNameValuePair(CAPTION_FIELD, caption)
        );
      
      if (duration != null)
        params.add(
            new BasicNameValuePair(DURATION_FIELD, duration.toString())
        );
      
      if (performer != null)
        params.add(
            new BasicNameValuePair(PERFORMER_FIELD, performer)
        );
      
      if (title != null)
        params.add(
            new BasicNameValuePair(TITLE_FIELD, title)
        );
      
      return EntityBuilder.create()
          .setParameters(params)
          .build();
    }
  }
  
}
