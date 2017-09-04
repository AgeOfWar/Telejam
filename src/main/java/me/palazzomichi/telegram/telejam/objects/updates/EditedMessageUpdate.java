package me.palazzomichi.telegram.telejam.objects.updates;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.messages.Message;

import java.util.Objects;

/**
 * This object represents an incoming update.
 *
 * @author Michi Palazzo
 */
public class EditedMessageUpdate extends Update {

  static final String EDITED_MESSAGE_FIELD = "edited_message";

  /**
   * New version of a message that is known to the bot and was edited.
   */
  @SerializedName(EDITED_MESSAGE_FIELD)
  private Message editedMessage;


  public EditedMessageUpdate(long id, Message editedMessage) {
    super(id);
    this.editedMessage = Objects.requireNonNull(editedMessage);
  }


  /**
   * Getter for property {@link #editedMessage}.
   *
   * @return value for property {@link #editedMessage}
   */
  public Message getMessage() {
    return editedMessage;
  }

}
