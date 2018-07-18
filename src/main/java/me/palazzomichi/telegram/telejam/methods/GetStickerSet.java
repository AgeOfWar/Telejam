package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.StickerSet;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to get a sticker set.
 * On success, a StickerSet object is returned.
 *
 * @author Michi Palazzo
 */
public class GetStickerSet implements TelegramMethod<StickerSet> {

  public static final String NAME = "getStickerSet";

  static final String NAME_FIELD = "name";

  /**
   * Name of the sticker set.
   */
  private String name;


  public GetStickerSet name(String name) {
    this.name = name;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(NAME_FIELD, name);
  }
  
  @Override
  public Class<? extends StickerSet> getReturnType() {
    return StickerSet.class;
  }

}
