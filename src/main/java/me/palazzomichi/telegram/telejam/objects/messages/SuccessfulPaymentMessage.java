package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.payments.SuccessfulPayment;

import java.util.Objects;

/**
 * This object represents a successful payment message.
 *
 * @author Michi Palazzo
 */
public class SuccessfulPaymentMessage extends Message {

  static final String SUCCESSFUL_PAYMENT_FIELD = "successful_payment";

  /**
   * Information about the payment.
   */
  @SerializedName(SUCCESSFUL_PAYMENT_FIELD)
  private SuccessfulPayment successfulPayment;


  public SuccessfulPaymentMessage(long id,
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
                                  SuccessfulPayment successfulPayment) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.successfulPayment = Objects.requireNonNull(successfulPayment);
  }


  /**
   * Getter for property {@link #successfulPayment}.
   *
   * @return value for property {@link #successfulPayment}
   */
  public SuccessfulPayment getSuccessfulPayment() {
    return successfulPayment;
  }

}
