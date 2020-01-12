package io.github.ageofwar.telejam.chats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This class represents a group chat.
 *
 * @author Michi Palazzo
 */
public class Group extends Chat {
  
  static final String TITLE_FIELD = "title";
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "group";
  
  /**
   * Title of the group.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;
  
  
  public Group(long id, String title, boolean allAdmins) {
    super(id);
    this.title = Objects.requireNonNull(title);
  }
  
  
  @Override
  public String toUrl() {
    return "https://t.me/c/" + getId();
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
  
}
