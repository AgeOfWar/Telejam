package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

/**
 * This object represents a point on the map.
 *
 * @author Michi Palazzo
 */
public class Location implements TelegramObject {

  static final String LONGITUDE_FIELD = "longitude";
  static final String LATITUDE_FIELD = "latitude";

  /**
   * Latitude as defined by sender.
   */
  @SerializedName(LATITUDE_FIELD)
  private float latitude;

  /**
   * Longitude as defined by sender.
   */
  @SerializedName(LONGITUDE_FIELD)
  private float longitude;


  /**
   * Constructs a location.
   *
   * @param longitude the longitude
   * @param latitude  the latitude
   */
  public Location(float latitude, float longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }


  /**
   * Getter for property {@link #latitude}.
   *
   * @return value for property {@link #latitude}
   */
  public float getLatitude() {
    return latitude;
  }

  /**
   * Setter for property {@link #latitude}.
   *
   * @param latitude value for property {@link #latitude}
   */
  public void setLatitude(float latitude) {
    this.latitude = latitude;
  }

  /**
   * Getter for property {@link #longitude}.
   *
   * @return value for property {@link #longitude}
   */
  public float getLongitude() {
    return longitude;
  }

  /**
   * Setter for property {@link #longitude}.
   *
   * @param longitude value for property {@link #longitude}
   */
  public void setLongitude(float longitude) {
    this.longitude = longitude;
  }

}
