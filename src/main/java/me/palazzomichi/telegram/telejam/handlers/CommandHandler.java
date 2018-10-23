package me.palazzomichi.telegram.telejam.handlers;

import me.palazzomichi.telegram.telejam.commands.Command;
import me.palazzomichi.telegram.telejam.objects.TextMessage;

/**
 * Interface that handles commands received from a bot.
 */
@FunctionalInterface
public interface CommandHandler {
  
  /**
   * Handles a command.
   *
   * @param command the command
   * @param message the command message
   * @throws Throwable if a throwable is thrown
   */
  void onCommand(Command command, TextMessage message) throws Throwable;
  
}
