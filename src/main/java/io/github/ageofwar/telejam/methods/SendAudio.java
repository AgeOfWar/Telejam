package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.replymarkups.ReplyMarkup;
import io.github.ageofwar.telejam.connection.UploadFile;
import io.github.ageofwar.telejam.messages.*;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.text.Text;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

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
  static final String PARSE_MODE_FIELD = "parse_mode";
  static final String DURATION_FIELD = "duration";
  static final String PERFORMER_FIELD = "performer";
  static final String TITLE_FIELD = " title";
  
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
   * Audio file to send.
   */
  private String audio;
  
  /**
   * Audio caption, 0-200 characters.
   */
  private Text caption;
  
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
  private UploadFile newAudio;
  
  
  public SendAudio chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SendAudio chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SendAudio chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }
  
  public SendAudio disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }
  
  public SendAudio disableNotification() {
    disableNotification = true;
    return this;
  }
  
  public SendAudio replyToMessage(Message message) {
    replyToMessageId = message.getId();
    chatId = message.getChat().getId();
    chatUsername = null;
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
  
  public SendAudio audio(UploadFile newAudio) {
    audio = null;
    this.newAudio = newAudio;
    return this;
  }
  
  public SendAudio caption(String caption) {
    this.caption = new Text(caption);
    return this;
  }
  
  public SendAudio caption(Text caption) {
    this.caption = caption;
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
  public Map<String, Object> getParameters() {
    return mapOf(
        CHAT_ID_FIELD, chatId != null ? chatId : chatUsername,
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId,
        REPLY_MARKUP_FIELD, replyMarkup,
        AUDIO_FIELD, audio,
        CAPTION_FIELD, caption != null ? caption.toHtmlString() : null,
        PARSE_MODE_FIELD, "HTML",
        DURATION_FIELD, duration,
        PERFORMER_FIELD, performer,
        TITLE_FIELD, title
    );
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(AUDIO_FIELD, newAudio);
  }
  
  @Override
  public Class<? extends AudioMessage> getReturnType() {
    return AudioMessage.class;
  }
  
}
