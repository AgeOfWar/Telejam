package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.PhotoSize;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a photo message.
 *
 * @author Michi Palazzo
 */
public class PhotoMessage extends Message {

  static final String PHOTO_FIELD = "photo";
  static final String CAPTION_FIELD = "caption";

  /**
   * Optional.
   * Available sizes of the photo.
   */
  @SerializedName(PHOTO_FIELD)
  private PhotoSize[] photo;

  /**
   * Caption for the photo, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;


  public PhotoMessage(long id,
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
                      PhotoSize[] photo,
                      String caption) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.photo = Objects.requireNonNull(photo);
    this.caption = caption;
  }


  /**
   * Getter for property {@link #photo}.
   *
   * @return value for property {@link #photo}
   */
  public PhotoSize[] getPhoto() {
    return photo;
  }

  /**
   * Getter for property {@link #caption}.
   *
   * @return optional value for property {@link #caption}
   */
  public Optional<String> getCaption() {
    return Optional.ofNullable(caption);
  }

}
