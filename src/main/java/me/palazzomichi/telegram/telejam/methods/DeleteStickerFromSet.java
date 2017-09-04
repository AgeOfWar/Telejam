package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import me.palazzomichi.telegram.telejam.objects.Sticker;

/**
 * Use this method to delete a sticker from a set created by the bot.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class DeleteStickerFromSet extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "deleteStickerFromSet";

  static final String STICKER_FIELD = "sticker";

  /**
   * File identifier of the sticker.
   */
  private String sticker;


  public DeleteStickerFromSet(String sticker) {
    this.sticker = sticker;
  }

  public DeleteStickerFromSet(Sticker sticker) {
    this(sticker.getId());
  }


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
  public Class<? extends Boolean> getReturnType(JsonElement response) {
    return Boolean.class;
  }

}
