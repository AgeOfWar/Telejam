package io.github.ageofwar.telejam.keyboards;

import com.google.gson.annotations.SerializedName;

/**
 * This object represents one button of the reply keyboard.
 * The user's current location will be sent when
 * the button is pressed.
 * Available in private chats only.
 *
 * @author Michi Palazzo
 */
public class RequestLocationKeyboardButton extends KeyboardButton {
  
  static final String REQUEST_LOCATION_FIELD = "request_location";
  
  @SerializedName(REQUEST_LOCATION_FIELD)
  private static final boolean REQUEST_LOCATION = true;
  
  /**
   * Constructs a keyboard button.
   *
   * @param text the text of the button
   */
  public RequestLocationKeyboardButton(String text) {
    super(text);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof RequestLocationKeyboardButton)) return false;
    KeyboardButton button = (KeyboardButton) obj;
    return getText().equals(button.getText());
  }
  
  @Override
  public int hashCode() {
    return getText().hashCode();
  }
  
}
