package io.github.ageofwar.telejam.updates;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.payments.ShippingQuery;

import java.util.Objects;

/**
 * This object represents an incoming update.
 *
 * @author Michi Palazzo
 */
public class ShippingQueryUpdate extends Update {
  
  static final String SHIPPING_QUERY_FIELD = "shipping_query";
  
  /**
   * New incoming shipping query. Only for invoices with flexible price.
   */
  @SerializedName(SHIPPING_QUERY_FIELD)
  private final ShippingQuery shippingQuery;
  
  
  public ShippingQueryUpdate(long id, ShippingQuery shippingQuery) {
    super(id);
    this.shippingQuery = Objects.requireNonNull(shippingQuery);
  }
  
  
  /**
   * Getter for property {@link #shippingQuery}.
   *
   * @return value for property {@link #shippingQuery}
   */
  public ShippingQuery getShippingQuery() {
    return shippingQuery;
  }
  
}
