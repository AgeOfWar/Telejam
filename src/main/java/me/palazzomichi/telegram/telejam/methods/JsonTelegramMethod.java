package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.TelegramObject;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.Serializable;

/**
 * A serializable method of Telegram Bots Api in JSON.
 *
 * @param <T> the return type of the method
 * @author Michi Palazzo
 */
public abstract class JsonTelegramMethod<T extends Serializable> implements TelegramMethod<T> {

  @Override
  public HttpEntity getHttpEntity() {
    return new StringEntity(TelegramObject.gson.toJson(this), ContentType.APPLICATION_JSON);
  }

}
