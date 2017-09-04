package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Date;

/**
 * Use this method to restrict a user in a supergroup.
 * The bot must be an administrator in the supergroup for
 * this to work and must have the appropriate admin rights.
 * Pass True for all boolean parameters to lift restrictions from a user.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class RestrictChatMember extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "restrictChatMember";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String USER_ID_FIELD = "user_id";
  static final String UNTIL_DATE_FIELD = "until_date";
  static final String CAN_SEND_MESSAGES_FIELD = "can_send_messages";
  static final String CAN_SEND_MEDIA_MESSAGES_FIELD = "can_send_media_messages";
  static final String CAN_SEND_OTHER_MESSAGES_FIELD = "can_send_other_messages";
  static final String CAN_ADD_WEB_PAGE_PREVIEWS_FIELD = "can_add_web_page_previews";

  /**
   * Unique identifier for the target group or username of the
   * target supergroup or channel (in the format @channelusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId;

  /**
   * Unique identifier of the target user.
   */
  @SerializedName(USER_ID_FIELD)
  private Long userId;

  /**
   * Date when restrictions will be lifted for the user, unix time.
   * If user is restricted for more than 366 days or less than 30 seconds
   * from the current time, they are considered to be restricted forever.
   */
  @SerializedName(UNTIL_DATE_FIELD)
  private Long untilDate;

  /**
   * Pass True, if the user can send text messages, contacts, locations and venues.
   */
  @SerializedName(CAN_SEND_MESSAGES_FIELD)
  private Boolean canSendMessages;

  /**
   * Pass True, if the user can send audios, documents, photos,
   * videos, video notes and voice notes, implies {@link #canSendMessages}.
   */
  @SerializedName(CAN_SEND_MEDIA_MESSAGES_FIELD)
  private Boolean canSendMediaMessages;

  /**
   * Pass True, if the user can send animations, games,
   * stickers and use inline bots, implies {@link #canSendMessages}.
   */
  @SerializedName(CAN_SEND_OTHER_MESSAGES_FIELD)
  private Boolean canSendOtherMessages;

  /**
   * Pass True, if the user may add web page previews
   * to their messages, implies {@link #canSendMessages}.
   */
  @SerializedName(CAN_ADD_WEB_PAGE_PREVIEWS_FIELD)
  private Boolean canAddWebPagePreviews;


  public RestrictChatMember chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public RestrictChatMember chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public RestrictChatMember chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public RestrictChatMember user(Long userId) {
    this.userId = userId;
    return this;
  }

  public RestrictChatMember user(User user) {
    this.userId = user.getId();
    return this;
  }

  public RestrictChatMember untilDate(Long untilDate) {
    this.untilDate = untilDate;
    return this;
  }

  public RestrictChatMember untilDate(Date untilDate) {
    this.untilDate = untilDate.getTime();
    return this;
  }

  public RestrictChatMember canSendMessages(Boolean canSendMessages) {
    this.canSendMessages = canSendMessages;
    return this;
  }

  public RestrictChatMember canSendMediaMessages(Boolean canSendMediaMessages) {
    this.canSendMediaMessages = canSendMediaMessages;
    return this;
  }

  public RestrictChatMember canSendOtherMessages(Boolean canSendOtherMessages) {
    this.canSendOtherMessages = canSendOtherMessages;
    return this;
  }

  public RestrictChatMember canAddWebPagePreviews(Boolean canAddWebPagePreviews) {
    this.canAddWebPagePreviews = canAddWebPagePreviews;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Boolean> getReturnType(JsonElement response) {
    return Boolean.class;
  }

}
