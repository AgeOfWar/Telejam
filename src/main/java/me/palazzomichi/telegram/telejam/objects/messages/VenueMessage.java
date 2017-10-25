package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.Venue;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Objects;

/**
 * This object represents a message.
 *
 * @author Michi Palazzo
 */
public class VenueMessage extends Message implements Forwardable {

  static final String VENUE_FIELD = "venue";

  /**
   * Information about the venue.
   */
  @SerializedName(VENUE_FIELD)
  private Venue venue;


  public VenueMessage(long id,
                      User sender,
                      long date,
                      Chat chat,
                      Message replyToMessage,
                      Long editDate,
                      String authorSignature,
                      Venue venue) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
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
