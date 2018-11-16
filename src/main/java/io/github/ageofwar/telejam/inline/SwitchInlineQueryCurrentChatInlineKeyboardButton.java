package io.github.ageofwar.telejam.inline;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object represents one button of an inline keyboard.
 *
 * @author Michi Palazzo
 */
public class SwitchInlineQueryCurrentChatInlineKeyboardButton extends InlineKeyboardButton {
  
  static final String SWITCH_INLINE_QUERY_CURRENT_CHAT_FIELD = "switch_inline_query_current_chat";
  
  /**
   * Pressing the button will insert the bot‘s username and the
   * specified inline query in the current chat's input field.
   * Can be empty, in which case only the bot’s username will be inserted.
   */
  @SerializedName(SWITCH_INLINE_QUERY_CURRENT_CHAT_FIELD)
  private final String query;
  
  
  /**
   * Constructs a SwitchInlineQueryCurrentChatInlineKeyboardButton.
   *
   * @param text  label text on the button
   * @param query inline query to send
   */
  public SwitchInlineQueryCurrentChatInlineKeyboardButton(String text, String query) {
    super(text);
    this.query = Objects.requireNonNull(query);
  }
  
  
  /**
   * Getter for property {@link #query}.
   *
   * @return value for property {@link #query}
   */
  public String getQuery() {
    return query;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof SwitchInlineQueryCurrentChatInlineKeyboardButton)) {
      return false;
    }
    SwitchInlineQueryCurrentChatInlineKeyboardButton button =
        (SwitchInlineQueryCurrentChatInlineKeyboardButton) obj;
    return getText().equals(button.getText()) && query.equals(button.query);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getText(), query);
  }
  
}
