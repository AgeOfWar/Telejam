package io.github.ageofwar.telejam.updates;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.callbacks.CallbackQuery;

import java.util.Objects;

/**
 * This object represents an incoming update.
 *
 * @author Michi Palazzo
 */
public class CallbackQueryUpdate extends Update {
  
  static final String CALLBACK_QUERY_FIELD = "callback_query";
  
  /**
   * New incoming callback query.
   */
  @SerializedName(CALLBACK_QUERY_FIELD)
  private final CallbackQuery callbackQuery;
  
  
  public CallbackQueryUpdate(long id, CallbackQuery callbackQuery) {
    super(id);
    this.callbackQuery = Objects.requireNonNull(callbackQuery);
  }
  
  
  /**
   * Getter for property {@link #callbackQuery}.
   *
   * @return value for property {@link #callbackQuery}
   */
  public CallbackQuery getCallbackQuery() {
    return callbackQuery;
  }
  
}
