package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a link to a page containing an embedded video player or a video file.
 * By default, this video file will be sent by the user with an optional caption.
 * Alternatively, you can use {@link #inputMessageContent} to send a message with the
 * specified content instead of the video.
 *
 * <p>If an InlineQueryResultVideo message contains an embedded video (e.g., YouTube),
 * you <b>must</b> replace its content using input_message_content.</p>
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultVideo extends InlineQueryResult {

  static final String VIDEO_URL_FIELD = "video_url";
  static final String MIME_TYPE_FIELD = "mime_type";
  static final String THUMB_URL_FIELD = "thumb_url";
  static final String TITLE_FIELD = "title";
  static final String CAPTION_FIELD = "caption";
  static final String VIDEO_WIDTH_FIELD = "video_width";
  static final String VIDEO_HEIGHT_FIELD = "video_height";
  static final String VIDEO_DURATION_FIELD = "video_duration";
  static final String DESCRIPTION_FIELD = "description";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String PARSE_MODE_FIELD = "parse_mode";

  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "video";
  
  @Expose
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";

  /**
   * A valid URL for the embedded video player or video file.
   */
  @SerializedName(VIDEO_URL_FIELD)
  private final String videoUrl;

  /**
   * Mime type of the content of video url, "text/html" or "video/mp4".
   */
  @SerializedName(MIME_TYPE_FIELD)
  private final String mimeType;

  /**
   * URL of the thumbnail (jpeg only) for the video.
   */
  @SerializedName(THUMB_URL_FIELD)
  private final String thumbUrl;

  /**
   * Title for the result.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;

  /**
   * Caption of the video to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private final String caption;

  /**
   * Video width.
   */
  @SerializedName(VIDEO_WIDTH_FIELD)
  private final Integer videoWidth;

  /**
   * Video height.
   */
  @SerializedName(VIDEO_HEIGHT_FIELD)
  private final Integer videoHeight;

  /**
   * Video duration in seconds.
   */
  @SerializedName(VIDEO_DURATION_FIELD)
  private final Integer videoDuration;

  /**
   * Short description of the result.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private final String description;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private final InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the video.
   * This field is required if InlineQueryResultVideo is used to
   * send an HTML-page as a result (e.g., a YouTube video).
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private final InputMessageContent inputMessageContent;


  public InlineQueryResultVideo(String id,
                                String videoUrl,
                                String mimeType,
                                String thumbUrl,
                                String title,
                                Text caption,
                                Integer videoWidth,
                                Integer videoHeight,
                                Integer videoDuration,
                                String description,
                                InlineKeyboardMarkup replyMarkup,
                                InputMessageContent inputMessageContent) {
    super(id);
    this.videoUrl = Objects.requireNonNull(videoUrl);
    this.mimeType = Objects.requireNonNull(mimeType);
    this.thumbUrl = Objects.requireNonNull(thumbUrl);
    this.title = Objects.requireNonNull(title);
    this.caption = caption != null ? caption.toHtmlString() : null;
    this.videoWidth = videoWidth;
    this.videoHeight = videoHeight;
    this.videoDuration = videoDuration;
    this.description = description;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultVideo(String id, String videoUrl, String mimeType, String thumbUrl, String title) {
    this(id, videoUrl, mimeType, thumbUrl, title, null, null, null, null, null, null, null);
  }


  /**
   * Getter for property {@link #videoUrl}.
   *
   * @return value for property {@link #videoUrl}
   */
  public String getVideoUrl() {
    return videoUrl;
  }

  /**
   * Getter for property {@link #mimeType}.
   *
   * @return value for property {@link #mimeType}
   */
  public String getMimeType() {
    return mimeType;
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
   * Getter for property {@link #videoWidth}.
   *
   * @return optional value for property {@link #videoWidth}
   */
  public OptionalInt getVideoWidth() {
    return videoWidth == null ? OptionalInt.empty() : OptionalInt.of(videoDuration);
  }

  /**
   * Getter for property {@link #videoHeight}.
   *
   * @return optional value for property {@link #videoHeight}
   */
  public OptionalInt getVideoHeight() {
    return videoHeight == null ? OptionalInt.empty() : OptionalInt.of(videoDuration);
  }

  /**
   * Getter for property {@link #videoDuration}.
   *
   * @return optional value for property {@link #videoDuration}
   */
  public OptionalInt getVideoDuration() {
    return videoDuration == null ? OptionalInt.empty() : OptionalInt.of(videoDuration);
  }

  /**
   * Getter for property {@link #description}.
   *
   * @return optional value for property {@link #description}
   */
  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
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
