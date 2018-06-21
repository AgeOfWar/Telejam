package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

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

  /**
   * Name of the venue.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Address of the venue.
   */
  @SerializedName(ADDRESS_FIELD)
  private String address;

  /**
   * Foursquare identifier of the venue, if known.
   */
  @SerializedName(FOURSQUARE_ID_FIELD)
  private String foursquareId;


  public InputVenueMessageContent(Venue venue) {
    super(venue.getLocation());
    this.title = venue.getTitle();
    this.address = venue.getAddress();
    this.foursquareId = venue.getFoursquareId().orElse(null);
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

}
