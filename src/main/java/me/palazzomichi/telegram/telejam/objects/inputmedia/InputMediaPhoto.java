package me.palazzomichi.telegram.telejam.objects.inputmedia;

import com.google.gson.annotations.SerializedName;

import java.io.InputStream;

/**
 * Represents a photo to be sent.
 *
 * @author Michi Palazzo
 */
public class InputMediaPhoto extends InputMedia {
  
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "photo";
  
  
  /**
   * Constructs an input media photo.
   *
   * @param media   file to send
   * @param caption caption of the file to be sent, 0-200 characters
   */
  public InputMediaPhoto(String media, String caption) {
    super(media, caption);
  }
  
  /**
   * Constructs an input media.
   *
   * @param media   file to send
   * @param name    unique name for this media
   * @param caption caption of the file to be sent, 0-200 characters
   */
  public InputMediaPhoto(InputStream media, String name, String caption) {
    super(media, name, caption);
  }
  
}
