package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * This object represents a general file (as opposed to photos,
 * voice messages and audio files).
 *
 * @author Michi Palazzo
 */
public class Document implements TelegramObject {

  static final String ID_FIELD = "file_id";
  static final String THUMBNAIL_FIELD = "thumb";
  static final String NAME_FIELD = "file_name";
  static final String MIME_TYPE_FIELD = "mime_type";
  static final String SIZE_FIELD = "file_size";

  /**
   * Unique file identifier.
   */
  @SerializedName(ID_FIELD)
  private String id;

  /**
   * Document thumbnail as defined by sender.
   */
  @SerializedName(THUMBNAIL_FIELD)
  private PhotoSize thumbnail;

  /**
   * Original filename as defined by sender.
   */
  @SerializedName(NAME_FIELD)
  private String name;

  /**
   * MIME type of the file as defined by sender.
   */
  @SerializedName(MIME_TYPE_FIELD)
  private String mimeType;

  /**
   * InputFile size.
   */
  @SerializedName(SIZE_FIELD)
  private Integer size;


  public Document(String id, PhotoSize thumbnail, String name, String mimeType, Integer size) {
    this.id = Objects.requireNonNull(id);
    this.thumbnail = thumbnail;
    this.name = name;
    this.mimeType = mimeType;
    this.size = size;
  }

  public Document(String id) {
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
   * Getter for property {@link #thumbnail}.
   *
   * @return optional value for property {@link #thumbnail}
   */
  public Optional<PhotoSize> getThumbnail() {
    return Optional.ofNullable(thumbnail);
  }

  /**
   * Getter for property {@link #name}.
   *
   * @return optional value for property {@link #name}
   */
  public Optional<String> getName() {
    return Optional.ofNullable(name);
  }

  /**
   * Getter for property {@link #mimeType}.
   *
   * @return optional value for property {@link #mimeType}
   */
  public Optional<String> getMimeType() {
    return Optional.ofNullable(mimeType);
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
    
    if (!(obj instanceof Document)) {
      return false;
    }
    
    Document document = (Document) obj;
    return id.equals(document.getId());
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }
  
}
