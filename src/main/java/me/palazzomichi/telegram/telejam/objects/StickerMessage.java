package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object represents a sticker message.
 *
 * @author Michi Palazzo
 */
public class StickerMessage extends Message {

  static final String STICKER_FIELD = "sticker";

  /**
   * Information about the sticker.
   */
  @SerializedName(STICKER_FIELD)
  private Sticker sticker;


  public StickerMessage(long id,
                        User sender,
                        long date,
                        Chat chat,
                        Message replyToMessage,
                        String authorSignature,
                        Sticker sticker) {
    super(id, sender, date, chat, replyToMessage, null, authorSignature);
    this.sticker = Objects.requireNonNull(sticker);
  }


  /**
   * Getter for property {@link #sticker}.
   *
   * @return value for property {@link #sticker}
   */
  public Sticker getSticker() {
    return sticker;
  }

}
