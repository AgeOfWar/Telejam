package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a link to a file.
 * By default, this file will be sent by the user with an optional caption.
 * Alternatively, you can use {@link #inputMessageContent} to send a message with
 * the specified content instead of the file. Currently, only .PDF and .ZIP
 * files can be sent using this method.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultDocument extends InlineQueryResult {

  static final String TITLE_FIELD = "title";
  static final String CAPTION_FIELD = "caption";
  static final String DOCUMENT_URL_FIELD = "document_url";
  static final String MIME_TYPE_FIELD = "mime_type";
  static final String DESCRIPTION_FIELD = "description";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String THUMB_URL_FIELD = "thumb_url";
  static final String THUMB_WIDTH_FIELD = "thumb_width";
  static final String THUMB_HEIGHT_FIELD = "thumb_height";
  static final String PARSE_MODE_FIELD = "parse_mode";

  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "document";
  
  @Expose
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";
  
  /**
   * Title for the result.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;

  /**
   * Caption of the document to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private final String caption;

  /**
   * A valid URL for the file.
   */
  @SerializedName(DOCUMENT_URL_FIELD)
  private final String documentUrl;

  /**
   * Mime type of the content of the file, either "application/pdf" or "application/zip".
   */
  @SerializedName(MIME_TYPE_FIELD)
  private final String mimeType;

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
   * Content of the message to be sent instead of the file.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private final InputMessageContent inputMessageContent;

  /**
   * URL of the thumbnail (jpeg only) for the file.
   */
  @SerializedName(THUMB_URL_FIELD)
  private final String thumbUrl;

  /**
   * Thumbnail width.
   */
  @SerializedName(THUMB_WIDTH_FIELD)
  private final Integer thumbWidth;

  /**
   * Thumbnail height.
   */
  @SerializedName(THUMB_HEIGHT_FIELD)
  private final Integer thumbHeight;


  public InlineQueryResultDocument(String id,
                                   String title,
                                   Text caption,
                                   String documentUrl,
                                   String mimeType,
                                   String description,
                                   InlineKeyboardMarkup replyMarkup,
                                   InputMessageContent inputMessageContent,
                                   String thumbUrl,
                                   Integer thumbWidth,
                                   Integer thumbHeight) {
    super(id);
    this.title = Objects.requireNonNull(title);
    this.caption = caption != null ? caption.toHtmlString() : null;
    this.documentUrl = Objects.requireNonNull(documentUrl);
    this.mimeType = Objects.requireNonNull(mimeType);
    this.description = description;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
    this.thumbUrl = thumbUrl;
    this.thumbWidth = thumbWidth;
    this.thumbHeight = thumbHeight;
  }

  public InlineQueryResultDocument(String id, String title, String documentUrl, String mimeType) {
    this(id, title, null, documentUrl, mimeType, null, null, null, null, null, null);
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
   * Getter for property {@link #documentUrl}.
   *
   * @return value for property {@link #documentUrl}
   */
  public String getDocumentUrl() {
    return documentUrl;
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
  
  /**
   * Getter for property {@link #thumbUrl}.
   *
   * @return optional value for property {@link #thumbUrl}
   */
  public Optional<String> getThumbUrl() {
    return Optional.ofNullable(thumbUrl);
  }
  
  /**
   * Getter for property {@link #thumbWidth}.
   *
   * @return optional value for property {@link #thumbWidth}
   */
  public OptionalInt getThumbWidth() {
    return thumbWidth == null ? OptionalInt.empty() : OptionalInt.of(thumbWidth);
  }
  
  /**
   * Getter for property {@link #thumbHeight}.
   *
   * @return optional value for property {@link #thumbHeight}
   */
  public OptionalInt getThumbHeight() {
    return thumbHeight == null ? OptionalInt.empty() : OptionalInt.of(thumbHeight);
  }

}
