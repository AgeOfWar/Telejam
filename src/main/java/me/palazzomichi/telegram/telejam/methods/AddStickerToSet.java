package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.MaskPosition;
import me.palazzomichi.telegram.telejam.objects.UploadFile;
import me.palazzomichi.telegram.telejam.objects.User;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to add a new sticker to a set created by the bot.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class AddStickerToSet implements TelegramMethod<Boolean> {

  public static final String NAME = "addStickerToSet";

  static final String USER_ID_FIELD = "user_id";
  static final String NAME_FIELD = "name";
  static final String STICKER_FIELD = "png_sticker";
  static final String EMOJIS_FIELD = "emojis";
  static final String MASK_POSITION_FIELD = "mask_position";

  /**
   * User identifier of sticker set owner.
   */
  private Long userId;

  /**
   * Sticker set name.
   */
  private String name;

  /**
   * Png image with the sticker, must be up to 512 kilobytes in size,
   * dimensions must not exceed 512px, and either width or height must be exactly 512px.
   * Pass a file_id as a String to send a file that already exists on the Telegram servers,
   * pass an HTTP URL as a String for Telegram to get a file from the Internet,
   * or upload a new one using multipart/form-data.
   */
  private String sticker;

  /**
   * One or more emoji corresponding to the sticker.
   */
  private String emojis;

  /**
   * A JSON-serialized object for position where the mask should be placed on faces.
   */
  private MaskPosition maskPosition;

  /**
   * True if the sticker is not present in Telegram servers.
   */
  private UploadFile newSticker;


  public AddStickerToSet user(long userId) {
    this.userId = userId;
    return this;
  }

  public AddStickerToSet user(User user) {
    userId = user.getId();
    return this;
  }

  public AddStickerToSet name(String name) {
    this.name = name;
    return this;
  }

  public AddStickerToSet sticker(String sticker) {
    newSticker = null;
    this.sticker = sticker;
    return this;
  }

  public AddStickerToSet sticker(UploadFile newSticker) {
    this.newSticker = newSticker;
    sticker = null;
    return this;
  }

  public AddStickerToSet emojis(String emojis) {
    this.emojis = emojis;
    return this;
  }

  public AddStickerToSet maskPosition(MaskPosition maskPosition) {
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
        STICKER_FIELD, sticker,
        EMOJIS_FIELD, emojis,
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
