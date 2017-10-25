package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a venue.
 *
 * @author Michi Palazzo
 */
public class Venue implements TelegramObject {

  static final String LOCATION_FIELD = "location";
  static final String TITLE_FIELD = "title";
  static final String ADDRESS_FIELD = "address";
  static final String FOURSQUARE_ID_FIELD = "foursquare_id";

  /**
   * Venue location.
   */
  @SerializedName(LOCATION_FIELD)
  private Location location;

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
   * Foursquare identifier of the venue.
   */
  @SerializedName(FOURSQUARE_ID_FIELD)
  private String foursquareId;


  public Venue(Location location, String title, String address, String foursquareId) {
    this.location = Objects.requireNonNull(location);
    this.title = Objects.requireNonNull(title);
    this.address = Objects.requireNonNull(address);
    this.foursquareId = foursquareId;
  }


  /**
   * Getter for property {@link #location}.
   *
   * @return value for property {@link #location}
   */
  public Location getLocation() {
    return location;
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
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof Venue)) {
      return false;
    }
    
    Venue venue = (Venue) obj;
    return location.equals(venue.getLocation());
  }
  
  @Override
  public int hashCode() {
    return location.hashCode();
  }

}
