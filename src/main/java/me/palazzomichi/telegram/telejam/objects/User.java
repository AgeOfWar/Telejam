package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.Bot;

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

  private static final String NAME_SEPARATOR = " ";

  /**
   * Unique identifier for this user or bot.
   */
  @SerializedName(ID_FIELD)
  private long id;

  /**
   * User‘s or bot’s first name.
   */
  @SerializedName(FIRST_NAME_FIELD)
  private String firstName;

  /**
   * User‘s or bot’s last name.
   */
  @SerializedName(LAST_NAME_FIELD)
  private String lastName;

  /**
   * User‘s or bot’s username.
   */
  @SerializedName(USERNAME_FIELD)
  private String username;

  /**
   * IETF language tag of the user's language.
   */
  @SerializedName(LANGUAGE_CODE_FIELD)
  private String languageCode;

  /**
   * <code>true</code> if this user is a bot, <code>false</code> otherwise.
   */
  @SerializedName(IS_BOT_FIELD)
  private boolean isBot;


  public User(long id,
              String firstName,
              String lastName,
              String username,
              String languageCode,
              boolean isBot) {
    this.id = id;
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = lastName;
    this.username = username;
    this.languageCode = languageCode;
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
    return lastName == null ? firstName : firstName + NAME_SEPARATOR + lastName;
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
   * Getter for property {@link #languageCode}.
   *
   * @return optional value for property {@link #languageCode}
   */
  public Optional<String> getLanguageCode() {
    return Optional.ofNullable(languageCode);
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
    return (obj instanceof User && ((User) obj).getId() == id) ||
        (obj instanceof Bot && ((Bot) obj).getId() == id);
  }

}
