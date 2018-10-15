package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound).
 * By default, this animated MPEG-4 file will be sent by the user with optional caption.
 * Alternatively, you can use {@link #inputMessageContent} to send a message with the
 * specified content instead of the animation.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultMpeg4Gif extends InlineQueryResult {

  static final String MPEG4_URL_FIELD = "mpeg4_url";
  static final String MPEG4_WIDTH_FIELD = "mpeg4_width";
  static final String MPEG4_HEIGHT_FIELD = "mpeg4_height";
  static final String MPEG4_DURATION_FIELD = "mpeg4_duration";
  static final String THUMB_URL_FIELD = "thumb_url";
  static final String TITLE_FIELD = "title";
  static final String CAPTION_FIELD = "caption";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String PARSE_MODE_FIELD = "parse_mode";

  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "mpeg4_gif";
  
  @Expose
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";
  
  /**
   * A valid URL for the MP4 file.
   * InputFile size must not exceed 1MB.
   */
  @SerializedName(MPEG4_URL_FIELD)
  private final String mpeg4Url;

  /**
   * Video width.
   */
  @SerializedName(MPEG4_WIDTH_FIELD)
  private final Integer mpeg4Width;

  /**
   * Video height.
   */
  @SerializedName(MPEG4_HEIGHT_FIELD)
  private final Integer mpeg4Height;

  /**
   * Video duration.
   */
  @SerializedName(MPEG4_DURATION_FIELD)
  private final Integer mpeg4Duration;

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
   * Caption of the MPEG-4 file to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private final String caption;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private final InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the video animation.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private final InputMessageContent inputMessageContent;


  public InlineQueryResultMpeg4Gif(String id,
                              String mpeg4Url,
                              Integer mpeg4Width,
                              Integer mpeg4Height,
                              Integer gifDuration,
                              String thumbUrl,
                              String title,
                              Text caption,
                              InlineKeyboardMarkup replyMarkup,
                              InputMessageContent inputMessageContent) {
    super(id);
    this.mpeg4Url = Objects.requireNonNull(mpeg4Url);
    this.mpeg4Width = mpeg4Width;
    this.mpeg4Height = mpeg4Height;
    mpeg4Duration = gifDuration;
    this.thumbUrl = Objects.requireNonNull(thumbUrl);
    this.title = title;
    this.caption = caption != null ? caption.toHtmlString() : null;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultMpeg4Gif(String id, String mpeg4Url, String thumbUrl) {
    this(id, mpeg4Url, null, null, null, thumbUrl, null, null, null, null);
  }


  /**
   * Getter for property {@link #mpeg4Url}.
   *
   * @return value for property {@link #mpeg4Url}
   */
  public String getMpeg4Url() {
    return mpeg4Url;
  }

  /**
   * Getter for property {@link #mpeg4Width}.
   *
   * @return optional value for property {@link #mpeg4Width}
   */
  public OptionalInt getMpeg4Width() {
    return mpeg4Width == null ? OptionalInt.empty() : OptionalInt.of(mpeg4Width);
  }

  /**
   * Getter for property {@link #mpeg4Height}.
   *
   * @return optional value for property {@link #mpeg4Height}
   */
  public OptionalInt getMpeg4Height() {
    return mpeg4Height == null ? OptionalInt.empty() : OptionalInt.of(mpeg4Height);
  }
  
  /**
   * Getter for property {@link #mpeg4Duration}.
   *
   * @return optional value for property {@link #mpeg4Duration}
   */
  public OptionalInt getMpeg4Duration() {
    return mpeg4Duration == null ? OptionalInt.empty() : OptionalInt.of(mpeg4Duration);
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
