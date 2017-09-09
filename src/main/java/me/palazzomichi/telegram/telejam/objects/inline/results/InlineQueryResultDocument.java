package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

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

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "document";

  /**
   * Title for the result.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Caption of the document to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;

  /**
   * A valid URL for the file.
   */
  @SerializedName(DOCUMENT_URL_FIELD)
  private String documentUrl;

  /**
   * Mime type of the content of the file, either “application/pdf” or “application/zip”.
   */
  @SerializedName(MIME_TYPE_FIELD)
  private String mimeType;

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
   * Content of the message to be sent instead of the file.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;

  /**
   * URL of the thumbnail (jpeg only) for the file.
   */
  @SerializedName(THUMB_URL_FIELD)
  private String thumbUrl;

  /**
   * Thumbnail width.
   */
  @SerializedName(THUMB_WIDTH_FIELD)
  private Integer thumbWidth;

  /**
   * Thumbnail height.
   */
  @SerializedName(THUMB_HEIGHT_FIELD)
  private Integer thumbHeight;


  public InlineQueryResultDocument(String id,
                                   String title,
                                   String caption,
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
    this.caption = caption;
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
   * Getter for property {@link #documentUrl}.
   *
   * @return value for property {@link #documentUrl}
   */
  public String getDocumentUrl() {
    return documentUrl;
  }

  /**
   * Setter for property {@link #documentUrl}.
   *
   * @param documentUrl value for property {@link #documentUrl}
   */
  public void setDocumentUrl(String documentUrl) {
    this.documentUrl = Objects.requireNonNull(documentUrl);
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

  /**
   * Getter for property {@link #thumbUrl}.
   *
   * @return optional value for property {@link #thumbUrl}
   */
  public Optional<String> getThumbUrl() {
    return Optional.ofNullable(thumbUrl);
  }

  /**
   * Setter for property {@link #thumbUrl}.
   *
   * @param thumbUrl value for property {@link #thumbUrl}
   */
  public void setThumbUrl(String thumbUrl) {
    this.thumbUrl = thumbUrl;
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
   * Setter for property {@link #thumbWidth}.
   *
   * @param thumbWidth value for property {@link #thumbWidth}
   */
  public void setThumbWidth(Integer thumbWidth) {
    this.thumbWidth = thumbWidth;
  }

  /**
   * Getter for property {@link #thumbHeight}.
   *
   * @return optional value for property {@link #thumbHeight}
   */
  public OptionalInt getThumbHeight() {
    return thumbHeight == null ? OptionalInt.empty() : OptionalInt.of(thumbHeight);
  }

  /**
   * Setter for property {@link #thumbHeight}.
   *
   * @param thumbHeight value for property {@link #thumbHeight}
   */
  public void setThumbHeight(Integer thumbHeight) {
    this.thumbHeight = thumbHeight;
  }

}
