package me.palazzomichi.telegram.telejam.objects.payments;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;
import me.palazzomichi.telegram.telejam.objects.User;

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
  private String id;

  /**
   * User who sent the query.
   */
  @SerializedName(SENDER_FIELD)
  private User sender;

  /**
   * Bot specified invoice payload.
   */
  @SerializedName(INVOICE_PAYLOAD_FIELD)
  private String invoicePayload;

  /**
   * User specified shipping address.
   */
  @SerializedName(SHIPPING_ADDRESS_FIELD)
  private ShippingAddress shippingAddress;


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
