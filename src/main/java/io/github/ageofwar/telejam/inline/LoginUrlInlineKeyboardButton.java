package io.github.ageofwar.telejam.inline;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object represents one button of an inline keyboard.
 *
 * @author Michi Palazzo
 */
public class LoginUrlInlineKeyboardButton extends InlineKeyboardButton {
  
  static final String LOGIN_URL_FIELD = "login_url";
  
  /**
   * HTTP url to be opened when button is pressed.
   */
  @SerializedName(LOGIN_URL_FIELD)
  private final LoginUrl loginUrl;
  
  
  /**
   * Constructs an UrlInlineKeyboardButton.
   *
   * @param text     label text on the button
   * @param loginUrl HTTP url to be opened when button is pressed
   */
  public LoginUrlInlineKeyboardButton(String text, LoginUrl loginUrl) {
    super(text);
    this.loginUrl = Objects.requireNonNull(loginUrl);
  }
  
  
  /**
   * Getter for property {@link #loginUrl}.
   *
   * @return value for property {@link #loginUrl}
   */
  public LoginUrl getUrl() {
    return loginUrl;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof LoginUrlInlineKeyboardButton)) {
      return false;
    }
    LoginUrlInlineKeyboardButton button = (LoginUrlInlineKeyboardButton) obj;
    return getText().equals(button.getText()) && loginUrl.equals(button.loginUrl);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getText(), loginUrl);
  }
  
}
