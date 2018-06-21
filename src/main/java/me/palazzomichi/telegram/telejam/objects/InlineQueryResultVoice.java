package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

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
  static final String VOICE_DURATION_FIELD = "voice_duration";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String PARSE_MODE_FIELD = "parse_mode";

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "voice";
  
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";
  
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
                                Text caption,
                                Integer voiceDuration,
                                InlineKeyboardMarkup replyMarkup,
                                InputMessageContent inputMessageContent) {
    super(id);
    this.voiceUrl = Objects.requireNonNull(voiceUrl);
    this.title = Objects.requireNonNull(title);
    this.caption = caption != null ? caption.toHtmlString() : null;
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
   * Getter for property {@link #title}.
   *
   * @return value for property {@link #title}
   */
  public String getTitle() {
    return title;
  }

  /**
   * Getter for property {@link #caption}.
   *
   * @return optional value for property {@link #caption}
   */
  public Optional<Text> getCaption() {
    return caption != null ? Optional.of(Text.parseHtml(caption)) : Optional.empty();
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
   * Getter for property {@link #replyMarkup}.
   *
   * @return optional value for property {@link #replyMarkup}
   */
  public Optional<InlineKeyboardMarkup> getReplyMarkup() {
    return Optional.ofNullable(replyMarkup);
  }
  
  /**
   * Getter for property {@link #inputMessageContent}.
   *
   * @return optional value for property {@link #inputMessageContent}
   */
  public Optional<InputMessageContent> getInputMessageContent() {
    return Optional.ofNullable(inputMessageContent);
  }
  
}
