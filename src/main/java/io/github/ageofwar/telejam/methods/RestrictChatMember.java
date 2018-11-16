package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.users.User;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to restrict a user in a supergroup.
 * The bot must be an administrator in the supergroup for
 * this to work and must have the appropriate admin rights.
 * Pass True for all boolean parameters to lift restrictions from a user.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class RestrictChatMember implements TelegramMethod<Boolean> {

  public static final String NAME = "restrictChatMember";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String USER_ID_FIELD = "user_id";
  static final String UNTIL_DATE_FIELD = "until_date";
  static final String CAN_SEND_MESSAGES_FIELD = "can_send_messages";
  static final String CAN_SEND_MEDIA_MESSAGES_FIELD = "can_send_media_messages";
  static final String CAN_SEND_OTHER_MESSAGES_FIELD = "can_send_other_messages";
  static final String CAN_ADD_WEB_PAGE_PREVIEWS_FIELD = "can_add_web_page_previews";
  
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
   * Date when restrictions will be lifted for the user, unix time.
   * If user is restricted for more than 366 days or less than 30 seconds
   * from the current time, they are considered to be restricted forever.
   */
  private Long untilDate;

  /**
   * Pass True, if the user can send text messages, contacts, locations and venues.
   */
  private Boolean canSendMessages;

  /**
   * Pass True, if the user can send audios, documents, photos,
   * videos, video notes and voice notes, implies {@link #canSendMessages}.
   */
  private Boolean canSendMediaMessages;

  /**
   * Pass True, if the user can send animations, games,
   * stickers and use inline bots, implies {@link #canSendMessages}.
   */
  private Boolean canSendOtherMessages;

  /**
   * Pass True, if the user may add web page previews
   * to their messages, implies {@link #canSendMessages}.
   */
  private Boolean canAddWebPagePreviews;
  
  
  public RestrictChatMember chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public RestrictChatMember chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public RestrictChatMember chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public RestrictChatMember user(Long userId) {
    this.userId = userId;
    return this;
  }

  public RestrictChatMember user(User user) {
    userId = user.getId();
    return this;
  }

  public RestrictChatMember untilDate(Long untilDate) {
    this.untilDate = untilDate;
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
  public Map<String, Object> getParameters() {
    return mapOf(
        CHAT_ID_FIELD, chatId != null ? chatId : chatUsername,
        USER_ID_FIELD, userId,
        UNTIL_DATE_FIELD, untilDate,
        CAN_SEND_MESSAGES_FIELD, canSendMessages,
        CAN_SEND_MEDIA_MESSAGES_FIELD, canSendMediaMessages,
        CAN_SEND_OTHER_MESSAGES_FIELD, canSendOtherMessages,
        CAN_ADD_WEB_PAGE_PREVIEWS_FIELD, canAddWebPagePreviews
    );
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
