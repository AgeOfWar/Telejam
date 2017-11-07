package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.CallbackQuery;
import me.palazzomichi.telegram.telejam.objects.updates.CallbackQueryUpdate;
import me.palazzomichi.telegram.telejam.objects.updates.Update;

/**
 * Represents an operation that accepts a callback query and returns no
 * result. Used usually to handle updates received from a bot.
 *
 * @author Michi Palazzo
 * @see me.palazzomichi.telegram.telejam.Bot
 */
public interface CallbackQueryHandler extends UpdateHandler {

  /**
   * Performs this operation on the given callback query.
   *
   * @param callbackQuery the callback query
   */
  void accept(CallbackQuery callbackQuery) throws Throwable;

  @Override
  default void accept(Update update) throws Throwable {
    if (update instanceof CallbackQueryUpdate) {
      CallbackQuery callbackQuery = ((CallbackQueryUpdate) update).getCallbackQuery();
      accept(callbackQuery);
    }
  }

}
