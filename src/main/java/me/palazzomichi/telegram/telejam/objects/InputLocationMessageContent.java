package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the content of a location message to be sent as the result of an inline query.
 *
 * @author Michi Palazzo
 */
public class InputLocationMessageContent implements InputMessageContent {

  static final String LATITUDE_FIELD = "latitude";
  static final String LONGITUDE_FIELD = "longitude";

  /**
   * Latitude of the location in degrees.
   */
  @SerializedName(LATITUDE_FIELD)
  private float latitude;

  /**
   * Longitude of the location in degrees.
   */
  @SerializedName(LONGITUDE_FIELD)
  private float longitude;


  public InputLocationMessageContent(Location location) {
    this.latitude = location.getLatitude();
    this.longitude = location.getLongitude();
  }
  
  /**
   * Returns the location of the input location message content.
   *
   * @return the location of the input location message content
   */
  public Location getLocation() {
    return new Location(latitude, longitude);
  }

}
