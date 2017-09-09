package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a link to an animated GIF file stored on the Telegram servers.
 * By default, this animated GIF file will be sent by the user with an optional caption.
 * Alternatively, you can use {@link #inputMessageContent} to send a message with specified
 * content instead of the animation.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultCachedGif extends InlineQueryResult {

  static final String GIF_FILE_ID_FIELD = "gif_file_id";
  static final String TITLE_FIELD = "title";
  static final String CAPTION_FIELD = "caption";
  static final String REPLY_MARKUP_FIELD  = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "gif";

  /**
   * A valid file identifier for the GIF file.
   */
  @SerializedName(GIF_FILE_ID_FIELD)
  private String gifFileId;

  /**
   * Title for the result.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Caption of the GIF file to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the GIF animation.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;


  public InlineQueryResultCachedGif(String id,
                                    String gifFileId,
                                    String title,
                                    String caption,
                                    InlineKeyboardMarkup replyMarkup,
                                    InputMessageContent inputMessageContent) {
    super(id);
    this.gifFileId = Objects.requireNonNull(gifFileId);
    this.title = title;
    this.caption = caption;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultCachedGif(String id, String gifFileId) {
    this(id, gifFileId, null, null, null, null);
  }


  /**
   * Getter for property {@link #gifFileId}.
   *
   * @return value for property {@link #gifFileId}
   */
  public String getGifFileId() {
    return gifFileId;
  }

  /**
   * Setter for property {@link #gifFileId}.
   *
   * @param gifFileId value for property {@link #gifFileId}
   */
  public void setGifFileId(String gifFileId) {
    this.gifFileId = Objects.requireNonNull(gifFileId);
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
