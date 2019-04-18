package io.github.ageofwar.telejam.inline;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.messages.Venue;

import java.util.Optional;

/**
 * Represents the content of a venue message to be sent as the result of an inline query.
 *
 * @author Michi Palazzo
 */
public class InputVenueMessageContent extends InputLocationMessageContent {
  
  static final String TITLE_FIELD = "title";
  static final String ADDRESS_FIELD = "address";
  static final String FOURSQUARE_ID_FIELD = "foursquare_id";
  static final String FOURSQUARE_TYPE_FIELD = "foursquare_type";
  
  /**
   * Name of the venue.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;
  
  /**
   * Address of the venue.
   */
  @SerializedName(ADDRESS_FIELD)
  private final String address;
  
  /**
   * Foursquare identifier of the venue, if known.
   */
  @SerializedName(FOURSQUARE_ID_FIELD)
  private final String foursquareId;
  
  /**
   * Foursquare type of the venue.
   */
  @SerializedName(FOURSQUARE_TYPE_FIELD)
  private final String foursquareType;
  
  
  public InputVenueMessageContent(Venue venue) {
    super(venue.getLocation());
    title = venue.getTitle();
    address = venue.getAddress();
    foursquareId = venue.getFoursquareId().orElse(null);
    foursquareType = venue.getFoursquareType().orElse(null);
  }
  
  
  /**
   * Getter for property {@link #title}.
   *
   * @return value for property {@link #title}
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * Getter for property {@link #address}.
   *
   * @return value for property {@link #address}
   */
  public String getAddress() {
    return address;
  }
  
  /**
   * Getter for property {@link #foursquareId}.
   *
   * @return optional value for property {@link #foursquareId}
   */
  public Optional<String> getFoursquareId() {
    return Optional.ofNullable(foursquareId);
  }
  
  /**
   * Getter for property {@link #foursquareType}.
   *
   * @return optional value for property {@link #foursquareType}
   */
  public Optional<String> getFoursquareType() {
    return Optional.ofNullable(foursquareType);
  }
  
}
