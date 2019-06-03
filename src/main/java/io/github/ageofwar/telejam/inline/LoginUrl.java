package io.github.ageofwar.telejam.inline;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.hash;

/**
 * This object represents a parameter of the inline
 * keyboard button used to automatically authorize a user.
 *
 * @author Michi Palazzo
 */
public class LoginUrl implements TelegramObject {
  
  static final String URL_FIELD = "url";
  static final String FORWARD_TEXT_FIELD = "forward_text";
  static final String BOT_USERNAME_FIELD = "bot_username";
  static final String REQUEST_WRITE_ACCESS_FIELD = "request_write_access";
  
  /**
   * An HTTP URL to be opened with user authorization data added to the query
   * string when the button is pressed.
   * If the user refuses to provide authorization data,
   * the original URL without information about the user will be opened.
   */
  @SerializedName(URL_FIELD)
  private final String url;
  
  /**
   * New text of the button in forwarded messages.
   */
  @SerializedName(FORWARD_TEXT_FIELD)
  private final String forwardText;
  
  /**
   * Username of a bot, which will be used for user authorization.
   */
  @SerializedName(BOT_USERNAME_FIELD)
  private final String botUsername;
  
  /**
   * Pass True to request the permission for your bot to send messages to the user.
   */
  @SerializedName(REQUEST_WRITE_ACCESS_FIELD)
  private final boolean requestWriteAccess;
  
  /**
   * Constructs a login url.
   *
   * @param url                HTTP URL to be opened with user authorization data added to the query string when the button is pressed
   * @param forwardText        new text of the button in forwarded messages
   * @param botUsername        username of a bot, which will be used for user authorization
   * @param requestWriteAccess request the permission for your bot to send messages to the user
   */
  public LoginUrl(String url, String forwardText, String botUsername, boolean requestWriteAccess) {
    this.url = url;
    this.forwardText = forwardText;
    this.botUsername = botUsername;
    this.requestWriteAccess = requestWriteAccess;
  }
  
  /**
   * Getter for property {@link #url}.
   *
   * @return value for property {@link #url}
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * Getter for property {@link #forwardText}.
   *
   * @return value for property {@link #forwardText}
   */
  public Optional<String> getForwardText() {
    return Optional.ofNullable(forwardText);
  }
  
  /**
   * Getter for property {@link #botUsername}.
   *
   * @return value for property {@link #botUsername}
   */
  public Optional<String> getBotUsername() {
    return Optional.ofNullable(botUsername);
  }
  
  /**
   * Getter for property {@link #requestWriteAccess}.
   *
   * @return value for property {@link #requestWriteAccess}
   */
  public boolean isRequestWriteAccess() {
    return requestWriteAccess;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof LoginUrl)) return false;
    LoginUrl loginUrl = (LoginUrl) obj;
    return url.equals(loginUrl.url) &&
        Objects.equals(forwardText, loginUrl.forwardText) &&
        Objects.equals(botUsername, loginUrl.botUsername) &&
        requestWriteAccess == loginUrl.requestWriteAccess;
  }
  
  @Override
  public int hashCode() {
    return hash(url, forwardText, botUsername, requestWriteAccess);
  }
  
}
