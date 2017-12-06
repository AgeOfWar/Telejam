package me.palazzomichi.telegram.telejam.objects.inputmedia;

import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
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
   * Constructs an input media video.
   *
   * @param media    file to send
   * @param caption  caption of the file to be sent, 0-200 characters
   * @param width    video width
   * @param height   video height
   * @param duration video duration
   */
  public InputMediaVideo(String media, String caption, int width, int height, int duration) {
    super(media, caption);
    this.width = width;
    this.height = height;
    this.duration = duration;
  }
  
  /**
   * Constructs an input media video.
   *
   * @param media   file to send
   * @param caption caption of the file to be sent, 0-200 characters
   */
  public InputMediaVideo(String media, String caption) {
    super(media, caption);
  }
  
  /**
   * Constructs an input media.
   *
   * @param media    file to send
   * @param name     unique name for this media
   * @param caption  caption of the file to be sent, 0-200 characters
   * @param width    video width
   * @param height   video height
   * @param duration video duration
   */
  public InputMediaVideo(InputStream media, String name, String caption, int width, int height, int duration) {
    super(media, name, caption);
    this.width = width;
    this.height = height;
    this.duration = duration;
  }
  
  /**
   * Constructs an input media.
   *
   * @param media    file to send
   * @param name     unique name for this media
   * @param caption  caption of the file to be sent, 0-200 characters
   */
  public InputMediaVideo(InputStream media, String name, String caption) {
    super(media, name, caption);
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
   * Setter for property {@link #width}.
   *
   * @param width value for property {@link #width}
   */
  public void setWidth(Integer width) {
    this.width = width;
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
   * Setter for property {@link #height}.
   *
   * @param height value for property {@link #height}
   */
  public void setHeight(Integer height) {
    this.height = height;
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
   * Setter for property {@link #duration}.
   *
   * @param duration value for property {@link #duration}
   */
  public void setDuration(Integer duration) {
    this.duration = duration;
  }
  
}
