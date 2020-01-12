package io.github.ageofwar.telejam.media;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * This object represents an audio file to be treated as music by the Telegram clients.
 *
 * @author Michi Palazzo
 */
public class Audio implements TelegramObject {
  
  static final String FILE_UNIQUE_ID_FIELD = "file_unique_id";
  static final String ID_FIELD = "file_id";
  static final String DURATION_FIELD = "duration";
  static final String PERFORMER_FIELD = "performer";
  static final String TITLE_FIELD = "title";
  static final String MIME_TYPE_FIELD = "mime_type";
  static final String SIZE_FIELD = "file_size";
  static final String THUMBNAIL_FIELD = "thumb";
  
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
   * Duration of the audio in seconds as defined by sender.
   */
  @SerializedName(DURATION_FIELD)
  private final int duration;
  
  /**
   * Performer of the audio as defined by sender or by audio tags.
   */
  @SerializedName(PERFORMER_FIELD)
  private String performer;
  
  /**
   * Title of the audio as defined by sender or by audio tags.
   */
  @SerializedName(TITLE_FIELD)
  private String title;
  
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
  
  /**
   * Thumbnail of the album cover to which the music file belongs.
   */
  @SerializedName(THUMBNAIL_FIELD)
  private PhotoSize thumbnail;
  
  
  public Audio(String uniqueId,
               String id,
               int duration,
               String performer,
               String title,
               String mimeType,
               Integer size,
               PhotoSize thumbnail) {
    this.uniqueId = Objects.requireNonNull(uniqueId);
    this.id = Objects.requireNonNull(id);
    this.duration = duration;
    this.performer = performer;
    this.title = title;
    this.mimeType = mimeType;
    this.size = size;
    this.thumbnail = thumbnail;
  }
  
  public Audio(String uniqueId, String id, int duration) {
    this.uniqueId = Objects.requireNonNull(uniqueId);
    this.id = Objects.requireNonNull(id);
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
   * Getter for property {@link #duration}.
   *
   * @return value for property {@link #duration}
   */
  public int getDuration() {
    return duration;
  }
  
  /**
   * Getter for property {@link #performer}.
   *
   * @return optional value for property {@link #performer}
   */
  public Optional<String> getPerformer() {
    return Optional.ofNullable(performer);
  }
  
  /**
   * Getter for property {@link #title}.
   *
   * @return optional value for property {@link #title}
   */
  public Optional<String> getTitle() {
    return Optional.ofNullable(title);
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
    
    if (!(obj instanceof Audio)) {
      return false;
    }
    
    Audio audio = (Audio) obj;
    return uniqueId.equals(audio.getUniqueId());
  }
  
  @Override
  public int hashCode() {
    return uniqueId.hashCode();
  }
  
}
