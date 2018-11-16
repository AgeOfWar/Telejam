package io.github.ageofwar.telejam.payments;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;

/**
 * This object contains information about an incoming shipping query.
 *
 * @author Michi Palazzo
 */
public class ShippingQuery implements TelegramObject {
  
  static final String ID_FIELD = "id";
  static final String SENDER_FIELD = "from";
  static final String INVOICE_PAYLOAD_FIELD = "invoice_payload";
  static final String SHIPPING_ADDRESS_FIELD = "shipping_address";
  
  /**
   * Unique query identifier.
   */
  @SerializedName(ID_FIELD)
  private final String id;
  
  /**
   * User who sent the query.
   */
  @SerializedName(SENDER_FIELD)
  private final User sender;
  
  /**
   * Bot specified invoice payload.
   */
  @SerializedName(INVOICE_PAYLOAD_FIELD)
  private final String invoicePayload;
  
  /**
   * User specified shipping address.
   */
  @SerializedName(SHIPPING_ADDRESS_FIELD)
  private final ShippingAddress shippingAddress;
  
  
  public ShippingQuery(String id, User sender, String invoicePayload, ShippingAddress shippingAddress) {
    this.id = Objects.requireNonNull(id);
    this.sender = Objects.requireNonNull(sender);
    this.invoicePayload = Objects.requireNonNull(invoicePayload);
    this.shippingAddress = Objects.requireNonNull(shippingAddress);
  }
  
  
  /**
   * Getter for property {@link #id}.
   *
   * @return value for property {@link #id}
   */
  public String getId() {
    return id;
  }
  
  /**
   * Getter for property {@link #sender}.
   *
   * @return value for property {@link #sender}
   */
  public User getSender() {
    return sender;
  }
  
  /**
   * Getter for property {@link #invoicePayload}.
   *
   * @return value for property {@link #invoicePayload}
   */
  public String getInvoicePayload() {
    return invoicePayload;
  }
  
  /**
   * Getter for property {@link #shippingAddress}.
   *
   * @return value for property {@link #shippingAddress}
   */
  public ShippingAddress getShippingAddress() {
    return shippingAddress;
  }
  
}
