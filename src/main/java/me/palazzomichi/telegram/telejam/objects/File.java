package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * This object represents a file ready to be downloaded.
 *
 * @author Michi Palazzo
 */
public class File implements TelegramObject {

  static final String ID_FIELD = "file_id";
  static final String SIZE_FIELD = "file_size";
  static final String PATH_FIELD = "file_path";

  /**
   * Unique identifier for this file.
   */
  @SerializedName(ID_FIELD)
  private String id;

  /**
   * File size, if known.
   */
  @SerializedName(SIZE_FIELD)
  private Integer size;

  /**
   * File path.
   */
  @SerializedName(PATH_FIELD)
  private String path;


  public File(String id, Integer size, String path) {
    this.id = Objects.requireNonNull(id);
    this.size = size;
    this.path = path;
  }

  public File(String id) {
    this.id = Objects.requireNonNull(id);
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
   * Getter for property {@link #size}.
   *
   * @return optional value for property {@link #size}
   */
  public OptionalInt getSize() {
    return size == null ? OptionalInt.empty() : OptionalInt.of(size);
  }

  /**
   * Getter for property {@link #path}.
   *
   * @return optional value for property {@link #path}
   */
  public Optional<String> getPath() {
    return Optional.ofNullable(path);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof File)) {
      return false;
    }
    
    File file = (File) obj;
    return id.equals(file.getId());
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }

}
