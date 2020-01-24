package io.github.ageofwar.telejam.keyboards;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object represents one button of the reply keyboard.
 */
public class RequestPollKeyboardButton extends KeyboardButton {
  
  static final String REQUEST_POLL_FIELD = "request_poll";
  
  /**
   * If specified, the user will be asked to create a poll and send
   * it to the bot when the button is pressed.
   * Available in private chats only.
   */
  @SerializedName(REQUEST_POLL_FIELD)
  private KeyboardButtonPollType requestPoll;
  
  /**
   * Constructs a keyboard button.
   *
   * @param text the text of the button
   */
  public RequestPollKeyboardButton(String text, KeyboardButtonPollType keyboardButtonPollType) {
    super(text);
    this.requestPoll = keyboardButtonPollType;
  }
  
  /**
   * Getter for property {@link #requestPoll}.
   *
   * @return value for property {@link #requestPoll}
   */
  public KeyboardButtonPollType getRequestPoll() {
    return requestPoll;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof RequestPollKeyboardButton)) return false;
    RequestPollKeyboardButton button = (RequestPollKeyboardButton) obj;
    return getText().equals(button.getText()) && requestPoll.equals(button.requestPoll);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getText(), requestPoll);
  }
  
}
