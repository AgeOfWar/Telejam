package io.github.ageofwar.telejam.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;
import io.github.ageofwar.telejam.connection.UploadFile;
import io.github.ageofwar.telejam.text.Text;

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
  static final String THUMBNAIL_FIELD = "thumb";
  static final String CAPTION_FIELD = "caption";
  static final String PARSE_MODE_FIELD = "parse_mode";
  
  private static final String ATTACH_PREFIX = "attach://";
  
  @Expose
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";
  
  /**
   * Media to send. Pass a file_id to send a file that exists on the Telegram servers (recommended),
   * pass an HTTP URL for Telegram to get a file from the Internet, or pass a file.
   */
  @SerializedName(MEDIA_FIELD)
  private final String media;
  
  /**
   * Thumbnail of the file sent. The thumbnail should be in JPEG
   * format and less than 200 kB in size. A thumbnail‘s width
   * and height should not exceed 90.
   */
  @SerializedName(THUMBNAIL_FIELD)
  private final String thumbnail;
  
  /**
   * Caption of the file to be sent, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private final String caption;
  /**
   * Thumbnail not present in Telegram servers.
   */
  private final UploadFile newThumbnail;
  /**
   * Media not present in Telegram servers.
   */
  private UploadFile newMedia;
  
  
  /**
   * Constructs an input media.
   *
   * @param media     media to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the newMedia to be sent, 0-200 characters
   */
  public InputMedia(String media, UploadFile thumbnail, Text caption) {
    this.media = Objects.requireNonNull(media);
    this.thumbnail = thumbnail != null ? ATTACH_PREFIX + thumbnail.getFileName() : null;
    this.caption = caption != null ? caption.toHtmlString() : null;
    newThumbnail = thumbnail;
  }
  
  /**
   * Constructs an input media.
   *
   * @param media     media to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the newMedia to be sent, 0-200 characters
   */
  public InputMedia(UploadFile media, UploadFile thumbnail, Text caption) {
    this.media = ATTACH_PREFIX + media.getFileName();
    this.thumbnail = thumbnail != null ? ATTACH_PREFIX + thumbnail.getFileName() : null;
    this.caption = caption != null ? caption.toHtmlString() : null;
    newMedia = media;
    newThumbnail = thumbnail;
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
   * Getter for property {@link #caption}.
   *
   * @return optional value for property {@link #caption}
   */
  public Optional<Text> getCaption() {
    return Optional.ofNullable(Text.parseHtml(caption));
  }
  
  /**
   * Getter for property {@link #newMedia}.
   *
   * @return optional value for property {@link #newMedia}
   */
  public Optional<UploadFile> getFile() {
    return Optional.ofNullable(newMedia);
  }
  
  /**
   * Getter for property {@link #newThumbnail}.
   *
   * @return optional value for property {@link #newThumbnail}
   */
  public Optional<UploadFile> getThumbnail() {
    return Optional.ofNullable(newThumbnail);
  }
  
}
