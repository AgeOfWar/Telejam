package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

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

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "video";

  /**
   * A valid URL for the embedded video player or video file.
   */
  @SerializedName(VIDEO_URL_FIELD)
  private String videoUrl;

  /**
   * Mime type of the content of video url, “text/html” or “video/mp4”.
   */
  @SerializedName(MIME_TYPE_FIELD)
  private String mimeType;

  /**
   * URL of the thumbnail (jpeg only) for the video.
   */
  @SerializedName(THUMB_URL_FIELD)
  private String thumbUrl;

  /**
   * Title for the result.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Caption of the video to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;

  /**
   * Video width.
   */
  @SerializedName(VIDEO_WIDTH_FIELD)
  private Integer videoWidth;

  /**
   * Video height.
   */
  @SerializedName(VIDEO_HEIGHT_FIELD)
  private Integer videoHeight;

  /**
   * Video duration in seconds.
   */
  @SerializedName(VIDEO_DURATION_FIELD)
  private Integer videoDuration;

  /**
   * Short description of the result.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private String description;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the video.
   * This field is required if InlineQueryResultVideo is used to
   * send an HTML-page as a result (e.g., a YouTube video).
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;


  public InlineQueryResultVideo(String id,
                                String videoUrl,
                                String mimeType,
                                String thumbUrl,
                                String title,
                                String caption,
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
    this.caption = caption;
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
   * Setter for property {@link #videoUrl}.
   *
   * @param videoUrl value for property {@link #videoUrl}
   */
  public void setVideoUrl(String videoUrl) {
    this.videoUrl = Objects.requireNonNull(videoUrl);
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
   * Setter for property {@link #mimeType}.
   *
   * @param mimeType value for property {@link #mimeType}
   */
  public void setMimeType(String mimeType) {
    this.mimeType = Objects.requireNonNull(mimeType);
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
   * Getter for property {@link #videoWidth}.
   *
   * @return optional value for property {@link #videoWidth}
   */
  public OptionalInt getVideoWidth() {
    return videoWidth == null ? OptionalInt.empty() : OptionalInt.of(videoDuration);
  }

  /**
   * Setter for property {@link #videoWidth}.
   *
   * @param videoWidth value for property {@link #videoWidth}
   */
  public void setVideoWidth(Integer videoWidth) {
    this.videoWidth = videoWidth;
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
   * Setter for property {@link #videoHeight}.
   *
   * @param videoHeight value for property {@link #videoHeight}
   */
  public void setVideoHeight(Integer videoHeight) {
    this.videoHeight = videoHeight;
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
   * Setter for property {@link #videoDuration}.
   *
   * @param videoDuration value for property {@link #videoDuration}
   */
  public void setVideoDuration(Integer videoDuration) {
    this.videoDuration = videoDuration;
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
   * Setter for property {@link #description}.
   *
   * @param description value for property {@link #description}
   */
  public void setDescription(String description) {
    this.description = description;
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
