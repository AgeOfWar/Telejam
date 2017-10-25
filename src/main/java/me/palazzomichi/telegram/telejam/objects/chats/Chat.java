package me.palazzomichi.telegram.telejam.objects.chats;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.ChatPhoto;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;

import java.util.Optional;

/**
 * This class represents a chat.
 *
 * @author Michi Palazzo
 * @see me.palazzomichi.telegram.telejam.methods.GetChat
 */
public abstract class Chat implements TelegramObject {

  static final String ID_FIELD = "id";
  static final String TYPE_FIELD = "type";
  static final String PHOTO_FIELD = "photo";

  /**
   * Unique identifier for this chat.
   */
  @SerializedName(ID_FIELD)
  private long id;

  /**
   * Chat photo.
   * Returned only in {@link me.palazzomichi.telegram.telejam.methods.GetChat}.
   */
  @SerializedName(PHOTO_FIELD)
  private ChatPhoto photo;
  
  
  public Chat(long id, ChatPhoto photo) {
    this.id = id;
    this.photo = photo;
  }

  public Chat(long id) {
    this.id = id;
  }


  /**
   * Getter for property {@link #id}.
   *
   * @return value for property {@link #id}
   */
  public long getId() {
    return id;
  }

  /**
   * Getter for property {@link #photo}.
   *
   * @return optional value for property {@link #photo}
   */
  public Optional<ChatPhoto> getPhoto() {
    return Optional.ofNullable(photo);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof Chat)) {
      return false;
    }
    
    Chat chat = (Chat) obj;
    return id == chat.getId();
  }
  
  @Override
  public int hashCode() {
    return Long.hashCode(id);
  }

}
