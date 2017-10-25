package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.MessageEntity;
import me.palazzomichi.telegram.telejam.objects.PhotoSize;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.util.text.Text;

import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a photo message.
 *
 * @author Michi Palazzo
 */
public class PhotoMessage extends Message implements Captioned, Forwardable {

  static final String PHOTO_FIELD = "photo";
  static final String CAPTION_FIELD = "caption";
  static final String CAPTION_ENTITIES = "caption_entities";

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
  
  /**
   * Special entities like usernames, URLs, bot
   * commands, etc. that appear in the caption.
   */
  @SerializedName(CAPTION_ENTITIES)
  private MessageEntity[] captionEntities;


  public PhotoMessage(long id,
                      User sender,
                      long date,
                      Chat chat,
                      Message replyToMessage,
                      Long editDate,
                      String authorSignature,
                      PhotoSize[] photo,
                      String caption,
                      MessageEntity[] captionEntities) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.photo = Objects.requireNonNull(photo);
    this.caption = caption;
    this.captionEntities = captionEntities;
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
   * Returns the text of the caption.
   *
   * @return text of the optional caption
   */
  public Optional<Text> getCaption() {
    if (caption == null) {
      return Optional.empty();
    }
    
    return Optional.of(
        new Text(caption, captionEntities == null ? new MessageEntity[0] : captionEntities)
    );
  }

}
