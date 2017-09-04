package me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents;

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


  public InputLocationMessageContent(float latitude, float longitude) {
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
