package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

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

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "audio";

  /**
   * A valid URL for the audio file.
   */
  @SerializedName(AUDIO_URL_FIELD)
  private String audioUrl;

  /**
   * Title.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Caption, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;

  /**
   * Performer.
   */
  @SerializedName(PERFORMER_FIELD)
  private String performer;

  /**
   * Audio duration in seconds.
   */
  @SerializedName(AUDIO_DURATION_FIELD)
  private Integer audioDuration;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the audio.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;


  public InlineQueryResultAudio(String id,
                                String audioUrl,
                                String title,
                                String caption,
                                String performer,
                                Integer audioDuration,
                                InlineKeyboardMarkup replyMarkup,
                                InputMessageContent inputMessageContent) {
    super(id);
    this.audioUrl = Objects.requireNonNull(audioUrl);
    this.title = Objects.requireNonNull(title);
    this.caption = caption;
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
   * Setter for property {@link #audioUrl}.
   *
   * @param audioUrl value for property {@link #audioUrl}
   */
  public void setAudioUrl(String audioUrl) {
    this.audioUrl = Objects.requireNonNull(audioUrl);
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
   * Getter for property {@link #performer}.
   *
   * @return optional value for property {@link #performer}
   */
  public Optional<String> getPerformer() {
    return Optional.ofNullable(performer);
  }

  /**
   * Setter for property {@link #performer}.
   *
   * @param performer value for property {@link #performer}
   */
  public void setPerformer(String performer) {
    this.performer = performer;
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
   * Setter for property {@link #audioDuration}.
   *
   * @param audioDuration value for property {@link #audioDuration}
   */
  public void setAudioDuration(Integer audioDuration) {
    this.audioDuration = audioDuration;
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
