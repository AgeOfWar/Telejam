package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;

/**
 * This object represents a contact message.
 *
 * @author Michi Palazzo
 */
public class DiceMessage extends Message {
  
  static final String DICE_FIELD = "dice";
  
  /**
   * Information about the contact.
   */
  @SerializedName(DICE_FIELD)
  private final Dice dice;
  
  public DiceMessage(long id,
                     User sender,
                     long date,
                     Chat chat,
                     Message replyToMessage,
                     String authorSignature,
                     Dice dice,
                     InlineKeyboardMarkup replyMarkup) {
    super(id, sender, date, chat, replyToMessage, null, authorSignature, replyMarkup);
    this.dice = Objects.requireNonNull(dice);
  }
  
  /**
   * Getter for property {@link #dice}.
   *
   * @return value for property {@link #dice}
   */
  public Dice getDice() {
    return dice;
  }
  
}
