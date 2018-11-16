package io.github.ageofwar.telejam;

import com.google.gson.JsonParseException;
import io.github.ageofwar.telejam.connection.Http;
import io.github.ageofwar.telejam.connection.InputFile;
import io.github.ageofwar.telejam.connection.Result;
import io.github.ageofwar.telejam.connection.UploadFile;
import io.github.ageofwar.telejam.methods.GetFile;
import io.github.ageofwar.telejam.methods.GetMe;
import io.github.ageofwar.telejam.methods.TelegramMethod;
import io.github.ageofwar.telejam.users.User;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static io.github.ageofwar.telejam.json.Json.*;

/**
 * Class representing a connection to the Telegram API.
 *
 * @author Michi Palazzo
 */
public final class Bot {
  
  /**
   * General link to the Telegram API.
   */
  private static final String API_URL = "https://api.telegram.org/bot";
  
  /**
   * General link for download Telegram files.
   */
  private static final String API_FILE_URL = "https://api.telegram.org/file/bot";
  
  /**
   * The unique identifier of the bot.
   */
  private final long id;
  
  /**
   * The bot username.
   */
  private final String username;
  
  /**
   * Telegram API url.
   */
  private final String apiUrl;
  
  /**
   * Telegram API files location.
   */
  private final String apiFileUrl;
  
  
  /**
   * Constructs and returns a Telegram Bot with a
   * token and ensures that it is a valid bot.
   *
   * @param token the token used for the connection to the Telegram API
   * @return the created bot
   * @throws IOException       if an I/O Exception occurs
   * @throws TelegramException if the token is invalid
   */
  public static Bot fromToken(String token) throws IOException {
    return fromApiUrl(API_URL + token, API_FILE_URL + token);
  }
  
  /**
   * Constructs and returns a Telegram Bot with the API url
   * and ensures that it is a valid bot.
   *
   * @param apiUrl     the API url
   * @param apiFileUrl the API file url
   * @return the created bot
   * @throws IOException       if an I/O Exception occurs
   * @throws TelegramException if the token is invalid
   */
  public static Bot fromApiUrl(String apiUrl, String apiFileUrl) throws IOException {
    return new Bot(apiUrl, apiFileUrl);
  }
  
  
  private Bot(String apiUrl, String apiFileUrl) throws IOException {
    this.apiUrl = apiUrl;
    this.apiFileUrl = apiFileUrl;
    User thisBot = execute(GetMe.INSTANCE);
    username = thisBot.getUsername().orElseThrow(AssertionError::new);
    id = thisBot.getId();
  }
  
  
  /**
   * Invokes a method of the Telegram Bot API.
   *
   * @param method     the method to execute
   * @param <T>        the return type of the method
   * @return the result of the method invocation
   * @throws IOException       if an I/O error occurs
   * @throws TelegramException if the method invocation returns an error
   */
  public <T extends Serializable> T execute(TelegramMethod<T> method) throws IOException {
    String url = apiUrl + '/' + method.getName();
    Map<String, Object> parameters = method.getParameters();
    Map<String, UploadFile> files = method.getFiles();
    InputStream inputStream = Http.post(url, parameters, files);
    return parseResult(inputStream, method.getReturnType());
  }
  
  private static <T extends Serializable> T parseResult(InputStream inputStream, Type returnType)
      throws IOException {
    try (BufferedReader json = new BufferedReader(new InputStreamReader(inputStream))) {
      Result<T> result = fromJson(json, genericTypeOf(Result.class, returnType));
      if (!result.ok()) {
        throw result.toException();
      }
      return result.get();
    } catch (JsonParseException e) {
      throw new IOException(e);
    }
  }
  
  /**
   * Returns the file with the specified id.
   *
   * @param fileId the id of the file to download
   * @return the file
   * @throws IOException when an I/O Exception occurs
   */
  public InputStream downloadFile(String fileId) throws IOException {
    GetFile getFile = new GetFile()
        .fileId(fileId);
    return downloadFile(execute(getFile));
  }
  
  private InputStream downloadFile(InputFile file) throws IOException {
    Optional<String> filePath = file.getPath();
    return download(filePath.orElseThrow(IOException::new));
  }
  
  private InputStream download(String filePath) throws IOException {
    Objects.requireNonNull(filePath, "file cannot be null!");
    URL url = new URL(apiFileUrl + '/' + filePath);
    return url.openStream();
  }
  
  /**
   * Returns the username of the bot.
   *
   * @return the username of the bot
   */
  public String getUsername() {
    return username;
  }
  
  /**
   * Returns the id of the bot.
   *
   * @return the id of the bot
   */
  public long getId() {
    return id;
  }
  
}
