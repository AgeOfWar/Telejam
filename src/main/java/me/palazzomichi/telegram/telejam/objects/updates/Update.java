package me.palazzomichi.telegram.telejam.objects.updates;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;

/**
 * This object represents an incoming update.
 *
 * @author Michi Palazzo
 */
public abstract class Update implements TelegramObject {
  
  static final String ID_FIELD = "update_id";
  
  /**
   * The update‘s unique identifier.
   */
  @SerializedName(ID_FIELD)
  private long id;
  
  
  public Update(long id) {
    this.id = id;
  }
  
  
  /**
   * Getter for property {@link #id}.
   *
   * @return value for property {@link #id}
   */
  public long getId() {
    return id;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof Update)) {
      return false;
    }
    
    Update update = (Update) obj;
    return id == update.getId();
  }
  
  @Override
  public int hashCode() {
    return Long.hashCode(id);
  }
  
}
