package io.github.ageofwar.telejam.chats;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.Objects;

/**
 * This object represents a chat photo.
 *
 * @author Michi Palazzo
 */
public class ChatPhoto implements TelegramObject {
  
  static final String SMALL_FILE_UNIQUE_ID_FIELD = "small_file_unique_id";
  static final String BIG_FILE_UNIQUE_ID_FIELD = "big_file_unique_id";
  static final String SMALL_FILE_ID_FIELD = "small_file_id";
  static final String BIG_FILE_ID_FIELD = "big_file_id";
  
  /**
   * File identifier of small (160x160) chat photo.
   * This file_id can be used only for photo download
   * and only for as long as the photo is not changed.
   */
  @SerializedName(SMALL_FILE_ID_FIELD)
  private final String smallFileUniqueId;
  
  /**
   * File identifier of big (640x640) chat photo.
   * This file_id can be used only for photo download
   * and only for as long as the photo is not changed.
   */
  @SerializedName(BIG_FILE_ID_FIELD)
  private final String bigFileUniqueId;
  
  /**
   * Unique file identifier of small (160x160) chat photo.
   * This file_id can be used only for photo download.
   */
  @SerializedName(SMALL_FILE_ID_FIELD)
  private final String smallFileId;
  
  /**
   * Unique file identifier of big (640x640) chat photo.
   * This file_id can be used only for photo download.
   */
  @SerializedName(BIG_FILE_ID_FIELD)
  private final String bigFileId;
  
  
  public ChatPhoto(String smallFileUniqueId, String bigFileUniqueId, String smallFileId, String bigFileId) {
    this.smallFileUniqueId = Objects.requireNonNull(smallFileUniqueId);
    this.bigFileUniqueId = Objects.requireNonNull(bigFileUniqueId);
    this.smallFileId = Objects.requireNonNull(smallFileId);
    this.bigFileId = Objects.requireNonNull(bigFileId);
  }
  
  /**
   * Getter for property {@link #smallFileUniqueId}.
   *
   * @return value for property {@link #smallFileUniqueId}
   */
  public String getSmallFileUniqueId() {
    return smallFileUniqueId;
  }
  
  /**
   * Getter for property {@link #bigFileUniqueId}.
   *
   * @return value for property {@link #bigFileUniqueId}
   */
  public String getBigFileUniqueId() {
    return bigFileUniqueId;
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
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
  
    if (!(obj instanceof ChatPhoto)) {
      return false;
    }
  
    ChatPhoto chatPhoto = (ChatPhoto) obj;
    return smallFileUniqueId.equals(chatPhoto.getSmallFileUniqueId()) &&
        bigFileUniqueId.equals(chatPhoto.getBigFileUniqueId());
  }
  
  @Override
  public int hashCode() {
    return smallFileUniqueId.hashCode() * 31 + bigFileUniqueId.hashCode();
  }
  
}
