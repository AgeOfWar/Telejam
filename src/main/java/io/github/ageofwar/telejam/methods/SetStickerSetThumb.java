package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.connection.UploadFile;
import io.github.ageofwar.telejam.users.User;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to set the thumbnail of a sticker set.
 * Animated thumbnails can be set for animated sticker sets only.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class SetStickerSetThumb implements TelegramMethod<Boolean> {
  
  public static final String NAME = "setStickerSetThumb";
  
  static final String USER_ID_FIELD = "user_id";
  static final String NAME_FIELD = "name";
  static final String THUMBNAIL_FIELD = "thumb";
  
  /**
   * User identifier of sticker set owner.
   */
  private Long userId;
  
  /**
   * Sticker set name.
   */
  private String name;
  
  /**
   * A PNG image with the thumbnail,
   * must be up to 128 kilobytes in size and have width and height exactly 100px,
   * or a TGS animation with the thumbnail up to 32 kilobytes in size.
   */
  private String thumbnail;
  
  /**
   * New PNG thumbnail.
   */
  private UploadFile newThumbnail;
  
  
  public SetStickerSetThumb user(long userId) {
    this.userId = userId;
    return this;
  }
  
  public SetStickerSetThumb user(User user) {
    userId = user.getId();
    return this;
  }
  
  public SetStickerSetThumb name(String name) {
    this.name = name;
    return this;
  }
  
  public SetStickerSetThumb thumbnail(String thumbnail) {
    newThumbnail = null;
    this.thumbnail = thumbnail;
    return this;
  }
  
  public SetStickerSetThumb thumbnail(UploadFile newThumbnail) {
    thumbnail = null;
    this.newThumbnail = newThumbnail;
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
        NAME_FIELD, name,
        THUMBNAIL_FIELD, thumbnail
    );
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(THUMBNAIL_FIELD, newThumbnail);
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }
  
}
