package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;

/**
 * This object represents a phone contact.
 *
 * @author Michi Palazzo
 */
public class Contact implements TelegramObject {

  static final String PHONE_NUMBER_FIELD = "phone_number";
  static final String FIRST_NAME_FIELD = "first_name";
  static final String LAST_NAME_FIELD = "last_name";
  static final String USER_ID = "user_id";

  private static final String NAME_SEPARATOR = " ";

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
   * Contact's user identifier in Telegram.
   */
  @SerializedName(USER_ID)
  private Long userId;


  public Contact(String phoneNumber, String firstName, String lastName, Long userId) {
    this.phoneNumber = Objects.requireNonNull(phoneNumber);
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = lastName;
    this.userId = userId;
  }

  public Contact(String phoneNumber, String firstName) {
    this.phoneNumber = Objects.requireNonNull(phoneNumber);
    this.firstName = Objects.requireNonNull(firstName);
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
   * Returns the name of the contact.
   *
   * @return the name of the contact
   */
  public String getName() {
    return lastName == null ? firstName : firstName + NAME_SEPARATOR + lastName;
  }

  /**
   * Getter for property {@link #userId}.
   *
   * @return optional value for property {@link #userId}
   */
  public OptionalLong getUserId() {
    return userId == null ? OptionalLong.empty() : OptionalLong.of(userId);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof Contact)) {
      return false;
    }
    
    Contact contact = (Contact) obj;
    return phoneNumber.equals(contact.phoneNumber) &&
        firstName.equals(contact.getFirstName()) &&
        Objects.equals(lastName, contact.lastName) &&
        Objects.equals(userId, contact.userId);
  }
  
  @Override
  public int hashCode() {
    int result = 1;
    result = 37 * result + phoneNumber.hashCode();
    result = 37 * result + firstName.hashCode();
    result = 37 * result + Objects.hashCode(lastName);
    result = 37 * result + Objects.hashCode(userId);
    return result;
  }
  
}
