package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a link to a photo.
 * By default, this photo will be sent by the user with optional caption.
 * Alternatively, you can use {@link #inputMessageContent} to send a message with
 * the specified content instead of the photo.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultGif extends InlineQueryResult {

  static final String GIF_URL_FIELD = "gif_url";
  static final String GIF_WIDTH_FIELD = "gif_width";
  static final String GIF_HEIGHT_FIELD = "gif_height";
  static final String GIF_DURATION_FIELD = "gif_duration";
  static final String THUMB_URL_FIELD = "thumb_url";
  static final String TITLE_FIELD = "title";
  static final String CAPTION_FIELD = "caption";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "gif";

  /**
   * A valid URL for the GIF file.
   * File size must not exceed 1MB.
   */
  @SerializedName(GIF_URL_FIELD)
  private String gifUrl;

  /**
   * Width of the GIF.
   */
  @SerializedName(GIF_WIDTH_FIELD)
  private Integer gifWidth;

  /**
   * Height of the GIF.
   */
  @SerializedName(GIF_HEIGHT_FIELD)
  private Integer gifHeight;

  /**
   * Duration of the GIF.
   */
  @SerializedName(GIF_DURATION_FIELD)
  private Integer gifDuration;

  /**
   * URL of the static thumbnail for the result (jpeg or gif).
   */
  @SerializedName(THUMB_URL_FIELD)
  private String thumbUrl;

  /**
   * Title for the result.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Caption of the GIF file to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the GIF animation.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;


  public InlineQueryResultGif(String id,
                              String gifUrl,
                              Integer gifWidth,
                              Integer gifHeight,
                              Integer gifDuration,
                              String thumbUrl,
                              String title,
                              String caption,
                              InlineKeyboardMarkup replyMarkup,
                              InputMessageContent inputMessageContent) {
    super(id);
    this.gifUrl = Objects.requireNonNull(gifUrl);
    this.gifWidth = gifWidth;
    this.gifHeight = gifHeight;
    this.gifDuration = gifDuration;
    this.thumbUrl = Objects.requireNonNull(thumbUrl);
    this.title = title;
    this.caption = caption;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultGif(String id, String gifUrl, String thumbUrl) {
    this(id, gifUrl, null, null, null, thumbUrl, null, null, null, null);
  }


  /**
   * Getter for property {@link #gifUrl}.
   *
   * @return value for property {@link #gifUrl}
   */
  public String getGifUrl() {
    return gifUrl;
  }

  /**
   * Setter for property {@link #gifUrl}.
   *
   * @param gifUrl value for property {@link #gifUrl}
   */
  public void setGifUrl(String gifUrl) {
    this.gifUrl = Objects.requireNonNull(gifUrl);
  }

  /**
   * Getter for property {@link #gifWidth}.
   *
   * @return optional value for property {@link #gifWidth}
   */
  public OptionalInt getGifWidth() {
    return gifWidth == null ? OptionalInt.empty() : OptionalInt.of(gifWidth);
  }

  /**
   * Setter for property {@link #gifWidth}.
   *
   * @param gifWidth value for property {@link #gifWidth}
   */
  public void setGifWidth(Integer gifWidth) {
    this.gifWidth = gifWidth;
  }

  /**
   * Getter for property {@link #gifHeight}.
   *
   * @return optional value for property {@link #gifHeight}
   */
  public OptionalInt getGifHeight() {
    return gifHeight == null ? OptionalInt.empty() : OptionalInt.of(gifHeight);
  }

  /**
   * Setter for property {@link #gifHeight}.
   *
   * @param gifHeight value for property {@link #gifHeight}
   */
  public void setGifHeight(Integer gifHeight) {
    this.gifHeight = gifHeight;
  }

  /**
   * Getter for property {@link #gifDuration}.
   *
   * @return optional value for property {@link #gifDuration}
   */
  public OptionalInt getGifDuration() {
    return gifDuration == null ? OptionalInt.empty() : OptionalInt.of(gifDuration);
  }

  /**
   * Setter for property {@link #gifDuration}.
   *
   * @param gifDuration value for property {@link #gifDuration}
   */
  public void setGifDuration(Integer gifDuration) {
    this.gifDuration = gifDuration;
  }

  /**
   * Getter for property {@link #thumbUrl}.
   *
   * @return value for property {@link #thumbUrl}
   */
  public String getThumbUrl() {
    return thumbUrl;
  }

  /**
   * Setter for property {@link #thumbUrl}.
   *
   * @param thumbUrl value for property {@link #thumbUrl}
   */
  public void setThumbUrl(String thumbUrl) {
    this.thumbUrl = Objects.requireNonNull(thumbUrl);
  }

  /**
   * Getter for property {@link #title}.
   *
   * @return optional value for property {@link #title}
   */
  public Optional<String> getTitle() {
    return Optional.ofNullable(title);
  }

  /**
   * Setter for property {@link #title}.
   *
   * @param title value for property {@link #title}
   */
  public void setTitle(String title) {
    this.title = title;
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
