package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a link to a photo. By default, this photo will be sent by
 * the user with optional caption. Alternatively, you can use {@link #inputMessageContent}
 * to send a message with the specified content instead of the photo.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultPhoto extends InlineQueryResult {

  static final String PHOTO_URL_FIELD = "photo_url";
  static final String THUMBNAIL_URL_FIELD = "thumb_url";
  static final String PHOTO_WIDTH_FIELD = "photo_width";
  static final String PHOTO_HEIGHT_FIELD = "photo_height";
  static final String TITLE_FIELD = "title";
  static final String DESCRIPTION_FIELD = "description";
  static final String CAPTION_FIELD = "caption";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String PARSE_MODE_FIELD = "parse_mode";

  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "photo";
  
  @Expose
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";

  /**
   * A valid URL of the photo.
   * Photo must be in jpeg format.
   * Photo size must not exceed 5MB.
   */
  @SerializedName(PHOTO_URL_FIELD)
  private String photoUrl;

  /**
   * URL of the thumbnail for the photo.
   */
  @SerializedName(THUMBNAIL_URL_FIELD)
  private String thumbnailUrl;

  /**
   * Width of the photo.
   */
  @SerializedName(PHOTO_WIDTH_FIELD)
  private Integer photoWidth;

  /**
   * Height of the photo.
   */
  @SerializedName(PHOTO_HEIGHT_FIELD)
  private Integer photoHeight;

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
   * Caption of the photo to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the photo.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;


  public InlineQueryResultPhoto(String id,
                                String photoUrl,
                                String thumbnailUrl,
                                Integer photoWidth,
                                Integer photoHeight,
                                String title,
                                String description,
                                Text caption,
                                InlineKeyboardMarkup replyMarkup,
                                InputMessageContent inputMessageContent) {
    super(id);
    this.photoUrl = Objects.requireNonNull(photoUrl);
    this.thumbnailUrl = Objects.requireNonNull(thumbnailUrl);
    this.photoWidth = photoWidth;
    this.photoHeight = photoHeight;
    this.title = title;
    this.description = description;
    this.caption = caption != null ? caption.toHtmlString() : null;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultPhoto(String id, String photoUrl, String thumbnailUrl) {
    this(id, photoUrl, thumbnailUrl, null, null, null, null, null, null, null);
  }


  /**
   * Getter for property {@link #photoUrl}.
   *
   * @return value for property {@link #photoUrl}
   */
  public String getPhotoUrl() {
    return photoUrl;
  }

  /**
   * Getter for property {@link #thumbnailUrl}.
   *
   * @return value for property {@link #thumbnailUrl}
   */
  public String getThumbnailUrl() {
    return thumbnailUrl;
  }
  
  /**
   * Getter for property {@link #photoWidth}.
   *
   * @return optional value for property {@link #photoWidth}
   */
  public OptionalInt getPhotoWidth() {
    return photoWidth == null ? OptionalInt.empty() : OptionalInt.of(photoWidth);
  }

  /**
   * Getter for property {@link #photoHeight}.
   *
   * @return optional value for property {@link #photoHeight}
   */
  public OptionalInt getPhotoHeight() {
    return photoHeight == null ? OptionalInt.empty() : OptionalInt.of(photoHeight);
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
