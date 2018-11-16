package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.stickers.MaskPosition;
import io.github.ageofwar.telejam.connection.UploadFile;
import io.github.ageofwar.telejam.users.User;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to create new sticker set owned by a user.
 * The bot will be able to edit the created sticker set.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class CreateNewStickerSet implements TelegramMethod<Boolean> {
  
  public static final String NAME = "createNewStickerSet";
  
  static final String USER_ID_FIELD = "user_id";
  static final String NAME_FIELD = "name";
  static final String TITLE_FIELD = "title";
  static final String STICKER_FIELD = "png_sticker";
  static final String EMOJIS_FIELD = "emojis";
  static final String CONTAINS_MASKS_FIELD = "contains_masks";
  static final String MASK_POSITION_FIELD = "mask_position";
  
  /**
   * User identifier of created sticker set owner.
   */
  private Long userId;
  
  /**
   * Short name of sticker set, to be used in <code>t.me/addstickers/</code> URLs (e.g., animals).
   * Can contain only english letters, digits and underscores.
   * Must begin with a letter, can't contain consecutive underscores and must
   * end in <code>"_by_&lt;bot username&gt;"</code>.
   * <code>&lt;bot_username&gt;</code> is case insensitive.
   * 1-64 characters.
   */
  private String name;
  
  /**
   * Sticker set title, 1-64 characters.
   */
  private String title;
  
  /**
   * Png image with the sticker, must be up to 512 kilobytes in size,
   * dimensions must not exceed 512px, and either width or height must be
   * exactly 512px. Pass a file_id as a String to send a file that already
   * exists on the Telegram servers, pass an HTTP URL as a String for
   * Telegram to get a file from the Internet, or upload a new one using multipart/form-data.
   */
  private String sticker;
  
  /**
   * One or more emoji corresponding to the sticker.
   */
  private String emojis;
  
  /**
   * Pass True, if a set of mask stickers should be created.
   */
  private Boolean containsMasks;
  
  /**
   * Point where the mask should be placed on faces.
   */
  private MaskPosition maskPosition;
  
  /**
   * Sticker not present in Telegram servers.
   */
  private UploadFile newSticker;
  
  
  public CreateNewStickerSet user(long userId) {
    this.userId = userId;
    return this;
  }
  
  public CreateNewStickerSet user(User user) {
    userId = user.getId();
    return this;
  }
  
  public CreateNewStickerSet name(String name) {
    this.name = name;
    return this;
  }
  
  public CreateNewStickerSet title(String title) {
    this.title = title;
    return this;
  }
  
  public CreateNewStickerSet sticker(String sticker) {
    newSticker = null;
    this.sticker = sticker;
    return this;
  }
  
  public CreateNewStickerSet sticker(UploadFile newSticker) {
    sticker = null;
    this.newSticker = newSticker;
    return this;
  }
  
  public CreateNewStickerSet emojis(String emojis) {
    this.emojis = emojis;
    return this;
  }
  
  public CreateNewStickerSet containsMasks(Boolean containsMasks) {
    this.containsMasks = containsMasks;
    return this;
  }
  
  public CreateNewStickerSet containsMask(boolean containsMasks) {
    this.containsMasks = containsMasks;
    return this;
  }
  
  public CreateNewStickerSet containsMask() {
    containsMasks = true;
    return this;
  }
  
  public CreateNewStickerSet maskPosition(MaskPosition maskPosition) {
    this.maskPosition = maskPosition;
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
        TITLE_FIELD, title,
        STICKER_FIELD, sticker,
        EMOJIS_FIELD, emojis,
        CONTAINS_MASKS_FIELD, containsMasks,
        MASK_POSITION_FIELD, maskPosition
    );
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(STICKER_FIELD, newSticker);
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }
  
}
