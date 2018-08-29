package me.palazzomichi.telegram.telejam.objects;

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
  static final String ALL_ADMINS_FIELD = "all_members_are_administrators";
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "group";
  
  /**
   * Title of the group.
   */
  @SerializedName(TITLE_FIELD)
  private String title;
  
  /**
   * True if a group has ‘All Members Are Admins’ enabled.
   */
  @SerializedName(ALL_ADMINS_FIELD)
  private boolean allAdmins;
  
  
  public Group(long id, String title, boolean allAdmins) {
    super(id);
    this.title = Objects.requireNonNull(title);
    this.allAdmins = allAdmins;
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
   * Getter for property {@link #allAdmins}.
   *
   * @return value for property {@link #allAdmins}
   */
  public boolean allMembersAreAdministrators() {
    return allAdmins;
  }
  
}
