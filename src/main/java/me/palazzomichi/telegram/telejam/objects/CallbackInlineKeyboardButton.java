package me.palazzomichi.telegram.telejam.objects;

import java.util.HashMap;
import java.util.UUID;

/**
 * This object represents one button of an inline keyboard.
 *
 * @author Michi Palazzo
 */
public abstract class CallbackInlineKeyboardButton extends CallbackDataInlineKeyboardButton {
  
  private static final HashMap<String, CallbackInlineKeyboardButton> buttons = new HashMap<>();
  
  /**
   * Returns the button with the specified data.
   *
   * @param data the data
   */
  public static CallbackInlineKeyboardButton fromData(String data) {
    return buttons.get(data);
  }
  
  /**
   * Constructs a CallbackInlineKeyboardButton.
   *
   * @param text label text on the button
   */
  public CallbackInlineKeyboardButton(String text) {
    super(text, UUID.randomUUID().toString());
    buttons.put(getData(), this);
  }
  
  /**
   * Called when the button is clicked.
   */
  public abstract void onClick() throws Throwable;
  
}
