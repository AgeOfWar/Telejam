package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

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
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof InlineKeyboardButton)) {
      return false;
    }
    InlineKeyboardButton button = (InlineKeyboardButton) obj;
    return text.equals(button.text);
  }
  
  @Override
  public int hashCode() {
    return text.hashCode();
  }

}
