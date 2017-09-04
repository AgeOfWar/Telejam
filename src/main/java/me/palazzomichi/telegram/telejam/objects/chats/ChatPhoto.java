package me.palazzomichi.telegram.telejam.objects.chats;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;

/**
 * This object represents a chat photo.
 *
 * @author Michi Palazzo
 */
public class ChatPhoto implements TelegramObject {

  static final String SMALL_FILE_ID_FIELD = "small_file_id";
  static final String BIG_FILE_ID_FIELD = "big_file_id";

  /**
   * Unique file identifier of small (160x160) chat photo.
   * This file_id can be used only for photo download.
   */
  @SerializedName(SMALL_FILE_ID_FIELD)
  private String smallFileId;

  /**
   * Unique file identifier of big (640x640) chat photo.
   * This file_id can be used only for photo download.
   */
  @SerializedName(BIG_FILE_ID_FIELD)
  private String bigFileId;


  public ChatPhoto(String smallFileId, String bigFileId) {
    this.smallFileId = smallFileId;
    this.bigFileId = bigFileId;
  }


  /**
   * Getter for property {@link #smallFileId}.
   *
   * @return value for property {@link #smallFileId}
   */
  public String getSmallFileId() {
    return smallFileId;
  }

  /**
   * Getter for property {@link #bigFileId}.
   *
   * @return value for property {@link #bigFileId}
   */
  public String getBigFileId() {
    return bigFileId;
  }

}
