package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * This object represents a video file.
 *
 * @author Michi Palazzo
 */
public class Video implements TelegramObject {

  static final String ID_FIELD = "file_id";
  static final String WIDTH_FIELD = "width";
  static final String HEIGHT_FIELD = "height";
  static final String DURATION_FIELD = "duration";
  static final String THUMBNAIL_FIELD = "thumb";
  static final String MIME_TYPE_FIELD = "mime_type";
  static final String SIZE_FIELD = "file_size";

  /**
   * Unique identifier for this file.
   */
  @SerializedName(ID_FIELD)
  private String id;

  /**
   * Video width as defined by sender.
   */
  @SerializedName(WIDTH_FIELD)
  private int width;

  /**
   * Video height as defined by sender.
   */
  @SerializedName(HEIGHT_FIELD)
  private int height;

  /**
   * Duration of the video in seconds as defined by sender.
   */
  @SerializedName(DURATION_FIELD)
  private int duration;

  /**
   * Video thumbnail.
   */
  @SerializedName(THUMBNAIL_FIELD)
  private PhotoSize thumbnail;

  /**
   * Mime type of a file as defined by sender.
   */
  @SerializedName(MIME_TYPE_FIELD)
  private String mimeType;

  /**
   * File size.
   */
  @SerializedName(SIZE_FIELD)
  private Integer size;


  public Video(String id,
               int width,
               int height,
               int duration,
               PhotoSize thumbnail,
               String mimeType,
               Integer size) {
    this.id = Objects.requireNonNull(id);
    this.width = width;
    this.height = height;
    this.duration = duration;
    this.thumbnail = thumbnail;
    this.mimeType = mimeType;
    this.size = size;
  }

  public Video(String id, int width, int height, int duration) {
    this.id = Objects.requireNonNull(id);
    this.width = width;
    this.height = height;
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

}
