package io.github.ageofwar.telejam.stickers;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;
import io.github.ageofwar.telejam.media.PhotoSize;

import java.util.Optional;

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
  static final String IS_ANIMATED_FIELD = "is_animated";
  static final String THUMBNAIL_FIELD = "thumb";
  
  /**
   * Sticker set name.
   */
  @SerializedName(NAME_FIELD)
  private final String name;
  
  /**
   * Sticker set title.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;
  
  /**
   * <code>true</code> if the sticker set contains masks, <code>false</code> otherwise.
   */
  @SerializedName(CONTAINS_MASKS_FIELD)
  private final boolean containsMasks;
  
  /**
   * List of all set stickers.
   */
  @SerializedName(STICKERS_FIELD)
  private final Sticker[] stickers;
  
  /**
   * True, if the sticker set contains animated stickers.
   */
  @SerializedName(IS_ANIMATED_FIELD)
  private boolean animated;
  
  /**
   * Sticker set thumbnail in the .WEBP or .TGS format.
   */
  @SerializedName(THUMBNAIL_FIELD)
  private PhotoSize thumbnail;
  
  public StickerSet(String name, String title, boolean containsMasks, Sticker[] stickers, boolean animated, PhotoSize thumbnail) {
    this.name = name;
    this.title = title;
    this.containsMasks = containsMasks;
    this.stickers = stickers;
    this.animated = animated;
    this.thumbnail = thumbnail;
  }
  
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
  
  /**
   * Getter for property {@link #animated}.
   *
   * @return value for property {@link #animated}
   */
  public boolean isAnimated() {
    return animated;
  }
  
  /**
   * Getter for property {@link #thumbnail}.
   *
   * @return optional value for property {@link #thumbnail}
   */
  public Optional<PhotoSize> getThumbnail() {
    return Optional.ofNullable(thumbnail);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof StickerSet)) {
      return false;
    }
    
    StickerSet set = (StickerSet) obj;
    return name.equals(set.getName());
  }
  
  @Override
  public int hashCode() {
    return name.hashCode();
  }
  
}
