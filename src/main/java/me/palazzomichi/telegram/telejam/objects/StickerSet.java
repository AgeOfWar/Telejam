package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

/**
 * This object represents a sticker set.
 *
 * @author Michi Palazzo
 */
public class StickerSet implements TelegramObject {

  static final String NAME_FIELD = "name";
  static final String TITLE_FIELD = "title";
  static final String CONTAINS_MASKS_FIELD = "contains_masks";
  static final String STICKERS_FIELD = "stickers";

  /**
   * Sticker set name.
   */
  @SerializedName(NAME_FIELD)
  private String name;

  /**
   * Sticker set title.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * <code>true</code> if the sticker set contains masks, <code>false</code> otherwise.
   */
  @SerializedName(CONTAINS_MASKS_FIELD)
  private boolean containsMasks;

  /**
   * List of all set stickers.
   */
  @SerializedName(STICKERS_FIELD)
  private Sticker[] stickers;


  public StickerSet(String name, String title, boolean containsMasks, Sticker[] stickers) {
    this.name = name;
    this.title = title;
    this.containsMasks = containsMasks;
    this.stickers = stickers;
  }


  /**
   * Getter for property {@link #name}.
   *
   * @return value for property {@link #name}
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for property {@link #title}.
   *
   * @return value for property {@link #title}
   */
  public String getTitle() {
    return title;
  }

  /**
   * Getter for property {@link #containsMasks}.
   *
   * @return value for property {@link #containsMasks}
   */
  public boolean isContainsMasks() {
    return containsMasks;
  }

  /**
   * Getter for property {@link #stickers}.
   *
   * @return value for property {@link #stickers}
   */
  public Sticker[] getStickers() {
    return stickers;
  }

}
