package io.github.ageofwar.telejam.inline;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object represents one button of an inline keyboard.
 *
 * @author Michi Palazzo
 */
public class CallbackDataInlineKeyboardButton extends InlineKeyboardButton {
  
  static final String CALLBACK_DATA_FIELD = "callback_data";
  
  /**
   * Data to be sent in a callback query to the bot when button is pressed.
   */
  @SerializedName(CALLBACK_DATA_FIELD)
  private final String data;
  
  
  /**
   * Constructs a CallbackDataInlineKeyboardButton.
   *
   * @param text label text on the button
   * @param data data to be sent in a callback query to the
   *             bot when button is pressed.
   */
  public CallbackDataInlineKeyboardButton(String text, String data) {
    super(text);
    this.data = Objects.requireNonNull(data);
  }
  
  
  /**
   * Getter for property {@link #data}.
   *
   * @return value for property {@link #data}
   */
  public String getData() {
    return data;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof CallbackDataInlineKeyboardButton)) {
      return false;
    }
    CallbackDataInlineKeyboardButton button = (CallbackDataInlineKeyboardButton) obj;
    return getText().equals(button.getText()) && data.equals(button.data);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getText(), data);
  }
  
}
