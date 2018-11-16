package io.github.ageofwar.telejam.locations;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

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
  private final float latitude;
  
  /**
   * Longitude as defined by sender.
   */
  @SerializedName(LONGITUDE_FIELD)
  private final float longitude;
  
  
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
   * Getter for property {@link #longitude}.
   *
   * @return value for property {@link #longitude}
   */
  public float getLongitude() {
    return longitude;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof Location)) {
      return false;
    }
    
    Location location = (Location) obj;
    return latitude == location.getLatitude() &&
        longitude == location.getLongitude();
  }
  
  @Override
  public int hashCode() {
    return 31 * Float.hashCode(latitude) + Float.hashCode(longitude);
  }
  
}
