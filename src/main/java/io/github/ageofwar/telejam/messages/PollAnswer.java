package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;

/**
 * This object represents an answer of a user in a non-anonymous poll.
 */
public class PollAnswer {
  
  static final String POLL_ID_FIELD = "poll_id";
  static final String USER_FIELD = "user";
  static final String OPTION_IDS_FIELD = "option_ids";
  
  @SerializedName(POLL_ID_FIELD)
  private String pollId;
  
  @SerializedName(USER_FIELD)
  private User user;
  
  @SerializedName(OPTION_IDS_FIELD)
  private int[] optionIds;
  
  public PollAnswer(String pollId, User user, int[] optionIds) {
    this.pollId = pollId;
    this.user = user;
    this.optionIds = optionIds;
  }
  
  public String getPollId() {
    return pollId;
  }
  
  public User getUser() {
    return user;
  }
  
  public int[] getOptionIds() {
    return optionIds;
  }
  
}
