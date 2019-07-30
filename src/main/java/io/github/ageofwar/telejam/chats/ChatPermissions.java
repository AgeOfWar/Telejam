package io.github.ageofwar.telejam.chats;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

public class ChatPermissions implements TelegramObject {
  static final String CAN_SEND_MESSAGES_FIELD = "can_send_messages";
  static final String CAN_SEND_MEDIA_MESSAGES_FIELD = "can_send_media_messages";
  static final String CAN_SEND_POLLS_FIELD = "can_send_polls";
  static final String CAN_SEND_OTHER_MESSAGES_FIELD = "can_send_other_messages";
  static final String CAN_ADD_WEB_PAGE_PREVIEWS_FIELD = "can_add_web_page_previews";
  static final String CAN_CHANGE_INFO_FIELD = "can_change_info";
  static final String CAN_INVITE_USERS_FIELD = "can_invite_users";
  static final String CAN_PIN_MESSAGES_FIELD = "can_pin_messages";
  
  @SerializedName(CAN_SEND_MESSAGES_FIELD)
  private boolean canSendMessages;
  
  @SerializedName(CAN_SEND_MEDIA_MESSAGES_FIELD)
  private boolean canSendMediaMessages;
  
  @SerializedName(CAN_SEND_POLLS_FIELD)
  private boolean canSendPolls;
  
  @SerializedName(CAN_SEND_OTHER_MESSAGES_FIELD)
  private boolean canSendOtherMessages;
  
  @SerializedName(CAN_ADD_WEB_PAGE_PREVIEWS_FIELD)
  private boolean canAddWebPagePreviews;
  
  @SerializedName(CAN_CHANGE_INFO_FIELD)
  private boolean canChangeInfo;
  
  @SerializedName(CAN_INVITE_USERS_FIELD)
  private boolean canInviteUsers;
  
  @SerializedName(CAN_PIN_MESSAGES_FIELD)
  private boolean canPinMessages;
  
  public ChatPermissions(boolean canSendMessages,
                         boolean canSendMediaMessages,
                         boolean canSendPolls,
                         boolean canSendOtherMessages,
                         boolean canAddWebPagePreviews,
                         boolean canChangeInfo,
                         boolean canInviteUsers,
                         boolean canPinMessages) {
    this.canSendMessages = canSendMessages;
    this.canSendMediaMessages = canSendMediaMessages;
    this.canSendPolls = canSendPolls;
    this.canSendOtherMessages = canSendOtherMessages;
    this.canAddWebPagePreviews = canAddWebPagePreviews;
    this.canChangeInfo = canChangeInfo;
    this.canInviteUsers = canInviteUsers;
    this.canPinMessages = canPinMessages;
  }
  
  public boolean canSendMessages() {
    return canSendMessages;
  }
  
  public boolean canSendMediaMessages() {
    return canSendMediaMessages;
  }
  
  public boolean canSendPolls() {
    return canSendPolls;
  }
  
  public boolean canSendOtherMessages() {
    return canSendOtherMessages;
  }
  
  public boolean canAddWebPagePreviews() {
    return canAddWebPagePreviews;
  }
  
  public boolean canChangeInfo() {
    return canChangeInfo;
  }
  
  public boolean canInviteUsers() {
    return canInviteUsers;
  }
  
  public boolean canPinMessages() {
    return canPinMessages;
  }
  
}
