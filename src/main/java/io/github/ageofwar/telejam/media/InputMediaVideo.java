package io.github.ageofwar.telejam.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.connection.UploadFile;
import io.github.ageofwar.telejam.text.Text;

import java.util.OptionalInt;

/**
 * Represents a video to be sent.
 *
 * @author Michi Palazzo
 */
public class InputMediaVideo extends InputMedia {
  
  static final String WIDTH_FIELD = "width";
  static final String HEIGHT_FIELD = "height";
  static final String DURATION_FIELD = "duration";
  static final String SUPPORTS_STREAMING_FIELD = "supports_streaming";
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "video";
  
  /**
   * Video width.
   */
  @SerializedName(WIDTH_FIELD)
  private Integer width;
  
  /**
   * Video height.
   */
  @SerializedName(HEIGHT_FIELD)
  private Integer height;
  
  /**
   * Video duration.
   */
  @SerializedName(DURATION_FIELD)
  private Integer duration;
  
  /**
   * Whether or not the uploaded video is suitable for streaming.
   */
  @SerializedName(SUPPORTS_STREAMING_FIELD)
  private Boolean supportsStreaming;
  
  /**
   * Constructs an input media video.
   *
   * @param media             file to send
   * @param thumbnail         thumbnail of the file sent
   * @param caption           caption of the file to be sent, 0-200 characters
   * @param width             video width
   * @param height            video height
   * @param duration          video duration
   * @param supportsStreaming if the uploaded video is suitable for streaming
   */
  public InputMediaVideo(String media,
                         UploadFile thumbnail,
                         Text caption,
                         Integer width,
                         Integer height,
                         Integer duration,
                         Boolean supportsStreaming) {
    super(media, thumbnail, caption);
    this.width = width;
    this.height = height;
    this.duration = duration;
    this.supportsStreaming = supportsStreaming;
  }
  
  /**
   * Constructs an input media video.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   */
  public InputMediaVideo(String media, UploadFile thumbnail, Text caption) {
    super(media, thumbnail, caption);
  }
  
  /**
   * Constructs an input media.
   *
   * @param media             file to send
   * @param thumbnail         thumbnail of the file sent
   * @param caption           caption of the file to be sent, 0-200 characters
   * @param width             video width
   * @param height            video height
   * @param duration          video duration
   * @param supportsStreaming if the uploaded video is suitable for streaming
   */
  public InputMediaVideo(UploadFile media,
                         UploadFile thumbnail,
                         Text caption,
                         Integer width,
                         Integer height,
                         Integer duration,
                         Boolean supportsStreaming) {
    super(media, thumbnail, caption);
    this.width = width;
    this.height = height;
    this.duration = duration;
    this.supportsStreaming = supportsStreaming;
  }
  
  /**
   * Constructs an input media.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   */
  public InputMediaVideo(UploadFile media, UploadFile thumbnail, Text caption) {
    super(media, thumbnail, caption);
  }
  
  
  /**
   * Getter for property {@link #width}.
   *
   * @return optional value for property {@link #width}
   */
  public OptionalInt getWidth() {
    return width == null ? OptionalInt.empty() : OptionalInt.of(width);
  }
  
  /**
   * Getter for property {@link #height}.
   *
   * @return optional value for property {@link #height}
   */
  public OptionalInt getHeight() {
    return height == null ? OptionalInt.empty() : OptionalInt.of(height);
  }
  
  /**
   * Getter for property {@link #duration}.
   *
   * @return optional value for property {@link #duration}
   */
  public OptionalInt getDuration() {
    return duration == null ? OptionalInt.empty() : OptionalInt.of(duration);
  }
  
  /**
   * Getter for property {@link #supportsStreaming}.
   *
   * @return optional value for property {@link #supportsStreaming}
   */
  public Boolean supportsStreaming() {
    return supportsStreaming;
  }
  
}
