package me.palazzomichi.telegram.telejam.objects.updates;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.payments.PreCheckoutQuery;

import java.util.Objects;

/**
 * This object represents an incoming update.
 *
 * @author Michi Palazzo
 */
public class PreCheckoutQueryUpdate extends Update {

  static final String PRE_CHECKOUT_QUERY_FIELD = "pre_checkout_query";

  /**
   * New incoming pre-checkout query. Contains full information about checkout.
   */
  @SerializedName(PRE_CHECKOUT_QUERY_FIELD)
  private PreCheckoutQuery preCheckoutQuery;


  public PreCheckoutQueryUpdate(long id, PreCheckoutQuery preCheckoutQuery) {
    super(id);
    this.preCheckoutQuery = Objects.requireNonNull(preCheckoutQuery);
  }


  /**
   * Getter for property {@link #preCheckoutQuery}.
   *
   * @return value for property {@link #preCheckoutQuery}
   */
  public PreCheckoutQuery getPreCheckoutQuery() {
    return preCheckoutQuery;
  }

}
