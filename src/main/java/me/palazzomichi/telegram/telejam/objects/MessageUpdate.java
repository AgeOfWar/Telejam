package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object represents an incoming update.
 *
 * @author Michi Palazzo
 */
public class MessageUpdate extends Update {

  static final String MESSAGE_FIELD = "message";

  /**
   * New incoming message of any kind.
   */
  @SerializedName(MESSAGE_FIELD)
  private Message message;


  public MessageUpdate(long id, Message message) {
    super(id);
    this.message = Objects.requireNonNull(message);
  }


  /**
   * Getter for property {@link #message}.
   *
   * @return value for property {@link #message}
   */
  public Message getMessage() {
    return message;
  }

}
