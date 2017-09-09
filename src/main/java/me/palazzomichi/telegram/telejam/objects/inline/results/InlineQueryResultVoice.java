package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a link to a voice recording in an .ogg container encoded with OPUS.
 * By default, this voice recording will be sent by the user. Alternatively,
 * you can use {@link #inputMessageContent} to send a message with the specified
 * content instead of the the voice message.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultVoice extends InlineQueryResult {

  static final String VOICE_URL_FIELD = "voice_url";
  static final String TITLE_FIELD = "title";
  static final String CAPTION_FIELD = "caption";
  static final String PERFORMER_FIELD = "performer";
  static final String VOICE_DURATION_FIELD = "voice_duration";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "voice";

  /**
   * A valid URL for the voice recording.
   */
  @SerializedName(VOICE_URL_FIELD)
  private String voiceUrl;

  /**
   * Recording title.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Caption, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;

  /**
   * Audio duration in seconds.
   */
  @SerializedName(VOICE_DURATION_FIELD)
  private Integer voiceDuration;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the voice recording.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;


  public InlineQueryResultVoice(String id,
                                String voiceUrl,
                                String title,
                                String caption,
                                Integer voiceDuration,
                                InlineKeyboardMarkup replyMarkup,
                                InputMessageContent inputMessageContent) {
    super(id);
    this.voiceUrl = Objects.requireNonNull(voiceUrl);
    this.title = Objects.requireNonNull(title);
    this.caption = caption;
    this.voiceDuration = voiceDuration;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultVoice(String id, String voiceUrl, String title) {
    this(id, voiceUrl, title, null, null, null, null);
  }


  /**
   * Getter for property {@link #voiceUrl}.
   *
   * @return value for property {@link #voiceUrl}
   */
  public String getVoiceUrl() {
    return voiceUrl;
  }

  /**
   * Setter for property {@link #voiceUrl}.
   *
   * @param voiceUrl value for property {@link #voiceUrl}
   */
  public void setVoiceUrl(String voiceUrl) {
    this.voiceUrl = Objects.requireNonNull(voiceUrl);
  }

  /**
   * Getter for property {@link #title}.
   *
   * @return value for property {@link #title}
   */
  public String getTitle() {
    return title;
  }

  /**
   * Setter for property {@link #title}.
   *
   * @param title value for property {@link #title}
   */
  public void setTitle(String title) {
    this.title = Objects.requireNonNull(title);
  }

  /**
   * Getter for property {@link #caption}.
   *
   * @return optional value for property {@link #caption}
   */
  public Optional<String> getCaption() {
    return Optional.ofNullable(caption);
  }

  /**
   * Setter for property {@link #caption}.
   *
   * @param caption value for property {@link #caption}
   */
  public void setCaption(String caption) {
    this.caption = caption;
  }

  /**
   * Getter for property {@link #voiceDuration}.
   *
   * @return optional value for property {@link #voiceDuration}
   */
  public OptionalInt getVoiceDuration() {
    return voiceDuration == null ? OptionalInt.empty() : OptionalInt.of(voiceDuration);
  }

  /**
   * Setter for property {@link #voiceDuration}.
   *
   * @param voiceDuration value for property {@link #voiceDuration}
   */
  public void setVoiceDuration(Integer voiceDuration) {
    this.voiceDuration = voiceDuration;
  }

  /**
   * Getter for property {@link #replyMarkup}.
   *
   * @return optional value for property {@link #replyMarkup}
   */
  public Optional<InlineKeyboardMarkup> getReplyMarkup() {
    return Optional.ofNullable(replyMarkup);
  }

  /**
   * Setter for property {@link #replyMarkup}.
   *
   * @param replyMarkup value for property {@link #replyMarkup}
   */
  public void setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
  }

  /**
   * Getter for property {@link #inputMessageContent}.
   *
   * @return optional value for property {@link #inputMessageContent}
   */
  public Optional<InputMessageContent> getInputMessageContent() {
    return Optional.ofNullable(inputMessageContent);
  }

  /**
   * Setter for property {@link #inputMessageContent}.
   *
   * @param inputMessageContent value for property {@link #inputMessageContent}
   */
  public void setInputMessageContent(InputMessageContent inputMessageContent) {
    this.inputMessageContent = inputMessageContent;
  }

}
