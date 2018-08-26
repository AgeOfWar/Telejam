package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This object represents a custom keyboard with reply options.
 *
 * @author Michi Palazzo
 */
public class ReplyKeyboardMarkup implements ReplyMarkup {

  static final String KEYBOARD_FIELD = "keyboard";
  static final String RESIZE_KEYBOARD_FIELD = "resize_keyboard";
  static final String ONE_TIME_KEYBOARD_FIELD = "one_time_keyboard";
  static final String SELECTIVE_FIELD = "selective";

  /**
   * Array of button rows, each represented by an
   * Array of {@link KeyboardButton} objects.
   */
  @SerializedName(KEYBOARD_FIELD)
  private KeyboardButton[][] keyboard;

  /**
   * Optional.
   * Requests clients to resize the keyboard vertically for
   * optimal fit (e.g., make the keyboard smaller if there are just two
   * rows of inlinekeyboardbuttons). Defaults to false, in which case the custom
   * keyboard is always of the same height as the app's standard keyboard.
   */
  @SerializedName(RESIZE_KEYBOARD_FIELD)
  private boolean resizeKeyboard;

  /**
   * Optional.
   * Requests clients to hide the keyboard as soon as it's been used.
   * The keyboard will still be available, but clients will
   * automatically display the usual letter-keyboard in the
   * chat – the user can press a special button in the input
   * field to see the custom keyboard again.
   * Defaults to false.
   */
  @SerializedName(ONE_TIME_KEYBOARD_FIELD)
  private boolean oneTimeKeyboard;

  /**
   * Optional.
   * Use this parameter if you want to show the keyboard to
   * specific users only.
   * Targets:
   * 1) users that are @mentioned in the text of the Message object;
   * 2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
   */
  @SerializedName(SELECTIVE_FIELD)
  private boolean selective;
  
  
  /**
   * Returns a ReplyKeyboardMarkup given the buttons and the
   * maximum number of columns in a row.
   *
   * @param columns the maximum number of columns in a row
   * @param resizeKeyboard if true resize vertically the keyboard
   * @param oneTimeKeyboard if true requests clients to hide the
   *                        keyboard as soon as it's been used
   * @param selective set to <code>true</code> if you want to
   *                  show the keyboard to specific users only
   * @param buttons the buttons of the keyboard
   * @return the created keyboard
   */
  public static ReplyKeyboardMarkup fromColumns(int columns,
                                                boolean resizeKeyboard,
                                                boolean oneTimeKeyboard,
                                                boolean selective,
                                                KeyboardButton... buttons) {
    int rows = buttons.length / columns;
    int len = rows * columns == buttons.length ? rows : rows + 1;
    KeyboardButton[][] keyboard = new KeyboardButton[len][];
    for (int row = 0;row < rows;row++) {
      keyboard[row] = new KeyboardButton[columns];
      System.arraycopy(buttons, row * columns, keyboard[row], 0, columns);
    }
    int remained = buttons.length - rows * columns;
    if (remained > 0) {
      keyboard[rows] = new KeyboardButton[remained];
      System.arraycopy(buttons, rows * columns, keyboard[rows], 0, remained);
    }
    return new ReplyKeyboardMarkup(keyboard, resizeKeyboard, oneTimeKeyboard, selective);
  }
  
  /**
   * Returns a ReplyKeyboardMarkup given the buttons and the
   * maximum number of columns in a row.
   *
   * @param columns the maximum number of columns in a row
   * @param buttons the buttons of the keyboard
   * @return the created keyboard
   */
  public static ReplyKeyboardMarkup fromColumns(int columns, KeyboardButton... buttons) {
    return fromColumns(columns, false, false, false, buttons);
  }
  
  /**
   * Returns a ReplyKeyboardMarkup given the buttons and the
   * maximum number of columns in a row.
   *
   * @param columns the maximum number of columns in a row
   * @param resizeKeyboard if true resize vertically the keyboard
   * @param oneTimeKeyboard if true requests clients to hide the
   *                        keyboard as soon as it's been used
   * @param selective set to <code>true</code> if you want to
   *                  show the keyboard to specific users only
   * @param buttons the buttons of the keyboard
   * @return the created keyboard
   */
  public static ReplyKeyboardMarkup fromColumns(int columns,
                                                boolean resizeKeyboard,
                                                boolean oneTimeKeyboard,
                                                boolean selective,
                                                List<KeyboardButton> buttons) {
    return fromColumns(
        columns, resizeKeyboard, oneTimeKeyboard, selective, buttons.toArray(new KeyboardButton[0])
    );
  }
  
  /**
   * Returns a ReplyKeyboardMarkup given the buttons and the
   * maximum number of columns in a row.
   *
   * @param columns the maximum number of columns in a row
   * @param buttons the buttons of the keyboard
   * @return the created keyboard
   */
  public static ReplyKeyboardMarkup fromColumns(int columns, List<KeyboardButton> buttons) {
    return fromColumns(columns, buttons.toArray(new KeyboardButton[0]));
  }
  

  /**
   * Constructs a ReplyKeyboardMarkup.
   *
   * @param keyboard        array of button rows
   * @param resizeKeyboard  if true resize vertically the keyboard
   * @param oneTimeKeyboard if true requests clients to hide the
   *                        keyboard as soon as it's been used
   * @param selective       set to <code>true</code> if you want to
   *                        show the keyboard to specific users only
   */
  public ReplyKeyboardMarkup(KeyboardButton[][] keyboard,
                             boolean resizeKeyboard,
                             boolean oneTimeKeyboard,
                             boolean selective) {
    this.keyboard = Objects.requireNonNull(keyboard);
    this.resizeKeyboard = resizeKeyboard;
    this.oneTimeKeyboard = oneTimeKeyboard;
    this.selective = selective;
  }

  /**
   * Constructs a ReplyKeyboardMarkup.
   *
   * @param keyboard array of button rows
   */
  public ReplyKeyboardMarkup(KeyboardButton[][] keyboard) {
    this.keyboard = keyboard;
  }

  /**
   * Constructs a ReplyKeyboardMarkup.
   *
   * @param keyboard array of button rows
   */
  public ReplyKeyboardMarkup(String[][] keyboard) {
    int rows = keyboard.length;

    KeyboardButton[][] keyboardButtons = new KeyboardButton[rows][];
    for (int i = 0; i < rows; i++) {
      int buttons = keyboard[i].length;
      keyboardButtons[i] = new KeyboardButton[buttons];
      for (int j = 0; j < buttons; j++) {
        keyboardButtons[i][j] = new KeyboardButton(keyboard[i][j]);
      }
    }
    this.keyboard = keyboardButtons;
  }


  /**
   * Getter for property {@link #keyboard}.
   *
   * @return value for property {@link #keyboard}
   */
  public KeyboardButton[][] getKeyboard() {
    return keyboard;
  }

  /**
   * Getter for property {@link #resizeKeyboard}.
   *
   * @return value for property {@link #resizeKeyboard}
   */
  public boolean resizeKeyboard() {
    return resizeKeyboard;
  }

  /**
   * Getter for property {@link #oneTimeKeyboard}.
   *
   * @return value for property {@link #oneTimeKeyboard}
   */
  public boolean isOneTimeKeyboard() {
    return oneTimeKeyboard;
  }

  /**
   * Getter for property {@link #selective}.
   *
   * @return value for property {@link #selective}
   */
  public boolean isSelective() {
    return selective;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof ReplyKeyboardMarkup)) return false;
    ReplyKeyboardMarkup keyboard = (ReplyKeyboardMarkup) obj;
    return Arrays.deepEquals(this.keyboard, keyboard.keyboard) &&
        resizeKeyboard == keyboard.resizeKeyboard &&
        oneTimeKeyboard == keyboard.oneTimeKeyboard &&
        selective == keyboard.selective;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(Arrays.deepHashCode(keyboard), resizeKeyboard, oneTimeKeyboard, selective);
  }
  
}
