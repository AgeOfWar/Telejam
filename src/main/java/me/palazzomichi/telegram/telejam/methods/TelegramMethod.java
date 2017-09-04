package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import org.apache.http.HttpEntity;

import java.io.Serializable;

/**
 * A method of Telegram Bots Api.
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
   * Returns the return type of the method.
   * If the method can return different types, the response is consulted.
   *
   * @return the return type of the method
   * @param response the response
   */
  Class<? extends T> getReturnType(JsonElement response);

  /**
   * Returns the HTTP entity of the request.
   *
   * @return the HTTP entity of the request that can be null
   */
  HttpEntity getHttpEntity();

}
