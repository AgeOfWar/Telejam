package io.github.ageofwar.telejam.inline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;

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
  static final String VCARD_FIELD = "vcard";
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "contact";
  
  /**
   * Contact's phone number.
   */
  @SerializedName(PHONE_NUMBER_FIELD)
  private final String phoneNumber;
  
  /**
   * Contact's first name.
   */
  @SerializedName(FIRST_NAME_FIELD)
  private final String firstName;
  
  /**
   * Contact's last name.
   */
  @SerializedName(LAST_NAME_FIELD)
  private final String lastName;
  
  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private final InlineKeyboardMarkup replyMarkup;
  
  /**
   * Content of the message to be sent instead of the contact.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private final InputMessageContent inputMessageContent;
  
  /**
   * Url of the thumbnail for the result.
   */
  @SerializedName(THUMB_URL_FIELD)
  private final String thumbUrl;
  
  /**
   * Thumbnail width.
   */
  @SerializedName(THUMB_WIDTH_FIELD)
  private final Integer thumbWidth;
  
  /**
   * Thumbnail height.
   */
  @SerializedName(THUMB_HEIGHT_FIELD)
  private final Integer thumbHeight;
  
  /**
   * Additional data about the contact in the form of a vCard.
   */
  @SerializedName(VCARD_FIELD)
  private final String vCard;
  
  
  public InlineQueryResultContact(String id,
                                  String phoneNumber,
                                  String firstName,
                                  String lastName,
                                  InlineKeyboardMarkup replyMarkup,
                                  InputMessageContent inputMessageContent,
                                  String thumbUrl,
                                  Integer thumbWidth,
                                  Integer thumbHeight,
                                  String vCard) {
    super(id);
    this.phoneNumber = Objects.requireNonNull(phoneNumber);
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = lastName;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
    this.thumbUrl = thumbUrl;
    this.thumbWidth = thumbWidth;
    this.thumbHeight = thumbHeight;
    this.vCard = vCard;
  }
  
  public InlineQueryResultContact(String id, String phoneNumber, String firstName) {
    this(id, phoneNumber, firstName, null, null, null, null, null, null, null);
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
   * Getter for property {@link #firstName}.
   *
   * @return value for property {@link #firstName}
   */
  public String getFirstName() {
    return firstName;
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
   * Getter for property {@link #replyMarkup}.
   *
   * @return optional value for property {@link #replyMarkup}
   */
  public Optional<InlineKeyboardMarkup> getReplyMarkup() {
    return Optional.ofNullable(replyMarkup);
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
   * Getter for property {@link #thumbUrl}.
   *
   * @return optional value for property {@link #thumbUrl}
   */
  public Optional<String> getThumbUrl() {
    return Optional.ofNullable(thumbUrl);
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
   * Getter for property {@link #thumbHeight}.
   *
   * @return optional value for property {@link #thumbHeight}
   */
  public OptionalInt getThumbHeight() {
    return thumbHeight == null ? OptionalInt.empty() : OptionalInt.of(thumbHeight);
  }
  
  /**
   * Getter for property {@link #vCard}.
   *
   * @return optional value for property {@link #vCard}
   */
  public Optional<String> getVCard() {
    return Optional.ofNullable(vCard);
  }
  
}
