package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.Objects;

/**
 * This object contains information about a poll.
 *
 * @author Michi Palazzo
 */
public class Poll implements TelegramObject {
  
  static final String ID_FIELD = "id";
  static final String QUESTION_FIELD = "question";
  static final String OPTIONS_FIELD = "options";
  static final String IS_CLOSED_FIELD = "is_closed";
  
  /**
   * Unique poll identifier.
   */
  @SerializedName(ID_FIELD)
  private final String id;
  
  /**
   * Poll question, 1-255 characters.
   */
  @SerializedName(QUESTION_FIELD)
  private final String question;
  
  /**
   * List of poll options.
   */
  @SerializedName(OPTIONS_FIELD)
  private final Option[] options;
  
  /**
   * Whether or not the poll is closed.
   */
  @SerializedName(IS_CLOSED_FIELD)
  private final boolean closed;
  
  public Poll(String id, String question, Option[] options, boolean closed) {
    this.id = Objects.requireNonNull(id);
    this.question = Objects.requireNonNull(question);
    this.options = Objects.requireNonNull(options);
    this.closed = closed;
  }
  
  public String getId() {
    return id;
  }
  
  public String getQuestion() {
    return question;
  }
  
  public Option[] getOptions() {
    return options;
  }
  
  public boolean isClosed() {
    return closed;
  }
  
  public static class Option implements TelegramObject {
    
    static final String TEXT_FIELD = "text";
    static final String VOTER_COUNT_FIELD = "voter_count";
    
    /**
     * Option text, 1-100 characters.
     */
    @SerializedName(TEXT_FIELD)
    private final String text;
    
    /**
     * Number of users that voted for this option.
     */
    @SerializedName(VOTER_COUNT_FIELD)
    private final int voterCount;
    
    public Option(String text, int voterCount) {
      this.text = Objects.requireNonNull(text);
      this.voterCount = voterCount;
    }
    
    public String getText() {
      return text;
    }
    
    public int getVoterCount() {
      return voterCount;
    }
    
  }
  
}
