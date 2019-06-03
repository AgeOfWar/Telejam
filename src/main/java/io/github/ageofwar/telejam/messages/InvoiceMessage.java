package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.payments.Invoice;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;

/**
 * This object represents an invoice message.
 *
 * @author Michi Palazzo
 */
public class InvoiceMessage extends Message {
  
  static final String INVOICE_FIELD = "invoice";
  
  /**
   * Information about the invoice.
   */
  @SerializedName(INVOICE_FIELD)
  private final Invoice invoice;
  
  
  public InvoiceMessage(long id,
                        User sender,
                        long date,
                        Chat chat,
                        Message replyToMessage,
                        Long editDate,
                        String authorSignature,
                        Invoice invoice,
                        InlineKeyboardMarkup replyMarkup) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature, replyMarkup);
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
