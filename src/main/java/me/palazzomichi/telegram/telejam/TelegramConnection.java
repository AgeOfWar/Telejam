package me.palazzomichi.telegram.telejam;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import me.palazzomichi.telegram.telejam.methods.TelegramMethod;
import me.palazzomichi.telegram.telejam.objects.Result;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Class representing a connection to the Telegram API.
 *
 * @author Michi Palazzo
 */
public class TelegramConnection {

  /**
   * General link to the Telegram API.
   */
  private static final String API_URL = "https://api.telegram.org/bot";

  /**
   * The HTTP client used for send requests.
   */
  private CloseableHttpClient httpClient;

  /**
   * Token to access Telegram API.
   */
  private final String token;


  /**
   * Constructs a Telegram Connection with a token.
   *
   * @param httpClient the HTTP client used for send requests
   * @param token      the token used for the connection to the Telegram API
   */
  TelegramConnection(CloseableHttpClient httpClient, String token) {
    this.httpClient = httpClient;
    this.token = Objects.requireNonNull(token);
  }

  /**
   * Constructs a Telegram Connection with a token.
   *
   * @param token the token used for the connection to the Telegram API
   */
  public TelegramConnection(String token) {
    this(HttpClients.createMinimal(), token);
  }


  /**
   * Invokes a method from the Telegram API.
   *
   * @param method the method
   * @param <T>    the return type
   * @return the object returned from the method invocation
   * @throws IOException       when an I/O Exception occurs during the method
   *                           invocation
   * @throws TelegramException when the method invocation returns an error
   * @throws JsonParseException when the object returned is unknown
   */
  public <T extends Serializable> T execute(TelegramMethod<T> method) throws IOException {
    Objects.requireNonNull(method, "method cannot be null!");

    HttpPost httpPost = new HttpPost(API_URL + token + '/' + method.getName());
    httpPost.setEntity(method.getHttpEntity());

    try (CloseableHttpResponse response = httpClient.execute(httpPost);
         InputStreamReader reader =
             new InputStreamReader(response.getEntity().getContent());
         BufferedReader bufferedReader = new BufferedReader(reader);
         JsonReader json = new JsonReader(bufferedReader)) {

      JsonElement src = Streams.parse(json);
      Type typeOfT =
          $Gson$Types.newParameterizedTypeWithOwner(null, Result.class, method.getReturnType(src));
      Result<T> result = TelegramObject.gson.fromJson(src, typeOfT);

      if (!result.ok()) {
        throw result.toException();
      }

      return result.get();
    } catch (JsonParseException e) {
      throw new IOException(e);
    }
  }

  /**
   * Getter for property {@link #token}.
   *
   * @return value for property {@link #token}
   */
  public String getToken() {
    return token;
  }

}
