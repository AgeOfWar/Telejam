package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a link to a photo. By default, this photo will be sent by
 * the user with optional caption. Alternatively, you can use input_message_content
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

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "photo";

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
                                String caption,
                                InlineKeyboardMarkup replyMarkup,
                                InputMessageContent inputMessageContent) {
    super(id);
    this.photoUrl = Objects.requireNonNull(photoUrl);
    this.thumbnailUrl = Objects.requireNonNull(thumbnailUrl);
    this.photoWidth = photoWidth;
    this.photoHeight = photoHeight;
    this.title = title;
    this.description = description;
    this.caption = caption;
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
   * Setter for property {@link #photoUrl}.
   *
   * @param photoUrl value for property {@link #photoUrl}
   */
  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = Objects.requireNonNull(photoUrl);
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
   * Setter for property {@link #thumbnailUrl}.
   *
   * @param thumbnailUrl value for property {@link #thumbnailUrl}
   */
  public void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = Objects.requireNonNull(thumbnailUrl);
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
   * Setter for property {@link #photoWidth}.
   *
   * @param photoWidth value for property {@link #photoWidth}
   */
  public void setPhotoWidth(Integer photoWidth) {
    this.photoWidth = photoWidth;
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
   * Setter for property {@link #photoHeight}.
   *
   * @param photoHeight value for property {@link #photoHeight}
   */
  public void setPhotoHeight(Integer photoHeight) {
    this.photoHeight = photoHeight;
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
