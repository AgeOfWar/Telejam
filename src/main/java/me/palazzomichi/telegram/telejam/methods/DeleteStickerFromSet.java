package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Sticker;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to delete a sticker from a set created by the bot.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class DeleteStickerFromSet implements TelegramMethod<Boolean> {

  public static final String NAME = "deleteStickerFromSet";

  static final String STICKER_FIELD = "sticker";

  /**
   * File identifier of the sticker.
   */
  private String sticker;


  public DeleteStickerFromSet sticker(String sticker) {
    this.sticker = sticker;
    return this;
  }

  public DeleteStickerFromSet sticker(Sticker sticker) {
    this.sticker = sticker.getId();
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(STICKER_FIELD, sticker);
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
