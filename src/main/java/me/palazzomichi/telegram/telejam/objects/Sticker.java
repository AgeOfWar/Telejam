package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * This object represents a sticker.
 *
 * @author Michi Palazzo
 */
public class Sticker implements TelegramObject {

  static final String ID_FIELD = "file_id";
  static final String WIDTH_FIELD = "width";
  static final String HEIGHT_FIELD = "height";
  static final String THUMBNAIL_FIELD = "thumb";
  static final String EMOJI_FIELD = "emoji";
  static final String SET_NAME_FIELD = "set_name";
  static final String MASK_POSITION_FIELD = "mask_position";
  static final String SIZE_FIELD = "file_size";

  /**
   * Unique identifier for this file.
   */
  @SerializedName(ID_FIELD)
  private final String id;

  /**
   * Sticker width.
   */
  @SerializedName(WIDTH_FIELD)
  private final int width;

  /**
   * Sticker height.
   */
  @SerializedName(HEIGHT_FIELD)
  private final int height;

  /**
   * Sticker thumbnail in .webp or .jpg format.
   */
  @SerializedName(THUMBNAIL_FIELD)
  private PhotoSize thumbnail;

  /**
   * Emoji associated with the sticker.
   */
  @SerializedName(EMOJI_FIELD)
  private String emoji;

  /**
   * Name of the sticker set to which the sticker belongs.
   */
  @SerializedName(SET_NAME_FIELD)
  private String setName;

  /**
   * For mask stickers, the position where the mask should be placed.
   */
  @SerializedName(MASK_POSITION_FIELD)
  private MaskPosition maskPosition;

  /**
   * InputFile size.
   */
  @SerializedName(SIZE_FIELD)
  private Integer size;


  public Sticker(String id,
                 int width,
                 int height,
                 PhotoSize thumbnail,
                 String emoji,
                 String setName,
                 MaskPosition maskPosition,
                 Integer size) {
    this.id = Objects.requireNonNull(id);
    this.width = width;
    this.height = height;
    this.thumbnail = thumbnail;
    this.emoji = emoji;
    this.setName = setName;
    this.maskPosition = maskPosition;
    this.size = size;
  }

  public Sticker(String id, int width, int height) {
    this.id = id;
    this.width = width;
    this.height = height;
  }


  /**
   * Getter for property {@link #id}.
   *
   * @return value for property {@link #id}
   */
  public String getId() {
    return id;
  }

  /**
   * Getter for property {@link #width}.
   *
   * @return value for property {@link #width}
   */
  public int getWidth() {
    return width;
  }

  /**
   * Getter for property {@link #height}.
   *
   * @return value for property {@link #height}
   */
  public int getHeight() {
    return height;
  }

  /**
   * Getter for property {@link #thumbnail}.
   *
   * @return optional value for property {@link #thumbnail}
   */
  public Optional<PhotoSize> getThumbnail() {
    return Optional.ofNullable(thumbnail);
  }

  /**
   * Getter for property {@link #emoji}.
   *
   * @return optional value for property {@link #emoji}
   */
  public Optional<String> getEmoji() {
    return Optional.ofNullable(emoji);
  }

  /**
   * Getter for property {@link #setName}.
   *
   * @return optional value for property {@link #setName}
   */
  public Optional<String> getSetName() {
    return Optional.ofNullable(setName);
  }

  /**
   * Getter for property {@link #maskPosition}.
   *
   * @return optional value for property {@link #maskPosition}
   */
  public Optional<MaskPosition> getMaskPosition() {
    return Optional.ofNullable(maskPosition);
  }

  /**
   * Getter for property {@link #size}.
   *
   * @return optional value for property {@link #size}
   */
  public OptionalInt getSize() {
    return size == null ? OptionalInt.empty() : OptionalInt.of(size);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof Sticker)) {
      return false;
    }
    
    Sticker sticker = (Sticker) obj;
    return id.equals(sticker.getId());
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }

}
