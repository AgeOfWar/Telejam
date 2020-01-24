package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;
import java.util.Optional;

/**
 * This object represents one special entity in a text message.
 * For example, hashtags, usernames, URLs, etc.
 *
 * @author Michi Palazzo
 */
public class MessageEntity implements TelegramObject {
  
  private static final String TYPE_FIELD = "type";
  private static final String OFFSET_FIELD = "offset";
  private static final String LENGTH_FIELD = "length";
  private static final String URL_FIELD = "url";
  private static final String USER_FIELD = "user";
  private static final String LANGUAGE_FIELD = "language";
  
  /**
   * Type of the entity.
   */
  @SerializedName(TYPE_FIELD)
  private final Type type;
  
  /**
   * Offset in UTF-16 code units to the start of the entity.
   */
  @SerializedName(OFFSET_FIELD)
  private final int offset;
  
  /**
   * Length of the entity in UTF-16 code units.
   */
  @SerializedName(LENGTH_FIELD)
  private final int length;
  
  /**
   * For "text_link" type only, url that will be opened after user taps on the text.
   */
  @SerializedName(URL_FIELD)
  private final String url;
  
  /**
   * For "text_mention" type only, the mentioned user.
   */
  @SerializedName(USER_FIELD)
  private final User user;
  
  /**
   * For "pre" only, the programming language of the entity text.
   */
  @SerializedName(LANGUAGE_FIELD)
  private final String language;
  
  public MessageEntity(Type type, int offset, int length, String url, User user, String language) {
    this.type = type;
    this.offset = offset;
    this.length = length;
    this.url = url;
    this.user = user;
    this.language = language;
  }
  
  public MessageEntity(Type type, int offset, int length) {
    this.type = type;
    this.offset = offset;
    this.length = length;
    url = null;
    user = null;
    language = null;
  }
  
  public MessageEntity move(int offset, int length) {
    return new MessageEntity(type, offset, length, url, user, language);
  }
  
  /**
   * Getter for property {@link #type}.
   *
   * @return value for property {@link #type}
   */
  public Type getType() {
    return type;
  }
  
  /**
   * Getter for property {@link #offset}.
   *
   * @return value for property {@link #offset}
   */
  public int getOffset() {
    return offset;
  }
  
  /**
   * Getter for property {@link #length}.
   *
   * @return value for property {@link #length}
   */
  public int getLength() {
    return length;
  }
  
  /**
   * Getter for property {@link #url}.
   *
   * @return optional value for property {@link #url}
   */
  public Optional<String> getUrl() {
    return Optional.ofNullable(url);
  }
  
  /**
   * Getter for property {@link #user}.
   *
   * @return optional value for property {@link #user}
   */
  public Optional<User> getUser() {
    return Optional.ofNullable(user);
  }
  
  /**
   * Getter for property {@link #language}.
   *
   * @return optional value for property {@link #language}
   */
  public Optional<String> getLanguage() {
    return Optional.ofNullable(language);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof MessageEntity)) {
      return false;
    }
    
    MessageEntity entity = (MessageEntity) obj;
    return type == entity.type &&
        offset == entity.getOffset() &&
        length == entity.getLength() &&
        Objects.equals(url, entity.url) &&
        Objects.equals(user, entity.user) &&
        Objects.equals(language, entity.language);
  }
  
  @Override
  public int hashCode() {
    int result = 1;
    result = 37 * result + type.hashCode();
    result = 37 * result + offset;
    result = 37 * result + length;
    result = 37 * result + Objects.hashCode(url);
    result = 37 * result + Objects.hashCode(user);
    result = 37 * result + Objects.hashCode(language);
    return result;
  }
  
  public enum Type {
    @SerializedName("bold") BOLD,
    @SerializedName("italic") ITALIC,
    @SerializedName("code") CODE,
    @SerializedName("pre") CODE_BLOCK,
    @SerializedName("text_link") LINK,
    @SerializedName("mention") MENTION,
    @SerializedName("text_mention") TEXT_MENTION,
    @SerializedName("hashtag") HASHTAG,
    @SerializedName("bot_command") BOT_COMMAND,
    @SerializedName("url") URL,
    @SerializedName("email") EMAIL,
    @SerializedName("cashtag") CASHTAG,
    @SerializedName("phone_number") PHONE_NUMBER,
    @SerializedName("underline") UNDERLINE,
    @SerializedName("strikethrough") STRIKETHROUGH
  }
  
}
