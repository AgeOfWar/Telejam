package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Location;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Objects;

/**
 * This object represents a location message.
 *
 * @author Michi Palazzo
 */
public class LocationMessage extends Message {

  static final String LOCATION_FIELD = "location";

  /**
   * Information about the location.
   */
  @SerializedName(LOCATION_FIELD)
  private Location location;


  public LocationMessage(long id,
                         User sender,
                         long date,
                         Chat chat,
                         User forwardMessageSender,
                         Chat forwardMessageChat,
                         Long forwardMessageId,
                         Long forwardMessageDate,
                         Message replyToMessage,
                         Long editDate,
                         String authorSignature,
                         String forwardSignature,
                         Location location) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.location = Objects.requireNonNull(location);
  }


  /**
   * Getter for property {@link #location}.
   *
   * @return value for property {@link #location}
   */
  public Location getLocation() {
    return location;
  }

}
