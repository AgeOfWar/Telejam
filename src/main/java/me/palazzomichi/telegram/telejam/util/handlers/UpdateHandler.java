package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.updates.Update;

/**
 * Represents an operation that accepts an update and returns no
 * result. Used usually to handle updates received from a bot.
 *
 * @author Michi Palazzo
 */
public interface UpdateHandler {
  
  /**
   * Performs this operation on the given update.
   *
   * @param update the update
   * @throws Throwable if a throwable is thrown
   */
  void accept(Update update) throws Throwable;
  
}
