package me.palazzomichi.telegram.telejam.objects.replymarkups;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.replymarkups.inlinekeyboardbuttons.InlineKeyboardButton;

import java.util.Objects;

/**
 * This object represents an inline keyboard that
 * appears right next to the message it belongs to.
 *
 * @author Michi Palazzo
 */
public class InlineKeyboardMarkup implements ReplyMarkup {

  static final String INLINE_KEYBOARD_FIELD = "inline_keyboard";

  /**
   * Array of button rows, each represented by an
   * Array of {@link InlineKeyboardButton} objects.
   */
  @SerializedName(INLINE_KEYBOARD_FIELD)
  private InlineKeyboardButton[][] inlineKeyboard;


  /**
   * Constructs an InlineKeyboardMarkup
   *
   * @param inlineKeyboard the keyboard
   */
  public InlineKeyboardMarkup(InlineKeyboardButton[][] inlineKeyboard) {
    this.inlineKeyboard = Objects.requireNonNull(inlineKeyboard);
  }


  /**
   * Getter for property {@link #inlineKeyboard}.
   *
   * @return value for property {@link #inlineKeyboard}
   */
  public InlineKeyboardButton[][] getInlineKeyboard() {
    return inlineKeyboard;
  }

  /**
   * Setter for property {@link #inlineKeyboard}.
   *
   * @param inlineKeyboard value for property {@link #inlineKeyboard}
   */
  public void setInlineKeyboard(InlineKeyboardButton[][] inlineKeyboard) {
    this.inlineKeyboard = Objects.requireNonNull(inlineKeyboard);
  }

}
