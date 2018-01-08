package me.palazzomichi.telegram.telejam;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import me.palazzomichi.telegram.telejam.methods.*;
import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.objects.File;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.inline.InlineQuery;
import me.palazzomichi.telegram.telejam.objects.inline.results.InlineQueryResult;
import me.palazzomichi.telegram.telejam.objects.inputmedia.InputMedia;
import me.palazzomichi.telegram.telejam.objects.messages.*;
import me.palazzomichi.telegram.telejam.objects.payments.*;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;
import me.palazzomichi.telegram.telejam.objects.replymarkups.ReplyMarkup;
import me.palazzomichi.telegram.telejam.objects.updates.Update;
import me.palazzomichi.telegram.telejam.util.text.ParseMode;
import me.palazzomichi.telegram.telejam.util.text.Text;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

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
   * General link for download Telegram files.
   */
  private static final String API_FILE_URL = "https://api.telegram.org/file/bot";
  
  /**
   * Token to access Telegram API.
   */
  private final String token;
  
  /**
   * The HTTP client used for send requests.
   */
  private CloseableHttpClient httpClient;
  
  
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
    this(newHttpClient(), token);
  }
  
  private static CloseableHttpClient newHttpClient() {
    SocketConfig socketConfig = SocketConfig.custom()
        .setSoTimeout(5_000)
        .build();
    
    return HttpClientBuilder.create()
        .setDefaultSocketConfig(socketConfig)
        .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
        .disableCookieManagement()
        .disableRedirectHandling()
        .build();
  }
  
  /**
   * Invokes a method from the Telegram API.
   *
   * @param method the method
   * @param <T>    the return type
   * @return the object returned from the method invocation
   * @throws IOException        when an I/O Exception occurs during the method
   *                            invocation
   * @throws TelegramException  when the method invocation returns an error
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
   * Returns the stream of the file in the specified path.
   *
   * @param filePath the path of the file
   * @return the stream of the file
   * @throws IOException when an I/O Exception occurs
   */
  public InputStream downloadFile(String filePath) throws IOException {
    Objects.requireNonNull(filePath, "file cannot be null!");
    
    HttpGet httpGet = new HttpGet(API_FILE_URL + token + "/" + filePath);
    return httpClient.execute(httpGet).getEntity().getContent();
  }
  
  /**
   * Returns the stream of a file if is possible.
   *
   * @param file the file
   * @return teh stream of the file
   * @throws IOException when an I/O Exception occurs
   */
  public Optional<InputStream> downloadFile(File file) throws IOException {
    Optional<String> filePath = file.getPath();
    if (filePath.isPresent()) {
      return Optional.of(downloadFile(filePath.get()));
    }
    return Optional.empty();
  }
  
  /* API methods */
  
  public Update[] getUpdates(long offset, int limit, int timeout, String... allowedUpdates) throws IOException {
    GetUpdates getUpdates = new GetUpdates()
        .offset(offset)
        .limit(limit)
        .timeout(timeout)
        .allowedUpdates(allowedUpdates);
    return execute(getUpdates);
  }
  
  public Update[] getUpdates(long offset, int limit, String... allowedUpdates) throws IOException {
    GetUpdates getUpdates = new GetUpdates()
        .offset(offset)
        .limit(limit)
        .allowedUpdates(allowedUpdates);
    return execute(getUpdates);
  }
  
  public Update[] getUpdates(long offset, String... allowedUpdates) throws IOException {
    GetUpdates getUpdates = new GetUpdates()
        .offset(offset)
        .allowedUpdates(allowedUpdates);
    return execute(getUpdates);
  }
  
  public Update[] getUpdates(String... allowedUpdates) throws IOException {
    GetUpdates getUpdates = new GetUpdates()
        .allowedUpdates(allowedUpdates);
    return execute(getUpdates);
  }
  
  public void setWebhook(String url, java.io.File certificate, int maxConnections, String... allowedUpdates)
      throws IOException {
    SetWebhook setWebhook = new SetWebhook()
        .url(url)
        .certificate(certificate)
        .maxConnections(maxConnections)
        .setAllowedUpdates(allowedUpdates);
    execute(setWebhook);
  }
  
  public void deleteWebhook() throws IOException {
    execute(DeleteWebhook.INSTANCE);
  }
  
  public WebhookInfo getWebhookInfo() throws IOException {
    return execute(GetWebhookInfo.INSTANCE);
  }
  
  public User getMe() throws IOException {
    return execute(GetMe.INSTANCE);
  }
  
  public TextMessage sendMessage(Chat chat,
                                 String text,
                                 ParseMode parseMode,
                                 ReplyMarkup replyMarkup,
                                 boolean disableWebPagePreview,
                                 boolean disableNotification) throws IOException {
    SendMessage sendMessage = new SendMessage()
        .chat(chat)
        .text(text)
        .parseMode(parseMode)
        .replyMarkup(replyMarkup)
        .disableWebPagePreview(disableWebPagePreview)
        .disableNotification(disableNotification);
    return execute(sendMessage);
  }
  
  public TextMessage sendMessage(Chat chat,
                                 Text text,
                                 ReplyMarkup replyMarkup,
                                 boolean disableWebPagePreview,
                                 boolean disableNotification) throws IOException {
    SendMessage sendMessage = new SendMessage()
        .chat(chat)
        .text(text)
        .replyMarkup(replyMarkup)
        .disableWebPagePreview(disableWebPagePreview)
        .disableNotification(disableNotification);
    return execute(sendMessage);
  }
  
  public TextMessage sendMessage(Message replyToMessage,
                                 String text,
                                 ParseMode parseMode,
                                 ReplyMarkup replyMarkup,
                                 boolean disableWebPagePreview,
                                 boolean disableNotification) throws IOException {
    SendMessage sendMessage = new SendMessage()
        .replyToMessage(replyToMessage)
        .text(text)
        .parseMode(parseMode)
        .replyMarkup(replyMarkup)
        .disableWebPagePreview(disableWebPagePreview)
        .disableNotification(disableNotification);
    return execute(sendMessage);
  }
  
  public TextMessage sendMessage(Message replyToMessage,
                                 Text text,
                                 ReplyMarkup replyMarkup,
                                 boolean disableWebPagePreview,
                                 boolean disableNotification) throws IOException {
    SendMessage sendMessage = new SendMessage()
        .replyToMessage(replyToMessage)
        .text(text)
        .replyMarkup(replyMarkup)
        .disableWebPagePreview(disableWebPagePreview)
        .disableNotification(disableNotification);
    return execute(sendMessage);
  }
  
  public TextMessage sendMessage(Chat chat,
                                 String text,
                                 ParseMode parseMode,
                                 ReplyMarkup replyMarkup) throws IOException {
    SendMessage sendMessage = new SendMessage()
        .chat(chat)
        .text(text)
        .parseMode(parseMode)
        .replyMarkup(replyMarkup);
    return execute(sendMessage);
  }
  
  public TextMessage sendMessage(Chat chat,
                                 Text text,
                                 ReplyMarkup replyMarkup) throws IOException {
    SendMessage sendMessage = new SendMessage()
        .chat(chat)
        .text(text)
        .replyMarkup(replyMarkup);
    return execute(sendMessage);
  }
  
  public TextMessage sendMessage(Message replyToMessage,
                                 String text,
                                 ParseMode parseMode,
                                 ReplyMarkup replyMarkup) throws IOException {
    SendMessage sendMessage = new SendMessage()
        .replyToMessage(replyToMessage)
        .text(text)
        .parseMode(parseMode)
        .replyMarkup(replyMarkup);
    return execute(sendMessage);
  }
  
  public TextMessage sendMessage(Message replyToMessage,
                                 Text text,
                                 ReplyMarkup replyMarkup) throws IOException {
    SendMessage sendMessage = new SendMessage()
        .replyToMessage(replyToMessage)
        .text(text)
        .replyMarkup(replyMarkup);
    return execute(sendMessage);
  }
  
  public <T extends Message & Forwardable> Forward<T> forwardMessage(T message, Chat chat, boolean disableNotification)
      throws IOException {
    ForwardMessage<T> forwardMessage = new ForwardMessage<T>()
        .message(message)
        .chat(chat)
        .disableNotification(disableNotification);
    return execute(forwardMessage);
  }
  
  public <T extends Message & Forwardable> Forward<T> forwardMessage(T message, Chat chat)
      throws IOException {
    ForwardMessage<T> forwardMessage = new ForwardMessage<T>()
        .message(message)
        .chat(chat);
    return execute(forwardMessage);
  }
  
  public PhotoMessage sendPhoto(Message replyToMessage,
                                String photo,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendPhoto sendPhoto = new SendPhoto()
        .replyToMessage(replyToMessage)
        .photo(photo)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendPhoto);
  }
  
  public PhotoMessage sendPhoto(Chat chat,
                                String photo,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendPhoto sendPhoto = new SendPhoto()
        .chat(chat)
        .photo(photo)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendPhoto);
  }
  
  public PhotoMessage sendPhoto(Message replyToMessage,
                                InputStream photo,
                                String photoName,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendPhoto sendPhoto = new SendPhoto()
        .replyToMessage(replyToMessage)
        .photo(photo, photoName)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendPhoto);
  }
  
  public PhotoMessage sendPhoto(Chat chat,
                                InputStream photo,
                                String photoName,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendPhoto sendPhoto = new SendPhoto()
        .chat(chat)
        .photo(photo, photoName)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendPhoto);
  }
  
  public AudioMessage sendAudio(Message replyToMessage,
                                String audio,
                                String caption,
                                String title,
                                String performer,
                                int duration,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendAudio sendAudio = new SendAudio()
        .replyToMessage(replyToMessage)
        .audio(audio)
        .caption(caption)
        .title(title)
        .performer(performer)
        .duration(duration)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendAudio);
  }
  
  public AudioMessage sendAudio(Message replyToMessage,
                                String audio,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendAudio sendAudio = new SendAudio()
        .replyToMessage(replyToMessage)
        .audio(audio)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendAudio);
  }
  
  public AudioMessage sendAudio(Chat chat,
                                String audio,
                                String caption,
                                String title,
                                String performer,
                                int duration,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendAudio sendAudio = new SendAudio()
        .chat(chat)
        .audio(audio)
        .caption(caption)
        .title(title)
        .performer(performer)
        .duration(duration)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendAudio);
  }
  
  public AudioMessage sendAudio(Chat chat,
                                String audio,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendAudio sendAudio = new SendAudio()
        .chat(chat)
        .audio(audio)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendAudio);
  }
  
  public AudioMessage sendAudio(Message replyToMessage,
                                InputStream audio,
                                String audioName,
                                String caption,
                                String title,
                                String performer,
                                int duration,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendAudio sendAudio = new SendAudio()
        .replyToMessage(replyToMessage)
        .audio(audio, audioName)
        .caption(caption)
        .title(title)
        .performer(performer)
        .duration(duration)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendAudio);
  }
  
  public AudioMessage sendAudio(Message replyToMessage,
                                InputStream audio,
                                String audioName,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendAudio sendAudio = new SendAudio()
        .replyToMessage(replyToMessage)
        .audio(audio, audioName)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendAudio);
  }
  
  public AudioMessage sendAudio(Chat chat,
                                InputStream audio,
                                String audioName,
                                String caption,
                                String title,
                                String performer,
                                int duration,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendAudio sendAudio = new SendAudio()
        .chat(chat)
        .audio(audio, audioName)
        .caption(caption)
        .title(title)
        .performer(performer)
        .duration(duration)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendAudio);
  }
  
  public AudioMessage sendAudio(Chat chat,
                                InputStream audio,
                                String audioName,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendAudio sendAudio = new SendAudio()
        .chat(chat)
        .audio(audio, audioName)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendAudio);
  }
  
  public DocumentMessage sendDocument(Message replyToMessage,
                                      String document,
                                      String caption,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    SendDocument sendDocument = new SendDocument()
        .replyToMessage(replyToMessage)
        .file(document)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendDocument);
  }
  
  public DocumentMessage sendDocument(Chat chat,
                                      String document,
                                      String caption,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    SendDocument sendDocument = new SendDocument()
        .chat(chat)
        .file(document)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendDocument);
  }
  
  public DocumentMessage sendDocument(Message replyToMessage,
                                      InputStream document,
                                      String documentName,
                                      String caption,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    SendDocument sendDocument = new SendDocument()
        .replyToMessage(replyToMessage)
        .file(document, documentName)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendDocument);
  }
  
  public DocumentMessage sendDocument(Chat chat,
                                      InputStream document,
                                      String documentName,
                                      String caption,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    SendDocument sendDocument = new SendDocument()
        .chat(chat)
        .file(document, documentName)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendDocument);
  }
  
  public VideoMessage sendVideo(Message replyToMessage,
                                String video,
                                int duration,
                                int width,
                                int height,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVideo sendVideo = new SendVideo()
        .replyToMessage(replyToMessage)
        .video(video)
        .duration(duration)
        .size(width, height)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoMessage sendVideo(Chat chat,
                                String video,
                                int duration,
                                int width,
                                int height,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVideo sendVideo = new SendVideo()
        .chat(chat)
        .video(video)
        .duration(duration)
        .size(width, height)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoMessage sendVideo(Message replyToMessage,
                                String video,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVideo sendVideo = new SendVideo()
        .replyToMessage(replyToMessage)
        .video(video)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoMessage sendVideo(Chat chat,
                                String video,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVideo sendVideo = new SendVideo()
        .chat(chat)
        .video(video)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoMessage sendVideo(Message replyToMessage,
                                InputStream video,
                                String videoName,
                                int duration,
                                int width,
                                int height,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVideo sendVideo = new SendVideo()
        .replyToMessage(replyToMessage)
        .video(video, videoName)
        .duration(duration)
        .size(width, height)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoMessage sendVideo(Chat chat,
                                InputStream video,
                                String videoName,
                                int duration,
                                int width,
                                int height,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVideo sendVideo = new SendVideo()
        .chat(chat)
        .video(video, videoName)
        .duration(duration)
        .size(width, height)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoMessage sendVideo(Message replyToMessage,
                                InputStream video,
                                String videoName,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVideo sendVideo = new SendVideo()
        .replyToMessage(replyToMessage)
        .video(video, videoName)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoMessage sendVideo(Chat chat,
                                InputStream video,
                                String videoName,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVideo sendVideo = new SendVideo()
        .chat(chat)
        .video(video, videoName)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VoiceMessage sendVoice(Message replyToMessage,
                                String voice,
                                String caption,
                                int duration,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVoice sendVoice = new SendVoice()
        .replyToMessage(replyToMessage)
        .voice(voice)
        .caption(caption)
        .duration(duration)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVoice);
  }
  
  public VoiceMessage sendVoice(Chat chat,
                                String voice,
                                String caption,
                                int duration,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVoice sendVoice = new SendVoice()
        .chat(chat)
        .voice(voice)
        .caption(caption)
        .duration(duration)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVoice);
  }
  
  public VoiceMessage sendVoice(Message replyToMessage,
                                String voice,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVoice sendVoice = new SendVoice()
        .replyToMessage(replyToMessage)
        .voice(voice)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVoice);
  }
  
  public VoiceMessage sendVoice(Chat chat,
                                String voice,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVoice sendVoice = new SendVoice()
        .chat(chat)
        .voice(voice)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVoice);
  }
  
  public VoiceMessage sendVoice(Message replyToMessage,
                                InputStream voice,
                                String voiceName,
                                String caption,
                                int duration,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVoice sendVoice = new SendVoice()
        .replyToMessage(replyToMessage)
        .voice(voice, voiceName)
        .caption(caption)
        .duration(duration)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVoice);
  }
  
  public VoiceMessage sendVoice(Chat chat,
                                InputStream voice,
                                String voiceName,
                                String caption,
                                int duration,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVoice sendVoice = new SendVoice()
        .chat(chat)
        .voice(voice, voiceName)
        .caption(caption)
        .duration(duration)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVoice);
  }
  
  public VoiceMessage sendVoice(Message replyToMessage,
                                InputStream voice,
                                String voiceName,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVoice sendVoice = new SendVoice()
        .replyToMessage(replyToMessage)
        .voice(voice, voiceName)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVoice);
  }
  
  public VoiceMessage sendVoice(Chat chat,
                                InputStream voice,
                                String voiceName,
                                String caption,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVoice sendVoice = new SendVoice()
        .chat(chat)
        .voice(voice, voiceName)
        .caption(caption)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVoice);
  }
  
  public VideoNoteMessage sendVideoNote(Message replyToMessage,
                                        String video,
                                        int duration,
                                        int size,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    SendVideoNote sendVideo = new SendVideoNote()
        .replyToMessage(replyToMessage)
        .video(video)
        .duration(duration)
        .size(size)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoNoteMessage sendVideoNote(Chat chat,
                                        String video,
                                        int duration,
                                        int size,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    SendVideoNote sendVideo = new SendVideoNote()
        .chat(chat)
        .video(video)
        .duration(duration)
        .size(size)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoNoteMessage sendVideoNote(Message replyToMessage,
                                        String video,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    SendVideoNote sendVideo = new SendVideoNote()
        .replyToMessage(replyToMessage)
        .video(video)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoNoteMessage sendVideoNote(Chat chat,
                                        String video,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    SendVideoNote sendVideo = new SendVideoNote()
        .chat(chat)
        .video(video)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoNoteMessage sendVideoNote(Message replyToMessage,
                                        InputStream video,
                                        String videoName,
                                        int duration,
                                        int size,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    SendVideoNote sendVideo = new SendVideoNote()
        .replyToMessage(replyToMessage)
        .video(video, videoName)
        .duration(duration)
        .size(size)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoNoteMessage sendVideoNote(Chat chat,
                                        InputStream video,
                                        String videoName,
                                        int duration,
                                        int size,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    SendVideoNote sendVideo = new SendVideoNote()
        .chat(chat)
        .video(video, videoName)
        .duration(duration)
        .size(size)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoNoteMessage sendVideoNote(Message replyToMessage,
                                        InputStream video,
                                        String videoName,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    SendVideoNote sendVideo = new SendVideoNote()
        .replyToMessage(replyToMessage)
        .video(video, videoName)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public VideoNoteMessage sendVideoNote(Chat chat,
                                        InputStream video,
                                        String videoName,
                                        ReplyMarkup replyMarkup,
                                        boolean disableNotification) throws IOException {
    SendVideoNote sendVideo = new SendVideoNote()
        .chat(chat)
        .video(video, videoName)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVideo);
  }
  
  public LocationMessage sendLocation(Message replyToMessage,
                                      float latitude,
                                      float longitude,
                                      int livePeriod,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    SendLocation sendLocation = new SendLocation()
        .replyToMessage(replyToMessage)
        .latitude(latitude)
        .longitude(longitude)
        .livePeriod(livePeriod)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendLocation);
  }
  
  public LocationMessage sendLocation(Message replyToMessage,
                                      Location location,
                                      int livePeriod,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    SendLocation sendLocation = new SendLocation()
        .replyToMessage(replyToMessage)
        .location(location)
        .livePeriod(livePeriod)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendLocation);
  }
  
  public LocationMessage sendLocation(Message replyToMessage,
                                      float latitude,
                                      float longitude,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    SendLocation sendLocation = new SendLocation()
        .replyToMessage(replyToMessage)
        .latitude(latitude)
        .longitude(longitude)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendLocation);
  }
  
  public LocationMessage sendLocation(Message replyToMessage,
                                      Location location,
                                      ReplyMarkup replyMarkup,
                                      boolean disableNotification) throws IOException {
    SendLocation sendLocation = new SendLocation()
        .replyToMessage(replyToMessage)
        .location(location)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendLocation);
  }
  
  public Serializable editMessageLiveLocation(LocationMessage message,
                                              float latitude,
                                              float longitude,
                                              InlineKeyboardMarkup replyMarkup) throws IOException {
    EditMessageLiveLocation editMessageLiveLocation = new EditMessageLiveLocation()
        .message(message)
        .latitude(latitude)
        .longitude(longitude)
        .replyMarkup(replyMarkup);
    return execute(editMessageLiveLocation);
  }
  
  public Serializable editMessageLiveLocation(String inlineMessageId,
                                              float latitude,
                                              float longitude,
                                              InlineKeyboardMarkup replyMarkup) throws IOException {
    EditMessageLiveLocation editMessageLiveLocation = new EditMessageLiveLocation()
        .inlineMessage(inlineMessageId)
        .latitude(latitude)
        .longitude(longitude)
        .replyMarkup(replyMarkup);
    return execute(editMessageLiveLocation);
  }
  
  public Serializable editMessageLiveLocation(LocationMessage message,
                                              Location location,
                                              InlineKeyboardMarkup replyMarkup) throws IOException {
    EditMessageLiveLocation editMessageLiveLocation = new EditMessageLiveLocation()
        .message(message)
        .location(location)
        .replyMarkup(replyMarkup);
    return execute(editMessageLiveLocation);
  }
  
  public Serializable editMessageLiveLocation(String inlineMessageId,
                                              Location location,
                                              InlineKeyboardMarkup replyMarkup) throws IOException {
    EditMessageLiveLocation editMessageLiveLocation = new EditMessageLiveLocation()
        .inlineMessage(inlineMessageId)
        .location(location)
        .replyMarkup(replyMarkup);
    return execute(editMessageLiveLocation);
  }
  
  public Serializable stopMessageLiveLocation(LocationMessage message,
                                              InlineKeyboardMarkup replyMarkup) throws IOException {
    StopMessageLiveLocation stopMessageLiveLocation = new StopMessageLiveLocation()
        .message(message)
        .replyMarkup(replyMarkup);
    return execute(stopMessageLiveLocation);
  }
  
  public Serializable stopMessageLiveLocation(String inlineMessageId,
                                              InlineKeyboardMarkup replyMarkup) throws IOException {
    StopMessageLiveLocation stopMessageLiveLocation = new StopMessageLiveLocation()
        .inlineMessage(inlineMessageId)
        .replyMarkup(replyMarkup);
    return execute(stopMessageLiveLocation);
  }
  
  public Serializable stopMessageLiveLocation(LocationMessage message) throws IOException {
    StopMessageLiveLocation stopMessageLiveLocation = new StopMessageLiveLocation()
        .message(message);
    return execute(stopMessageLiveLocation);
  }
  
  public Serializable stopMessageLiveLocation(String inlineMessageId) throws IOException {
    StopMessageLiveLocation stopMessageLiveLocation = new StopMessageLiveLocation()
        .inlineMessage(inlineMessageId);
    return execute(stopMessageLiveLocation);
  }
  
  public VenueMessage sendVenue(Message replyToMessage,
                                float latitude,
                                float longitude,
                                String title,
                                String address,
                                String foursquareId,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVenue sendVenue = new SendVenue()
        .replyToMessage(replyToMessage)
        .latitude(latitude)
        .longitude(longitude)
        .title(title)
        .address(address)
        .foursquareId(foursquareId)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVenue);
  }
  
  public VenueMessage sendVenue(Chat chat,
                                float latitude,
                                float longitude,
                                String title,
                                String address,
                                String foursquareId,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVenue sendVenue = new SendVenue()
        .chat(chat)
        .latitude(latitude)
        .longitude(longitude)
        .title(title)
        .address(address)
        .foursquareId(foursquareId)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVenue);
  }
  
  public VenueMessage sendVenue(Message replyToMessage,
                                Location location,
                                String title,
                                String address,
                                String foursquareId,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVenue sendVenue = new SendVenue()
        .replyToMessage(replyToMessage)
        .location(location)
        .title(title)
        .address(address)
        .foursquareId(foursquareId)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVenue);
  }
  
  public VenueMessage sendVenue(Chat chat,
                                Location location,
                                String title,
                                String address,
                                String foursquareId,
                                ReplyMarkup replyMarkup,
                                boolean disableNotification) throws IOException {
    SendVenue sendVenue = new SendVenue()
        .chat(chat)
        .location(location)
        .title(title)
        .address(address)
        .foursquareId(foursquareId)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendVenue);
  }
  
  public ContactMessage sendContact(Message replyToMessage,
                                    String phoneNumber,
                                    String firstName,
                                    String lastName,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    SendContact sendContact = new SendContact()
        .replyToMessage(replyToMessage)
        .phoneNumber(phoneNumber)
        .name(firstName, lastName)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendContact);
  }
  
  public ContactMessage sendContact(Chat chat,
                                    String phoneNumber,
                                    String firstName,
                                    String lastName,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    SendContact sendContact = new SendContact()
        .chat(chat)
        .phoneNumber(phoneNumber)
        .name(firstName, lastName)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendContact);
  }
  
  public ContactMessage sendContact(Message replyToMessage,
                                    String phoneNumber,
                                    String firstName,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    SendContact sendContact = new SendContact()
        .replyToMessage(replyToMessage)
        .phoneNumber(phoneNumber)
        .firstName(firstName)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendContact);
  }
  
  public ContactMessage sendContact(Chat chat,
                                    String phoneNumber,
                                    String firstName,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    SendContact sendContact = new SendContact()
        .chat(chat)
        .phoneNumber(phoneNumber)
        .firstName(firstName)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendContact);
  }
  
  public void sendChatAction(Chat chat, String action) throws IOException {
    SendChatAction sendChatAction = new SendChatAction()
        .chat(chat)
        .action(action);
    execute(sendChatAction);
  }
  
  public UserProfilePhotos getUserProfilePhotos(User user, int offset, int limit) throws IOException {
    GetUserProfilePhotos getUserProfilePhotos = new GetUserProfilePhotos()
        .user(user)
        .offset(offset)
        .limit(limit);
    return execute(getUserProfilePhotos);
  }
  
  public UserProfilePhotos getUserProfilePhotos(User user) throws IOException {
    GetUserProfilePhotos getUserProfilePhotos = new GetUserProfilePhotos()
        .user(user);
    return execute(getUserProfilePhotos);
  }
  
  public File getFile(String fileId) throws IOException {
    GetFile getFile = new GetFile()
        .fileId(fileId);
    return execute(getFile);
  }
  
  public void kickChatMember(Chat chat, User user, Date untilDate) throws IOException {
    KickChatMember kickChatMember = new KickChatMember()
        .chat(chat)
        .user(user)
        .untilDate(untilDate);
    execute(kickChatMember);
  }
  
  public void kickChatMember(Chat chat, User user, long untilDate) throws IOException {
    KickChatMember kickChatMember = new KickChatMember()
        .chat(chat)
        .user(user)
        .untilDate(untilDate);
    execute(kickChatMember);
  }
  
  public void kickChatMember(Chat chat, User user) throws IOException {
    KickChatMember kickChatMember = new KickChatMember()
        .chat(chat)
        .user(user);
    execute(kickChatMember);
  }
  
  public void unbanChatMember(Chat chat, User user) throws IOException {
    UnbanChatMember unbanChatMember = new UnbanChatMember()
        .chat(chat)
        .user(user);
    execute(unbanChatMember);
  }
  
  public void restrictChatMember(Chat chat,
                                 User user,
                                 Date untilDate,
                                 boolean canSendMessages,
                                 boolean canSendMediaMessages,
                                 boolean canSendOtherMessages,
                                 boolean canAddWebPagePreviews) throws IOException {
    RestrictChatMember restrictChatMember = new RestrictChatMember()
        .chat(chat)
        .user(user)
        .untilDate(untilDate)
        .canSendMessages(canSendMessages)
        .canSendMediaMessages(canSendMediaMessages)
        .canSendOtherMessages(canSendOtherMessages)
        .canAddWebPagePreviews(canAddWebPagePreviews);
    execute(restrictChatMember);
  }
  
  public void restrictChatMember(Chat chat,
                                 User user,
                                 Date untilDate,
                                 boolean canSendMessages,
                                 boolean canSendMediaMessages) throws IOException {
    RestrictChatMember restrictChatMember = new RestrictChatMember()
        .chat(chat)
        .user(user)
        .untilDate(untilDate)
        .canSendMessages(canSendMessages)
        .canSendMediaMessages(canSendMediaMessages);
    execute(restrictChatMember);
  }
  
  public void restrictChatMember(Chat chat,
                                 User user,
                                 Date untilDate,
                                 boolean canSendMessages) throws IOException {
    RestrictChatMember restrictChatMember = new RestrictChatMember()
        .chat(chat)
        .user(user)
        .untilDate(untilDate)
        .canSendMessages(canSendMessages);
    execute(restrictChatMember);
  }
  
  public void restrictChatMember(Chat chat,
                                 User user,
                                 Date untilDate) throws IOException {
    RestrictChatMember restrictChatMember = new RestrictChatMember()
        .chat(chat)
        .user(user)
        .untilDate(untilDate);
    execute(restrictChatMember);
  }
  
  public void restrictChatMember(Chat chat,
                                 User user,
                                 boolean canSendMessages,
                                 boolean canSendMediaMessages,
                                 boolean canSendOtherMessages,
                                 boolean canAddWebPagePreviews) throws IOException {
    RestrictChatMember restrictChatMember = new RestrictChatMember()
        .chat(chat)
        .user(user)
        .canSendMessages(canSendMessages)
        .canSendMediaMessages(canSendMediaMessages)
        .canSendOtherMessages(canSendOtherMessages)
        .canAddWebPagePreviews(canAddWebPagePreviews);
    execute(restrictChatMember);
  }
  
  public void restrictChatMember(Chat chat,
                                 User user,
                                 boolean canSendMessages,
                                 boolean canSendMediaMessages) throws IOException {
    RestrictChatMember restrictChatMember = new RestrictChatMember()
        .chat(chat)
        .user(user)
        .canSendMessages(canSendMessages)
        .canSendMediaMessages(canSendMediaMessages);
    execute(restrictChatMember);
  }
  
  public void restrictChatMember(Chat chat,
                                 User user,
                                 boolean canSendMessages) throws IOException {
    RestrictChatMember restrictChatMember = new RestrictChatMember()
        .chat(chat)
        .user(user)
        .canSendMessages(canSendMessages);
    execute(restrictChatMember);
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
    PromoteChatMember promoteChatMember = new PromoteChatMember()
        .chat(chat)
        .user(user)
        .canChangeInfo(canChangeInfo)
        .canPostMessages(canPostMessages)
        .canDeleteMessages(canDeleteMessages)
        .canInviteUsers(canInviteUsers)
        .canRestrictMembers(canRestrictMembers)
        .canPinMessages(canPinMessages)
        .canPromoteMembers(canPromoteMembers);
    execute(promoteChatMember);
  }
  
  public void promoteChatMember(Chat chat,
                                User user,
                                boolean canChangeInfo,
                                boolean canDeleteMessages,
                                boolean canInviteUsers,
                                boolean canRestrictMembers,
                                boolean canPinMessages,
                                boolean canPromoteMembers) throws IOException {
    PromoteChatMember promoteChatMember = new PromoteChatMember()
        .chat(chat)
        .user(user)
        .canChangeInfo(canChangeInfo)
        .canDeleteMessages(canDeleteMessages)
        .canInviteUsers(canInviteUsers)
        .canRestrictMembers(canRestrictMembers)
        .canPinMessages(canPinMessages)
        .canPromoteMembers(canPromoteMembers);
    execute(promoteChatMember);
  }
  
  public void memberCanChangeInfo(Chat chat, User user, boolean canChangeInfo) throws IOException {
    PromoteChatMember promoteChatMember = new PromoteChatMember()
        .chat(chat)
        .user(user)
        .canChangeInfo(canChangeInfo);
    execute(promoteChatMember);
  }
  
  public void memberCanPostMessages(Chat chat, User user, boolean canPostMessages) throws IOException {
    PromoteChatMember promoteChatMember = new PromoteChatMember()
        .chat(chat)
        .user(user)
        .canPostMessages(canPostMessages);
    execute(promoteChatMember);
  }
  
  public void memberCanDeleteMessages(Chat chat, User user, boolean canDeleteMessages) throws IOException {
    PromoteChatMember promoteChatMember = new PromoteChatMember()
        .chat(chat)
        .user(user)
        .canDeleteMessages(canDeleteMessages);
    execute(promoteChatMember);
  }
  
  public void memberCanInviteUsers(Chat chat, User user, boolean canInviteUsers) throws IOException {
    PromoteChatMember promoteChatMember = new PromoteChatMember()
        .chat(chat)
        .user(user)
        .canInviteUsers(canInviteUsers);
    execute(promoteChatMember);
  }
  
  public void memberCanRestrictMembers(Chat chat, User user, boolean canRestrictMembers) throws IOException {
    PromoteChatMember promoteChatMember = new PromoteChatMember()
        .chat(chat)
        .user(user)
        .canRestrictMembers(canRestrictMembers);
    execute(promoteChatMember);
  }
  
  public void memberCanPinMessages(Chat chat, User user, boolean canPinMessages) throws IOException {
    PromoteChatMember promoteChatMember = new PromoteChatMember()
        .chat(chat)
        .user(user)
        .canPinMessages(canPinMessages);
    execute(promoteChatMember);
  }
  
  public void memberCanPromoteMembers(Chat chat, User user, boolean canPromoteMembers) throws IOException {
    PromoteChatMember promoteChatMember = new PromoteChatMember()
        .chat(chat)
        .user(user)
        .canPromoteMembers(canPromoteMembers);
    execute(promoteChatMember);
  }
  
  public String exportChatInviteLink(Chat chat) throws IOException {
    ExportChatInviteLink exportChatInviteLink = new ExportChatInviteLink()
        .chat(chat);
    return execute(exportChatInviteLink);
  }
  
  public String exportChatInviteLink(long chatId) throws IOException {
    ExportChatInviteLink exportChatInviteLink = new ExportChatInviteLink()
        .chat(chatId);
    return execute(exportChatInviteLink);
  }
  
  public String exportChatInviteLink(String chatId) throws IOException {
    ExportChatInviteLink exportChatInviteLink = new ExportChatInviteLink()
        .chat(chatId);
    return execute(exportChatInviteLink);
  }
  
  public void setChatPhoto(Chat chat, String photo) throws IOException {
    SetChatPhoto setChatPhoto = new SetChatPhoto()
        .chat(chat)
        .photo(photo);
    execute(setChatPhoto);
  }
  
  public void setChatPhoto(Chat chat, InputStream photo, String photoName) throws IOException {
    SetChatPhoto setChatPhoto = new SetChatPhoto()
        .chat(chat)
        .photo(photo, photoName);
    execute(setChatPhoto);
  }
  
  public void setChatPhoto(Chat chat, java.io.File photo) throws IOException {
    SetChatPhoto setChatPhoto = new SetChatPhoto()
        .chat(chat)
        .photo(photo);
    execute(setChatPhoto);
  }
  
  public void deleteChatPhoto(Chat chat) throws IOException {
    DeleteChatPhoto deleteChatPhoto = new DeleteChatPhoto()
        .chat(chat);
    execute(deleteChatPhoto);
  }
  
  public void setChatTitle(Chat chat, String title) throws IOException {
    SetChatTitle setChatTitle = new SetChatTitle()
        .chat(chat)
        .title(title);
    execute(setChatTitle);
  }
  
  public void setChatDescription(Chat chat, String description) throws IOException {
    SetChatDescription setChatDescription = new SetChatDescription()
        .chat(chat)
        .description(description);
    execute(setChatDescription);
  }
  
  public void pinChatMessage(Message message, boolean disableNotification) throws IOException {
    PinChatMessage pinChatMessage = new PinChatMessage()
        .message(message)
        .disableNotification(disableNotification);
    execute(pinChatMessage);
  }
  
  public void pinChatMessage(Message message) throws IOException {
    PinChatMessage pinChatMessage = new PinChatMessage()
        .message(message);
    execute(pinChatMessage);
  }
  
  public void unpinChatMessage(Chat chat) throws IOException {
    UnpinChatMessage unpinChatMessage = new UnpinChatMessage()
        .chat(chat);
    execute(unpinChatMessage);
  }
  
  public void leaveChat(Chat chat) throws IOException {
    LeaveChat leaveChat = new LeaveChat()
        .chat(chat);
    execute(leaveChat);
  }
  
  public Chat getChat(Chat chat) throws IOException {
    GetChat getChat = new GetChat()
        .chat(chat);
    return execute(getChat);
  }
  
  public Chat getChat(long chatId) throws IOException {
    GetChat getChat = new GetChat()
        .chat(chatId);
    return execute(getChat);
  }
  
  public Chat getChat(String chatId) throws IOException {
    GetChat getChat = new GetChat()
        .chat(chatId);
    return execute(getChat);
  }
  
  public ChatMember[] getChatAdministrators(Chat chat) throws IOException {
    GetChatAdministrators getChatAdministrators = new GetChatAdministrators()
        .chat(chat);
    return execute(getChatAdministrators);
  }
  
  public int getChatMembersCount(Chat chat) throws IOException {
    GetChatMembersCount getChatMembersCount = new GetChatMembersCount()
        .chat(chat);
    return execute(getChatMembersCount);
  }
  
  public ChatMember getChatMember(Chat chat, User user) throws IOException {
    GetChatMember getChatMember = new GetChatMember()
        .chat(chat)
        .user(user);
    return execute(getChatMember);
  }
  
  public void setChatStickerSet(Chat chat, StickerSet stickerSet) throws IOException {
    SetChatStickerSet setChatStickerSet = new SetChatStickerSet()
        .chat(chat)
        .stickerSet(stickerSet);
    execute(setChatStickerSet);
  }
  
  public void setChatStickerSet(Chat chat, String stickerSetName) throws IOException {
    SetChatStickerSet setChatStickerSet = new SetChatStickerSet()
        .chat(chat)
        .stickerSet(stickerSetName);
    execute(setChatStickerSet);
  }
  
  public void deleteChatStickerSet(Chat chat) throws IOException {
    DeleteChatStickerSet deleteChatStickerSet = new DeleteChatStickerSet()
        .chat(chat);
    execute(deleteChatStickerSet);
  }
  
  public void answerCallbackQuery(CallbackQuery callbackQuery,
                                  String text,
                                  boolean showAlert,
                                  int cacheTime) throws IOException {
    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery()
        .callbackQuery(callbackQuery)
        .text(text)
        .showAlert(showAlert)
        .cacheTime(cacheTime);
    execute(answerCallbackQuery);
  }
  
  public void answerCallbackQuery(CallbackQuery callbackQuery,
                                  String text,
                                  boolean showAlert) throws IOException {
    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery()
        .callbackQuery(callbackQuery)
        .text(text)
        .showAlert(showAlert);
    execute(answerCallbackQuery);
  }
  
  public void answerCallbackQuery(CallbackQuery callbackQuery,
                                  String url,
                                  int cacheTime) throws IOException {
    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery()
        .callbackQuery(callbackQuery)
        .url(url)
        .cacheTime(cacheTime);
    execute(answerCallbackQuery);
  }
  
  public void answerCallbackQuery(CallbackQuery callbackQuery, String url) throws IOException {
    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery()
        .callbackQuery(callbackQuery)
        .url(url);
    execute(answerCallbackQuery);
  }
  
  public Serializable editMessageText(TextMessage message,
                                      Text text,
                                      InlineKeyboardMarkup replyMarkup,
                                      boolean disableWebPagePreview) throws IOException {
    EditMessageText editMessageText = new EditMessageText()
        .message(message)
        .text(text)
        .replyMarkup(replyMarkup)
        .disableWebPagePreview(disableWebPagePreview);
    return execute(editMessageText);
  }
  
  public Serializable editMessageText(String inlineMessageId,
                                      Text text,
                                      InlineKeyboardMarkup replyMarkup,
                                      boolean disableWebPagePreview) throws IOException {
    EditMessageText editMessageText = new EditMessageText()
        .inlineMessage(inlineMessageId)
        .text(text)
        .replyMarkup(replyMarkup)
        .disableWebPagePreview(disableWebPagePreview);
    return execute(editMessageText);
  }
  
  public Serializable editMessageText(TextMessage message,
                                      Text text,
                                      InlineKeyboardMarkup replyMarkup) throws IOException {
    EditMessageText editMessageText = new EditMessageText()
        .message(message)
        .text(text)
        .replyMarkup(replyMarkup);
    return execute(editMessageText);
  }
  
  public Serializable editMessageText(String inlineMessageId,
                                      Text text,
                                      InlineKeyboardMarkup replyMarkup) throws IOException {
    EditMessageText editMessageText = new EditMessageText()
        .inlineMessage(inlineMessageId)
        .text(text)
        .replyMarkup(replyMarkup);
    return execute(editMessageText);
  }
  
  public Serializable editMessageText(TextMessage message,
                                      String text,
                                      ParseMode parseMode,
                                      InlineKeyboardMarkup replyMarkup,
                                      boolean disableWebPagePreview) throws IOException {
    EditMessageText editMessageText = new EditMessageText()
        .message(message)
        .text(text)
        .parseMode(parseMode)
        .replyMarkup(replyMarkup)
        .disableWebPagePreview(disableWebPagePreview);
    return execute(editMessageText);
  }
  
  public Serializable editMessageText(String inlineMessageId,
                                      String text,
                                      ParseMode parseMode,
                                      InlineKeyboardMarkup replyMarkup,
                                      boolean disableWebPagePreview) throws IOException {
    EditMessageText editMessageText = new EditMessageText()
        .inlineMessage(inlineMessageId)
        .text(text)
        .parseMode(parseMode)
        .replyMarkup(replyMarkup)
        .disableWebPagePreview(disableWebPagePreview);
    return execute(editMessageText);
  }
  
  public Serializable editMessageText(TextMessage message,
                                      String text,
                                      ParseMode parseMode,
                                      InlineKeyboardMarkup replyMarkup) throws IOException {
    EditMessageText editMessageText = new EditMessageText()
        .message(message)
        .text(text)
        .parseMode(parseMode)
        .replyMarkup(replyMarkup);
    return execute(editMessageText);
  }
  
  public Serializable editMessageText(String inlineMessageId,
                                      String text,
                                      ParseMode parseMode,
                                      InlineKeyboardMarkup replyMarkup) throws IOException {
    EditMessageText editMessageText = new EditMessageText()
        .inlineMessage(inlineMessageId)
        .text(text)
        .parseMode(parseMode)
        .replyMarkup(replyMarkup);
    return execute(editMessageText);
  }
  
  public <CaptionedMessage extends Message & Captioned> Serializable editMessageCaption(
      CaptionedMessage message,
      String caption,
      InlineKeyboardMarkup replyMarkup) throws IOException {
    EditMessageCaption editMessageCaption = new EditMessageCaption()
        .message(message)
        .caption(caption)
        .replyMarkup(replyMarkup);
    return execute(editMessageCaption);
  }
  
  public <CaptionedMessage extends Message & Captioned> Serializable editMessageCaption(
      CaptionedMessage message,
      String caption) throws IOException {
    EditMessageCaption editMessageCaption = new EditMessageCaption()
        .message(message)
        .caption(caption);
    return execute(editMessageCaption);
  }
  
  public Serializable editMessageCaption(String inlineMessageId,
                                         String caption,
                                         InlineKeyboardMarkup replyMarkup) throws IOException {
    EditMessageCaption editMessageCaption = new EditMessageCaption()
        .inlineMessage(inlineMessageId)
        .caption(caption)
        .replyMarkup(replyMarkup);
    return execute(editMessageCaption);
  }
  
  public Serializable editMessageCaption(String inlineMessageId, String caption) throws IOException {
    EditMessageCaption editMessageCaption = new EditMessageCaption()
        .inlineMessage(inlineMessageId)
        .caption(caption);
    return execute(editMessageCaption);
  }
  
  public Serializable editMessageReplyMarkup(Message message, InlineKeyboardMarkup replyMarkup)
      throws IOException {
    EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup()
        .message(message)
        .replyMarkup(replyMarkup);
    return execute(editMessageReplyMarkup);
  }
  
  public Serializable editMessageReplyMarkup(String inlineMessageId, InlineKeyboardMarkup replyMarkup)
      throws IOException {
    EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup()
        .inlineMessage(inlineMessageId)
        .replyMarkup(replyMarkup);
    return execute(editMessageReplyMarkup);
  }
  
  public void deleteMessage(Message message) throws IOException {
    DeleteMessage deleteMessage = new DeleteMessage()
        .message(message);
    execute(deleteMessage);
  }
  
  public StickerMessage sendSticker(Message replyToMessage,
                                    String sticker,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    SendSticker sendSticker = new SendSticker()
        .replyToMessage(replyToMessage)
        .sticker(sticker)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendSticker);
  }
  
  public StickerMessage sendSticker(Chat chat,
                                    String sticker,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    SendSticker sendSticker = new SendSticker()
        .chat(chat)
        .sticker(sticker)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendSticker);
  }
  
  public StickerMessage sendSticker(Message replyToMessage,
                                    String sticker) throws IOException {
    SendSticker sendSticker = new SendSticker()
        .replyToMessage(replyToMessage)
        .sticker(sticker);
    return execute(sendSticker);
  }
  
  public StickerMessage sendSticker(Chat chat,
                                    String sticker) throws IOException {
    SendSticker sendSticker = new SendSticker()
        .chat(chat)
        .sticker(sticker);
    return execute(sendSticker);
  }
  
  public StickerMessage sendSticker(Message replyToMessage,
                                    InputStream sticker,
                                    String stickerName,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    SendSticker sendSticker = new SendSticker()
        .replyToMessage(replyToMessage)
        .sticker(sticker, stickerName)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendSticker);
  }
  
  public StickerMessage sendSticker(Chat chat,
                                    InputStream sticker,
                                    String stickerName,
                                    ReplyMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    SendSticker sendSticker = new SendSticker()
        .chat(chat)
        .sticker(sticker, stickerName)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendSticker);
  }
  
  public StickerMessage sendSticker(Message replyToMessage,
                                    InputStream sticker,
                                    String stickerName) throws IOException {
    SendSticker sendSticker = new SendSticker()
        .replyToMessage(replyToMessage)
        .sticker(sticker, stickerName);
    return execute(sendSticker);
  }
  
  public StickerMessage sendSticker(Chat chat,
                                    InputStream sticker,
                                    String stickerName) throws IOException {
    SendSticker sendSticker = new SendSticker()
        .chat(chat)
        .sticker(sticker, stickerName);
    return execute(sendSticker);
  }
  
  public StickerSet getStickerSet(String name) throws IOException {
    GetStickerSet getStickerSet = new GetStickerSet()
        .name(name);
    return execute(getStickerSet);
  }
  
  public File uploadStickerFile(User owner, InputStream sticker) throws IOException {
    UploadStickerFile uploadStickerFile = new UploadStickerFile()
        .user(owner)
        .sticker(sticker);
    return execute(uploadStickerFile);
  }
  
  public File uploadStickerFile(long ownerId, InputStream sticker) throws IOException {
    UploadStickerFile uploadStickerFile = new UploadStickerFile()
        .user(ownerId)
        .sticker(sticker);
    return execute(uploadStickerFile);
  }
  
  public void createNewStickerSet(User owner,
                                  String name,
                                  String title,
                                  String pngSticker,
                                  String emojis,
                                  boolean containsMasks,
                                  MaskPosition maskPosition) throws IOException {
    CreateNewStickerSet createNewStickerSet = new CreateNewStickerSet()
        .user(owner)
        .name(name)
        .title(title)
        .sticker(pngSticker)
        .emojis(emojis)
        .containsMask(containsMasks)
        .maskPosition(maskPosition);
    execute(createNewStickerSet);
  }
  
  public void createNewStickerSet(User owner,
                                  String name,
                                  String title,
                                  String pngSticker,
                                  String emojis) throws IOException {
    CreateNewStickerSet createNewStickerSet = new CreateNewStickerSet()
        .user(owner)
        .name(name)
        .title(title)
        .sticker(pngSticker)
        .emojis(emojis);
    execute(createNewStickerSet);
  }
  
  public void createNewStickerSet(User owner,
                                  String name,
                                  String title,
                                  InputStream pngSticker,
                                  String emojis,
                                  boolean containsMasks,
                                  MaskPosition maskPosition) throws IOException {
    CreateNewStickerSet createNewStickerSet = new CreateNewStickerSet()
        .user(owner)
        .name(name)
        .title(title)
        .sticker(pngSticker)
        .emojis(emojis)
        .containsMask(containsMasks)
        .maskPosition(maskPosition);
    execute(createNewStickerSet);
  }
  
  public void createNewStickerSet(User owner,
                                  String name,
                                  String title,
                                  InputStream pngSticker,
                                  String emojis) throws IOException {
    CreateNewStickerSet createNewStickerSet = new CreateNewStickerSet()
        .user(owner)
        .name(name)
        .title(title)
        .sticker(pngSticker)
        .emojis(emojis);
    execute(createNewStickerSet);
  }
  
  public void createNewStickerSet(long ownerId,
                                  String name,
                                  String title,
                                  String pngSticker,
                                  String emojis,
                                  boolean containsMasks,
                                  MaskPosition maskPosition) throws IOException {
    CreateNewStickerSet createNewStickerSet = new CreateNewStickerSet()
        .user(ownerId)
        .name(name)
        .title(title)
        .sticker(pngSticker)
        .emojis(emojis)
        .containsMask(containsMasks)
        .maskPosition(maskPosition);
    execute(createNewStickerSet);
  }
  
  public void createNewStickerSet(long ownerId,
                                  String name,
                                  String title,
                                  String pngSticker,
                                  String emojis) throws IOException {
    CreateNewStickerSet createNewStickerSet = new CreateNewStickerSet()
        .user(ownerId)
        .name(name)
        .title(title)
        .sticker(pngSticker)
        .emojis(emojis);
    execute(createNewStickerSet);
  }
  
  public void createNewStickerSet(long ownerId,
                                  String name,
                                  String title,
                                  InputStream pngSticker,
                                  String emojis,
                                  boolean containsMasks,
                                  MaskPosition maskPosition) throws IOException {
    CreateNewStickerSet createNewStickerSet = new CreateNewStickerSet()
        .user(ownerId)
        .name(name)
        .title(title)
        .sticker(pngSticker)
        .emojis(emojis)
        .containsMask(containsMasks)
        .maskPosition(maskPosition);
    execute(createNewStickerSet);
  }
  
  public void createNewStickerSet(long ownerId,
                                  String name,
                                  String title,
                                  InputStream pngSticker,
                                  String emojis) throws IOException {
    CreateNewStickerSet createNewStickerSet = new CreateNewStickerSet()
        .user(ownerId)
        .name(name)
        .title(title)
        .sticker(pngSticker)
        .emojis(emojis);
    execute(createNewStickerSet);
  }
  
  public void addStickerToSet(User owner,
                              String name,
                              String pngSticker,
                              String emojis,
                              MaskPosition maskPosition) throws IOException {
    AddStickerToSet addStickerToSet = new AddStickerToSet()
        .user(owner)
        .name(name)
        .sticker(pngSticker)
        .emojis(emojis)
        .maskPosition(maskPosition);
    execute(addStickerToSet);
  }
  
  public void addStickerToSet(User owner,
                              String name,
                              String pngSticker,
                              String emojis) throws IOException {
    AddStickerToSet addStickerToSet = new AddStickerToSet()
        .user(owner)
        .name(name)
        .sticker(pngSticker)
        .emojis(emojis);
    execute(addStickerToSet);
  }
  
  public void addStickerToSet(long ownerId,
                              String name,
                              String pngSticker,
                              String emojis,
                              MaskPosition maskPosition) throws IOException {
    AddStickerToSet addStickerToSet = new AddStickerToSet()
        .user(ownerId)
        .name(name)
        .sticker(pngSticker)
        .emojis(emojis)
        .maskPosition(maskPosition);
    execute(addStickerToSet);
  }
  
  public void addStickerToSet(long ownerId,
                              String name,
                              String pngSticker,
                              String emojis) throws IOException {
    AddStickerToSet addStickerToSet = new AddStickerToSet()
        .user(ownerId)
        .name(name)
        .sticker(pngSticker)
        .emojis(emojis);
    execute(addStickerToSet);
  }
  
  public void setStickerPositionInSet(Sticker sticker, int position) throws IOException {
    SetStickerPositionInSet setStickerPositionInSet = new SetStickerPositionInSet()
        .sticker(sticker)
        .position(position);
    execute(setStickerPositionInSet);
  }
  
  public void setStickerPositionInSet(String stickerId, int position) throws IOException {
    SetStickerPositionInSet setStickerPositionInSet = new SetStickerPositionInSet()
        .sticker(stickerId)
        .position(position);
    execute(setStickerPositionInSet);
  }
  
  public void deleteStickerFromSet(Sticker sticker) throws IOException {
    DeleteStickerFromSet deleteStickerFromSet = new DeleteStickerFromSet()
        .sticker(sticker);
    execute(deleteStickerFromSet);
  }
  
  public void deleteStickerFromSet(String stickerId) throws IOException {
    DeleteStickerFromSet deleteStickerFromSet = new DeleteStickerFromSet()
        .sticker(stickerId);
    execute(deleteStickerFromSet);
  }
  
  public void answerInlineQuery(InlineQuery inlineQuery,
                                InlineQueryResult[] results,
                                int cacheTime,
                                boolean isPersonal,
                                String nextOffset,
                                String switchPmText,
                                boolean deepLinking) throws IOException {
    AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery()
        .inlineQuery(inlineQuery)
        .results(results)
        .cacheTime(cacheTime)
        .isPersonal(isPersonal)
        .nextOffset(nextOffset);
    if (deepLinking) {
      answerInlineQuery.switchPmParameter(switchPmText);
    } else {
      answerInlineQuery.switchPmText(switchPmText);
    }
    execute(answerInlineQuery);
  }
  
  public void answerInlineQuery(InlineQuery inlineQuery,
                                InlineQueryResult[] results,
                                int cacheTime,
                                boolean isPersonal,
                                String nextOffset) throws IOException {
    AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery()
        .inlineQuery(inlineQuery)
        .results(results)
        .cacheTime(cacheTime)
        .isPersonal(isPersonal)
        .nextOffset(nextOffset);
    execute(answerInlineQuery);
  }
  
  public void answerInlineQuery(InlineQuery inlineQuery,
                                InlineQueryResult[] results,
                                int cacheTime,
                                boolean isPersonal) throws IOException {
    AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery()
        .inlineQuery(inlineQuery)
        .results(results)
        .cacheTime(cacheTime)
        .isPersonal(isPersonal);
    execute(answerInlineQuery);
  }
  
  public void answerInlineQuery(InlineQuery inlineQuery,
                                InlineQueryResult[] results,
                                boolean isPersonal) throws IOException {
    AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery()
        .inlineQuery(inlineQuery)
        .results(results)
        .isPersonal(isPersonal);
    execute(answerInlineQuery);
  }
  
  public void answerInlineQuery(InlineQuery inlineQuery, InlineQueryResult... results) throws IOException {
    AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery()
        .inlineQuery(inlineQuery)
        .results(results);
    execute(answerInlineQuery);
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
                                    int photoSize,
                                    int photoWidth,
                                    int photoHeight,
                                    boolean needName,
                                    boolean needPhoneNumber,
                                    boolean needEmail,
                                    boolean needShippingAddress,
                                    boolean isFlexible,
                                    InlineKeyboardMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    SendInvoice sendInvoice = new SendInvoice()
        .replyToMessage(replyToMessage)
        .title(title)
        .description(description)
        .payload(payload)
        .providerToken(providerToken)
        .startParameter(startParameter)
        .currency(currency)
        .prices(prices)
        .providerData(providerData)
        .photoUrl(photoUrl)
        .photoSize(photoSize)
        .photoWidth(photoWidth)
        .photoHeight(photoHeight)
        .needName(needName)
        .needPhoneNumber(needPhoneNumber)
        .needEmail(needEmail)
        .needShippingAddress(needShippingAddress)
        .isFlexible(isFlexible)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendInvoice);
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
    SendInvoice sendInvoice = new SendInvoice()
        .replyToMessage(replyToMessage)
        .title(title)
        .description(description)
        .payload(payload)
        .providerToken(providerToken)
        .startParameter(startParameter)
        .currency(currency)
        .prices(prices)
        .providerData(providerData)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendInvoice);
  }
  
  public InvoiceMessage sendInvoice(Message replyToMessage,
                                    String title,
                                    String description,
                                    String payload,
                                    String providerToken,
                                    String startParameter,
                                    Currency currency,
                                    LabeledPrice[] prices) throws IOException {
    SendInvoice sendInvoice = new SendInvoice()
        .replyToMessage(replyToMessage)
        .title(title)
        .description(description)
        .payload(payload)
        .providerToken(providerToken)
        .startParameter(startParameter)
        .currency(currency)
        .prices(prices);
    return execute(sendInvoice);
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
                                    int photoSize,
                                    int photoWidth,
                                    int photoHeight,
                                    boolean needName,
                                    boolean needPhoneNumber,
                                    boolean needEmail,
                                    boolean needShippingAddress,
                                    boolean isFlexible,
                                    InlineKeyboardMarkup replyMarkup,
                                    boolean disableNotification) throws IOException {
    SendInvoice sendInvoice = new SendInvoice()
        .chat(chat)
        .title(title)
        .description(description)
        .payload(payload)
        .providerToken(providerToken)
        .startParameter(startParameter)
        .currency(currency)
        .prices(prices)
        .providerData(providerData)
        .photoUrl(photoUrl)
        .photoSize(photoSize)
        .photoWidth(photoWidth)
        .photoHeight(photoHeight)
        .needName(needName)
        .needPhoneNumber(needPhoneNumber)
        .needEmail(needEmail)
        .needShippingAddress(needShippingAddress)
        .isFlexible(isFlexible)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendInvoice);
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
    SendInvoice sendInvoice = new SendInvoice()
        .chat(chat)
        .title(title)
        .description(description)
        .payload(payload)
        .providerToken(providerToken)
        .startParameter(startParameter)
        .currency(currency)
        .prices(prices)
        .providerData(providerData)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendInvoice);
  }
  
  public InvoiceMessage sendInvoice(Chat chat,
                                    String title,
                                    String description,
                                    String payload,
                                    String providerToken,
                                    String startParameter,
                                    Currency currency,
                                    LabeledPrice[] prices) throws IOException {
    SendInvoice sendInvoice = new SendInvoice()
        .chat(chat)
        .title(title)
        .description(description)
        .payload(payload)
        .providerToken(providerToken)
        .startParameter(startParameter)
        .currency(currency)
        .prices(prices);
    return execute(sendInvoice);
  }
  
  public void answerShippingQuery(ShippingQuery shippingQuery, ShippingOption... shippingOptions)
      throws IOException {
    AnswerShippingQuery answerShippingQuery = new AnswerShippingQuery()
        .shippingQuery(shippingQuery)
        .shippingOptions(shippingOptions);
    execute(answerShippingQuery);
  }
  
  public void answerShippingQuery(ShippingQuery shippingQuery, String errorMessage)
      throws IOException {
    AnswerShippingQuery answerShippingQuery = new AnswerShippingQuery()
        .shippingQuery(shippingQuery)
        .errorMessage(errorMessage);
    execute(answerShippingQuery);
  }
  
  public void answerPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery) throws IOException {
    AnswerPreCheckoutQuery answerPreCheckoutQuery = new AnswerPreCheckoutQuery()
        .preCheckoutQuery(preCheckoutQuery);
    execute(answerPreCheckoutQuery);
  }
  
  public void answerPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery, String errorMessage)
      throws IOException {
    AnswerPreCheckoutQuery answerPreCheckoutQuery = new AnswerPreCheckoutQuery()
        .preCheckoutQuery(preCheckoutQuery)
        .errorMessage(errorMessage);
    execute(answerPreCheckoutQuery);
  }
  
  public GameMessage sendGame(Message replyToMessage,
                              String gameShortName,
                              InlineKeyboardMarkup replyMarkup,
                              boolean disableNotification) throws IOException {
    SendGame sendGame = new SendGame()
        .replyToMessage(replyToMessage)
        .gameShortName(gameShortName)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendGame);
  }
  
  public GameMessage sendGame(Chat chat,
                              String gameShortName,
                              InlineKeyboardMarkup replyMarkup,
                              boolean disableNotification) throws IOException {
    SendGame sendGame = new SendGame()
        .chat(chat)
        .gameShortName(gameShortName)
        .replyMarkup(replyMarkup)
        .disableNotification(disableNotification);
    return execute(sendGame);
  }
  
  public GameMessage sendGame(Message replyToMessage, String gameShortName) throws IOException {
    SendGame sendGame = new SendGame()
        .replyToMessage(replyToMessage)
        .gameShortName(gameShortName);
    return execute(sendGame);
  }
  
  public GameMessage sendGame(Chat chat, String gameShortName) throws IOException {
    SendGame sendGame = new SendGame()
        .chat(chat)
        .gameShortName(gameShortName);
    return execute(sendGame);
  }
  
  public Serializable setGameScore(User user,
                                   int score,
                                   boolean force,
                                   boolean disableEditMessage,
                                   Message message) throws IOException {
    SetGameScore setGameScore = new SetGameScore()
        .user(user)
        .score(score)
        .force(force)
        .disableEdit(disableEditMessage)
        .message(message);
    return execute(setGameScore);
  }
  
  public Serializable setGameScore(User user,
                                   int score,
                                   boolean force,
                                   boolean disableEditMessage,
                                   String inlineMessageId) throws IOException {
    SetGameScore setGameScore = new SetGameScore()
        .user(user)
        .score(score)
        .force(force)
        .disableEdit(disableEditMessage)
        .inlineMessage(inlineMessageId);
    return execute(setGameScore);
  }
  
  public Serializable setGameScore(User user, int score, Message message) throws IOException {
    SetGameScore setGameScore = new SetGameScore()
        .user(user)
        .score(score)
        .message(message);
    return execute(setGameScore);
  }
  
  public Serializable setGameScore(User user, int score, String inlineMessageId) throws IOException {
    SetGameScore setGameScore = new SetGameScore()
        .user(user)
        .score(score)
        .inlineMessage(inlineMessageId);
    return execute(setGameScore);
  }
  
  public GameHighScore[] getGameHighScores(User user, Message message) throws IOException {
    GetGameHighScores getGameHighScores = new GetGameHighScores()
        .user(user)
        .message(message);
    return execute(getGameHighScores);
  }
  
  public GameHighScore[] getGameHighScores(User user, String inlineMessageId) throws IOException {
    GetGameHighScores getGameHighScores = new GetGameHighScores()
        .user(user)
        .inlineMessage(inlineMessageId);
    return execute(getGameHighScores);
  }
  
  public Message[] sendMediaGroup(Message replyToMessage, InputMedia[] media, boolean disableNotification)
      throws IOException {
    SendMediaGroup sendMediaGroup = new SendMediaGroup()
        .replyToMessage(replyToMessage)
        .media(media)
        .disableNotification(disableNotification);
    return execute(sendMediaGroup);
  }
  
  public Message[] sendMediaGroup(Message replyToMessage, InputMedia... media) throws IOException {
    SendMediaGroup sendMediaGroup = new SendMediaGroup()
        .replyToMessage(replyToMessage)
        .media(media);
    return execute(sendMediaGroup);
  }
  
  public Message[] sendMediaGroup(Chat chat, InputMedia[] media, boolean disableNotification)
      throws IOException {
    SendMediaGroup sendMediaGroup = new SendMediaGroup()
        .chat(chat)
        .media(media)
        .disableNotification(disableNotification);
    return execute(sendMediaGroup);
  }
  
  public Message[] sendMediaGroup(Chat chat, InputMedia... media) throws IOException {
    SendMediaGroup sendMediaGroup = new SendMediaGroup()
        .chat(chat)
        .media(media);
    return execute(sendMediaGroup);
  }
  
  /* end API methods */
  
  /**
   * Getter for property {@link #token}.
   *
   * @return value for property {@link #token}
   */
  public String getToken() {
    return token;
  }
  
}
