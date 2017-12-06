package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.messages.VoiceMessage;
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
 * Use this method to send audio files, if you want Telegram
 * clients to display the file as a playable voice message.
 * For this to work, your audio must be in an .ogg file
 * encoded with OPUS (other formats may be sent as Audio or Document).
 * On success, the sent Message is returned.
 * Bots can currently send voice messages of up to 50 MB in size,
 * this limit may be changed in the future.
 *
 * @author Michi Palazzo
 * @see SendAudio
 */
public class SendVoice implements TelegramMethod<VoiceMessage> {

  public static final String NAME = "sendVoice";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String VOICE_FIELD = "voice";
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
   * Audio file to send.
   */
  private String voice;

  /**
   * Voice caption, 0-200 characters.
   */
  private String caption;

  /**
   * Duration of the voice in seconds.
   */
  private Integer duration;

  /**
   * Voice not present in Telegram servers.
   */
  private InputStream newVoice;


  public SendVoice chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public SendVoice chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public SendVoice chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public SendVoice disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendVoice disableNotification() {
    this.disableNotification = true;
    return this;
  }

  public SendVoice replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    this.chatId = Long.toString(message.getChat().getId());
    return this;
  }

  public SendVoice replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }

  public SendVoice replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  public SendVoice voice(String voice) {
    newVoice = null;
    this.voice = voice;
    return this;
  }
  
  public SendVoice voice(InputStream newVoice) {
    voice = null;
    this.newVoice = newVoice;
    return this;
  }
  
  public SendVoice voice(File newVoice) throws FileNotFoundException {
    voice = null;
    this.newVoice = new FileInputStream(newVoice);
    return this;
  }
  
  public SendVoice voice(Path newVoice) throws IOException {
    voice = null;
    this.newVoice = Files.newInputStream(newVoice);
    return this;
  }

  public SendVoice caption(String caption) {
    this.caption = caption;
    return this;
  }

  public SendVoice caption(Text caption) {
    this.caption = caption.toString();
    return this;
  }

  public SendVoice duration(Integer duration) {
    this.duration = duration;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends VoiceMessage> getReturnType(JsonElement response) {
    return VoiceMessage.class;
  }

  @Override
  public HttpEntity getHttpEntity() {
    if (newVoice != null) {
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();

      if (chatId != null)
        builder.addTextBody(CHAT_ID_FIELD, chatId);

      if (disableNotification != null)
        builder.addTextBody(DISABLE_NOTIFICATION_FIELD, disableNotification.toString());

      if (replyToMessageId != null)
        builder.addTextBody(REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId.toString());

      if (replyMarkup != null)
        builder.addTextBody(REPLY_MARKUP_FIELD, replyMarkup.toString());

      builder.addBinaryBody(VOICE_FIELD, newVoice);

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

      if (voice != null)
        params.add(
            new BasicNameValuePair(VOICE_FIELD, voice)
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
