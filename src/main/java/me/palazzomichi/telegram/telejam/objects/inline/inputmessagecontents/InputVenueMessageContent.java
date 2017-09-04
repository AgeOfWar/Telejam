package me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Location;

import java.util.Objects;
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


  public InputVenueMessageContent(float latitude, float longitude, String title, String address, String foursquareId) {
    super(latitude, longitude);
    this.title = Objects.requireNonNull(title);
    this.address = Objects.requireNonNull(address);
    this.foursquareId = foursquareId;
  }

  public InputVenueMessageContent(float latitude, float longitude, String title, String address) {
    this(latitude, longitude, title, address, null);
  }

  public InputVenueMessageContent(Location location, String title, String address, String foursquareId) {
    this(location.getLatitude(), location.getLongitude(), title, address, foursquareId);
  }

  public InputVenueMessageContent(Location location, String title, String address) {
    this(location, title, address, null);
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
   * Setter for property {@link #title}.
   *
   * @param title value for property {@link #title}
   */
  public void setTitle(String title) {
    this.title = Objects.requireNonNull(title);
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
   * Setter for property {@link #address}.
   *
   * @param address value for property {@link #address}
   */
  public void setAddress(String address) {
    this.address = Objects.requireNonNull(address);
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
   * Setter for property {@link #foursquareId}.
   *
   * @param foursquareId value for property {@link #foursquareId}
   */
  public void setFoursquareId(String foursquareId) {
    this.foursquareId = foursquareId;
  }

}
