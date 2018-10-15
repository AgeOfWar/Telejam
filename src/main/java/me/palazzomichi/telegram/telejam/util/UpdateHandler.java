package me.palazzomichi.telegram.telejam.util;

import me.palazzomichi.telegram.telejam.objects.Update;

/**
 * Interface that handles updates received from a bot.
 */
@FunctionalInterface
public interface UpdateHandler {
  
  /**
   * Handles an incoming update.
   *
   * @param update new incoming update
   * @throws Throwable if a throwable is thrown
   */
  void onUpdate(Update update) throws Throwable;
  
}
