package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.*;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to send phone contacts.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendContact implements TelegramMethod<ContactMessage> {

  public static final String NAME = "sendContact";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String PHONE_NUMBER_FIELD = "phone_number";
  static final String FIRST_NAME_FIELD = "first_name";
  static final String LAST_NAME_FIELD = "last_name";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  private Boolean disableNotification;

  /**
   * If the message is a reply, ID of the original message.
   */
  private Long replyToMessageId;

  /**
   * Additional interface options.
   */
  private ReplyMarkup replyMarkup;

  /**
   * Contact's phone number.
   */
  private String phoneNumber;

  /**
   * Contact's first name.
   */
  private String firstName;

  /**
   * Contact's last name.
   */
  private String lastName;
  
  
  public SendContact chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    return this;
  }
  
  public SendContact chat(Long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    return this;
  }
  
  public SendContact chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
    return this;
  }

  public SendContact disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendContact disableNotification() {
    this.disableNotification = true;
    return this;
  }

  public SendContact replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    this.chatId = message.getChat().getId();
    this.chatUsername = null;
    return this;
  }

  public SendContact replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }

  public SendContact replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  public SendContact phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public SendContact firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public SendContact lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public SendContact name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    return this;
  }
  
  public SendContact contact(Contact contact) {
    this.firstName = contact.getFirstName();
    this.lastName = contact.getLastName().orElse(null);
    this.phoneNumber = contact.getPhoneNumber();
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
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId,
        REPLY_MARKUP_FIELD, replyMarkup,
        PHONE_NUMBER_FIELD, phoneNumber,
        FIRST_NAME_FIELD, firstName,
        LAST_NAME_FIELD, lastName
    );
  }
  
  @Override
  public Class<? extends ContactMessage> getReturnType() {
    return ContactMessage.class;
  }

}