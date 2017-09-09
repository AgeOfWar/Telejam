package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a contact with a phone number. By default, this contact will be sent by the user.
 * Alternatively, you can use {@link #inputMessageContent} to send a message with
 * the specified content instead of the contact.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultContact extends InlineQueryResult {

  static final String PHONE_NUMBER_FIELD = "phone_number";
  static final String FIRST_NAME_FIELD = "first_name";
  static final String LAST_NAME_FIELD = "last_name";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String THUMB_URL_FIELD = "thumb_url";
  static final String THUMB_WIDTH_FIELD = "thumb_width";
  static final String THUMB_HEIGHT_FIELD = "thumb_height";

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "contact";

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

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the contact.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;

  /**
   * Url of the thumbnail for the result.
   */
  @SerializedName(THUMB_URL_FIELD)
  private String thumbUrl;

  /**
   * Thumbnail width.
   */
  @SerializedName(THUMB_WIDTH_FIELD)
  private Integer thumbWidth;

  /**
   * Thumbnail height.
   */
  @SerializedName(THUMB_HEIGHT_FIELD)
  private Integer thumbHeight;


  public InlineQueryResultContact(String id,
                                  String phoneNumber,
                                  String firstName,
                                  String lastName,
                                  InlineKeyboardMarkup replyMarkup,
                                  InputMessageContent inputMessageContent,
                                  String thumbUrl,
                                  Integer thumbWidth,
                                  Integer thumbHeight) {
    super(id);
    this.phoneNumber = Objects.requireNonNull(phoneNumber);
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = lastName;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
    this.thumbUrl = thumbUrl;
    this.thumbWidth = thumbWidth;
    this.thumbHeight = thumbHeight;
  }

  public InlineQueryResultContact(String id, String phoneNumber, String firstName) {
    this(id, phoneNumber, firstName, null, null, null, null, null, null);
  }


  /**
   * Getter for property {@link #phoneNumber}.
   *
   * @return value for property {@link #phoneNumber}
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Setter for property {@link #phoneNumber}.
   *
   * @param phoneNumber value for property {@link #phoneNumber}
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = Objects.requireNonNull(phoneNumber);
  }

  /**
   * Getter for property {@link #firstName}.
   *
   * @return value for property {@link #firstName}
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Setter for property {@link #firstName}.
   *
   * @param firstName value for property {@link #firstName}
   */
  public void setFirstName(String firstName) {
    this.firstName = Objects.requireNonNull(firstName);
  }

  /**
   * Getter for property {@link #lastName}.
   *
   * @return optional value for property {@link #lastName}
   */
  public Optional<String> getLastName() {
    return Optional.ofNullable(lastName);
  }

  /**
   * Setter for property {@link #lastName}.
   *
   * @param lastName value for property {@link #lastName}
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Getter for property {@link #replyMarkup}.
   *
   * @return optional value for property {@link #replyMarkup}
   */
  public Optional<InlineKeyboardMarkup> getReplyMarkup() {
    return Optional.ofNullable(replyMarkup);
  }

  /**
   * Setter for property {@link #replyMarkup}.
   *
   * @param replyMarkup value for property {@link #replyMarkup}
   */
  public void setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
  }

  /**
   * Getter for property {@link #inputMessageContent}.
   *
   * @return optional value for property {@link #inputMessageContent}
   */
  public Optional<InputMessageContent> getInputMessageContent() {
    return Optional.ofNullable(inputMessageContent);
  }

  /**
   * Setter for property {@link #inputMessageContent}.
   *
   * @param inputMessageContent value for property {@link #inputMessageContent}
   */
  public void setInputMessageContent(InputMessageContent inputMessageContent) {
    this.inputMessageContent = inputMessageContent;
  }

  /**
   * Getter for property {@link #thumbUrl}.
   *
   * @return optional value for property {@link #thumbUrl}
   */
  public Optional<String> getThumbUrl() {
    return Optional.ofNullable(thumbUrl);
  }

  /**
   * Setter for property {@link #thumbUrl}.
   *
   * @param thumbUrl value for property {@link #thumbUrl}
   */
  public void setThumbUrl(String thumbUrl) {
    this.thumbUrl = thumbUrl;
  }

  /**
   * Getter for property {@link #thumbWidth}.
   *
   * @return optional value for property {@link #thumbWidth}
   */
  public OptionalInt getThumbWidth() {
    return thumbWidth == null ? OptionalInt.empty() : OptionalInt.of(thumbWidth);
  }

  /**
   * Setter for property {@link #thumbWidth}.
   *
   * @param thumbWidth value for property {@link #thumbWidth}
   */
  public void setThumbWidth(Integer thumbWidth) {
    this.thumbWidth = thumbWidth;
  }

  /**
   * Getter for property {@link #thumbHeight}.
   *
   * @return optional value for property {@link #thumbHeight}
   */
  public OptionalInt getThumbHeight() {
    return thumbHeight == null ? OptionalInt.empty() : OptionalInt.of(thumbHeight);
  }

  /**
   * Setter for property {@link #thumbHeight}.
   *
   * @param thumbHeight value for property {@link #thumbHeight}
   */
  public void setThumbHeight(Integer thumbHeight) {
    this.thumbHeight = thumbHeight;
  }

}
