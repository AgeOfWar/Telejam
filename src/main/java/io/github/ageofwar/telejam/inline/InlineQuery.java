package io.github.ageofwar.telejam.inline;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.messages.Location;
import io.github.ageofwar.telejam.TelegramObject;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;
import java.util.Optional;

/**
 * This object represents an incoming inline query.
 *
 * @author Michi Palazzo
 */
public class InlineQuery implements TelegramObject {
  
  static final String ID_FIELD = "id";
  static final String SENDER_FIELD = "from";
  static final String LOCATION_FIELD = "location";
  static final String QUERY_FIELD = "query";
  static final String OFFSET_FIELD = "offset";
  
  /**
   * Unique identifier for this query.
   */
  @SerializedName(ID_FIELD)
  private final String id;
  
  /**
   * Sender.
   */
  @SerializedName(SENDER_FIELD)
  private final User sender;
  
  /**
   * Sender location, only for bots that request user location.
   */
  @SerializedName(LOCATION_FIELD)
  private final Location location;
  
  /**
   * Text of the query (up to 512 characters).
   */
  @SerializedName(QUERY_FIELD)
  private final String query;
  
  /**
   * Offset of the results to be returned, can be controlled by the bot.
   */
  @SerializedName(OFFSET_FIELD)
  private final String offset;
  
  
  public InlineQuery(String id, User sender, Location location, String query, String offset) {
    this.id = Objects.requireNonNull(id);
    this.sender = Objects.requireNonNull(sender);
    this.location = location;
    this.query = Objects.requireNonNull(query);
    this.offset = Objects.requireNonNull(offset);
  }
  
  
  /**
   * Getter for property {@link #id}.
   *
   * @return value for property {@link #id}
   */
  public String getId() {
    return id;
  }
  
  /**
   * Getter for property {@link #sender}.
   *
   * @return value for property {@link #sender}
   */
  public User getSender() {
    return sender;
  }
  
  /**
   * Getter for property {@link #location}.
   *
   * @return value for property {@link #location}
   */
  public Optional<Location> getLocation() {
    return Optional.ofNullable(location);
  }
  
  /**
   * Getter for property {@link #query}.
   *
   * @return value for property {@link #query}
   */
  public String getQuery() {
    return query;
  }
  
  /**
   * Getter for property {@link #offset}.
   *
   * @return value for property {@link #offset}
   */
  public String getOffset() {
    return offset;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof InlineQuery)) {
      return false;
    }
    
    InlineQuery inlineQuery = (InlineQuery) obj;
    return id.equals(inlineQuery.getId());
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }
  
}
