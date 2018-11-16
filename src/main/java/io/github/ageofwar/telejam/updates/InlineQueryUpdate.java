package io.github.ageofwar.telejam.updates;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.inline.InlineQuery;

import java.util.Objects;

/**
 * This object represents an incoming update.
 *
 * @author Michi Palazzo
 */
public class InlineQueryUpdate extends Update {
  
  static final String INLINE_QUERY_FIELD = "inline_query";
  
  /**
   * New incoming inline query.
   */
  @SerializedName(INLINE_QUERY_FIELD)
  private final InlineQuery inlineQuery;
  
  
  public InlineQueryUpdate(long id, InlineQuery inlineQuery) {
    super(id);
    this.inlineQuery = Objects.requireNonNull(inlineQuery);
  }
  
  
  /**
   * Getter for property {@link #inlineQuery}.
   *
   * @return value for property {@link #inlineQuery}
   */
  public InlineQuery getInlineQuery() {
    return inlineQuery;
  }
  
}
