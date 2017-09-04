package me.palazzomichi.telegram.telejam.objects.replymarkups.inlinekeyboardbuttons;

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
  private String url;


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

  /**
   * Setter for property {@link #url}.
   *
   * @param url value for property {@link #url}
   */
  public void setUrl(String url) {
    this.url = Objects.requireNonNull(url);
  }

}
