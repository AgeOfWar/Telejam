package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

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
  static final String PARSE_MODE_FIELD = "parse_mode";

  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "gif";
  
  @Expose
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";
  
  /**
   * A valid URL for the GIF file.
   * InputFile size must not exceed 1MB.
   */
  @SerializedName(GIF_URL_FIELD)
  private final String gifUrl;

  /**
   * Width of the GIF.
   */
  @SerializedName(GIF_WIDTH_FIELD)
  private final Integer gifWidth;

  /**
   * Height of the GIF.
   */
  @SerializedName(GIF_HEIGHT_FIELD)
  private final Integer gifHeight;

  /**
   * Duration of the GIF.
   */
  @SerializedName(GIF_DURATION_FIELD)
  private final Integer gifDuration;

  /**
   * URL of the static thumbnail for the result (jpeg or gif).
   */
  @SerializedName(THUMB_URL_FIELD)
  private final String thumbUrl;

  /**
   * Title for the result.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;

  /**
   * Caption of the GIF file to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private final String caption;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private final InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the GIF animation.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private final InputMessageContent inputMessageContent;


  public InlineQueryResultGif(String id,
                              String gifUrl,
                              Integer gifWidth,
                              Integer gifHeight,
                              Integer gifDuration,
                              String thumbUrl,
                              String title,
                              Text caption,
                              InlineKeyboardMarkup replyMarkup,
                              InputMessageContent inputMessageContent) {
    super(id);
    this.gifUrl = Objects.requireNonNull(gifUrl);
    this.gifWidth = gifWidth;
    this.gifHeight = gifHeight;
    this.gifDuration = gifDuration;
    this.thumbUrl = Objects.requireNonNull(thumbUrl);
    this.title = title;
    this.caption = caption != null ? caption.toHtmlString() : null;
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
   * Getter for property {@link #gifWidth}.
   *
   * @return optional value for property {@link #gifWidth}
   */
  public OptionalInt getGifWidth() {
    return gifWidth == null ? OptionalInt.empty() : OptionalInt.of(gifWidth);
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
   * Getter for property {@link #gifDuration}.
   *
   * @return optional value for property {@link #gifDuration}
   */
  public OptionalInt getGifDuration() {
    return gifDuration == null ? OptionalInt.empty() : OptionalInt.of(gifDuration);
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
   * Getter for property {@link #title}.
   *
   * @return optional value for property {@link #title}
   */
  public Optional<String> getTitle() {
    return Optional.ofNullable(title);
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
