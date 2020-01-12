package io.github.ageofwar.telejam.connection;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * This object represents a file ready to be downloaded.
 *
 * @author Michi Palazzo
 */
public class InputFile implements TelegramObject {
  
  static final String FILE_UNIQUE_ID_FIELD = "file_unique_id";
  static final String ID_FIELD = "file_id";
  static final String SIZE_FIELD = "file_size";
  static final String PATH_FIELD = "file_path";
  
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
   * InputFile size, if known.
   */
  @SerializedName(SIZE_FIELD)
  private Integer size;
  
  /**
   * InputFile path.
   */
  @SerializedName(PATH_FIELD)
  private String path;
  
  
  public InputFile(String uniqueId, String id, Integer size, String path) {
    this.uniqueId = Objects.requireNonNull(uniqueId);
    this.id = Objects.requireNonNull(id);
    this.size = size;
    this.path = path;
  }
  
  public InputFile(String uniqueId, String id) {
    this.uniqueId = Objects.requireNonNull(uniqueId);
    this.id = Objects.requireNonNull(id);
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
    
    if (!(obj instanceof InputFile)) {
      return false;
    }
    
    InputFile file = (InputFile) obj;
    return uniqueId.equals(file.getUniqueId());
  }
  
  @Override
  public int hashCode() {
    return uniqueId.hashCode();
  }
  
}
