package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

/**
 * This object represents one pay button of an inline keyboard.
 *
 * @author Michi Palazzo
 */
public class PayInlineKeyboardButton extends InlineKeyboardButton {

  static final String PAY_FIELD = "pay";

  @SerializedName(PAY_FIELD)
  private static final boolean PAY = true;


  /**
   * Constructs an InlineKeyboardButton.
   *
   * @param text label text on the button
   */
  public PayInlineKeyboardButton(String text) {
    super(text);
  }

}
