package io.github.ageofwar.telejam.inline;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.Objects;

/**
 * This object represents one result of an inline query.
 *
 * @author Michi Palazzo
 */
public abstract class InlineQueryResult implements TelegramObject {
  
  static final String TYPE_FIELD = "type";
  static final String ID_FIELD = "id";
  
  /**
   * Unique identifier for this result, 1-64 Bytes.
   */
  @SerializedName(ID_FIELD)
  private final String id;
  
  
  /**
   * Constructs an InlineQueryResult.
   *
   * @param id unique identifier for this result
   */
  protected InlineQueryResult(String id) {
    this.id = Objects.requireNonNull(id);
  }
  
  
  /**
   * Getter for property {@link #id}.
   *
   * @return value for property {@link #id}
   */
  public String getId() {
    return id;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof InlineQueryResult)) {
      return false;
    }
    
    InlineQueryResult inlineQueryResult = (InlineQueryResult) obj;
    return id.equals(inlineQueryResult.getId());
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }
  
}
