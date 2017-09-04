package me.palazzomichi.telegram.telejam.objects.chats;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.messages.Message;

import java.util.Objects;
import java.util.Optional;

/**
 * This class represents a supergroup.
 *
 * @author Michi Palazzo
 */
public class SuperGroup extends Chat {

  static final String TITLE_FIELD = "title";
  static final String USERNAME_FIELD = "username";
  static final String PINNED_MESSAGE_FIELD = "pinned_message";

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "supergroup";

  /**
   * The title of the supergroup.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * The username of the supergroup.
   */
  @SerializedName(USERNAME_FIELD)
  private String username;

  /**
   * Pinned message, for supergroups.
   * Returned only in {@link me.palazzomichi.telegram.telejam.methods.GetChat}.
   */
  @SerializedName(PINNED_MESSAGE_FIELD)
  private Message pinnedMessage;


  public SuperGroup(long id,
                    ChatPhoto photo,
                    String description,
                    String inviteLink,
                    String title,
                    String username) {
    super(id, photo, description, inviteLink);
    this.title = Objects.requireNonNull(title);
    this.username = Objects.requireNonNull(username);
  }

  public SuperGroup(long id, String title, String username) {
    super(id);
    this.title = Objects.requireNonNull(title);
    this.username = Objects.requireNonNull(username);
  }


  /**
   * Getter for property {@link #title}.
   *
   * @return value for property {@link #title}
   */
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
   * Getter for property {@link #pinnedMessage}.
   *
   * @return optional value for property {@link #pinnedMessage}
   */
  public Optional<Message> getPinnedMessage() {
    return Optional.ofNullable(pinnedMessage);
  }

}
