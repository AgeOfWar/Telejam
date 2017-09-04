package me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents;

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


  public InputContactMessageContent(String phoneNumber, String firstName, String lastName) {
    this.phoneNumber = Objects.requireNonNull(phoneNumber);
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = lastName;
  }

  public InputContactMessageContent(String phoneNumber, String firstName) {
    this(phoneNumber, firstName, null);
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

}
