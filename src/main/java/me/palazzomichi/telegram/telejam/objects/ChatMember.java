package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object contains information about one member of a chat.
 *
 * @author Michi Palazzo
 */
public class ChatMember implements TelegramObject {

  static final String USER_FIELD = "user";
  static final String STATUS_FIELD = "status";
  static final String UNTIL_DATE_FIELD = "until_date";
  static final String CAN_BE_EDITED_FIELD = "can_be_edited";
  static final String CAN_CHANGE_INFORMATION_FIELD = "can_change_info";
  static final String CAN_POST_MESSAGES_FIELD = "can_post_messages";
  static final String CAN_EDIT_MESSAGES_FIELD = "can_edit_messages";
  static final String CAN_DELETE_MESSAGES_FIELD = "can_delete_messages";
  static final String CAN_INVITE_USERS_FIELD = "can_invite_users";
  static final String CAN_RESTRICT_MEMBERS_FIELD = "can_restrict_members";
  static final String CAN_PIN_MESSAGES_FIELD = "can_pin_messages";
  static final String CAN_PROMOTE_MEMBERS_FIELD = "can_promote_members";
  static final String CAN_SEND_MESSAGES_FIELD = "can_send_messages";
  static final String CAN_SEND_MEDIA_MESSAGES_FIELD = "can_send_media_messages";
  static final String CAN_SEND_OTHER_MESSAGES_FIELD = "can_send_other_messages";
  static final String CAN_ADD_WEB_PAGE_PREVIEWS_FIELD = "can_add_web_page_previews";

  /**
   * Information about the user.
   */
  @SerializedName(USER_FIELD)
  private User user;

  /**
   * The member's status in the chat.
   * Can be "creator", "administrator", "member",  "restricted", "left" or "kicked".
   */
  @SerializedName(STATUS_FIELD)
  private Status status;

  /**
   * if member is restricted or kicked , date when restrictions will
   * be lifted for this user, unix time, else <code>-1</code>.
   */
  @SerializedName(UNTIL_DATE_FIELD)
  private long untilDate = -1;

  /**
   * True, if the bot is allowed to edit administrator privileges of that user.
   */
  @SerializedName(CAN_BE_EDITED_FIELD)
  private boolean canBeEdited = false;

  /**
   * True, if the member can change the chat title, photo and other settings.
   */
  @SerializedName(CAN_CHANGE_INFORMATION_FIELD)
  private boolean canChangeInformation = false;

  /**
   * True, if the member can post in the channel, channels only.
   */
  @SerializedName(CAN_POST_MESSAGES_FIELD)
  private boolean canPostMessages = false;

  /**
   * True, if the member can edit messages of other users, channels only.
   */
  @SerializedName(CAN_EDIT_MESSAGES_FIELD)
  private boolean canEditMessages = false;

  /**
   * True, if the member can delete messages of other users.
   */
  @SerializedName(CAN_DELETE_MESSAGES_FIELD)
  private boolean canDeleteMessages = false;

  /**
   * True, if the member can invite new users to the chat.
   */
  @SerializedName(CAN_INVITE_USERS_FIELD)
  private boolean canInviteUsers = false;

  /**
   * True, if the member can restrict, ban or unban chat members.
   */
  @SerializedName(CAN_RESTRICT_MEMBERS_FIELD)
  private boolean canRestrictUsers = false;

  /**
   * True, if the member can pin messages, supergroups only.
   */
  @SerializedName(CAN_PIN_MESSAGES_FIELD)
  private boolean canPinMessages = false;

  /**
   * True, if the member can add new administrators with a
   * subset of his own privileges or demote administrators that
   * it has promoted, directly or indirectly (promoted by
   * administrators that were appointed by the bot).
   */
  @SerializedName(CAN_PROMOTE_MEMBERS_FIELD)
  private boolean canPromoteMembers = false;

  /**
   * True, if the user can send text messages, contacts, locations and venues.
   */
  @SerializedName(CAN_SEND_MESSAGES_FIELD)
  private boolean canSendMessages = true;

  /**
   * True, if the user can send audios, documents, photos, videos,
   * video notes and voice notes, implies {@link #canSendMessages}.
   */
  @SerializedName(CAN_SEND_MEDIA_MESSAGES_FIELD)
  private boolean canSendMediaMessages = true;

  /**
   * True, if the user can send animations, games, stickers and
   * use inline bots, implies {@link #canSendMediaMessages}.
   */
  @SerializedName(CAN_SEND_OTHER_MESSAGES_FIELD)
  private boolean canSendOtherMessages = true;

  /**
   * True, if user may add web page previews to his messages, implies {@link #canSendMessages}.
   */
  @SerializedName(CAN_ADD_WEB_PAGE_PREVIEWS_FIELD)
  private boolean canAddWebPagePreviews = true;


  public ChatMember(User user,
                    Status status,
                    long untilDate,
                    boolean canBeEdited,
                    boolean canChangeInformation,
                    boolean canPostMessages,
                    boolean canEditMessages,
                    boolean canDeleteMessages,
                    boolean canInviteUsers,
                    boolean canRestrictUsers,
                    boolean canPinMessages,
                    boolean canPromoteMembers,
                    boolean canSendMessages,
                    boolean canSendMediaMessages,
                    boolean canSendOtherMessages,
                    boolean canAddWebPagePreviews) {
    this.user = Objects.requireNonNull(user);
    this.status = Objects.requireNonNull(status);
    this.untilDate = untilDate;
    this.canBeEdited = canBeEdited;
    this.canChangeInformation = canChangeInformation;
    this.canPostMessages = canPostMessages;
    this.canEditMessages = canEditMessages;
    this.canDeleteMessages = canDeleteMessages;
    this.canInviteUsers = canInviteUsers;
    this.canRestrictUsers = canRestrictUsers;
    this.canPinMessages = canPinMessages;
    this.canPromoteMembers = canPromoteMembers;
    this.canSendMessages = canSendMessages;
    this.canSendMediaMessages = canSendMediaMessages;
    this.canSendOtherMessages = canSendOtherMessages;
    this.canAddWebPagePreviews = canAddWebPagePreviews;
  }

  public ChatMember(User user, Status status) {
    this.user = user;
    this.status = status;
  }
  
  
  /**
   * Returns whether or not this member is an administrator or a creator.
   *
   * @return <code>true</code> if this member is an administrator, <code>false</code> otherwise
   */
  public boolean isAdmin() {
    return status == Status.CREATOR || status == Status.ADMINISTRATOR;
  }

  /**
   * Getter for property {@link #user}.
   *
   * @return value for property {@link #user}
   */
  public User getUser() {
    return user;
  }

  /**
   * Getter for property {@link #status}.
   *
   * @return value for property {@link #status}
   */
  public Status getStatus() {
    return status;
  }

  /**
   * Getter for property {@link #untilDate}.
   *
   * @return value for property {@link #untilDate}
   */
  public long getUntilDate() {
    return untilDate;
  }

  /**
   * Getter for property {@link #canBeEdited}.
   *
   * @return value for property {@link #canBeEdited}
   */
  public boolean canBeEdited() {
    return canBeEdited;
  }

  /**
   * Getter for property {@link #canChangeInformation}.
   *
   * @return value for property {@link #canChangeInformation}
   */
  public boolean canChangeInformation() {
    return canChangeInformation;
  }

  /**
   * Getter for property {@link #canPostMessages}.
   *
   * @return value for property {@link #canPostMessages}
   */
  public boolean canPostMessages() {
    return canPostMessages;
  }

  /**
   * Getter for property {@link #canEditMessages}.
   *
   * @return value for property {@link #canEditMessages}
   */
  public boolean canEditMessages() {
    return canEditMessages;
  }

  /**
   * Getter for property {@link #canDeleteMessages}.
   *
   * @return value for property {@link #canDeleteMessages}
   */
  public boolean canDeleteMessages() {
    return canDeleteMessages;
  }

  /**
   * Getter for property {@link #canInviteUsers}.
   *
   * @return value for property {@link #canInviteUsers}
   */
  public boolean canInviteUsers() {
    return canInviteUsers;
  }

  /**
   * Getter for property {@link #canRestrictUsers}.
   *
   * @return value for property {@link #canRestrictUsers}
   */
  public boolean canRestrictUsers() {
    return canRestrictUsers;
  }

  /**
   * Getter for property {@link #canPinMessages}.
   *
   * @return value for property {@link #canPinMessages}
   */
  public boolean canPinMessages() {
    return canPinMessages;
  }

  /**
   * Getter for property {@link #canPromoteMembers}.
   *
   * @return value for property {@link #canPromoteMembers}
   */
  public boolean canPromoteMembers() {
    return canPromoteMembers;
  }

  /**
   * Getter for property {@link #canSendMessages}.
   *
   * @return value for property {@link #canSendMessages}
   */
  public boolean canSendMessages() {
    return canSendMessages;
  }

  /**
   * Getter for property {@link #canSendMediaMessages}.
   *
   * @return value for property {@link #canSendMediaMessages}
   */
  public boolean canSendMediaMessages() {
    return canSendMediaMessages;
  }

  /**
   * Getter for property {@link #canSendOtherMessages}.
   *
   * @return value for property {@link #canSendOtherMessages}
   */
  public boolean canSendOtherMessages() {
    return canSendOtherMessages;
  }

  /**
   * Getter for property {@link #canAddWebPagePreviews}.
   *
   * @return value for property {@link #canAddWebPagePreviews}
   */
  public boolean canAddWebPagePreviews() {
    return canAddWebPagePreviews;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof ChatMember)) {
      return false;
    }
    
    ChatMember chatMember = (ChatMember) obj;
    return user.equals(chatMember.getUser());
  }
  
  @Override
  public int hashCode() {
    return user.hashCode();
  }
  
  public enum Status {
    @SerializedName("creator") CREATOR,
    @SerializedName("administrator") ADMINISTRATOR,
    @SerializedName("member") MEMBER,
    @SerializedName("restricted") RESTRICTED,
    @SerializedName("left") LEFT,
    @SerializedName("kicked") KICKED
  }

}
