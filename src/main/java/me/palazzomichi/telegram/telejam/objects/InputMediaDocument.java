package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

public class InputMediaDocument extends InputMedia {
  
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "document";
  
  /**
   * Constructs an input media document.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   */
  public InputMediaDocument(String media, UploadFile thumbnail, Text caption) {
    super(media, thumbnail, caption);
  }
  
  /**
   * Constructs an input media document.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   */
  public InputMediaDocument(UploadFile media, UploadFile thumbnail, Text caption) {
    super(media, thumbnail, caption);
  }
  
}
