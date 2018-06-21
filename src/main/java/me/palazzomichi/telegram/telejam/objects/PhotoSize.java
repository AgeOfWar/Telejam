package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.OptionalInt;

/**
 * This object represents one size of a photo or a file / sticker thumbnail.
 *
 * @author Michi Palazzo
 */
public class PhotoSize implements TelegramObject {

  static final String ID_FIELD = "file_id";
  static final String WIDTH_FIELD = "width";
  static final String HEIGHT_FIELD = "height";
  static final String SIZE_FIELD = "file_size";

  /**
   * Unique identifier for this file.
   */
  @SerializedName(ID_FIELD)
  private String id;

  /**
   * Photo width.
   */
  @SerializedName(WIDTH_FIELD)
  private int width;

  /**
   * Photo height.
   */
  @SerializedName(HEIGHT_FIELD)
  private int height;

  /**
   * InputFile size.
   */
  @SerializedName(SIZE_FIELD)
  private Integer size;


  public PhotoSize(String id, int width, int height, Integer size) {
    this.id = id;
    this.width = width;
    this.height = height;
    this.size = size;
  }

  public PhotoSize(String id, int width, int height) {
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
    
    if (!(obj instanceof PhotoSize)) {
      return false;
    }
    
    PhotoSize photoSize = (PhotoSize) obj;
    return id.equals(photoSize.getId());
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }
  
}
