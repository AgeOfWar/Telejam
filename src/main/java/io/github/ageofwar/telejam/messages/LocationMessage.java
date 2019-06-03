package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.users.User;

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
  private final Location location;
  
  
  public LocationMessage(long id,
                         User sender,
                         long date,
                         Chat chat,
                         Message replyToMessage,
                         Long editDate,
                         String authorSignature,
                         Location location,
                         InlineKeyboardMarkup replyMarkup) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature, replyMarkup);
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
