package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

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

  /**
   * Type of the entity.
   * Can be mention (@username), hashtag, bot_command, url, email, bold (bold text),
   * italic (italic text), code (monowidth string), pre (monowidth block),
   * text_link (for clickable text URLs), text_mention (for users without usernames).
   */
  @SerializedName(TYPE_FIELD)
  private Type type;

  /**
   * Offset in UTF-16 code units to the start of the entity.
   */
  @SerializedName(OFFSET_FIELD)
  private int offset;

  /**
   * Length of the entity in UTF-16 code units.
   */
  @SerializedName(LENGTH_FIELD)
  private int length;

  /**
   * For "text_link" type only, url that will be opened after user taps on the text.
   */
  @SerializedName(URL_FIELD)
  private String url;

  /**
   * For "text_mention" type only, the mentioned user.
   */
  @SerializedName(USER_FIELD)
  private User user;
  

  public MessageEntity(String url, int offset, int length) {
    this.type = Type.LINK;
    this.offset = offset;
    this.length = length;
    this.url = url;
  }

  public MessageEntity(User user, int offset, int length) {
    this.type = Type.TEXT_MENTION;
    this.offset = offset;
    this.length = length;
    this.user = user;
  }

  public MessageEntity(Type type, int offset, int length) {
    this.type = type;
    this.offset = offset;
    this.length = length;
  }
  
  public MessageEntity move(int offset, int length) {
    if (url != null) {
      return new MessageEntity(url, offset, length);
    }
    if (user != null) {
      return new MessageEntity(user, offset, length);
    }
    return new MessageEntity(type, offset, length);
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
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof MessageEntity)) {
      return false;
    }
    
    MessageEntity entity = (MessageEntity) obj;
    return type.equals(entity.type) &&
        offset == entity.getOffset() &&
        length == entity.getLength() &&
        Objects.equals(url, entity.url) &&
        Objects.equals(user, entity.user);
  }
  
  @Override
  public int hashCode() {
    int result = 1;
    result = 37 * result + type.hashCode();
    result = 37 * result + offset;
    result = 37 * result + length;
    result = 37 * result + Objects.hashCode(url);
    result = 37 * result + Objects.hashCode(user);
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
    @SerializedName("phone_number") PHONE_NUMBER
  }

}
