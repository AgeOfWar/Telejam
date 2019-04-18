package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;

/**
 * This object represents a message.
 *
 * @author Michi Palazzo
 */
public class VenueMessage extends Message {
  
  static final String VENUE_FIELD = "venue";
  
  /**
   * Information about the venue.
   */
  @SerializedName(VENUE_FIELD)
  private final Venue venue;
  
  
  public VenueMessage(long id,
                      User sender,
                      long date,
                      Chat chat,
                      Message replyToMessage,
                      String authorSignature,
                      Venue venue) {
    super(id, sender, date, chat, replyToMessage, null, authorSignature);
    this.venue = Objects.requireNonNull(venue);
  }
  
  
  /**
   * Getter for property {@link #venue}.
   *
   * @return value for property {@link #venue}
   */
  public Venue getVenue() {
    return venue;
  }
  
}
