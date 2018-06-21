package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a link to a photo stored on the Telegram servers.
 * By default, this photo will be sent by the user with an optional caption.
 * Alternatively, you can use {@link #inputMessageContent} to send a message with
 * the specified content instead of the photo.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultCachedPhoto extends InlineQueryResult {

  static final String PHOTO_FILE_ID_FIELD = "photo_file_id";
  static final String TITLE_FIELD = "title";
  static final String DESCRIPTION_FIELD = "description";
  static final String CAPTION_FIELD = "caption";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String PARSE_MODE_FIELD = "parse_mode";

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "photo";
  
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";

  /**
   * A valid file identifier of the photo.
   */
  @SerializedName(PHOTO_FILE_ID_FIELD)
  private String photoFileId;

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


  public InlineQueryResultCachedPhoto(String id,
                                      String photoFileId,
                                      String title,
                                      String description,
                                      Text caption,
                                      InlineKeyboardMarkup replyMarkup,
                                      InputMessageContent inputMessageContent) {
    super(id);
    this.photoFileId = Objects.requireNonNull(photoFileId);
    this.title = title;
    this.description = description;
    this.caption = caption != null ? caption.toHtmlString() : null;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultCachedPhoto(String id, String photoFileId) {
    this(id, photoFileId, null, null, null, null, null);
  }


  /**
   * Getter for property {@link #photoFileId}.
   *
   * @return value for property {@link #photoFileId}
   */
  public String getPhotoFileId() {
    return photoFileId;
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
