package io.github.ageofwar.telejam.inline;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object represents one button of an inline keyboard.
 *
 * @author Michi Palazzo
 */
public class UrlInlineKeyboardButton extends InlineKeyboardButton {
  
  static final String URL_FIELD = "url";
  
  /**
   * HTTP url to be opened when button is pressed.
   */
  @SerializedName(URL_FIELD)
  private final String url;
  
  
  /**
   * Constructs an UrlInlineKeyboardButton.
   *
   * @param text label text on the button
   * @param url  HTTP url to be opened when button is pressed
   */
  public UrlInlineKeyboardButton(String text, String url) {
    super(text);
    this.url = Objects.requireNonNull(url);
  }
  
  
  /**
   * Getter for property {@link #url}.
   *
   * @return value for property {@link #url}
   */
  public String getUrl() {
    return url;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof UrlInlineKeyboardButton)) {
      return false;
    }
    UrlInlineKeyboardButton button = (UrlInlineKeyboardButton) obj;
    return getText().equals(button.getText()) && url.equals(button.url);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getText(), url);
  }
  
}
