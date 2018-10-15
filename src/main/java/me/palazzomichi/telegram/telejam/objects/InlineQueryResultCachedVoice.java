package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a link to a voice message stored on the Telegram servers.
 * By default, this voice message will be sent by the user.
 * Alternatively, you can use {@link #inputMessageContent} to send a message with
 * the specified content instead of the voice message.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultCachedVoice extends InlineQueryResult {

  static final String VOICE_FILE_ID_FIELD = "voice_file_id";
  static final String TITLE_FIELD = "title";
  static final String CAPTION_FIELD = "caption";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String PARSE_MODE_FIELD = "parse_mode";

  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "voice";
  
  @Expose
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";
  
  /**
   * A valid file identifier for the voice message.
   */
  @SerializedName(VOICE_FILE_ID_FIELD)
  private final String voiceFileId;

  /**
   * Voice message title.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;

  /**
   * Caption, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private final String caption;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private final InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the voice message.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private final InputMessageContent inputMessageContent;


  public InlineQueryResultCachedVoice(String id,
                                      String voiceFileId,
                                      String title,
                                      Text caption,
                                      InlineKeyboardMarkup replyMarkup,
                                      InputMessageContent inputMessageContent) {
    super(id);
    this.voiceFileId = Objects.requireNonNull(voiceFileId);
    this.title = Objects.requireNonNull(title);
    this.caption = caption != null ? caption.toHtmlString() : null;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultCachedVoice(String id, String voiceFileId, String title) {
    this(id, voiceFileId, title, null ,null, null);
  }


  /**
   * Getter for property {@link #voiceFileId}.
   *
   * @return value for property {@link #voiceFileId}
   */
  public String getVoiceFileId() {
    return voiceFileId;
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
