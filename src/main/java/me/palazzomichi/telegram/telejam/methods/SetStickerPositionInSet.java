package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Sticker;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to move a sticker in a set created by the bot to a specific position.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class SetStickerPositionInSet implements TelegramMethod<Boolean> {

  public static final String NAME = "setStickerPositionInSet";

  static final String STICKER_FIELD = "sticker";
  static final String POSITION_FIELD = "position";

  /**
   * File identifier of the sticker.
   */
  private String sticker;

  /**
   * New sticker position in the set, zero-based.
   */
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
  public Map<String, Object> getParameters() {
    return mapOf(
        STICKER_FIELD, sticker,
        POSITION_FIELD, position
    );
  }
  
  @Override
  public Class<? extends Boolean> getReturnType() {
    return Boolean.class;
  }

}
