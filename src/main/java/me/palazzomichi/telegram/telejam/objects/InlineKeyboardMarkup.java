package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
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

  
  public static InlineKeyboardMarkup fromColumns(int columns, InlineKeyboardButton... buttons) {
    int rows = buttons.length / columns;
    int len = rows * columns == buttons.length ? rows : rows + 1;
    InlineKeyboardButton[][] keyboard = new InlineKeyboardButton[len][];
    for (int row = 0;row < rows;row++) {
      keyboard[row] = new InlineKeyboardButton[columns];
      System.arraycopy(buttons, row * columns, keyboard[row], 0, columns);
    }
    int remained = buttons.length - rows * columns;
    if (remained > 0) {
      keyboard[rows] = new InlineKeyboardButton[remained];
      System.arraycopy(buttons, rows * columns, keyboard[rows], 0, remained);
    }
    return new InlineKeyboardMarkup(keyboard);
  }
  
  public static InlineKeyboardMarkup fromColumns(int columns, List<InlineKeyboardButton> buttons) {
    return fromColumns(columns, buttons.toArray(new InlineKeyboardButton[0]));
  }
  

  /**
   * Constructs an InlineKeyboardMarkup
   *
   * @param inlineKeyboard the keyboard
   */
  public InlineKeyboardMarkup(InlineKeyboardButton[][] inlineKeyboard) {
    this.inlineKeyboard = Objects.requireNonNull(inlineKeyboard);
  }
  
  /**
   * Constructs an InlineKeyboardMarkup
   *
   * @param inlineKeyboard the keyboard
   */
  public InlineKeyboardMarkup(InlineKeyboardButton[] inlineKeyboard) {
    this(new InlineKeyboardButton[][]{ inlineKeyboard });
  }
  
  /**
   * Constructs an InlineKeyboardMarkup
   *
   * @param inlineButton the keyboard
   */
  public InlineKeyboardMarkup(InlineKeyboardButton inlineButton) {
    this(new InlineKeyboardButton[]{ inlineButton });
  }


  /**
   * Getter for property {@link #inlineKeyboard}.
   *
   * @return value for property {@link #inlineKeyboard}
   */
  public InlineKeyboardButton[][] getInlineKeyboard() {
    return inlineKeyboard;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof InlineKeyboardMarkup)) return false;
    return Arrays.deepEquals(inlineKeyboard, ((InlineKeyboardMarkup) obj).inlineKeyboard);
  }
  
  @Override
  public int hashCode() {
    return Arrays.deepHashCode(inlineKeyboard);
  }
  
}
