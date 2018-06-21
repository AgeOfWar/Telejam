package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

/**
 * This object represents one button of the reply keyboard.
 * The user's phone number will be sent as a contact when
 * the button is pressed.
 * Available in private chats only.
 *
 * @author Michi Palazzo
 */
public class RequestContactKeyboardButton extends KeyboardButton {

  static final String REQUEST_CONTACT_FIELD = "request_contact";

  @SerializedName(REQUEST_CONTACT_FIELD)
  private static final boolean REQUEST_CONTACT = true;


  /**
   * Constructs a keyboard button.
   *
   * @param text the text of the button
   */
  public RequestContactKeyboardButton(String text) {
    super(text);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof RequestContactKeyboardButton)) return false;
    KeyboardButton button = (KeyboardButton) obj;
    return getText().equals(button.getText());
  }
  
  @Override
  public int hashCode() {
    return getText().hashCode();
  }
  
}
