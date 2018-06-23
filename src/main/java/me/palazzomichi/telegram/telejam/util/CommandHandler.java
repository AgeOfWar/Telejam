package me.palazzomichi.telegram.telejam.util;

import me.palazzomichi.telegram.telejam.objects.TextMessage;

/**
 * Interface that handles commands received from a bot.
 */
public interface CommandHandler {
  
  /**
   * Handles a command.
   *
   * @param command the command name
   * @param args    the command args
   * @param message the command message
   * @throws Throwable if a throwable is thrown
   */
  void onCommand(String command, String[] args, TextMessage message) throws Throwable;
  
}
