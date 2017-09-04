package me.palazzomichi.telegram.telejam.objects.replymarkups.inlinekeyboardbuttons;

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
  private String data;


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

  /**
   * Setter for property {@link #data}.
   *
   * @param data value for property {@link #data}
   */
  public void setData(String data) {
    this.data = Objects.requireNonNull(data);
  }

}
