package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.UserProfilePhotos;

/**
 * Use this method to get a list of profile pictures for a user.
 * Returns a UserProfilePhotos object.
 *
 * @author Michi Palazzo
 */
public class GetUserProfilePhotos extends JsonTelegramMethod<UserProfilePhotos> {

  public static final String NAME = "getUserProfilePhotos";

  static final String USER_ID_FIELD = "user_id";
  static final String OFFSET_FIELD = "offset";
  static final String LIMIT_FIELD = "limit";

  /**
   * Unique identifier of the target user.
   */
  @SerializedName(USER_ID_FIELD)
  private Long userId;

  /**
   * Sequential number of the first photo to be returned.
   * By default, all photos are returned.
   */
  @SerializedName(OFFSET_FIELD)
  private Integer offset;

  /**
   * Limits the number of photos to be retrieved.
   * Values between 1—100 are accepted.
   * Defaults to 100.
   */
  @SerializedName(LIMIT_FIELD)
  private Integer limit;


  public GetUserProfilePhotos user(Long userId) {
    this.userId = userId;
    return this;
  }

  public GetUserProfilePhotos user(User user) {
    this.userId = user.getId();
    return this;
  }

  public GetUserProfilePhotos offset(Integer offset) {
    this.offset = offset;
    return this;
  }

  public GetUserProfilePhotos limit(Integer limit) {
    this.offset = limit;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends UserProfilePhotos> getReturnType(JsonElement response) {
    return UserProfilePhotos.class;
  }

}
