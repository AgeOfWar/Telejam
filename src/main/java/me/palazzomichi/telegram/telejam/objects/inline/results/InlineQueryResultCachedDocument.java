package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a link to a file stored on the Telegram servers.
 * By default, this file will be sent by the user with an optional caption.
 * Alternatively, you can use {@link #inputMessageContent} to send a message with
 * the specified content instead of the file.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultCachedDocument extends InlineQueryResult {

  static final String TITLE_FIELD = "title";
  static final String DOCUMENT_FILE_ID_FIELD = "document_file_id";
  static final String DESCRIPTION_FIELD = "description";
  static final String CAPTION_FIELD = "caption";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "document";

  /**
   * Title for the result.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * A valid file identifier for the file.
   */
  @SerializedName(DOCUMENT_FILE_ID_FIELD)
  private String documentFileId;

  /**
   * Short description of the result.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private String description;

  /**
   * Caption of the document to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;

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


  public InlineQueryResultCachedDocument(String id,
                                         String title,
                                         String documentFileId,
                                         String description,
                                         String caption,
                                         InlineKeyboardMarkup replyMarkup,
                                         InputMessageContent inputMessageContent) {
    super(id);
    this.title = Objects.requireNonNull(title);
    this.documentFileId = Objects.requireNonNull(documentFileId);
    this.description = description;
    this.caption = caption;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultCachedDocument(String id, String title, String documentFileId) {
    this(id, title, documentFileId, null, null, null, null);
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
   * Getter for property {@link #documentFileId}.
   *
   * @return value for property {@link #documentFileId}
   */
  public String getDocumentFileId() {
    return documentFileId;
  }

  /**
   * Setter for property {@link #documentFileId}.
   *
   * @param documentFileId value for property {@link #documentFileId}
   */
  public void setDocumentFileId(String documentFileId) {
    this.documentFileId = Objects.requireNonNull(documentFileId);
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
