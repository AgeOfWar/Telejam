package io.github.ageofwar.telejam.chats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;

/**
 * This class represents a private chat.
 *
 * @author Michi Palazzo
 */
public class PrivateChat extends Chat {
  
  static final String USERNAME_FIELD = "username";
  static final String FIRST_NAME_FIELD = "first_name";
  static final String LAST_NAME_FIELD = "last_name";
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "private";
  
  /**
   * Username of the chat.
   */
  @SerializedName(USERNAME_FIELD)
  private final String username;
  
  /**
   * First name of the other party in the private chat.
   */
  @SerializedName(FIRST_NAME_FIELD)
  private final String firstName;
  
  /**
   * Last name of the other party in the private chat.
   */
  @SerializedName(LAST_NAME_FIELD)
  private final String lastName;
  
  
  public PrivateChat(long id, String username, String firstName, String lastName) {
    super(id);
    this.username = username;
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = lastName;
  }
  
  
  @Override
  public String getTitle() {
    return lastName == null ? firstName : firstName + " " + lastName;
  }
  
  /**
   * Getter for property {@link #username}.
   *
   * @return optional value for property {@link #username}
   */
  public Optional<String> getUsername() {
    return Optional.ofNullable(username);
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
  
}
