package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object represents one button of an inline keyboard.
 * NOTE: This type of button must always be the first button in the first row.
 *
 * @author Michi Palazzo
 */
public class CallbackGameInlineKeyboardButton extends InlineKeyboardButton {

  static final String CALLBACK_GAME_FIELD = "callback_game";

  /**
   * Description of the game that will be launched when the user presses the button.
   */
  @SerializedName(CALLBACK_GAME_FIELD)
  private final CallbackGame callbackGame;


  /**
   * Constructs a CallbackGameInlineKeyboardButton.
   *
   * @param text label text on the button
   * @param callbackGame description of the game that will be launched
   *                     when the user presses the button
   */
  public CallbackGameInlineKeyboardButton(String text, CallbackGame callbackGame) {
    super(text);
    this.callbackGame = Objects.requireNonNull(callbackGame);
  }

  /**
   * Constructs a CallbackGameInlineKeyboardButton.
   *
   * @param text label text on the button
   */
  public CallbackGameInlineKeyboardButton(String text) {
    this(text, CallbackGame.INSTANCE);
  }


  /**
   * Getter for property {@link #callbackGame}.
   *
   * @return value for property {@link #callbackGame}
   */
  public CallbackGame getCallbackGame() {
    return callbackGame;
  }

}
