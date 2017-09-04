package me.palazzomichi.telegram.telejam.objects.replymarkups.inlinekeyboardbuttons;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;

import java.util.Objects;

/**
 * This object represents one button of an inline keyboard.
 *
 * @author Michi Palazzo
 */
public abstract class InlineKeyboardButton implements TelegramObject {

  static final String TEXT_FIELD = "text";

  /**
   * Label text on the button.
   */
  @SerializedName(TEXT_FIELD)
  private String text;


  /**
   * Constructs an InlineKeyboardButton.
   *
   * @param text label text on the button
   */
  public InlineKeyboardButton(String text) {
    this.text = Objects.requireNonNull(text);
  }


  /**
   * Getter for property {@link #text}.
   *
   * @return value for property {@link #text}
   */
  public String getText() {
    return text;
  }

  /**
   * Setter for property {@link #text}.
   *
   * @param text value for property {@link #text}
   */
  public void setText(String text) {
    this.text = text;
  }

}
