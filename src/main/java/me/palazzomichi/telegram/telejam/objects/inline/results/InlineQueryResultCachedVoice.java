package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

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

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "voice";

  /**
   * A valid file identifier for the voice message.
   */
  @SerializedName(VOICE_FILE_ID_FIELD)
  private String voiceFileId;

  /**
   * Voice message title.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Caption, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the voice message.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;


  public InlineQueryResultCachedVoice(String id,
                                      String voiceFileId,
                                      String title,
                                      String caption,
                                      InlineKeyboardMarkup replyMarkup,
                                      InputMessageContent inputMessageContent) {
    super(id);
    this.voiceFileId = Objects.requireNonNull(voiceFileId);
    this.title = Objects.requireNonNull(title);
    this.caption = caption;
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
   * Setter for property {@link #voiceFileId}.
   *
   * @param voiceFileId value for property {@link #voiceFileId}
   */
  public void setVoiceFileId(String voiceFileId) {
    this.voiceFileId = Objects.requireNonNull(voiceFileId);
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
