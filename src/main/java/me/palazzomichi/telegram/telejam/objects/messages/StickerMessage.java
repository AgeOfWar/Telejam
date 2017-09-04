package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Sticker;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

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
                        User forwardMessageSender,
                        Chat forwardMessageChat,
                        Long forwardMessageId,
                        Long forwardMessageDate,
                        Message replyToMessage,
                        Long editDate,
                        String authorSignature,
                        String forwardSignature,
                        Sticker sticker) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
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
