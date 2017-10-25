package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.util.text.TextEntity;

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

  public static final String BOLD = "bold";
  public static final String ITALIC = "italic";
  public static final String CODE = "code";
  public static final String CODE_BLOCK = "pre";
  public static final String LINK = "text_link";
  public static final String MENTION = "mention";
  public static final String TEXT_MENTION = "text_mention";
  public static final String HASHTAG = "hashtag";
  public static final String BOT_COMMAND = "bot_command";
  public static final String URL = "url";
  public static final String EMAIL = "email";

  /**
   * Type of the entity.
   * Can be mention (@username), hashtag, bot_command, url, email, bold (bold text),
   * italic (italic text), code (monowidth string), pre (monowidth block),
   * text_link (for clickable text URLs), text_mention (for users without usernames).
   */
  @SerializedName(TYPE_FIELD)
  private String type;

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
   * For "text_link" type only, url that will be opened after user taps on the text
   */
  @SerializedName(URL_FIELD)
  private String url;

  /**
   * For "text_mention" type only, the mentioned user
   */
  @SerializedName(USER_FIELD)
  private User user;


  public MessageEntity(TextEntity type, int offset, int length, String url, User user) {
    this.type = type.getName();
    this.offset = offset;
    this.length = length;
    this.url = url;
    this.user = user;
  }

  public MessageEntity(TextEntity type, int offset, int length, String url) {
    this.type = type.getName();
    this.offset = offset;
    this.length = length;
    this.url = url;
  }

  public MessageEntity(TextEntity type, int offset, int length, User user) {
    this.type = type.getName();
    this.offset = offset;
    this.length = length;
    this.user = user;
  }

  public MessageEntity(TextEntity type, int offset, int length) {
    this.type = type.getName();
    this.offset = offset;
    this.length = length;
  }


  /**
   * Getter for property {@link #type}.
   *
   * @return value for property {@link #type}
   */
  public TextEntity getType() {
    return TextEntity.valueOf(type);
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

}
