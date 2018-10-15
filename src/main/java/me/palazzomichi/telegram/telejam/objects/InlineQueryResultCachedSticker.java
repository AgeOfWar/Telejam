package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a link to a sticker stored on the Telegram servers.
 * By default, this sticker will be sent by the user. Alternatively,
 * you can use {@link #inputMessageContent} to send a message with
 * the specified content instead of the sticker.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultCachedSticker extends InlineQueryResult {

  static final String STICKER_FILE_ID_FIELD = "sticker_file_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";

  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "sticker";

  /**
   * A valid file identifier of the sticker.
   */
  @SerializedName(STICKER_FILE_ID_FIELD)
  private final String stickerFileId;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private final InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the sticker.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private final InputMessageContent inputMessageContent;


  public InlineQueryResultCachedSticker(String id,
                                        String stickerFileId,
                                        InlineKeyboardMarkup replyMarkup,
                                        InputMessageContent inputMessageContent) {
    super(id);
    this.stickerFileId = Objects.requireNonNull(stickerFileId);
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
  }

  public InlineQueryResultCachedSticker(String id, String stickerFileId) {
    this(id, stickerFileId, null, null);
  }


  /**
   * Getter for property {@link #stickerFileId}.
   *
   * @return value for property {@link #stickerFileId}
   */
  public String getStickerFileId() {
    return stickerFileId;
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
