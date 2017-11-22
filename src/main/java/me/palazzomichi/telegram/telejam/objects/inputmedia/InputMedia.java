package me.palazzomichi.telegram.telejam.objects.inputmedia;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

/**
 * This object represents the content of a media message to be sent.
 *
 * @author Michi Palazzo
 */
public abstract class InputMedia implements TelegramObject {
  
  static final String TYPE_FIELD = "type";
  static final String MEDIA_FIELD = "media";
  static final String CAPTION_FIELD = "caption";
  
  private static final String ATTACH_PREFIX = "attach://";
  
  /**
   * File to send. Pass a file_id to send a file that exists on the Telegram servers (recommended),
   * pass an HTTP URL for Telegram to get a file from the Internet, or pass a file.
   */
  @SerializedName(MEDIA_FIELD)
  private String media;
  
  /**
   * Caption of the file to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;
  
  /**
   * The path to the file.
   */
  private String file;
  
  /**
   * Unique name for this media.
   */
  private String fileAttachName;
  
  
  /**
   * Constructs an input media.
   *
   * @param media   file to send
   * @param caption caption of the file to be sent, 0-200 characters
   */
  public InputMedia(String media, String caption) {
    this.media = Objects.requireNonNull(media);
    this.caption = caption;
  }
  
  /**
   * Constructs an input media.
   *
   * @param media   file to send
   * @param name    unique name for this media
   * @param caption caption of the file to be sent, 0-200 characters
   */
  public InputMedia(File media, String name, String caption) {
    this.media = ATTACH_PREFIX + name;
    this.caption = caption;
    this.file = media.getPath();
    this.fileAttachName = name;
  }
  
  
  /**
   * Getter for property {@link #media}.
   *
   * @return value for property {@link #media}
   */
  public String getMedia() {
    return media;
  }
  
  /**
   * Setter for property {@link #media}.
   *
   * @param media value for property {@link #media}
   */
  public void setMedia(String media) {
    this.media = Objects.requireNonNull(media);
    this.file = null;
    this.fileAttachName = null;
  }
  
  /**
   * Getter for property {@link #caption}.
   *
   * @return optional value for property {@link #caption}
   */
  public Optional<String> getCaption() {
    return Optional.ofNullable(caption);
  }
  
  /**
   * Setter for property {@link #caption}.
   *
   * @param caption value for property {@link #caption}
   */
  public void setCaption(String caption) {
    this.caption = caption;
  }
  
  /**
   * Getter for property {@link #file}.
   *
   * @return optional value for property {@link #file}
   */
  public Optional<String> getFile() {
    return Optional.ofNullable(file);
  }
  
  /**
   * Setter for property {@link #file}.
   *
   * @param file value for property {@link #file}
   */
  public void setFile(String file) {
    this.file = file;
  }
  
  /**
   * Getter for property {@link #fileAttachName}.
   *
   * @return optional value for property {@link #fileAttachName}
   */
  public Optional<String> getFileAttachName() {
    return Optional.ofNullable(fileAttachName);
  }
  
  /**
   * Setter for property {@link #fileAttachName}.
   *
   * @param fileAttachName value for property {@link #fileAttachName}
   */
  public void setFileAttachName(String fileAttachName) {
    this.fileAttachName = fileAttachName;
    this.media = ATTACH_PREFIX + fileAttachName;
  }
  
}
