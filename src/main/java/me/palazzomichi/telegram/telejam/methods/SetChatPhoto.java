package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Use this method to set a new profile photo for the chat.
 * Photos can't be changed for private chats.
 * The bot must be an administrator in the chat for this to
 * work and must have the appropriate admin rights.
 * Returns True on success.
 *
 * In regular groups (non-supergroups), this method will only work if
 * the ‘All Members Are Admins’ setting is off in the target group.
 *
 * @author Michi Palazzo
 */
public class SetChatPhoto implements TelegramMethod<Boolean> {

  public static final String NAME = "setChatPhoto";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String PHOTO_FIELD = "photo";

  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

  /**
   * New chat photo.
   */
  @SerializedName(PHOTO_FIELD)
  private InputStream photo;
  
  /**
   * Name of {@link #photo}.
   */
  private String fileName;


  public SetChatPhoto chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public SetChatPhoto chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public SetChatPhoto chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public SetChatPhoto photo(String photo) throws FileNotFoundException {
    this.photo = new FileInputStream(photo);
    return this;
  }
  
  public SetChatPhoto photo(InputStream photo, String name) {
    this.photo = photo;
    this.fileName = name;
    return this;
  }
  
  public SetChatPhoto photo(File photo) throws FileNotFoundException {
    this.photo = new FileInputStream(photo);
    this.fileName = photo.getName();
    return this;
  }
  
  public SetChatPhoto photo(File photo, String name) throws FileNotFoundException {
    this.photo = new FileInputStream(photo);
    this.fileName = name;
    return this;
  }
  
  public SetChatPhoto photo(Path photo, String name) throws IOException {
    this.photo = Files.newInputStream(photo);
    this.fileName = name;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Boolean> getReturnType(JsonElement response) {
    return Boolean.class;
  }

  @Override
  public HttpEntity getHttpEntity() {
    MultipartEntityBuilder builder = MultipartEntityBuilder.create();

    if(chatId != null)
        builder.addTextBody(CHAT_ID_FIELD, chatId);

    if(photo != null)
        builder.addBinaryBody(PHOTO_FIELD, photo, ContentType.APPLICATION_OCTET_STREAM, fileName);

    return builder.build();
  }

}
