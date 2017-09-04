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
public class InvoiceMessage extends Message {

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
                        User forwardMessageSender,
                        Chat forwardMessageChat,
                        Long forwardMessageId,
                        Long forwardMessageDate,
                        Message replyToMessage,
                        Long editDate,
                        String authorSignature,
                        String forwardSignature,
                        Invoice invoice) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
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
