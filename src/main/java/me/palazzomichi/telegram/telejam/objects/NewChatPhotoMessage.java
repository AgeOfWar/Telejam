package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object represents a message.
 *
 * @author Michi Palazzo
 */
public class NewChatPhotoMessage extends Message {

  static final String NEW_CHAT_PHOTO_FIELD = "new_chat_photo";

  /**
   * This message chat photo was change to this value.
   */
  @SerializedName(NEW_CHAT_PHOTO_FIELD)
  private final PhotoSize[] newChatPhoto;


  public NewChatPhotoMessage(long id,
                             User sender,
                             long date,
                             Chat chat,
                             PhotoSize[] newChatPhoto) {
    super(id, sender, date, chat, null, null, null);
    this.newChatPhoto = Objects.requireNonNull(newChatPhoto);
  }


  /**
   * Getter for property {@link #newChatPhoto}.
   *
   * @return value for property {@link #newChatPhoto}
   */
  public PhotoSize[] getNewChatPhoto() {
    return newChatPhoto;
  }

}
