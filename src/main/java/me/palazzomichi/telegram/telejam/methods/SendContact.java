package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.ContactMessage;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.replymarkups.ReplyMarkup;

/**
 * Use this method to send phone contacts.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendContact extends JsonTelegramMethod<ContactMessage> {

  public static final String NAME = "sendContact";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String PHONE_NUMBER_FIELD = "phone_number";
  static final String FIRST_NAME_FIELD = "first_name";
  static final String LAST_NAME_FIELD = "last_name";

  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>)
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  @SerializedName(DISABLE_NOTIFICATION_FIELD)
  private Boolean disableNotification;

  /**
   * If the message is a reply, ID of the original message.
   */
  @SerializedName(REPLY_TO_MESSAGE_ID_FIELD)
  private Long replyToMessageId;

  /**
   * Additional interface options.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private ReplyMarkup replyMarkup;

  /**
   * Contact's phone number.
   */
  @SerializedName(PHONE_NUMBER_FIELD)
  private String phoneNumber;

  /**
   * Contact's first name.
   */
  @SerializedName(FIRST_NAME_FIELD)
  private String firstName;

  /**
   * Contact's last name.
   */
  @SerializedName(LAST_NAME_FIELD)
  private String lastName;


  public SendContact chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public SendContact chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public SendContact chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
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
    this.chatId = Long.toString(message.getChat().getId());
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

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends ContactMessage> getReturnType(JsonElement response) {
    return ContactMessage.class;
  }

}
