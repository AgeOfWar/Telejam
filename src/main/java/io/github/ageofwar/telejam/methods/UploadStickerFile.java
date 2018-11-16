package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.connection.InputFile;
import io.github.ageofwar.telejam.connection.UploadFile;
import io.github.ageofwar.telejam.users.User;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to upload a .png file with a sticker for later
 * use in createNewStickerSet and addStickerToSet methods (can be used multiple times).
 * Returns the uploaded File on success.
 *
 * @author Michi Palazzo
 */
public class UploadStickerFile implements TelegramMethod<InputFile> {

  public static final String NAME = "uploadStickerFile";

  static final String USER_ID_FIELD = "user_id";
  static final String STICKER_FIELD = "png_sticker";

  /**
   * User identifier of sticker file owner.
   */
  private Long userId;

  /**
   * Png image with the sticker, must be up to 512 kilobytes in size,
   * dimensions must not exceed 512px, and either width or height must be exactly 512px.
   */
  private UploadFile sticker;


  public UploadStickerFile user(long userId) {
    this.userId = userId;
    return this;
  }

  public UploadStickerFile user(User user) {
    userId = user.getId();
    return this;
  }

  public UploadStickerFile sticker(UploadFile sticker) {
    this.sticker = sticker;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(USER_ID_FIELD, userId);
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(STICKER_FIELD, sticker);
  }
  
  @Override
  public Class<? extends InputFile> getReturnType() {
    return InputFile.class;
  }

}
