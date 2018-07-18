package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.ShippingOption;
import me.palazzomichi.telegram.telejam.objects.ShippingQuery;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * If you sent an invoice requesting a shipping address and the parameter
 * is_flexible was specified, the Bot API will send an Update with a
 * shipping_query field to the bot. Use this method to reply to shipping queries.
 * On success, True is returned.
 *
 * @author Michi Palazzo
 */
public class AnswerShippingQuery implements TelegramMethod<Boolean> {

  public static final String NAME = "answerShippingQuery";

  static final String SHIPPING_QUERY_ID_FIELD = "shipping_query_id";
  static final String SUCCESS_FIELD = "ok";
  static final String SHIPPING_OPTIONS_FIELD = "shipping_options";
  static final String ERROR_MESSAGE_FIELD = "error_message";

  /**
   * Unique identifier for the query to be answered.
   */
  private String shippingQueryId;

  /**
   * Specify <code>true</code> if delivery to the specified address is
   * possible and <code>false</code> if there are any problems (for example,
   * if delivery to the specified address is not possible).
   */
  private Boolean success;

  /**
   * Required if ok is <code>true</code>.
   * Array of available shipping options.
   */
  private ShippingOption[] shippingOptions;

  /**
   * Required if ok is <code>false</code>.
   * Error message in human readable form that explains why it is impossible to
   * complete the order (e.g. "Sorry, delivery to your desired address is unavailable').
   * Telegram will display this message to the user.
   */
  private String errorMessage;


  public AnswerShippingQuery shippingQuery(String shippingQueryId) {
    this.shippingQueryId = shippingQueryId;
    return this;
  }

  public AnswerShippingQuery shippingQuery(ShippingQuery shippingQuery) {
    this.shippingQueryId = shippingQuery.getId();
    return this;
  }

  public AnswerShippingQuery shippingOptions(ShippingOption... shippingOptions) {
    success = true;
    this.shippingOptions = shippingOptions;
    return this;
  }

  public AnswerShippingQuery errorMessage(String errorMessage) {
    success = false;
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
        SHIPPING_QUERY_ID_FIELD, shippingQueryId,
        SUCCESS_FIELD, success,
        SHIPPING_OPTIONS_FIELD, shippingOptions,
        ERROR_MESSAGE_FIELD, errorMessage
    );
  }
  
  @Override
  public Class<? extends Boolean> getReturnType() {
    return Boolean.class;
  }

}
