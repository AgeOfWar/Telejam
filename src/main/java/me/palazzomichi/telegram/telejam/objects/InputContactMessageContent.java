package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents the content of a contact message to be sent as the result of an inline query.
 *
 * @author Michi Palazzo
 */
public class InputContactMessageContent implements InputMessageContent {

  static final String PHONE_NUMBER_FIELD = "phone_number";
  static final String FIRST_NAME_FIELD = "first_name";
  static final String LAST_NAME_FIELD = "last_name";
  static final String VCARD_FIELD = "vcard";

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
   * Additional data about the contact in the form of a vCard.
   */
  @SerializedName(VCARD_FIELD)
  private String vCard;
  

  public InputContactMessageContent(String phoneNumber, String firstName, String lastName, String vCard) {
    this.phoneNumber = Objects.requireNonNull(phoneNumber);
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = lastName;
    this.vCard = vCard;
  }

  public InputContactMessageContent(String phoneNumber, String firstName) {
    this(phoneNumber, firstName, null, null);
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
   * Getter for property {@link #vCard}.
   *
   * @return optional value for property {@link #vCard}
   */
  public Optional<String> getVCard() {
    return Optional.ofNullable(vCard);
  }

}
