package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.File;

/**
 * Use this method to get basic info about a file and prepare it for downloading.
 * On success, a File object is returned.
 *
 * @author Michi Palazzo
 */
public class GetFile extends JsonTelegramMethod<File> {

  public static final String NAME = "getFile";

  static final String FILE_ID_FIELD = "file_id";

  /**
   * File identifier to get info about.
   */
  @SerializedName(FILE_ID_FIELD)
  private String fileId;

  public GetFile fileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends File> getReturnType(JsonElement response) {
    return File.class;
  }

}
