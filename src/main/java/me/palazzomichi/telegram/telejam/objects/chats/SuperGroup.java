package me.palazzomichi.telegram.telejam.objects.chats;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.ChatPhoto;
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
  static final String DESCRIPTION_FIELD = "description";
  static final String INVITE_LINK_FIELD = "invite_link";
  static final String PINNED_MESSAGE_FIELD = "pinned_message";
  static final String STICKER_SET_NAME_FIELD = "sticker_set_name";
  static final String CAN_SET_STICKER_SET_FIELD = "can_set_sticker_set";
  
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
   * Description of the supergroup.
   * Returned only in {@link me.palazzomichi.telegram.telejam.methods.GetChat}.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private String description;
  
  /**
   * Chat invite link of the supergroup.
   * Returned only in {@link me.palazzomichi.telegram.telejam.methods.GetChat}.
   */
  @SerializedName(INVITE_LINK_FIELD)
  private String inviteLink;
  
  /**
   * Pinned message, for supergroups.
   * Returned only in {@link me.palazzomichi.telegram.telejam.methods.GetChat}.
   */
  @SerializedName(PINNED_MESSAGE_FIELD)
  private Message pinnedMessage;
  
  /**
   * Name of group sticker set.
   * Returned only in {@link me.palazzomichi.telegram.telejam.methods.GetChat}.
   */
  @SerializedName(STICKER_SET_NAME_FIELD)
  private String stickerSetName;
  
  /**
   * True, if the bot can change the group sticker set.
   * Returned only in {@link me.palazzomichi.telegram.telejam.methods.GetChat}.
   */
  @SerializedName(CAN_SET_STICKER_SET_FIELD)
  private Boolean canSetStickerSet;
  
  
  public SuperGroup(long id,
                    ChatPhoto photo,
                    String title,
                    String username,
                    String description,
                    String inviteLink,
                    Message pinnedMessage,
                    String stickerSetName,
                    boolean canSetStickerSet) {
    super(id, photo);
    this.title = Objects.requireNonNull(title);
    this.username = Objects.requireNonNull(username);
    this.description = description;
    this.inviteLink = inviteLink;
    this.pinnedMessage = pinnedMessage;
    this.stickerSetName = stickerSetName;
    this.canSetStickerSet = canSetStickerSet;
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
  
  /**
   * Getter for property {@link #pinnedMessage}.
   *
   * @return optional value for property {@link #pinnedMessage}
   */
  public Optional<Message> getPinnedMessage() {
    return Optional.ofNullable(pinnedMessage);
  }
  
  /**
   * Getter for property {@link #stickerSetName}.
   *
   * @return optional value for property {@link #stickerSetName}
   */
  public Optional<String> getStickerSetName() {
    return Optional.ofNullable(stickerSetName);
  }
  
  /**
   * Getter for property {@link #canSetStickerSet}.
   *
   * @return value for property {@link #canSetStickerSet}
   */
  public Boolean getCanSetStickerSet() {
    return canSetStickerSet;
  }
  
}
