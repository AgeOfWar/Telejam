package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

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
  static final String PARSE_MODE_FIELD = "parse_mode";
  
  static final String ATTACH_PREFIX = "attach://";
  
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";
  
  /**
   * Media to send. Pass a file_id to send a file that exists on the Telegram servers (recommended),
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
   * Media not present in Telegram servers.
   */
  private UploadFile newMedia;
  
  
  /**
   * Constructs an input media.
   *
   * @param media   newMedia to send
   * @param caption caption of the newMedia to be sent, 0-200 characters
   */
  public InputMedia(String media, Text caption) {
    this.media = Objects.requireNonNull(media);
    this.caption = caption != null ? caption.toHtmlString() : null;
  }
  
  /**
   * Constructs an input media.
   *
   * @param media   newMedia to send
   * @param caption caption of the newMedia to be sent, 0-200 characters
   */
  public InputMedia(UploadFile media, Text caption) {
    this.media = ATTACH_PREFIX + media.getFileName();
    this.caption = caption != null ? caption.toHtmlString() : null;
    this.newMedia = media;
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
  
}
