package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * This object represents a voice note.
 *
 * @author Michi Palazzo
 */
public class Voice implements TelegramObject {

  static final String ID_FIELD = "file_id";
  static final String DURATION_FIELD = "duration";
  static final String MIME_TYPE_FIELD = "mime_type";
  static final String SIZE_FIELD = "file_size";

  /**
   * Unique identifier for this file.
   */
  @SerializedName(ID_FIELD)
  private String id;

  /**
   * Duration of the audio in seconds as defined by sender.
   */
  @SerializedName(DURATION_FIELD)
  private int duration;

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


  public Voice(String id, int duration, String mimeType, Integer size) {
    this.id = Objects.requireNonNull(id);
    this.duration = duration;
    this.mimeType = mimeType;
    this.size = size;
  }

  public Voice(String id, int duration) {
    this.id = Objects.requireNonNull(id);
    this.duration = duration;
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
   * Getter for property {@link #duration}.
   *
   * @return value for property {@link #duration}
   */
  public int getDuration() {
    return duration;
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
    
    if (!(obj instanceof Voice)) {
      return false;
    }
    
    Voice voice = (Voice) obj;
    return id.equals(voice.getId());
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }

}
