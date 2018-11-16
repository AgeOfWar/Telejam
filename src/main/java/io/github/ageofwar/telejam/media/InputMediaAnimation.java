package io.github.ageofwar.telejam.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.connection.UploadFile;
import io.github.ageofwar.telejam.text.Text;

import java.util.OptionalInt;

/**
 * Represents an animation to be sent.
 *
 * @author Michi Palazzo
 */
public class InputMediaAnimation extends InputMedia {
  
  static final String WIDTH_FIELD = "width";
  static final String HEIGHT_FIELD = "height";
  static final String DURATION_FIELD = "duration";
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "animation";
  
  /**
   * Animation width.
   */
  @SerializedName(WIDTH_FIELD)
  private Integer width;
  
  /**
   * Animation height.
   */
  @SerializedName(HEIGHT_FIELD)
  private Integer height;
  
  /**
   * Animation duration.
   */
  @SerializedName(DURATION_FIELD)
  private Integer duration;
  
  
  /**
   * Constructs an input media animation.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   * @param width     animation width
   * @param height    animation height
   * @param duration  animation duration
   */
  public InputMediaAnimation(String media,
                             UploadFile thumbnail,
                             Text caption,
                             Integer width,
                             Integer height,
                             Integer duration) {
    super(media, thumbnail, caption);
    this.width = width;
    this.height = height;
    this.duration = duration;
  }
  
  /**
   * Constructs an input media animation.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   */
  public InputMediaAnimation(String media, UploadFile thumbnail, Text caption) {
    super(media, thumbnail, caption);
  }
  
  /**
   * Constructs an input media animation.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   * @param width     animation width
   * @param height    animation height
   * @param duration  animation duration
   */
  public InputMediaAnimation(UploadFile media,
                             UploadFile thumbnail,
                             Text caption,
                             Integer width,
                             Integer height,
                             Integer duration) {
    super(media, thumbnail, caption);
    this.width = width;
    this.height = height;
    this.duration = duration;
  }
  
  /**
   * Constructs an input media animation.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   */
  public InputMediaAnimation(UploadFile media, UploadFile thumbnail, Text caption) {
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
  
}
