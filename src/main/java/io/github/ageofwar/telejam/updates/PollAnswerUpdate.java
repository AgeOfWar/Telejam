package io.github.ageofwar.telejam.updates;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.messages.PollAnswer;

public class PollAnswerUpdate extends Update {
  
  static final String POLL_ANSWER_FIELD = "poll_answer";
  
  @SerializedName(POLL_ANSWER_FIELD)
  private PollAnswer pollAnswer;
  
  public PollAnswerUpdate(long id, PollAnswer pollAnswer) {
    super(id);
  }
  
  public PollAnswer getPollAnswer() {
    return pollAnswer;
  }
  
}
