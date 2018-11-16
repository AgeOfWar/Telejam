package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.connection.UploadFile;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

/**
 * A method of Telegram Bots API.
 *
 * @param <T> the return type of the method
 * @author Michi Palazzo
 */
public interface TelegramMethod<T extends Serializable> {

  /**
   * Returns the name of the method.
   *
   * @return the name of the method
   */
  String getName();
  
  /**
   * Return the parameters of the method.
   *
   * @return the parameters of the method
   */
  Map<String, Object> getParameters();

  /**
   * Returns the return type of the method.
   *
   * @return the return type of the method
   */
  Type getReturnType();
  
  /**
   * Return the file parameters of the method.
   *
   * @return the files parameters of the method
   */
  default Map<String, UploadFile> getFiles() {
    return Collections.emptyMap();
  }

}
