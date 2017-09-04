package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Sticker;

/**
 * Use this method to move a sticker in a set created by the bot to a specific position.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class SetStickerPositionInSet extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "setStickerPositionInSet";

  static final String STICKER_FIELD = "sticker";
  static final String POSITION_FIELD = "position";

  /**
   * File identifier of the sticker.
   */
  @SerializedName(STICKER_FIELD)
  private String sticker;

  /**
   * New sticker position in the set, zero-based.
   */
  @SerializedName(POSITION_FIELD)
  private Integer position;


  public SetStickerPositionInSet sticker(String sticker) {
    this.sticker = sticker;
    return this;
  }

  public SetStickerPositionInSet sticker(Sticker sticker) {
    this.sticker = sticker.getId();
    return this;
  }

  public SetStickerPositionInSet position(Integer position) {
    this.position = position;
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
