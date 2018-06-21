package me.palazzomichi.telegram.telejam;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import me.palazzomichi.telegram.telejam.connection.HttpUrlConnection;
import me.palazzomichi.telegram.telejam.connection.MultipartHttpURLConnection;
import me.palazzomichi.telegram.telejam.json.Json;
import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.objects.Currency;
import me.palazzomichi.telegram.telejam.text.Text;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.function.Function;

import static me.palazzomichi.telegram.telejam.json.Json.fromJson;
import static me.palazzomichi.telegram.telejam.json.Json.genericTypeOf;

/**
 * Class representing a connection to the Telegram API.
 *
 * @author Michi Palazzo
 */
public class Bot {
  
  /**
   * General link to the Telegram API.
   */
  private static final String API_URL = "https://api.telegram.org/bot";
  
  /**
   * General link for download Telegram files.
   */
  private static final String API_FILE_URL = "https://api.telegram.org/file/bot";
  
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
   * @param apiUrl the API url
   * @return the created bot
   * @throws IOException       if an I/O Exception occurs
   * @throws TelegramException if the token is invalid
   */
  public static Bot fromApiUrl(String apiUrl, String apiFileUrl) throws IOException {
    Bot bot = new Bot(apiUrl, apiFileUrl);
    bot.getMe();
    return bot;
  }
  
  /**
   * Constructs a Telegram Connection with an API url.
   *
   * @param apiUrl the API url
   */
  private Bot(String apiUrl, String apiFileUrl) {
    this.apiUrl = apiUrl;
    this.apiFileUrl = apiFileUrl;
  }
  
  
  /**
   * Invokes a method of the Telegram Bot API.
   *
   * @param method     the name of the method
   * @param parameters parameters passed to the method
   * @param files      files passed to the method
   * @param returnType the return type of the method
   * @param <T>        the return type of the method
   * @return the result of the method invocation
   * @throws IOException if an I/O error occurs
   * @throws TelegramException if the method invocation returns an error
   */
  public <T extends Serializable> T execute(
      String method,
      Map<String, String> parameters,
      Map<String, UploadFile> files,
      Type returnType
  ) throws IOException {
    URL url = new URL(apiUrl + '/' + method);
    MultipartHttpURLConnection connection = new MultipartHttpURLConnection((HttpURLConnection) url.openConnection());
    for (Map.Entry<String, String> entry : parameters.entrySet()) {
      if (entry.getValue() != null) {
        connection.addFormField(entry.getKey(), entry.getValue());
      }
    }
    for (Map.Entry<String, UploadFile> entry : files.entrySet()) {
      if (entry.getValue() != null) {
        connection.addFilePart(entry.getKey(), entry.getValue());
      }
    }
    return parseResult(connection.getInputStream(), returnType);
  }
  
  /**
   * Invokes a method of the Telegram Bot API.
   *
   * @param method     the name of the method
   * @param parameters parameters passed to the method
   * @param returnType the return type of the method
   * @param <T>        the return type of the method
   * @return the result of the method invocation
   * @throws IOException if an I/O error occurs
   * @throws TelegramException if the method invocation returns an error
   */
  public <T extends Serializable> T execute(
      String method,
      Map<String, String> parameters,
      Type returnType
  ) throws IOException {
    URL url = new URL(apiUrl + '/' + method);
    HttpUrlConnection connection = new HttpUrlConnection((HttpURLConnection) url.openConnection());
    for (Map.Entry<String, String> parameter : parameters.entrySet()) {
      if (parameter.getValue() != null) {
        connection.addField(parameter.getKey(), parameter.getValue());
      }
    }
    return parseResult(connection.getInputStream(), returnType);
  }
  
  /**
   * Invokes a method of the Telegram Bot API.
   *
   * @param method     the name of the method
   * @param returnType the return type of the method
   * @param <T>        the return type of the method
   * @return the result of the method invocation
   * @throws IOException if an I/O error occurs
   * @throws TelegramException if the method invocation returns an error
   */
  public <T extends Serializable> T execute(String method, Type returnType) throws IOException {
    return execute(method, mapOf(), returnType);
  }
  
  private <T extends Serializable> T parseResult(InputStream inputStream, Type returnType) throws IOException {
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
   * Returns the stream of the file in the specified path.
   *
   * @param fileId the id of the file to download
   * @return the stream of the file
   * @throws IOException when an I/O Exception occurs
   */
  public InputStream downloadFile(String fileId) throws IOException {
    return downloadFile(getFile(fileId));
  }
  
  /**
   * Returns the stream of a file if is possible.
   *
   * @param file the file
   * @return teh stream of the file
   * @throws IOException when an I/O Exception occurs
   */
  private InputStream downloadFile(InputFile file) throws IOException {
    Optional<String> filePath = file.getPath();
    return downloadFile(filePath.orElseThrow(IOException::new));
  }
  
  private InputStream download(String filePath) throws IOException {
    Objects.requireNonNull(filePath, "file cannot be null!");
    URL url = new URL(apiFileUrl + '/' + filePath);
    return url.openStream();
  }
  
  /* API methods */
  
  public Update[] getUpdates(long offset, int limit, int timeout, String... allowedUpdates) throws IOException {
    Map<String, String> parameters = mapOf(
        "offset", toJson(offset),
        "limit", toJson(limit),
        "timeout", toJson(timeout),
        "allowed_updates", toJson(allowedUpdates)
    );
    return execute("getUpdates", parameters, Update[].class);
  }
  
  public Update[] getUpdates(long offset, int limit, String... allowedUpdates) throws IOException {
    Map<String, String> parameters = mapOf(
        "offset", toJson(offset),
        "limit", toJson(limit),
        "allowed_updates", toJson(allowedUpdates)
    );
    return execute("getUpdates", parameters, Update[].class);
  }
  
  public Update[] getUpdates(long offset, String... allowedUpdates) throws IOException {
    Map<String, String> parameters = mapOf(
        "offset", toJson(offset),
        "allowed_updates", toJson(allowedUpdates)
    );
    return execute("getUpdates", parameters, Update[].class);
  }
  
  public Update[] getUpdates(String... allowedUpdates) throws IOException {
    Map<String, String> parameters = mapOf("allowed_updates", toJson(allowedUpdates));
    return execute("getUpdates", parameters, Update[].class);
  }
  
  public void setWebhook(String url, UploadFile certificate, int maxConnections, String... allowedUpdates)
      throws IOException {
    Map<String, String> parameters = mapOf(
        "url", url,
        "max_connections", toJson(maxConnections),
        "allowed_updates", toJson(allowedUpdates)
    );
    Map<String, UploadFile> files = mapOf("certificate", certificate);
    execute("setWebhook", parameters, files, Boolean.class);
  }
  
  public void deleteWebhook() throws IOException {
    execute("deleteWebhook", Boolean.class);
  }
  
  public WebhookInfo getWebhookInfo() throws IOException {
    return execute("getWebhookInfo", WebhookInfo.class);
  }
  
  public User getMe() throws IOException {
    return execute("getMe", User.class);
  }
  
  public TextMessage sendMessage(Chat chat,
                                 Text text,
                                 ReplyMarkup replyMarkup,
                                 boolean disableWebPagePreview,
                                 boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "text", toJson(text),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "disable_web_page_preview", toJson(disableWebPagePreview),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendMessage", parameters, TextMessage.class);
  }
  
  public TextMessage sendMessage(Message replyToMessage,
                                 Text text,
                                 ReplyMarkup replyMarkup,
                                 boolean disableWebPagePreview,
                                 boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "text", toJson(text),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "disable_web_page_preview", toJson(disableWebPagePreview),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendMessage", parameters, TextMessage.class);
  }
  
  public TextMessage sendMessage(Chat chat,
                                 Text text,
                                 ReplyMarkup replyMarkup) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "text", toJson(text),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendMessage", parameters, TextMessage.class);
  }
  
  public TextMessage sendMessage(Message replyToMessage,
                                 Text text,
                                 ReplyMarkup replyMarkup) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "text", toJson(text),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendMessage", parameters, TextMessage.class);
  }
  
  public TextMessage sendMessage(Chat chat, Text text) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "text", toJson(text),
        "parse_mode", "HTML"
    );
    return execute("sendMessage", parameters, TextMessage.class);
  }
  
  public TextMessage sendMessage(Message replyToMessage, Text text) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "text", toJson(text),
        "parse_mode", "HTML"
    );
    return execute("sendMessage", parameters, TextMessage.class);
  }
  
  public <T extends Message> Forward<T> forwardMessage(T message, Chat chat, boolean disableNotification)
      throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "from_chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId()),
        "disable_notification", toJson(disableNotification)
    );
    return execute("forwardMessage", parameters, new TypeToken<Forward<T>>() {}.getType());
  }
  
  public <T extends Message> Forward<T> forwardMessage(T message, Chat chat)
      throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "from_chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId())
    );
    return execute("forwardMessage", parameters, new TypeToken<Forward<T>>() {
    }.getType());
  }
  
  public PhotoMessage sendPhoto(Message replyToMessage,
                                String photo,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "photo", photo,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "disable_notification", toJson(disableNotification),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendPhoto", parameters, PhotoMessage.class);
  }
  
  public PhotoMessage sendPhoto(Chat chat,
                                String photo,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "photo", photo,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "disable_notification", toJson(disableNotification),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendPhoto", parameters, PhotoMessage.class);
  }
  
  public PhotoMessage sendPhoto(Message replyToMessage,
                                UploadFile photo,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    System.out.println();
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "caption", caption != null ? toJson(caption) : null,
        "parse_mode", "HTML",
        "disable_notification", toJson(disableNotification),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "reply_markup", toJson(replyMarkup)
    );
    Map<String, UploadFile> files = mapOf("photo", photo);
    return execute("sendPhoto", parameters, files, PhotoMessage.class);
  }
  
  public PhotoMessage sendPhoto(Chat chat,
                                UploadFile photo,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "disable_notification", toJson(disableNotification),
        "reply_markup", toJson(replyMarkup)
    );
    Map<String, UploadFile> files = mapOf("photo", photo);
    return execute("sendPhoto", parameters, files, PhotoMessage.class);
  }
  
  public AudioMessage sendAudio(Message replyToMessage,
                                String audio,
                                Text caption,
                                String title,
                                String performer,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "audio", audio,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "title", title,
        "performer", performer,
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendAudio", parameters, AudioMessage.class);
  }
  
  public AudioMessage sendAudio(Message replyToMessage,
                                String audio,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "audio", audio,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendAudio", parameters, AudioMessage.class);
  }
  
  public AudioMessage sendAudio(Chat chat,
                                String audio,
                                Text caption,
                                String title,
                                String performer,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "audio", audio,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "title", title,
        "performer", performer,
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendAudio", parameters, AudioMessage.class);
  }
  
  public AudioMessage sendAudio(Chat chat,
                                String audio,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "audio", audio,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendAudio", parameters, AudioMessage.class);
  }
  
  public AudioMessage sendAudio(Message replyToMessage,
                                UploadFile audio,
                                Text caption,
                                String title,
                                String performer,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "title", title,
        "performer", performer,
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("audio", audio);
    return execute("sendAudio", parameters, files, AudioMessage.class);
  }
  
  public AudioMessage sendAudio(Message replyToMessage,
                                UploadFile audio,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("audio", audio);
    return execute("sendAudio", parameters, files, AudioMessage.class);
  }
  
  public AudioMessage sendAudio(Chat chat,
                                UploadFile audio,
                                Text caption,
                                String title,
                                String performer,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "title", title,
        "performer", performer,
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("audio", audio);
    return execute("sendAudio", parameters, files, AudioMessage.class);
  }
  
  public AudioMessage sendAudio(Chat chat,
                                UploadFile audio,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("audio", audio);
    return execute("sendAudio", parameters, files, AudioMessage.class);
  }
  
  public DocumentMessage sendDocument(Message replyToMessage,
                                      String document,
                                      Text caption,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "document", document,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendDocument", parameters, AudioMessage.class);
  }
  
  public DocumentMessage sendDocument(Chat chat,
                                      String document,
                                      Text caption,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "document", document,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendDocument", parameters, AudioMessage.class);
  }
  
  public DocumentMessage sendDocument(Message replyToMessage,
                                      UploadFile document,
                                      Text caption,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("document", document);
    return execute("sendDocument", parameters, files, AudioMessage.class);
  }
  
  public DocumentMessage sendDocument(Chat chat,
                                      UploadFile document,
                                      Text caption,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("document", document);
    return execute("sendDocument", parameters, files, AudioMessage.class);
  }
  
  public VideoMessage sendVideo(Message replyToMessage,
                                String video,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "video", video,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendVideo", parameters, AudioMessage.class);
  }
  
  public VideoMessage sendVideo(Chat chat,
                                String video,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "video", video,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendVideo", parameters, AudioMessage.class);
  }
  
  public VideoMessage sendVideo(Message replyToMessage,
                                UploadFile video,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("video", video);
    return execute("sendVideo", parameters, files, AudioMessage.class);
  }
  
  public VideoMessage sendVideo(Chat chat,
                                UploadFile video,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("video", video);
    return execute("sendVideo", parameters, files, AudioMessage.class);
  }
  
  public VoiceMessage sendVoice(Message replyToMessage,
                                String voice,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "voice", voice,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendVoice", parameters, AudioMessage.class);
  }
  
  public VoiceMessage sendVoice(Chat chat,
                                String voice,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "voice", voice,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendVoice", parameters, AudioMessage.class);
  }
  
  public VoiceMessage sendVoice(Message replyToMessage,
                                UploadFile voice,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("voice", voice);
    return execute("sendVoice", parameters, files, AudioMessage.class);
  }
  
  public VoiceMessage sendVoice(Chat chat,
                                UploadFile voice,
                                Text caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("voice", voice);
    return execute("sendVoice", parameters, files, AudioMessage.class);
  }
  
  public VideoNoteMessage sendVideoNote(Message replyToMessage,
                                        String video,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "video_note", video,
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendVoice", parameters, AudioMessage.class);
  }
  
  public VideoNoteMessage sendVideoNote(Chat chat,
                                        String video,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "video_note", video,
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendVoice", parameters, AudioMessage.class);
  }
  
  public VideoNoteMessage sendVideoNote(Message replyToMessage,
                                        UploadFile video,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("video_note", video);
    return execute("sendVoice", parameters, files, AudioMessage.class);
  }
  
  public VideoNoteMessage sendVideoNote(Chat chat,
                                        UploadFile video,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("video_note", video);
    return execute("sendVoice", parameters, files, AudioMessage.class);
  }
  
  public Message[] sendMediaGroup(Message replyToMessage,
                                  InputMedia[] inputMedia,
                                  boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "media", toJson(inputMedia),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    List<UploadFile> uploadFiles = new ArrayList<>();
    for (InputMedia media : inputMedia) {
      media.getFile().ifPresent(uploadFiles::add);
    }
    Map<String, UploadFile> files = groupBy(uploadFiles, UploadFile::getFileName);
    return execute("sendMediaGroup", parameters, files, Message[].class);
  }
  
  public Message[] sendMediaGroup(Chat chat,
                                  InputMedia[] inputMedia,
                                  boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "media", toJson(inputMedia),
        "disable_notification", toJson(disableNotification)
    );
    List<UploadFile> uploadFiles = new ArrayList<>();
    for (InputMedia media : inputMedia) {
      media.getFile().ifPresent(uploadFiles::add);
    }
    Map<String, UploadFile> files = groupBy(uploadFiles, UploadFile::getFileName);
    return execute("sendMediaGroup", parameters, files, Message[].class);
  }
  
  public Message[] sendMediaGroup(Message replyToMessage, InputMedia... inputMedia) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "media", toJson(inputMedia),
        "reply_to_message_id", toJson(replyToMessage.getId())
    );
    List<UploadFile> uploadFiles = new ArrayList<>();
    for (InputMedia media : inputMedia) {
      media.getFile().ifPresent(uploadFiles::add);
    }
    Map<String, UploadFile> files = groupBy(uploadFiles, UploadFile::getFileName);
    return execute("sendMediaGroup", parameters, files, Message[].class);
  }
  
  public Message[] sendMediaGroup(Chat chat, InputMedia... inputMedia) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "media", toJson(inputMedia)
    );
    List<UploadFile> uploadFiles = new ArrayList<>();
    for (InputMedia media : inputMedia) {
      media.getFile().ifPresent(uploadFiles::add);
    }
    Map<String, UploadFile> files = groupBy(uploadFiles, UploadFile::getFileName);
    return execute("sendMediaGroup", parameters, files, Message[].class);
  }
  
  public LocationMessage sendLocation(Message replyToMessage,
                                      Location location,
                                      int livePeriod,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "latitude", Float.toString(location.getLatitude()),
        "longitude", Float.toString(location.getLongitude()),
        "live_period", toJson(livePeriod),
        "disable_notification", toJson(disableNotification),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendLocation", parameters, LocationMessage.class);
  }
  
  public LocationMessage sendLocation(Message replyToMessage,
                                      Location location,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "latitude", Float.toString(location.getLatitude()),
        "longitude", Float.toString(location.getLongitude()),
        "disable_notification", toJson(disableNotification),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendLocation", parameters, LocationMessage.class);
  }
  
  public Serializable editMessageLiveLocation(LocationMessage message,
                                              Location location,
                                              InlineKeyboardMarkup replyMarkup) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(message.getChat().getId()),
        "latitude", Float.toString(location.getLatitude()),
        "longitude", Float.toString(location.getLongitude()),
        "message_id", toJson(message.getId()),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("editMessageLiveLocation", parameters, LocationMessage.class);
  }
  
  public Serializable editMessageLiveLocation(String inlineMessageId,
                                              Location location,
                                              InlineKeyboardMarkup replyMarkup) throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_message_id", inlineMessageId,
        "latitude", Float.toString(location.getLatitude()),
        "longitude", Float.toString(location.getLongitude()),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("editMessageLiveLocation", parameters, LocationMessage.class);
  }
  
  public Serializable stopMessageLiveLocation(LocationMessage message,
                                              InlineKeyboardMarkup replyMarkup) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId()),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("stopMessageLiveLocation", parameters, LocationMessage.class);
  }
  
  public Serializable stopMessageLiveLocation(String inlineMessageId,
                                              InlineKeyboardMarkup replyMarkup) throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_message_id", inlineMessageId,
        "reply_markup", toJson(replyMarkup)
    );
    return execute("stopMessageLiveLocation", parameters, LocationMessage.class);
  }
  
  public Serializable stopMessageLiveLocation(LocationMessage message) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId())
    );
    return execute("stopMessageLiveLocation", parameters, LocationMessage.class);
  }
  
  public Serializable stopMessageLiveLocation(String inlineMessageId) throws IOException {
    Map<String, String> parameters = mapOf("inline_message_id", inlineMessageId);
    return execute("stopMessageLiveLocation", parameters, LocationMessage.class);
  }
  
  public VenueMessage sendVenue(Message replyToMessage,
                                Venue venue,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "latitude", Float.toString(venue.getLocation().getLatitude()),
        "longitude", Float.toString(venue.getLocation().getLongitude()),
        "title", venue.getTitle(),
        "address", venue.getAddress(),
        "foursquare_id", venue.getFoursquareId().orElse(null),
        "disable_notification", toJson(disableNotification),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendVenue", parameters, VenueMessage.class);
  }
  
  public VenueMessage sendVenue(Chat chat,
                                Venue venue,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "latitude", Float.toString(venue.getLocation().getLatitude()),
        "longitude", Float.toString(venue.getLocation().getLongitude()),
        "title", venue.getTitle(),
        "address", venue.getAddress(),
        "foursquare_id", venue.getFoursquareId().orElse(null),
        "disable_notification", toJson(disableNotification),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendVenue", parameters, VenueMessage.class);
  }
  
  public ContactMessage sendContact(Message replyToMessage,
                                    Contact contact,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "phone_number", contact.getPhoneNumber(),
        "first_name", contact.getFirstName(),
        "last_name", contact.getLastName().orElse(null),
        "disable_notification", toJson(disableNotification),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendContact", parameters, ContactMessage.class);
  }
  
  public ContactMessage sendContact(Chat chat,
                                    Contact contact,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "phone_number", contact.getPhoneNumber(),
        "first_name", contact.getFirstName(),
        "last_name", contact.getLastName().orElse(null),
        "disable_notification", toJson(disableNotification),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendContact", parameters, ContactMessage.class);
  }
  
  public void sendChatAction(Chat chat, String action) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "action", action
    );
    execute("sendChatAction", parameters, Boolean.class);
  }
  
  public UserProfilePhotos getUserProfilePhotos(User user, int offset, int limit) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "offset", toJson(offset),
        "limit", toJson(limit)
    );
    return execute("getUserProfilePhotos", parameters, UserProfilePhotos.class);
  }
  
  public UserProfilePhotos getUserProfilePhotos(User user) throws IOException {
    Map<String, String> parameters = mapOf("user_id", toJson(user.getId()));
    return execute("getUserProfilePhotos", parameters, UserProfilePhotos.class);
  }
  
  public InputFile getFile(String fileId) throws IOException {
    Map<String, String> parameters = mapOf("file_id", fileId);
    return execute("getFile", parameters, InputFile.class);
  }
  
  public void kickChatMember(Chat chat, User user, long untilDate) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "until_date", toJson(untilDate)
    );
    execute("kickChatMember", parameters, Boolean.class);
  }
  
  public void kickChatMember(Chat chat, User user) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId())
    );
    execute("kickChatMember", parameters, Boolean.class);
  }
  
  public void unbanChatMember(Chat chat, User user) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId())
    );
    execute("unbanChatMember", parameters, Boolean.class);
  }
  
  public void restrictChatMember(SuperGroup chat,
                                 User user,
                                 long untilDate,
                                 boolean canSendMessages,
                                 boolean canSendMediaMessages,
                                 boolean canSendOtherMessages,
                                 boolean canAddWebPagePreviews) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "until_date", toJson(untilDate),
        "can_send_messages", toJson(canSendMessages),
        "can_send_media_messages", toJson(canSendMediaMessages),
        "can_send_other_messages", toJson(canSendOtherMessages),
        "can_add_web_page_previews", toJson(canAddWebPagePreviews)
    );
    execute("restrictChatMember", parameters, Boolean.class);
  }
  
  public void restrictChatMember(SuperGroup chat,
                                 User user,
                                 long untilDate,
                                 boolean canSendMessages,
                                 boolean canSendMediaMessages) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "until_date", toJson(untilDate),
        "can_send_messages", toJson(canSendMessages),
        "can_send_media_messages", toJson(canSendMediaMessages)
    );
    execute("restrictChatMember", parameters, Boolean.class);
  }
  
  public void restrictChatMember(SuperGroup chat,
                                 User user,
                                 long untilDate,
                                 boolean canSendMessages) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "until_date", toJson(untilDate),
        "can_send_messages", toJson(canSendMessages)
    );
    execute("restrictChatMember", parameters, Boolean.class);
  }
  
  public void restrictChatMember(SuperGroup chat,
                                 User user,
                                 long untilDate) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "until_date", toJson(untilDate)
    );
    execute("restrictChatMember", parameters, Boolean.class);
  }
  
  public void restrictChatMember(SuperGroup chat,
                                 User user,
                                 boolean canSendMessages,
                                 boolean canSendMediaMessages,
                                 boolean canSendOtherMessages,
                                 boolean canAddWebPagePreviews) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_send_messages", toJson(canSendMessages),
        "can_send_media_messages", toJson(canSendMediaMessages),
        "can_send_other_messages", toJson(canSendOtherMessages),
        "can_add_web_page_previews", toJson(canAddWebPagePreviews)
    );
    execute("restrictChatMember", parameters, Boolean.class);
  }
  
  public void restrictChatMember(SuperGroup chat,
                                 User user,
                                 boolean canSendMessages,
                                 boolean canSendMediaMessages) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_send_messages", toJson(canSendMessages),
        "can_send_media_messages", toJson(canSendMediaMessages)
    );
    execute("restrictChatMember", parameters, Boolean.class);
  }
  
  public void restrictChatMember(SuperGroup chat,
                                 User user,
                                 boolean canSendMessages) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_send_messages", toJson(canSendMessages)
    );
    execute("restrictChatMember", parameters, Boolean.class);
  }
  
  public void promoteChatMember(Chat chat,
                                User user,
                                boolean canChangeInfo,
                                boolean canPostMessages,
                                boolean canDeleteMessages,
                                boolean canInviteUsers,
                                boolean canRestrictMembers,
                                boolean canPinMessages,
                                boolean canPromoteMembers) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_change_info", toJson(canChangeInfo),
        "can_post_messages", toJson(canPostMessages),
        "can_delete_messages", toJson(canDeleteMessages),
        "can_invite_users", toJson(canInviteUsers),
        "can_restrict_members", toJson(canRestrictMembers),
        "can_pin_messages", toJson(canPinMessages),
        "can_promote_members", toJson(canPromoteMembers)
    );
    execute("promoteChatMember", parameters, Boolean.class);
  }
  
  public void promoteChatMember(Chat chat,
                                User user,
                                boolean canChangeInfo,
                                boolean canDeleteMessages,
                                boolean canInviteUsers,
                                boolean canRestrictMembers,
                                boolean canPinMessages,
                                boolean canPromoteMembers) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_change_info", toJson(canChangeInfo),
        "can_delete_messages", toJson(canDeleteMessages),
        "can_invite_users", toJson(canInviteUsers),
        "can_restrict_members", toJson(canRestrictMembers),
        "can_pin_messages", toJson(canPinMessages),
        "can_promote_members", toJson(canPromoteMembers)
    );
    execute("promoteChatMember", parameters, Boolean.class);
  }
  
  public void memberCanChangeInfo(Chat chat, User user, boolean canChangeInfo) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_change_info", toJson(canChangeInfo)
    );
    execute("promoteChatMember", parameters, Boolean.class);
  }
  
  public void memberCanPostMessages(Chat chat, User user, boolean canPostMessages) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_post_messages", toJson(canPostMessages)
    );
    execute("promoteChatMember", parameters, Boolean.class);
  }
  
  public void memberCanDeleteMessages(Chat chat, User user, boolean canDeleteMessages) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_delete_messages", toJson(canDeleteMessages)
    );
    execute("promoteChatMember", parameters, Boolean.class);
  }
  
  public void memberCanInviteUsers(Chat chat, User user, boolean canInviteUsers) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_invite_users", toJson(canInviteUsers)
    );
    execute("promoteChatMember", parameters, Boolean.class);
  }
  
  public void memberCanRestrictMembers(Chat chat, User user, boolean canRestrictMembers) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_restrict_members", toJson(canRestrictMembers)
    );
    execute("promoteChatMember", parameters, Boolean.class);
  }
  
  public void memberCanPinMessages(Chat chat, User user, boolean canPinMessages) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_pin_messages", toJson(canPinMessages)
    );
    execute("promoteChatMember", parameters, Boolean.class);
  }
  
  public void memberCanPromoteMembers(Chat chat, User user, boolean canPromoteMembers) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(chat.getId()),
        "can_promote_members", toJson(canPromoteMembers)
    );
    execute("promoteChatMember", parameters, Boolean.class);
  }
  
  public String exportChatInviteLink(Chat chat) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chat.getId()));
    return execute("exportChatInviteLink", parameters, String.class);
  }
  
  public String exportChatInviteLink(long chatId) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chatId));
    return execute("exportChatInviteLink", parameters, String.class);
  }
  
  public String exportChatInviteLink(String chatId) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", chatId);
    return execute("exportChatInviteLink", parameters, String.class);
  }
  
  public void setChatPhoto(Chat chat, String photo) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "photo", photo
    );
    execute("setChatPhoto", parameters, Boolean.class);
  }
  
  public void setChatPhoto(Chat chat, UploadFile photo) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chat.getId()));
    Map<String, UploadFile> files = mapOf("photo", photo);
    execute("setChatPhoto", parameters, files, Boolean.class);
  }
  
  public void deleteChatPhoto(Chat chat) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chat.getId()));
    execute("deleteChatPhoto", parameters, Boolean.class);
  }
  
  public void setChatTitle(Chat chat, String title) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "title", title
    );
    execute("setChatTitle", parameters, Boolean.class);
  }
  
  public void setChatDescription(Chat chat, String description) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "description", description
    );
    execute("setChatDescription", parameters, Boolean.class);
  }
  
  public void pinChatMessage(Message message, boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId()),
        "disable_notification", toJson(disableNotification)
    );
    execute("pinChatMessage", parameters, Boolean.class);
  }
  
  public void pinChatMessage(Message message) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId())
    );
    execute("pinChatMessage", parameters, Boolean.class);
  }
  
  public void unpinChatMessage(Chat chat) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chat.getId()));
    execute("unpinChatMessage", parameters, Boolean.class);
  }
  
  public void leaveChat(Chat chat) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chat.getId()));
    execute("leaveChat", parameters, Boolean.class);
  }
  
  public Chat getChat(Chat chat) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chat.getId()));
    return execute("getChat", parameters, Chat.class);
  }
  
  public Chat getChat(long chatId) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chatId));
    return execute("getChat", parameters, Chat.class);
  }
  
  public Chat getChat(String chatId) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", chatId);
    return execute("getChat", parameters, Chat.class);
  }
  
  public ChatMember[] getChatAdministrators(Chat chat) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chat.getId()));
    return execute("getChatAdministrators", parameters, ChatMember[].class);
  }
  
  public int getChatMembersCount(Chat chat) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chat.getId()));
    return execute("getChatMembersCount", parameters, Integer.class);
  }
  
  public ChatMember getChatMember(Chat chat, User user) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "user_id", toJson(user.getId())
    );
    return execute("getChatMember", parameters, ChatMember.class);
  }
  
  public void setChatStickerSet(Chat chat, StickerSet stickerSet) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "sticker_set_name", stickerSet.getName()
    );
    execute("setChatStickerSet", parameters, Boolean.class);
  }
  
  public void setChatStickerSet(Chat chat, String stickerSetName) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "sticker_set_name", stickerSetName
    );
    execute("setChatStickerSet", parameters, Boolean.class);
  }
  
  public void deleteChatStickerSet(Chat chat) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chat.getId()));
    execute("deleteChatStickerSet", parameters, Boolean.class);
  }
  
  public void answerCallbackQuery(CallbackQuery callbackQuery,
                                  String text,
                                  boolean showAlert,
                                  int cacheTime) throws IOException {
    Map<String, String> parameters = mapOf(
        "callback_query_id", callbackQuery.getId(),
        "text", text,
        "show_alert", toJson(showAlert),
        "cache_time", toJson(cacheTime)
    );
    execute("answerCallbackQuery", parameters, Boolean.class);
  }
  
  public void answerCallbackQuery(CallbackQuery callbackQuery,
                                  String text,
                                  boolean showAlert) throws IOException {
    Map<String, String> parameters = mapOf(
        "callback_query_id", callbackQuery.getId(),
        "text", text,
        "show_alert", toJson(showAlert)
    );
    execute("answerCallbackQuery", parameters, Boolean.class);
  }
  
  public void answerCallbackQuery(CallbackQuery callbackQuery,
                                  String url,
                                  int cacheTime) throws IOException {
    Map<String, String> parameters = mapOf(
        "callback_query_id", callbackQuery.getId(),
        "url", url,
        "cache_time", toJson(cacheTime)
    );
    execute("answerCallbackQuery", parameters, Boolean.class);
  }
  
  public void answerCallbackQuery(CallbackQuery callbackQuery, String url) throws IOException {
    Map<String, String> parameters = mapOf(
        "callback_query_id", callbackQuery.getId(),
        "url", url
    );
    execute("answerCallbackQuery", parameters, Boolean.class);
  }
  
  public TextMessage editMessageText(TextMessage message,
                                      Text text,
                                      InlineKeyboardMarkup replyMarkup,
                                      boolean disableWebPagePreview) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId()),
        "text", toJson(text),
        "parse_mode", "HTML",
        "disable_web_page_preview", toJson(disableWebPagePreview),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("editMessageText", parameters, TextMessage.class);
  }
  
  public void editMessageText(String inlineMessageId,
                              Text text,
                              InlineKeyboardMarkup replyMarkup,
                              boolean disableWebPagePreview) throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_message_id", inlineMessageId,
        "text", toJson(text),
        "parse_mode", "HTML",
        "disable_web_page_preview", toJson(disableWebPagePreview),
        "reply_markup", toJson(replyMarkup)
    );
    execute("editMessageText", parameters, Boolean.class);
  }
  
  public TextMessage editMessageText(TextMessage message,
                                     Text text,
                                     InlineKeyboardMarkup replyMarkup) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId()),
        "text", toJson(text),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup)
    );
    return execute("editMessageText", parameters, TextMessage.class);
  }
  
  public void editMessageText(String inlineMessageId,
                              Text text,
                              InlineKeyboardMarkup replyMarkup) throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_message_id", inlineMessageId,
        "text", toJson(text),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup)
    );
    execute("editMessageText", parameters, Boolean.class);
  }
  
  public Message editMessageCaption(Message message,
                                    Text caption,
                                    InlineKeyboardMarkup replyMarkup) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup)
    );
    return execute("editMessageCaption", parameters, Message.class);
  }
  
  public Message editMessageCaption(Message message, Text caption) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId()),
        "caption", toJson(caption),
        "parse_mode", "HTML"
    );
    return execute("editMessageCaption", parameters, Message.class);
  }
  
  public void editMessageCaption(String inlineMessageId,
                                 Text caption,
                                 InlineKeyboardMarkup replyMarkup) throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_message_id", inlineMessageId,
        "caption", toJson(caption),
        "parse_mode", "HTML",
        "reply_markup", toJson(replyMarkup)
    );
    execute("editMessageCaption", parameters, Boolean.class);
  }
  
  public void editMessageCaption(String inlineMessageId, Text caption) throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_message_id", inlineMessageId,
        "caption", toJson(caption),
        "parse_mode", "HTML"
    );
    execute("editMessageCaption", parameters, Boolean.class);
  }
  
  public Message editMessageReplyMarkup(Message message, InlineKeyboardMarkup replyMarkup)
      throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId()),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("editMessageReplyMarkup", parameters, Message.class);
  }
  
  public void editMessageReplyMarkup(String inlineMessageId, InlineKeyboardMarkup replyMarkup)
      throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_message_id", inlineMessageId,
        "reply_markup", toJson(replyMarkup)
    );
    execute("editMessageReplyMarkup", parameters, Boolean.class);
  }
  
  public void deleteMessage(Message message) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId())
    );
    execute("deleteMessage", parameters, Boolean.class);
  }
  
  public StickerMessage sendSticker(Message replyToMessage,
                                    String sticker,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "sticker", sticker,
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendSticker", parameters, StickerMessage.class);
  }
  
  public StickerMessage sendSticker(Chat chat,
                                    String sticker,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "sticker", sticker,
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendSticker", parameters, StickerMessage.class);
  }
  
  public StickerMessage sendSticker(Message replyToMessage, String sticker) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "sticker", sticker,
        "reply_to_message_id", toJson(replyToMessage.getId())
    );
    return execute("sendSticker", parameters, StickerMessage.class);
  }
  
  public StickerMessage sendSticker(Chat chat, String sticker) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "sticker", sticker
    );
    return execute("sendSticker", parameters, StickerMessage.class);
  }
  
  public StickerMessage sendSticker(Message replyToMessage,
                                    UploadFile sticker,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("sticker", sticker);
    return execute("sendSticker", parameters, files, StickerMessage.class);
  }
  
  public StickerMessage sendSticker(Chat chat,
                                    UploadFile sticker,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    Map<String, UploadFile> files = mapOf("sticker", sticker);
    return execute("sendSticker", parameters, files, StickerMessage.class);
  }
  
  public StickerMessage sendSticker(Message replyToMessage, UploadFile sticker) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "reply_to_message_id", toJson(replyToMessage.getId())
    );
    Map<String, UploadFile> files = mapOf("sticker", sticker);
    return execute("sendSticker", parameters, files, StickerMessage.class);
  }
  
  public StickerMessage sendSticker(Chat chat, UploadFile sticker) throws IOException {
    Map<String, String> parameters = mapOf("chat_id", toJson(chat.getId()));
    Map<String, UploadFile> files = mapOf("sticker", sticker);
    return execute("sendSticker", parameters, files, StickerMessage.class);
  }
  
  public StickerSet getStickerSet(String name) throws IOException {
    Map<String, String> parameters = mapOf("name", name);
    return execute("getStickerSet", parameters, StickerSet.class);
  }
  
  public InputFile uploadStickerFile(User owner, UploadFile sticker) throws IOException {
    Map<String, String> parameters = mapOf("user_id", toJson(owner.getId()));
    Map<String, UploadFile> files = mapOf("png_sticker", sticker);
    return execute("uploadStickerFile", parameters, files, InputFile.class);
  }
  
  public InputFile uploadStickerFile(long ownerId, UploadFile sticker) throws IOException {
    Map<String, String> parameters = mapOf("user_id", toJson(ownerId));
    Map<String, UploadFile> files = mapOf("png_sticker", sticker);
    return execute("uploadStickerFile", parameters, files, InputFile.class);
  }
  
  public void createNewStickerSet(User owner,
                                  String name,
                                  String title,
                                  String pngSticker,
                                  String emojis,
                                  boolean containsMasks,
                                  MaskPosition maskPosition) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(owner.getId()),
        "name", name,
        "title", title,
        "png_sticker", pngSticker,
        "emojis", emojis,
        "contains_masks", toJson(containsMasks),
        "mask_position", toJson(maskPosition)
    );
    execute("createNewStickerSet", parameters, Boolean.class);
  }
  
  public void createNewStickerSet(User owner,
                                  String name,
                                  String title,
                                  String pngSticker,
                                  String emojis) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(owner.getId()),
        "name", name,
        "title", title,
        "png_sticker", pngSticker,
        "emojis", emojis
    );
    execute("createNewStickerSet", parameters, Boolean.class);
  }
  
  public void createNewStickerSet(User owner,
                                  String name,
                                  String title,
                                  UploadFile pngSticker,
                                  String emojis,
                                  boolean containsMasks,
                                  MaskPosition maskPosition) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(owner.getId()),
        "name", name,
        "title", title,
        "emojis", emojis,
        "contains_masks", toJson(containsMasks),
        "mask_position", toJson(maskPosition)
    );
    Map<String, UploadFile> files = mapOf("png_sticker", pngSticker);
    execute("createNewStickerSet", parameters, files, Boolean.class);
  }
  
  public void createNewStickerSet(User owner,
                                  String name,
                                  String title,
                                  UploadFile pngSticker,
                                  String emojis) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(owner.getId()),
        "name", name,
        "title", title,
        "emojis", emojis
    );
    Map<String, UploadFile> files = mapOf("png_sticker", pngSticker);
    execute("createNewStickerSet", parameters, files, Boolean.class);
  }
  
  public void createNewStickerSet(long ownerId,
                                  String name,
                                  String title,
                                  String pngSticker,
                                  String emojis,
                                  boolean containsMasks,
                                  MaskPosition maskPosition) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(ownerId),
        "name", name,
        "title", title,
        "png_sticker", pngSticker,
        "emojis", emojis,
        "contains_masks", toJson(containsMasks),
        "mask_position", toJson(maskPosition)
    );
    execute("createNewStickerSet", parameters, Boolean.class);
  }
  
  public void createNewStickerSet(long ownerId,
                                  String name,
                                  String title,
                                  String pngSticker,
                                  String emojis) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(ownerId),
        "name", name,
        "title", title,
        "png_sticker", pngSticker,
        "emojis", emojis
    );
    execute("createNewStickerSet", parameters, Boolean.class);
  }
  
  public void createNewStickerSet(long ownerId,
                                  String name,
                                  String title,
                                  UploadFile pngSticker,
                                  String emojis,
                                  boolean containsMasks,
                                  MaskPosition maskPosition) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(ownerId),
        "name", name,
        "title", title,
        "emojis", emojis,
        "contains_masks", toJson(containsMasks),
        "mask_position", toJson(maskPosition)
    );
    Map<String, UploadFile> files = mapOf("png_sticker", pngSticker);
    execute("createNewStickerSet", parameters, files, Boolean.class);
  }
  
  public void createNewStickerSet(long ownerId,
                                  String name,
                                  String title,
                                  UploadFile pngSticker,
                                  String emojis) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(ownerId),
        "name", name,
        "title", title,
        "emojis", emojis
    );
    Map<String, UploadFile> files = mapOf("png_sticker", pngSticker);
    execute("createNewStickerSet", parameters, files, Boolean.class);
  }
  
  public void addStickerToSet(User owner,
                              String name,
                              String pngSticker,
                              String emojis,
                              MaskPosition maskPosition) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(owner.getId()),
        "name", name,
        "png_sticker", pngSticker,
        "emojis", emojis,
        "mask_position", toJson(maskPosition)
    );
    execute("addStickerToSet", parameters, Boolean.class);
  }
  
  public void addStickerToSet(User owner,
                              String name,
                              String pngSticker,
                              String emojis) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(owner.getId()),
        "name", name,
        "png_sticker", pngSticker,
        "emojis", emojis
    );
    execute("addStickerToSet", parameters, Boolean.class);
  }
  
  public void addStickerToSet(long ownerId,
                              String name,
                              String pngSticker,
                              String emojis,
                              MaskPosition maskPosition) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(ownerId),
        "name", name,
        "png_sticker", pngSticker,
        "emojis", emojis,
        "mask_position", toJson(maskPosition)
    );
    execute("addStickerToSet", parameters, Boolean.class);
  }
  
  public void addStickerToSet(long ownerId,
                              String name,
                              String pngSticker,
                              String emojis) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(ownerId),
        "name", name,
        "png_sticker", pngSticker,
        "emojis", emojis
    );
    execute("addStickerToSet", parameters, Boolean.class);
  }
  
  public void setStickerPositionInSet(Sticker sticker, int position) throws IOException {
    Map<String, String> parameters = mapOf(
        "sticker", sticker.getId(),
        "position", toJson(position)
    );
    execute("setStickerPositionInSet", parameters, Boolean.class);
  }
  
  public void setStickerPositionInSet(String stickerId, int position) throws IOException {
    Map<String, String> parameters = mapOf(
        "sticker", stickerId,
        "position", toJson(position)
    );
    execute("setStickerPositionInSet", parameters, Boolean.class);
  }
  
  public void deleteStickerFromSet(Sticker sticker) throws IOException {
    Map<String, String> parameters = mapOf("sticker", sticker.getId());
    execute("deleteStickerFromSet", parameters, Boolean.class);
  }
  
  public void deleteStickerFromSet(String stickerId) throws IOException {
    Map<String, String> parameters = mapOf("sticker", stickerId);
    execute("deleteStickerFromSet", parameters, Boolean.class);
  }
  
  public void answerInlineQuery(InlineQuery inlineQuery,
                                InlineQueryResult[] results,
                                int cacheTime,
                                boolean isPersonal,
                                String nextOffset,
                                String switchPmText,
                                boolean deepLinking) throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_query_id", inlineQuery.getId(),
        "results", toJson(results),
        "cache_time", toJson(cacheTime),
        "is_personal", toJson(isPersonal),
        "next_offset", nextOffset,
        deepLinking ? "switch_pm_parameter" : "switch_pm_text", switchPmText
    );
    execute("answerInlineQuery", parameters, Boolean.class);
  }
  
  public void answerInlineQuery(InlineQuery inlineQuery,
                                InlineQueryResult[] results,
                                int cacheTime,
                                boolean isPersonal,
                                String nextOffset) throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_query_id", inlineQuery.getId(),
        "results", toJson(results),
        "cache_time", toJson(cacheTime),
        "is_personal", toJson(isPersonal),
        "next_offset", nextOffset
    );
    execute("answerInlineQuery", parameters, Boolean.class);
  }
  
  public void answerInlineQuery(InlineQuery inlineQuery,
                                InlineQueryResult[] results,
                                int cacheTime,
                                boolean isPersonal) throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_query_id", inlineQuery.getId(),
        "results", toJson(results),
        "cache_time", toJson(cacheTime),
        "is_personal", toJson(isPersonal)
    );
    execute("answerInlineQuery", parameters, Boolean.class);
  }
  
  public void answerInlineQuery(InlineQuery inlineQuery,
                                InlineQueryResult[] results,
                                boolean isPersonal) throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_query_id", inlineQuery.getId(),
        "results", toJson(results),
        "is_personal", toJson(isPersonal)
    );
    execute("answerInlineQuery", parameters, Boolean.class);
  }
  
  public void answerInlineQuery(InlineQuery inlineQuery, InlineQueryResult... results) throws IOException {
    Map<String, String> parameters = mapOf(
        "inline_query_id", inlineQuery.getId(),
        "results", toJson(results)
    );
    execute("answerInlineQuery", parameters, Boolean.class);
  }
  
  public InvoiceMessage sendInvoice(Message replyToMessage,
                                    String title,
                                    String description,
                                    String payload,
                                    String providerToken,
                                    String startParameter,
                                    Currency currency,
                                    LabeledPrice[] prices,
                                    String providerData,
                                    String photoUrl,
                                    boolean needName,
                                    boolean needPhoneNumber,
                                    boolean needEmail,
                                    boolean needShippingAddress,
                                    boolean isFlexible,
                                    InlineKeyboardMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "title", title,
        "description", description,
        "payload", payload,
        "provider_token", providerToken,
        "start_parameter", startParameter,
        "currency", currency.getCode(),
        "prices", toJson(prices),
        "provider_data", providerData,
        "photo_url", photoUrl,
        "need_name", toJson(needName),
        "need_phone_number", toJson(needPhoneNumber),
        "need_email", toJson(needEmail),
        "need_shipping_address", toJson(needShippingAddress),
        "is_flexible", toJson(isFlexible),
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendInvoice", parameters, InvoiceMessage.class);
  }
  
  public InvoiceMessage sendInvoice(Message replyToMessage,
                                    String title,
                                    String description,
                                    String payload,
                                    String providerToken,
                                    String startParameter,
                                    Currency currency,
                                    LabeledPrice[] prices,
                                    String providerData,
                                    InlineKeyboardMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "title", title,
        "description", description,
        "payload", payload,
        "provider_token", providerToken,
        "start_parameter", startParameter,
        "currency", currency.getCode(),
        "prices", toJson(prices),
        "provider_data", providerData,
        "reply_markup", toJson(replyMarkup),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendInvoice", parameters, InvoiceMessage.class);
  }
  
  public InvoiceMessage sendInvoice(Message replyToMessage,
                                    String title,
                                    String description,
                                    String payload,
                                    String providerToken,
                                    String startParameter,
                                    Currency currency,
                                    LabeledPrice[] prices) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "title", title,
        "description", description,
        "payload", payload,
        "provider_token", providerToken,
        "start_parameter", startParameter,
        "currency", currency.getCode(),
        "prices", toJson(prices),
        "reply_to_message_id", toJson(replyToMessage.getId())
    );
    return execute("sendInvoice", parameters, InvoiceMessage.class);
  }
  
  public InvoiceMessage sendInvoice(Chat chat,
                                    String title,
                                    String description,
                                    String payload,
                                    String providerToken,
                                    String startParameter,
                                    Currency currency,
                                    LabeledPrice[] prices,
                                    String providerData,
                                    String photoUrl,
                                    boolean needName,
                                    boolean needPhoneNumber,
                                    boolean needEmail,
                                    boolean needShippingAddress,
                                    boolean isFlexible,
                                    InlineKeyboardMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "title", title,
        "description", description,
        "payload", payload,
        "provider_token", providerToken,
        "start_parameter", startParameter,
        "currency", currency.getCode(),
        "prices", toJson(prices),
        "provider_data", providerData,
        "photo_url", photoUrl,
        "need_name", toJson(needName),
        "need_phone_number", toJson(needPhoneNumber),
        "need_email", toJson(needEmail),
        "need_shipping_address", toJson(needShippingAddress),
        "is_flexible", toJson(isFlexible),
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendInvoice", parameters, InvoiceMessage.class);
  }
  
  public InvoiceMessage sendInvoice(Chat chat,
                                    String title,
                                    String description,
                                    String payload,
                                    String providerToken,
                                    String startParameter,
                                    Currency currency,
                                    LabeledPrice[] prices,
                                    String providerData,
                                    InlineKeyboardMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "title", title,
        "description", description,
        "payload", payload,
        "provider_token", providerToken,
        "start_parameter", startParameter,
        "currency", currency.getCode(),
        "prices", toJson(prices),
        "provider_data", providerData,
        "reply_markup", toJson(replyMarkup),
        "disable_notification", toJson(disableNotification)
    );
    return execute("sendInvoice", parameters, InvoiceMessage.class);
  }
  
  public InvoiceMessage sendInvoice(Chat chat,
                                    String title,
                                    String description,
                                    String payload,
                                    String providerToken,
                                    String startParameter,
                                    Currency currency,
                                    LabeledPrice[] prices) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "title", title,
        "description", description,
        "payload", payload,
        "provider_token", providerToken,
        "start_parameter", startParameter,
        "currency", currency.getCode(),
        "prices", toJson(prices)
    );
    return execute("sendInvoice", parameters, InvoiceMessage.class);
  }
  
  public void answerShippingQuery(ShippingQuery shippingQuery, ShippingOption... shippingOptions)
      throws IOException {
    Map<String, String> parameters = mapOf(
        "shipping_query_id", shippingQuery.getId(),
        "ok", "true",
        "shipping_options", toJson(shippingOptions)
    );
    execute("answerShippingQuery", parameters, Boolean.class);
  }
  
  public void answerShippingQuery(ShippingQuery shippingQuery, String errorMessage)
      throws IOException {
    Map<String, String> parameters = mapOf(
        "shipping_query_id", shippingQuery.getId(),
        "ok", "false",
        "error_message", errorMessage
    );
    execute("answerShippingQuery", parameters, Boolean.class);
  }
  
  public void answerPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery) throws IOException {
    Map<String, String> parameters = mapOf(
        "pre_checkout_query_id", preCheckoutQuery.getId(),
        "ok", "true"
    );
    execute("answerPreCheckoutQuery", parameters, Boolean.class);
  }
  
  public void answerPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery, String errorMessage)
      throws IOException {
    Map<String, String> parameters = mapOf(
        "pre_checkout_query_id", preCheckoutQuery.getId(),
        "ok", "false",
        "error_message", errorMessage
    );
    execute("answerPreCheckoutQuery", parameters, Boolean.class);
  }
  
  public GameMessage sendGame(Message replyToMessage,
                              String gameShortName,
                              InlineKeyboardMarkup replyMarkup,
                              boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "game_short_name", gameShortName,
        "disable_notification", toJson(disableNotification),
        "reply_to_message_id", toJson(replyToMessage.getId()),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendGame", parameters, GameMessage.class);
  }
  
  public GameMessage sendGame(Chat chat,
                              String gameShortName,
                              InlineKeyboardMarkup replyMarkup,
                              boolean disableNotification) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "game_short_name", gameShortName,
        "disable_notification", toJson(disableNotification),
        "reply_markup", toJson(replyMarkup)
    );
    return execute("sendGame", parameters, GameMessage.class);
  }
  
  public GameMessage sendGame(Message replyToMessage, String gameShortName) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(replyToMessage.getChat().getId()),
        "game_short_name", gameShortName,
        "reply_to_message_id", toJson(replyToMessage.getId())
    );
    return execute("sendGame", parameters, GameMessage.class);
  }
  
  public GameMessage sendGame(Chat chat, String gameShortName) throws IOException {
    Map<String, String> parameters = mapOf(
        "chat_id", toJson(chat.getId()),
        "game_short_name", gameShortName
    );
    return execute("sendGame", parameters, GameMessage.class);
  }
  
  public GameMessage setGameScore(User user,
                                  int score,
                                  boolean force,
                                  boolean disableEditMessage,
                                  Message message) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "score", toJson(score),
        "force", toJson(force),
        "disable_edit_message", toJson(disableEditMessage),
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId())
    );
    return execute("setGameScore", parameters, GameMessage.class);
  }
  
  public void setGameScore(User user,
                           int score,
                           boolean force,
                           boolean disableEditMessage,
                           String inlineMessageId) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "score", toJson(score),
        "force", toJson(force),
        "disable_edit_message", toJson(disableEditMessage),
        "inline_message_id", inlineMessageId
    );
    execute("setGameScore", parameters, Boolean.class);
  }
  
  public GameMessage setGameScore(User user, int score, Message message) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "score", toJson(score),
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId())
    );
    return execute("setGameScore", parameters, GameMessage.class);
  }
  
  public void setGameScore(User user, int score, String inlineMessageId) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "score", toJson(score),
        "inline_message_id", inlineMessageId
    );
    execute("setGameScore", parameters, Boolean.class);
  }
  
  public GameHighScore[] getGameHighScores(User user, Message message) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "chat_id", toJson(message.getChat().getId()),
        "message_id", toJson(message.getId())
    );
    return execute("getGameHighScores", parameters, GameHighScore[].class);
  }
  
  public GameHighScore[] getGameHighScores(User user, String inlineMessageId) throws IOException {
    Map<String, String> parameters = mapOf(
        "user_id", toJson(user.getId()),
        "inline_message_id", inlineMessageId
    );
    return execute("getGameHighScores", parameters, GameHighScore[].class);
  }
  
  /* end API methods */
  
  private static <K, V> Map<K, V> groupBy(Collection<V> c, Function<V, K> key) {
    Map<K, V> map = new HashMap<>(c.size());
    for (V element : c) {
      map.put(key.apply(element), element);
    }
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf() {
    return Collections.emptyMap();
  }
  
  private static <K, V> Map<K, V> mapOf(K key, V value) {
    return Collections.singletonMap(key, value);
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2) {
    Map<K, V> map = new HashMap<>(2);
    map.put(key1, value1);
    map.put(key2, value2);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3) {
    Map<K, V> map = new HashMap<>(3);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3,
                                        K key4, V value4) {
    Map<K, V> map = new HashMap<>(4);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    map.put(key4, value4);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3,
                                        K key4, V value4,
                                        K key5, V value5) {
    Map<K, V> map = new HashMap<>(5);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    map.put(key4, value4);
    map.put(key5, value5);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3,
                                        K key4, V value4,
                                        K key5, V value5,
                                        K key6, V value6) {
    Map<K, V> map = new HashMap<>(6);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    map.put(key4, value4);
    map.put(key5, value5);
    map.put(key6, value6);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3,
                                        K key4, V value4,
                                        K key5, V value5,
                                        K key6, V value6,
                                        K key7, V value7) {
    Map<K, V> map = new HashMap<>(7);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    map.put(key4, value4);
    map.put(key5, value5);
    map.put(key6, value6);
    map.put(key7, value7);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3,
                                        K key4, V value4,
                                        K key5, V value5,
                                        K key6, V value6,
                                        K key7, V value7,
                                        K key8, V value8) {
    Map<K, V> map = new HashMap<>(8);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    map.put(key4, value4);
    map.put(key5, value5);
    map.put(key6, value6);
    map.put(key7, value7);
    map.put(key8, value8);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3,
                                        K key4, V value4,
                                        K key5, V value5,
                                        K key6, V value6,
                                        K key7, V value7,
                                        K key8, V value8,
                                        K key9, V value9) {
    Map<K, V> map = new HashMap<>(9);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    map.put(key4, value4);
    map.put(key5, value5);
    map.put(key6, value6);
    map.put(key7, value7);
    map.put(key8, value8);
    map.put(key9, value9);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3,
                                        K key4, V value4,
                                        K key5, V value5,
                                        K key6, V value6,
                                        K key7, V value7,
                                        K key8, V value8,
                                        K key9, V value9,
                                        K key10, V value10) {
    Map<K, V> map = new HashMap<>(10);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    map.put(key4, value4);
    map.put(key5, value5);
    map.put(key6, value6);
    map.put(key7, value7);
    map.put(key8, value8);
    map.put(key9, value9);
    map.put(key10, value10);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3,
                                        K key4, V value4,
                                        K key5, V value5,
                                        K key6, V value6,
                                        K key7, V value7,
                                        K key8, V value8,
                                        K key9, V value9,
                                        K key10, V value10,
                                        K key11, V value11) {
    Map<K, V> map = new HashMap<>(11);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    map.put(key4, value4);
    map.put(key5, value5);
    map.put(key6, value6);
    map.put(key7, value7);
    map.put(key8, value8);
    map.put(key9, value9);
    map.put(key10, value10);
    map.put(key11, value11);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3,
                                        K key4, V value4,
                                        K key5, V value5,
                                        K key6, V value6,
                                        K key7, V value7,
                                        K key8, V value8,
                                        K key9, V value9,
                                        K key10, V value10,
                                        K key11, V value11,
                                        K key12, V value12) {
    Map<K, V> map = new HashMap<>(12);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    map.put(key4, value4);
    map.put(key5, value5);
    map.put(key6, value6);
    map.put(key7, value7);
    map.put(key8, value8);
    map.put(key9, value9);
    map.put(key10, value10);
    map.put(key11, value11);
    map.put(key12, value12);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3,
                                        K key4, V value4,
                                        K key5, V value5,
                                        K key6, V value6,
                                        K key7, V value7,
                                        K key8, V value8,
                                        K key9, V value9,
                                        K key10, V value10,
                                        K key11, V value11,
                                        K key12, V value12,
                                        K key13, V value13,
                                        K key14, V value14,
                                        K key15, V value15,
                                        K key16, V value16,
                                        K key17, V value17) {
    Map<K, V> map = new HashMap<>(17);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    map.put(key4, value4);
    map.put(key5, value5);
    map.put(key6, value6);
    map.put(key7, value7);
    map.put(key8, value8);
    map.put(key9, value9);
    map.put(key10, value10);
    map.put(key11, value11);
    map.put(key12, value12);
    map.put(key13, value13);
    map.put(key14, value14);
    map.put(key15, value15);
    map.put(key16, value16);
    map.put(key17, value17);
    return map;
  }
  
  private static <K, V> Map<K, V> mapOf(K key1, V value1,
                                        K key2, V value2,
                                        K key3, V value3,
                                        K key4, V value4,
                                        K key5, V value5,
                                        K key6, V value6,
                                        K key7, V value7,
                                        K key8, V value8,
                                        K key9, V value9,
                                        K key10, V value10,
                                        K key11, V value11,
                                        K key12, V value12,
                                        K key13, V value13,
                                        K key14, V value14,
                                        K key15, V value15,
                                        K key16, V value16,
                                        K key17, V value17,
                                        K key18, V value18) {
    Map<K, V> map = new HashMap<>(18);
    map.put(key1, value1);
    map.put(key2, value2);
    map.put(key3, value3);
    map.put(key4, value4);
    map.put(key5, value5);
    map.put(key6, value6);
    map.put(key7, value7);
    map.put(key8, value8);
    map.put(key9, value9);
    map.put(key10, value10);
    map.put(key11, value11);
    map.put(key12, value12);
    map.put(key13, value13);
    map.put(key14, value14);
    map.put(key15, value15);
    map.put(key16, value16);
    map.put(key17, value17);
    map.put(key18, value18);
    return map;
  }
  
  private static String toJson(Object object) {
    if (object == null) return null;
    return Json.toJson(object, object.getClass());
  }
  
  private static String toJson(Text text) {
    if (text == null) return null;
    return text.toHtmlString();
  }
  
}
