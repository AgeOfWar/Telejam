package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

/**
 * Represents a photo to be sent.
 *
 * @author Michi Palazzo
 */
public class InputMediaPhoto extends InputMedia {
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "photo";
  
  
  /**
   * Constructs an input media photo.
   *
   * @param media   file to send
   * @param caption caption of the file to be sent, 0-200 characters
   */
  public InputMediaPhoto(String media, Text caption) {
    super(media, null, caption);
  }
  
  /**
   * Constructs an input media.
   *
   * @param media   file to send
   * @param caption caption of the file to be sent, 0-200 characters
   */
  public InputMediaPhoto(UploadFile media, Text caption) {
    super(media, null, caption);
  }
  
}
