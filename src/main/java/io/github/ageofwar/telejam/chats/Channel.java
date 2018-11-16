package io.github.ageofwar.telejam.chats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This class represents a channel.
 *
 * @author Michi Palazzo
 */
public class Channel extends Chat {
  
  static final String TITLE_FIELD = "title";
  static final String USERNAME_FIELD = "username";
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "channel";
  
  /**
   * The title of the channel.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;
  
  /**
   * The username of the channel.
   */
  @SerializedName(USERNAME_FIELD)
  private final String username;
  
  
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
  
}
