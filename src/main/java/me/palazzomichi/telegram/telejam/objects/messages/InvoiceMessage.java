package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.payments.Invoice;

import java.util.Objects;

/**
 * This object represents an invoice message.
 *
 * @author Michi Palazzo
 */
public class InvoiceMessage extends Message implements Forwardable {

  static final String INVOICE_FIELD = "invoice";

  /**
   * Information about the invoice.
   */
  @SerializedName(INVOICE_FIELD)
  private Invoice invoice;


  public InvoiceMessage(long id,
                        User sender,
                        long date,
                        Chat chat,
                        Message replyToMessage,
                        Long editDate,
                        String authorSignature,
                        Invoice invoice) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.invoice = Objects.requireNonNull(invoice);
  }


  /**
   * Getter for property {@link #invoice}.
   *
   * @return value for property {@link #invoice}
   */
  public Invoice getInvoice() {
    return invoice;
  }

}
