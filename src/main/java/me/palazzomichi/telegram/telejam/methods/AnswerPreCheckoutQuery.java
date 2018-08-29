package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.PreCheckoutQuery;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Once the user has confirmed their payment and shipping details,
 * the Bot API sends the final confirmation in the form of an Update
 * with the field pre_checkout_query.
 * Use this method to respond to such pre-checkout queries.
 * On success, True is returned.
 * <b>Note:</b> The Bot API must receive an answer within 10
 * seconds after the pre-checkout query was sent.
 *
 * @author Michi Palazzo
 */
public class AnswerPreCheckoutQuery implements TelegramMethod<Boolean> {

  public static final String NAME = "answerPreCheckoutQuery";

  static final String PRE_CHECKOUT_QUERY_ID_FIELD = "pre_checkout_query_id";
  static final String SUCCESS_FIELD = "ok";
  static final String ERROR_MESSAGE_FIELD = "error_message";

  /**
   * Unique identifier for the query to be answered.
   */
  private String preCheckoutQueryId;

  /**
   * Specify <code>true</code> if everything is alright (goods are available, etc.) and
   * the bot is ready to proceed with the order. Use <code>false</code> if there are any problems.
   */
  private boolean success = true;

  /**
   * Required if ok is <code>false</code>.
   * Error message in human readable form that explains the reason for failure
   * to proceed with the checkout (e.g. "Sorry, somebody just bought the last
   * of our amazing black T-shirts while you were busy filling out your
   * payment details. Please choose a different color or garment!").
   * Telegram will display this message to the user.
   */
  private String errorMessage;


  public AnswerPreCheckoutQuery preCheckoutQuery(String preCheckoutQueryId) {
    this.preCheckoutQueryId = preCheckoutQueryId;
    return this;
  }

  public AnswerPreCheckoutQuery preCheckoutQuery(PreCheckoutQuery preCheckoutQuery) {
    this.preCheckoutQueryId = preCheckoutQuery.getId();
    return this;
  }

  public AnswerPreCheckoutQuery errorMessage(String errorMessage) {
    success = errorMessage == null;
    this.errorMessage = errorMessage;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(
        PRE_CHECKOUT_QUERY_ID_FIELD, preCheckoutQueryId,
        SUCCESS_FIELD, success,
        ERROR_MESSAGE_FIELD, errorMessage
    );
  }
  
  @Override
  public Class<? extends Boolean> getReturnType() {
    return Boolean.class;
  }

}
