package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;

/**
 * This object represents a poll message.
 *
 * @author Michi Palazzo
 */
public class PollMessage extends Message {
  
  static final String POLL_FIELD = "poll";
  
  /**
   * Information about the poll.
   */
  @SerializedName(POLL_FIELD)
  private final Poll poll;
  
  public PollMessage(long id,
                     User sender,
                     long date,
                     Chat chat,
                     Message replyToMessage,
                     Long editDate,
                     String authorSignature,
                     Poll poll,
                     InlineKeyboardMarkup replyMarkup) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature, replyMarkup);
    this.poll = Objects.requireNonNull(poll);
  }
  
  public Poll getPoll() {
    return poll;
  }
  
}
