package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object represents an incoming update.
 *
 * @author Michi Palazzo
 */
public class ChosenInlineResultUpdate extends Update {

  static final String CHOSEN_INLINE_RESULT_FIELD = "chosen_inline_result";

  /**
   * The result of an inline query that was chosen by a user and sent to their chat partner.
   */
  @SerializedName(CHOSEN_INLINE_RESULT_FIELD)
  private final ChosenInlineResult chosenInlineResult;


  public ChosenInlineResultUpdate(long id, ChosenInlineResult chosenInlineResult) {
    super(id);
    this.chosenInlineResult = Objects.requireNonNull(chosenInlineResult);
  }


  /**
   * Getter for property {@link #chosenInlineResult}.
   *
   * @return value for property {@link #chosenInlineResult}
   */
  public ChosenInlineResult getChosenInlineResult() {
    return chosenInlineResult;
  }

}
