package io.github.ageofwar.telejam.replymarkups;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Upon receiving a message with this object, Telegram clients will
 * remove the current custom keyboard and display the default
 * letter-keyboard. By default, custom keyboards are displayed until a
 * new keyboard is sent by a bot. An exception is made for one-time
 * keyboards that are hidden immediately after the user presses a button.
 *
 * @author Michi Palazzo
 * @see ReplyKeyboardMarkup
 */
public final class ReplyKeyboardRemove implements ReplyMarkup {
  
  public static final ReplyKeyboardRemove NONSELECTIVE = new ReplyKeyboardRemove(false);
  public static final ReplyKeyboardRemove SELECTIVE = new ReplyKeyboardRemove(true);
  
  static final String REMOVE_KEYBOARD_FIELD = "remove_keyboard";
  static final String SELECTIVE_FIELD = "selective";
  
  @Expose
  @SerializedName(REMOVE_KEYBOARD_FIELD)
  private static final boolean REMOVE_KEYBOARD = true;
  
  /**
   * If true removes the keyboard to specific users only.
   * Targets:
   * 1) users that are @mentioned in the text of the Message object;
   * 2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
   */
  @SerializedName(SELECTIVE_FIELD)
  private final boolean selective;
  
  
  /**
   * Constructs a ReplyKeyboardRemove.
   *
   * @param selective set to <code>true</code> if you want to
   *                  remove the keyboard to specific users only
   */
  private ReplyKeyboardRemove(boolean selective) {
    this.selective = selective;
  }
  
}
