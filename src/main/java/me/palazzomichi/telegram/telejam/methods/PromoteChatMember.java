package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.User;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to promote or demote a user in a supergroup or a channel.
 * The bot must be an administrator in the chat for this to work and must
 * have the appropriate admin rights.
 * Pass False for all boolean parameters to demote a user.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class PromoteChatMember implements TelegramMethod<Boolean> {

  public static final String NAME = "promoteChatMember";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String USER_ID_FIELD = "user_id";
  static final String CAN_CHANGE_INFO_FIELD = "can_change_info";
  static final String CAN_POST_MESSAGES_FIELD = "can_post_messages";
  static final String CAN_EDIT_MESSAGES_FIELD = "can_edit_messages";
  static final String CAN_DELETE_MESSAGES_FIELD = "can_delete_messages";
  static final String CAN_INVITE_USERS_FIELD = "can_invite_users";
  static final String CAN_RESTRICT_MEMBERS_FIELD = "can_restrict_members";
  static final String CAN_PIN_MESSAGES_FIELD = "can_pin_messages";
  static final String CAN_PROMOTE_MEMBERS_FIELD = "can_promote_members";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;

  /**
   * Unique identifier of the target user.
   */
  private Long userId;

  /**
   * Pass True, if the administrator can change chat title, photo and other settings.
   */
  private Boolean canChangeInfo;

  /**
   * Pass True, if the administrator can create channel posts, channels only.
   */
  private Boolean canPostMessages;

  /**
   * Pass True, if the administrator can edit messages of other users, channels only.
   */
  private Boolean canEditMessage;

  /**
   * Pass True, if the administrator can delete messages of other users.
   */
  private Boolean canDeleteMessages;

  /**
   * Pass True, if the administrator can invite new users to the chat.
   */
  private Boolean canInviteUsers;

  /**
   * Pass True, if the administrator can restrict, ban or unban chat members.
   */
  private Boolean canRestrictMembers;

  /**
   * Pass True, if the administrator can pin messages, supergroups only.
   */
  private Boolean canPinMessages;

  /**
   * Pass True, if the administrator can add new administrators with a
   * subset of his own privileges or demote administrators that he has
   * promoted, directly or indirectly (promoted by administrators that were appointed by him).
   */
  private Boolean canPromoteMembers;
  
  
  public PromoteChatMember chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    return this;
  }
  
  public PromoteChatMember chat(Long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    return this;
  }
  
  public PromoteChatMember chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
    return this;
  }

  public PromoteChatMember user(Long userId) {
    this.userId = userId;
    return this;
  }

  public PromoteChatMember user(User user) {
    this.userId = user.getId();
    return this;
  }
  
  public PromoteChatMember canChangeInfo(Boolean canChangeInfo) {
    this.canChangeInfo = canChangeInfo;
    return this;
  }
  
  public PromoteChatMember canChangeInfo() {
    this.canChangeInfo = true;
    return this;
  }
  
  public PromoteChatMember canPostMessages(Boolean canPostMessages) {
    this.canPostMessages = canPostMessages;
    return this;
  }
  
  public PromoteChatMember canPostMessages() {
    this.canPostMessages = true;
    return this;
  }
  
  public PromoteChatMember canEditMessage(Boolean canEditMessage) {
    this.canEditMessage = canEditMessage;
    return this;
  }
  
  public PromoteChatMember canEditMessage() {
    this.canEditMessage = true;
    return this;
  }
  
  public PromoteChatMember canDeleteMessages(Boolean canDeleteMessages) {
    this.canDeleteMessages = canDeleteMessages;
    return this;
  }
  
  public PromoteChatMember canDeleteMessages() {
    this.canDeleteMessages = true;
    return this;
  }
  
  public PromoteChatMember canInviteUsers(Boolean canInviteUsers) {
    this.canInviteUsers = canInviteUsers;
    return this;
  }
  
  public PromoteChatMember canInviteUsers() {
    this.canInviteUsers = true;
    return this;
  }
  
  public PromoteChatMember canRestrictMembers(Boolean canRestrictMembers) {
    this.canRestrictMembers = canRestrictMembers;
    return this;
  }
  
  public PromoteChatMember canRestrictMembers() {
    this.canRestrictMembers = true;
    return this;
  }
  
  public PromoteChatMember canPinMessages(Boolean canPinMessages) {
    this.canPinMessages = canPinMessages;
    return this;
  }
  
  public PromoteChatMember canPinMessages() {
    this.canPinMessages = true;
    return this;
  }
  
  public PromoteChatMember canPromoteMembers(Boolean canPromoteMembers) {
    this.canPromoteMembers = canPromoteMembers;
    return this;
  }
  
  public PromoteChatMember canPromoteMembers() {
    this.canPromoteMembers = true;
    return this;
  }
  
  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(
        CHAT_ID_FIELD, chatId != null ? chatId : chatUsername,
        USER_ID_FIELD, userId,
        CAN_CHANGE_INFO_FIELD, canChangeInfo,
        CAN_POST_MESSAGES_FIELD, canPostMessages,
        CAN_EDIT_MESSAGES_FIELD, canEditMessage,
        CAN_DELETE_MESSAGES_FIELD, canDeleteMessages,
        CAN_INVITE_USERS_FIELD, canInviteUsers,
        CAN_RESTRICT_MEMBERS_FIELD, canRestrictMembers,
        CAN_PIN_MESSAGES_FIELD, canPinMessages,
        CAN_PROMOTE_MEMBERS_FIELD, canPromoteMembers
    );
  }
  
  @Override
  public Class<? extends Boolean> getReturnType() {
    return Boolean.class;
  }

}
