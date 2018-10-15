package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a link to an mp3 audio file.
 * By default, this audio file will be sent by the user.
 * Alternatively, you can use {@link #inputMessageContent} to send
 * a message with the specified content instead of the audio.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultAudio extends InlineQueryResult {

  static final String AUDIO_URL_FIELD = "audio_url";
  static final String TITLE_FIELD = "title";
  static final String CAPTION_FIELD = "caption";
  static final String PERFORMER_FIELD = "performer";
  static final String AUDIO_DURATION_FIELD = "audio_duration";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String PARSE_MODE_FIELD = "parse_mode";

  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "audio";
  
  @Expose
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";

  /**
   * A valid URL for the audio file.
   */
  @SerializedName(AUDIO_URL_FIELD)
  private final String audioUrl;

  /**
   * Title.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;

  /**
   * Caption, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private final String caption;

  /**
   * Performer.
   */
  @SerializedName(PERFORMER_FIELD)
  private final String performer;

  /**
   * Audio duration in seconds.
   */
  @SerializedName(AUDIO_DURATION_FIELD)
  private final Integer audioDuration;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private final InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the audio.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private final InputMessageContent inputMessageContent;


  public InlineQueryResultAudio(String id,
                                String audioUrl,
                                String title,
                                Text caption,
                                String performer,
                                Integer audioDuration,
                                InlineKeyboardMarkup replyMarkup,
                                InputMessageContent inputMessageContent) {
    super(id);
    this.audioUrl = Objects.requireNonNull(audioUrl);
    this.title = Objects.requireNonNull(title);
    this.caption = caption != null ? caption.toHtmlString() : null;
    this.performer = performer;
    this.audioDuration = audioDuration;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultAudio(String id, String audioUrl, String title) {
    this(id, audioUrl, title, null, null, null, null, null);
  }


  /**
   * Getter for property {@link #audioUrl}.
   *
   * @return value for property {@link #audioUrl}
   */
  public String getAudioUrl() {
    return audioUrl;
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
   * Getter for property {@link #performer}.
   *
   * @return optional value for property {@link #performer}
   */
  public Optional<String> getPerformer() {
    return Optional.ofNullable(performer);
  }

  /**
   * Getter for property {@link #audioDuration}.
   *
   * @return optional value for property {@link #audioDuration}
   */
  public OptionalInt getAudioDuration() {
    return audioDuration == null ? OptionalInt.empty() : OptionalInt.of(audioDuration);
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
