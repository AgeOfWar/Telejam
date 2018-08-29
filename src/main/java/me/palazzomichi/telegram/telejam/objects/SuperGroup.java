package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This class represents a supergroup.
 *
 * @author Michi Palazzo
 */
public class SuperGroup extends Chat {
  
  static final String TITLE_FIELD = "title";
  static final String USERNAME_FIELD = "username";
  static final String DESCRIPTION_FIELD = "description";
  static final String INVITE_LINK_FIELD = "invite_link";
  static final String PINNED_MESSAGE_FIELD = "pinned_message";
  static final String STICKER_SET_NAME_FIELD = "sticker_set_name";
  static final String CAN_SET_STICKER_SET_FIELD = "can_set_sticker_set";
  
  @Expose
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
