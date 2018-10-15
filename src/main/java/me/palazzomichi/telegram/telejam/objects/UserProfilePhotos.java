package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Objects;

/**
 * This object represent a user's profile pictures.
 *
 * @author Michi Palazzo
 */
public class UserProfilePhotos implements TelegramObject {
  
  static final String TOTAL_COUNT_FIELD = "total_count";
  static final String PHOTOS_FIELD = "photos";
  
  /**
   * Total number of profile pictures the target user has.
   */
  @SerializedName(TOTAL_COUNT_FIELD)
  private final int totalCount;
  
  /**
   * Requested profile pictures (in up to 4 sizes each).
   */
  @SerializedName(PHOTOS_FIELD)
  private final PhotoSize[][] photos;
  
  
  public UserProfilePhotos(int totalCount, PhotoSize[][] photos) {
    this.totalCount = totalCount;
    this.photos = Objects.requireNonNull(photos);
  }
  
  
  /**
   * Getter for property {@link #totalCount}.
   *
   * @return value for property {@link #totalCount}
   */
  public int getTotalCount() {
    return totalCount;
  }
  
  /**
   * Getter for property {@link #photos}.
   *
   * @return value for property {@link #photos}
   */
  public PhotoSize[][] getPhotos() {
    return photos;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof UserProfilePhotos)) {
      return false;
    }
    
    UserProfilePhotos userProfilePhotos = (UserProfilePhotos) obj;
    return totalCount == userProfilePhotos.totalCount &&
        Arrays.deepEquals(photos, userProfilePhotos.getPhotos());
  }
  
  @Override
  public int hashCode() {
    return 31 * totalCount + Arrays.deepHashCode(photos);
  }
  
}
