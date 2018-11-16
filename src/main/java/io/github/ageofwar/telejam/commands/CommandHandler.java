package io.github.ageofwar.telejam.commands;

import io.github.ageofwar.telejam.messages.TextMessage;

import java.util.Arrays;

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
  
  /**
   * Returns a new CommandHandler that filters command with the specified
   * name or aliases.
   *
   * @param name    the name of the commands to handle
   * @param aliases the aliases of the command to handle
   * @return a new CommandHandler that filters command with the specified name or aliases
   */
  default CommandHandler withName(String name, String... aliases) {
    return (command, message) -> {
      if (command.getName().equals(name) || Arrays.asList(aliases).contains(name)) {
        onCommand(command, message);
      }
    };
  }
  
}
