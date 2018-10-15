package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound) stored on
 * the Telegram servers. By default, this animated MPEG-4 file will be sent by the user
 * with an optional caption. Alternatively, you can use {@link #inputMessageContent} to send a
 * message with the specified content instead of the animation.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultCachedMpeg4Gif extends InlineQueryResult {

  static final String MPEG4_FILE_ID_FIELD = "mpeg4_file_id";
  static final String TITLE_FIELD = "title";
  static final String CAPTION_FIELD = "caption";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String PARSE_MODE_FIELD = "parse_mode";

  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "mpeg4_gif";
  
  @Expose
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";

  /**
   * A valid file identifier for the MP4 file.
   */
  @SerializedName(MPEG4_FILE_ID_FIELD)
  private final String mpeg4FileId;

  /**
   * Title for the result.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;

  /**
   * Caption of the MPEG-4 file to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private final String caption;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private final InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the video animation.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private final InputMessageContent inputMessageContent;


  public InlineQueryResultCachedMpeg4Gif(String id,
                                         String mpeg4FileId,
                                         String title,
                                         Text caption,
                                         InlineKeyboardMarkup replyMarkup,
                                         InputMessageContent inputMessageContent) {
    super(id);
    this.mpeg4FileId = Objects.requireNonNull(mpeg4FileId);
    this.title = title;
    this.caption = caption != null ? caption.toHtmlString() : null;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultCachedMpeg4Gif(String id, String mpeg4FileId) {
    this(id, mpeg4FileId, null, null, null, null);
  }


  /**
   * Getter for property {@link #mpeg4FileId}.
   *
   * @return value for property {@link #mpeg4FileId}
   */
  public String getMpeg4FileId() {
    return mpeg4FileId;
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
