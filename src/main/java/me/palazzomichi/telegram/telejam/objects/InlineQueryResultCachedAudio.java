package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a link to an mp3 audio file stored on the Telegram servers.
 * By default, this audio file will be sent by the user.
 * Alternatively, you can use {@link #inputMessageContent} to send a message
 * with the specified content instead of the audio.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultCachedAudio extends InlineQueryResult {

  static final String AUDIO_FILE_ID_FIELD = "audio_file_id";
  static final String CAPTION_FIELD = "caption";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String PARSE_MODE_FIELD = "parse_mode";
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "audio";
  
  @Expose
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";
  
  /**
   * A valid file identifier for the audio file.
   */
  @SerializedName(AUDIO_FILE_ID_FIELD)
  private String audioFileId;

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
   * Content of the message to be sent instead of the audio.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;


  public InlineQueryResultCachedAudio(String id,
                                      String audioFileId,
                                      Text caption,
                                      InlineKeyboardMarkup replyMarkup,
                                      InputMessageContent inputMessageContent) {
    super(id);
    this.audioFileId = Objects.requireNonNull(audioFileId);
    this.caption = caption != null ? caption.toHtmlString() : null;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultCachedAudio(String id, String audioFileId) {
    this(id, audioFileId, null, null, null);
  }


  /**
   * Getter for property {@link #audioFileId}.
   *
   * @return value for property {@link #audioFileId}
   */
  public String getAudioFileId() {
    return audioFileId;
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
