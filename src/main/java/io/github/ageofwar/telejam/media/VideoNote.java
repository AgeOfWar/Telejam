package io.github.ageofwar.telejam.media;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * This object represents a video message.
 *
 * @author Michi Palazzo
 */
public class VideoNote implements TelegramObject {
  
  static final String FILE_UNIQUE_ID_FIELD = "file_unique_id";
  static final String ID_FIELD = "file_id";
  static final String LENGTH_FIELD = "length";
  static final String DURATION_FIELD = "duration";
  static final String THUMBNAIL_FIELD = "thumb";
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
   * Video width and height as defined by sender.
   */
  @SerializedName(LENGTH_FIELD)
  private final int length;
  
  /**
   * Duration of the video in seconds as defined by sender.
   */
  @SerializedName(DURATION_FIELD)
  private final int duration;
  
  /**
   * Video thumbnail.
   */
  @SerializedName(THUMBNAIL_FIELD)
  private PhotoSize thumbnail;
  
  /**
   * InputFile size.
   */
  @SerializedName(SIZE_FIELD)
  private Integer size;
  
  
  public VideoNote(String uniqueId, String id, int length, int duration, PhotoSize thumbnail, Integer size) {
    this.uniqueId = Objects.requireNonNull(uniqueId);
    this.id = Objects.requireNonNull(id);
    this.length = length;
    this.duration = duration;
    this.thumbnail = thumbnail;
    this.size = size;
  }
  
  public VideoNote(String uniqueId, String id, int length, int duration) {
    this.uniqueId = Objects.requireNonNull(uniqueId);
    this.id = Objects.requireNonNull(id);
    this.length = length;
    this.duration = duration;
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
   * Getter for property {@link #length}.
   *
   * @return value for property {@link #length}
   */
  public int getLength() {
    return length;
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
   * Getter for property {@link #thumbnail}.
   *
   * @return optional value for property {@link #thumbnail}
   */
  public Optional<PhotoSize> getThumbnail() {
    return Optional.ofNullable(thumbnail);
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
    
    if (!(obj instanceof VideoNote)) {
      return false;
    }
    
    VideoNote videoNote = (VideoNote) obj;
    return uniqueId.equals(videoNote.getUniqueId());
  }
  
  @Override
  public int hashCode() {
    return uniqueId.hashCode();
  }
  
}
