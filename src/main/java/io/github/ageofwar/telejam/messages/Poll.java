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
  static final String TOTAL_VOTER_COUNT_FIELD = "total_voter_count";
  static final String IS_ANONYMOUS_FIELD = "is_anonymous";
  static final String TYPE_FIELD = "type";
  static final String ALLOWS_MULTIPLE_ANSWERS_FIELD = "allows_multiple_answers";
  static final String CORRECT_OPTION_ID_FIELD = "correct_option_id";
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
  
  /**
   * Total number of users that voted in the poll.
   */
  @SerializedName(TOTAL_VOTER_COUNT_FIELD)
  private final int totalVoterCount;
  
  /**
   * True, if the poll is anonymous.
   */
  @SerializedName(IS_ANONYMOUS_FIELD)
  private final boolean isAnonymous;
  
  /**
   * Poll type, currently can be "regular" or "quiz".
   */
  @SerializedName(TYPE_FIELD)
  private final String type;
  
  /**
   * True, if the poll allows multiple answers.
   */
  @SerializedName(ALLOWS_MULTIPLE_ANSWERS_FIELD)
  private final boolean allowMultipleAnswers;
  
  /**
   * 0-based identifier of the correct answer option.
   * Available only for polls in the quiz mode, which are closed,
   * or was sent (not forwarded) by the bot or to the private chat with the bot.
   */
  @SerializedName(CORRECT_OPTION_ID_FIELD)
  private final int[] correctOptionIds;
  
  
  public Poll(String id,
              String question,
              Option[] options,
              boolean closed,
              int totalVoterCount,
              boolean isAnonymous,
              String type,
              boolean allowMultipleAnswers,
              int[] correctOptionIds) {
    this.id = id;
    this.question = question;
    this.options = options;
    this.closed = closed;
    this.totalVoterCount = totalVoterCount;
    this.isAnonymous = isAnonymous;
    this.type = type;
    this.allowMultipleAnswers = allowMultipleAnswers;
    this.correctOptionIds = correctOptionIds;
  }
  
  public Poll(String id,
              String question,
              Option[] options,
              boolean closed,
              int totalVoterCount,
              boolean isAnonymous,
              String type,
              boolean allowMultipleAnswers) {
    this.id = id;
    this.question = question;
    this.options = options;
    this.closed = closed;
    this.totalVoterCount = totalVoterCount;
    this.isAnonymous = isAnonymous;
    this.type = type;
    this.allowMultipleAnswers = allowMultipleAnswers;
    correctOptionIds = null;
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
  
  public int getTotalVoterCount() {
    return totalVoterCount;
  }
  
  public boolean isAnonymous() {
    return isAnonymous;
  }
  
  public String getType() {
    return type;
  }
  
  public boolean isAllowMultipleAnswers() {
    return allowMultipleAnswers;
  }
  
  public int[] getCorrectOptionIds() {
    return correctOptionIds;
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
