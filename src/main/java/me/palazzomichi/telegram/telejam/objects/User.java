package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a Telegram user or bot.
 *
 * @author Michi Palazzo
 */
public class User implements TelegramObject {
  
  static final String ID_FIELD = "id";
  static final String FIRST_NAME_FIELD = "first_name";
  static final String LAST_NAME_FIELD = "last_name";
  static final String USERNAME_FIELD = "username";
  static final String LANGUAGE_CODE_FIELD = "language_code";
  static final String IS_BOT_FIELD = "is_bot";
  
  /**
   * Unique identifier for this user or bot.
   */
  @SerializedName(ID_FIELD)
  private final long id;
  
  /**
   * User‘s or bot’s first name.
   */
  @SerializedName(FIRST_NAME_FIELD)
  private final String firstName;
  
  /**
   * User‘s or bot’s last name.
   */
  @SerializedName(LAST_NAME_FIELD)
  private final String lastName;
  
  /**
   * User‘s or bot’s username.
   */
  @SerializedName(USERNAME_FIELD)
  private final String username;
  
  /**
   * User's locale.
   */
  @SerializedName(LANGUAGE_CODE_FIELD)
  private final Locale locale;
  
  /**
   * <code>true</code> if this user is a bot, <code>false</code> otherwise.
   */
  @SerializedName(IS_BOT_FIELD)
  private final boolean isBot;
  
  
  public User(long id,
              String firstName,
              String lastName,
              String username,
              Locale locale,
              boolean isBot) {
    this.id = id;
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = lastName;
    this.username = username;
    this.locale = locale;
    this.isBot = isBot;
  }
  
  
  /**
   * Getter for property {@link #id}.
   *
   * @return value for property {@link #id}
   */
  public long getId() {
    return id;
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
   * @return value for property {@link #lastName}
   */
  public Optional<String> getLastName() {
    return Optional.ofNullable(lastName);
  }
  
  /**
   * Returns the name of the user.
   *
   * @return the first and last name of the user
   */
  public String getName() {
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
   * Returns the locale of the user.
   * If This user is a bot then {@link Locale#ROOT} is returned.
   *
   * @return the locale of the user
   */
  public Locale getLocale() {
    return locale != null ? locale : Locale.ROOT;
  }
  
  /**
   * Getter for property {@link #isBot}.
   *
   * @return value for property {@link #isBot}
   */
  public boolean isBot() {
    return isBot;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof User)) {
      return false;
    }
    
    User user = (User) obj;
    return id == user.getId();
  }
  
  @Override
  public int hashCode() {
    return Long.hashCode(id);
  }
  
}
