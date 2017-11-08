package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.payments.PreCheckoutQuery;
import me.palazzomichi.telegram.telejam.objects.updates.PreCheckoutQueryUpdate;
import me.palazzomichi.telegram.telejam.objects.updates.Update;

/**
 * Represents an operation that accepts a pre-checkout query and returns no
 * result. Used usually to handle updates received from a bot.
 *
 * @author Michi Palazzo
 */
public interface PreCheckoutQueryHandler extends UpdateHandler {
  
  /**
   * Performs this operation on the given pre-checkout query.
   *
   * @param preCheckoutQuery the pre-checkout query
   * @throws Throwable if a throwable is thrown
   */
  void accept(PreCheckoutQuery preCheckoutQuery) throws Throwable;
  
  @Override
  default void accept(Update update) throws Throwable {
    if (update instanceof PreCheckoutQueryUpdate) {
      PreCheckoutQuery preCheckoutQuery = ((PreCheckoutQueryUpdate) update).getPreCheckoutQuery();
      accept(preCheckoutQuery);
    }
  }
  
}
