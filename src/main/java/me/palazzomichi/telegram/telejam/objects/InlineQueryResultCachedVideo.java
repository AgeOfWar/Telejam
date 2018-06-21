package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a link to a video file stored on the Telegram servers.
 * By default, this video file will be sent by the user with an optional caption.
 * Alternatively, you can use {@link #inputMessageContent} to send a message with
 * the specified content instead of the video.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultCachedVideo extends InlineQueryResult {

  static final String VIDEO_FILE_ID_FIELD = "video_file_id";
  static final String TITLE_FIELD = "title";
  static final String DESCRIPTION_FIELD = "description";
  static final String CAPTION_FIELD = "caption";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String PARSE_MODE_FIELD = "parse_mode";

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "video";
  
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";

  /**
   * A valid file identifier for the video file.
   */
  @SerializedName(VIDEO_FILE_ID_FIELD)
  private String videoFileId;

  /**
   * Title for the result.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Short description of the result.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private String description;

  /**
   * Caption of the video to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the video.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;


  public InlineQueryResultCachedVideo(String id,
                                      String videoFileId,
                                      String title,
                                      String description,
                                      Text caption,
                                      InlineKeyboardMarkup replyMarkup,
                                      InputMessageContent inputMessageContent) {
    super(id);
    this.videoFileId = Objects.requireNonNull(videoFileId);
    this.title = Objects.requireNonNull(title);
    this.description = description;
    this.caption = caption != null ? caption.toHtmlString() : null;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultCachedVideo(String id, String videoFileId, String title) {
    this(id, videoFileId, title, null, null, null, null);
  }


  /**
   * Getter for property {@link #videoFileId}.
   *
   * @return value for property {@link #videoFileId}
   */
  public String getVideoFileId() {
    return videoFileId;
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
   * Getter for property {@link #description}.
   *
   * @return optional value for property {@link #description}
   */
  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
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
