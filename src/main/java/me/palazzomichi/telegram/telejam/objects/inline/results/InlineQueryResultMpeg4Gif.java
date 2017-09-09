package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

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

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "mpeg4_gif";

  /**
   * A valid URL for the MP4 file.
   * File size must not exceed 1MB.
   */
  @SerializedName(MPEG4_URL_FIELD)
  private String mpeg4Url;

  /**
   * Video width.
   */
  @SerializedName(MPEG4_WIDTH_FIELD)
  private Integer mpeg4Width;

  /**
   * Video height.
   */
  @SerializedName(MPEG4_HEIGHT_FIELD)
  private Integer mpeg4Height;

  /**
   * Video duration.
   */
  @SerializedName(MPEG4_DURATION_FIELD)
  private Integer mpeg4Duration;

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
   * Caption of the MPEG-4 file to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the video animation.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;


  public InlineQueryResultMpeg4Gif(String id,
                              String mpeg4Url,
                              Integer mpeg4Width,
                              Integer mpeg4Height,
                              Integer gifDuration,
                              String thumbUrl,
                              String title,
                              String caption,
                              InlineKeyboardMarkup replyMarkup,
                              InputMessageContent inputMessageContent) {
    super(id);
    this.mpeg4Url = Objects.requireNonNull(mpeg4Url);
    this.mpeg4Width = mpeg4Width;
    this.mpeg4Height = mpeg4Height;
    this.mpeg4Duration = gifDuration;
    this.thumbUrl = Objects.requireNonNull(thumbUrl);
    this.title = title;
    this.caption = caption;
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
   * Setter for property {@link #mpeg4Url}.
   *
   * @param mpeg4Url value for property {@link #mpeg4Url}
   */
  public void setMpeg4Url(String mpeg4Url) {
    this.mpeg4Url = Objects.requireNonNull(mpeg4Url);
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
   * Setter for property {@link #mpeg4Width}.
   *
   * @param mpeg4Width value for property {@link #mpeg4Width}
   */
  public void setMpeg4Width(Integer mpeg4Width) {
    this.mpeg4Width = mpeg4Width;
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
   * Setter for property {@link #mpeg4Height}.
   *
   * @param mpeg4Height value for property {@link #mpeg4Height}
   */
  public void setMpeg4Height(Integer mpeg4Height) {
    this.mpeg4Height = mpeg4Height;
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
   * Setter for property {@link #mpeg4Duration}.
   *
   * @param mpeg4Duration value for property {@link #mpeg4Duration}
   */
  public void setMpeg4Duration(Integer mpeg4Duration) {
    this.mpeg4Duration = mpeg4Duration;
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
