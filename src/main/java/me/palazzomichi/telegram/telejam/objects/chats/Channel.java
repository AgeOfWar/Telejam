package me.palazzomichi.telegram.telejam.objects.chats;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.ChatPhoto;

import java.util.Objects;
import java.util.Optional;

/**
 * This class represents a channel.
 *
 * @author Michi Palazzo
 */
public class Channel extends Chat {
  
  static final String TITLE_FIELD = "title";
  static final String USERNAME_FIELD = "username";
  static final String DESCRIPTION_FIELD = "description";
  static final String INVITE_LINK_FIELD = "invite_link";
  
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "channel";
  
  /**
   * The title of the channel.
   */
  @SerializedName(TITLE_FIELD)
  private String title;
  
  /**
   * The username of the channel.
   */
  @SerializedName(USERNAME_FIELD)
  private String username;
  
  /**
   * Description of the channel.
   * Returned only in {@link me.palazzomichi.telegram.telejam.methods.GetChat}.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private String description;
  
  /**
   * Chat invite link of the channel.
   * Returned only in {@link me.palazzomichi.telegram.telejam.methods.GetChat}.
   */
  @SerializedName(INVITE_LINK_FIELD)
  private String inviteLink;
  
  
  public Channel(long id,
                 ChatPhoto photo,
                 String title,
                 String username,
                 String description,
                 String inviteLink) {
    super(id, photo);
    this.title = Objects.requireNonNull(title);
    this.username = Objects.requireNonNull(username);
    this.description = description;
    this.inviteLink = inviteLink;
  }
  
  public Channel(long id, String title, String username) {
    super(id);
    this.title = Objects.requireNonNull(title);
    this.username = Objects.requireNonNull(username);
  }
  
  
  /**
   * Getter for property {@link #title}.
   *
   * @return value for property {@link #title}
   */
  @Override
  public String getTitle() {
    return title;
  }
  
  /**
   * Getter for property {@link #username}.
   *
   * @return value for property {@link #username}
   */
  public String getUsername() {
    return username;
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
