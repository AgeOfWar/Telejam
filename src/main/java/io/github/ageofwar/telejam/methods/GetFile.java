package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.connection.InputFile;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to get basic info about a file and prepare it for downloading.
 * On success, a File object is returned.
 *
 * @author Michi Palazzo
 */
public class GetFile implements TelegramMethod<InputFile> {

  public static final String NAME = "getFile";

  static final String FILE_ID_FIELD = "file_id";

  /**
   * File identifier to get info about.
   */
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
  public Map<String, Object> getParameters() {
    return mapOf(FILE_ID_FIELD, fileId);
  }
  
  @Override
  public Class<? extends InputFile> getReturnType() {
    return InputFile.class;
  }

}
