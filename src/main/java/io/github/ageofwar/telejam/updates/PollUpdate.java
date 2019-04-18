package io.github.ageofwar.telejam.updates;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.messages.Poll;

import java.util.Objects;

/**
 * This object represents an incoming update.
 *
 * @author Michi Palazzo
 */
public class PollUpdate extends Update {
  
  static final String POLL_FIELD = "poll";
  
  /**
   * New poll state.
   */
  @SerializedName(POLL_FIELD)
  private final Poll poll;
  
  public PollUpdate(long id, Poll poll) {
    super(id);
    this.poll = Objects.requireNonNull(poll);
  }
  
  public Poll getPoll() {
    return poll;
  }
  
}
