package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

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
