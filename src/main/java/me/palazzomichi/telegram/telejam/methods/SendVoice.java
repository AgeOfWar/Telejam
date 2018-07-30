package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

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
   * Audio file to send.
   */
  private String voice;

  /**
   * Voice caption, 0-200 characters.
   */
  private Text caption;

  /**
   * Duration of the voice in seconds.
   */
  private Integer duration;

  /**
   * Voice not present in Telegram servers.
   */
  private UploadFile newVoice;
  
  
  public SendVoice chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    return this;
  }
  
  public SendVoice chat(Long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    return this;
  }
  
  public SendVoice chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
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
    this.chatId = message.getChat().getId();
    this.chatUsername = null;
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
  
  public SendVoice voice(UploadFile newVoice) {
    voice = null;
    this.newVoice = newVoice;
    return this;
  }

  public SendVoice caption(String caption) {
    this.caption = new Text(caption);
    return this;
  }

  public SendVoice caption(Text caption) {
    this.caption = caption;
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
  public Map<String, Object> getParameters() {
    return mapOf(
        CHAT_ID_FIELD, chatId != null ? chatId : chatUsername,
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId,
        REPLY_MARKUP_FIELD, replyMarkup,
        VOICE_FIELD, voice,
        CAPTION_FIELD, caption != null ? caption.toHtmlString() : null,
        PARSE_MODE_FIELD, "HTML",
        DURATION_FIELD, duration
    );
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(VOICE_FIELD, newVoice);
  }
  
  @Override
  public Class<? extends VoiceMessage> getReturnType() {
    return VoiceMessage.class;
  }
  
}
