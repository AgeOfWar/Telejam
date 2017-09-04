package me.palazzomichi.telegram.telejam.objects.chats;

import com.google.gson.annotations.SerializedName;
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
  static final String DESCRIPTION_FIELD = "description";
  static final String INVITE_LINK_FIELD = "invite_link";

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

  /**
   * Description, for supergroups and channel chats.
   * Returned only in {@link me.palazzomichi.telegram.telejam.methods.GetChat}.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private String description;

  /**
   * Chat invite link, for supergroups and channel chats.
   * Returned only in {@link me.palazzomichi.telegram.telejam.methods.GetChat}.
   */
  @SerializedName(INVITE_LINK_FIELD)
  private String inviteLink;


  public Chat(long id, ChatPhoto photo, String description, String inviteLink) {
    this.id = id;
    this.photo = photo;
    this.description = description;
    this.inviteLink = inviteLink;
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

  /**
   * Getter for property {@link #description}.
   *
   * @return optional value for property {@link #description}
   */
  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }

  /**
   * Getter for property {@link #inviteLink}.
   *
   * @return optional value for property {@link #inviteLink}
   */
  public Optional<String> getInviteLink() {
    return Optional.ofNullable(inviteLink);
  }

}
