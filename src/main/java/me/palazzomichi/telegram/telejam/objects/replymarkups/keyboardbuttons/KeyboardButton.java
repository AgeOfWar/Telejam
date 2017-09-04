package me.palazzomichi.telegram.telejam.objects.replymarkups.keyboardbuttons;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;

import java.util.Objects;

/**
 * This object represents one button of the reply keyboard.
 *
 * @author Michi Palazzo
 */
public class KeyboardButton implements TelegramObject {

  static final String TEXT_FIELD = "text";

  /**
   * Text of the button.
   * If none of the optional fields are used,
   * it will be sent to the bot as a
   * message when the button is pressed.
   */
  @SerializedName(TEXT_FIELD)
  private String text;


  /**
   * Constructs a keyboard button.
   *
   * @param text the text of the button
   */
  public KeyboardButton(String text) {
    this.text = Objects.requireNonNull(text);
  }


  /**
   * Getter for property {@link #text}.
   *
   * @return value for property {@link #text}
   */
  public String getText() {
    return text;
  }

  /**
   * Setter for property {@link #text}.
   *
   * @param text value for property {@link #text}
   */
  public void setText(String text) {
    this.text = Objects.requireNonNull(text);
  }

}
