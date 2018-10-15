package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.UserProfilePhotos;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to get a list of profile pictures for a user.
 * Returns a UserProfilePhotos object.
 *
 * @author Michi Palazzo
 */
public class GetUserProfilePhotos implements TelegramMethod<UserProfilePhotos> {

  public static final String NAME = "getUserProfilePhotos";

  static final String USER_ID_FIELD = "user_id";
  static final String OFFSET_FIELD = "offset";
  static final String LIMIT_FIELD = "limit";

  /**
   * Unique identifier of the target user.
   */
  private Long userId;

  /**
   * Sequential number of the first photo to be returned.
   * By default, all photos are returned.
   */
  private Integer offset;

  /**
   * Limits the number of photos to be retrieved.
   * Values between 1—100 are accepted.
   * Defaults to 100.
   */
  private Integer limit;


  public GetUserProfilePhotos user(Long userId) {
    this.userId = userId;
    return this;
  }

  public GetUserProfilePhotos user(User user) {
    userId = user.getId();
    return this;
  }

  public GetUserProfilePhotos offset(Integer offset) {
    this.offset = offset;
    return this;
  }

  public GetUserProfilePhotos limit(Integer limit) {
    offset = limit;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(
        USER_ID_FIELD, userId,
        OFFSET_FIELD, offset,
        LIMIT_FIELD, limit
    );
  }
  
  @Override
  public Class<? extends UserProfilePhotos> getReturnType() {
    return UserProfilePhotos.class;
  }

}
