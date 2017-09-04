package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.StickerSet;

/**
 * Use this method to get a sticker set.
 * On success, a StickerSet object is returned.
 *
 * @author Michi Palazzo
 */
public class GetStickerSet extends JsonTelegramMethod<StickerSet> {

  public static final String NAME = "getStickerSet";

  static final String NAME_FIELD = "name";

  /**
   * Name of the sticker set.
   */
  @SerializedName(NAME_FIELD)
  private String name;


  public GetStickerSet(String name) {
    this.name = name;
  }


  public GetStickerSet name(String name) {
    this.name = name;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends StickerSet> getReturnType(JsonElement response) {
    return StickerSet.class;
  }

}
