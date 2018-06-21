package me.palazzomichi.telegram.telejam.objects;

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
