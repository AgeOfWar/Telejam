package io.github.ageofwar.telejam.media;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.Objects;
import java.util.OptionalInt;

/**
 * This object represents one size of a photo or a file / sticker thumbnail.
 *
 * @author Michi Palazzo
 */
public class PhotoSize implements TelegramObject {
  
  static final String FILE_UNIQUE_ID_FIELD = "file_unique_id";
  static final String ID_FIELD = "file_id";
  static final String WIDTH_FIELD = "width";
  static final String HEIGHT_FIELD = "height";
  static final String SIZE_FIELD = "file_size";
  
  /**
   * Unique identifier for this file, which is
   * supposed to be the same over time and for different bots.
   * Can't be used to download or reuse the file.
   */
  @SerializedName(FILE_UNIQUE_ID_FIELD)
  private final String uniqueId;
  
  /**
   * Unique identifier for this file.
   */
  @SerializedName(ID_FIELD)
  private final String id;
  
  /**
   * Photo width.
   */
  @SerializedName(WIDTH_FIELD)
  private final int width;
  
  /**
   * Photo height.
   */
  @SerializedName(HEIGHT_FIELD)
  private final int height;
  
  /**
   * InputFile size.
   */
  @SerializedName(SIZE_FIELD)
  private Integer size;
  
  
  public PhotoSize(String uniqueId, String id, int width, int height, Integer size) {
    this.uniqueId = Objects.requireNonNull(uniqueId);
    this.id = Objects.requireNonNull(id);
    this.width = width;
    this.height = height;
    this.size = size;
  }
  
  public PhotoSize(String uniqueId, String id, int width, int height) {
    this.uniqueId = Objects.requireNonNull(uniqueId);
    this.id = Objects.requireNonNull(id);
    this.width = width;
    this.height = height;
  }
  
  /**
   * Getter for property {@link #uniqueId}.
   *
   * @return value for property {@link #uniqueId}
   */
  public String getUniqueId() {
    return uniqueId;
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
    return uniqueId.equals(photoSize.getUniqueId());
  }
  
  @Override
  public int hashCode() {
    return uniqueId.hashCode();
  }
  
}
