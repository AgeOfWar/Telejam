package me.palazzomichi.telegram.telejam.util;

import me.palazzomichi.telegram.telejam.objects.TextMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that handles commands.
 */
public final class CommandRegistry implements CommandHandler {
  
  private final Map<String, CommandHandler> commands;
  
  /**
   * Creates a command registry.
   */
  public CommandRegistry() {
    commands = new HashMap<>();
  }
  
  @Override
  public void onCommand(Command command, TextMessage message) throws Throwable {
    CommandHandler handler = getCommandHandler(command.getName());
    if (handler != null) {
      handler.onCommand(command, message);
    }
  }
  
  /**
   * Returns the command with ne given name or alias, otherwise <code>null</code>.
   *
   * @param name the name of the command
   * @return the command with ne given name or alias, otherwise <code>null</code>
   */
  public CommandHandler getCommandHandler(String name) {
    return commands.get(name);
  }
  
  /**
   * Registers a command.
   *
   * @param command the command to register
   * @param name    the name of the command
   */
  public void registerCommand(CommandHandler command, String name) {
    commands.put(name.toLowerCase(), command);
  }
  
  /**
   * Registers a command.
   *
   * @param command the command to register
   * @param name    the name of the command
   * @param aliases the aliases of the command
   */
  public void registerCommand(CommandHandler command, String name, String... aliases) {
    registerCommand(command, name);
    for (String alias : aliases) {
      registerCommand(command, alias);
    }
  }
  
  /**
   * Unregisters a command with the given name or alias.
   *
   * @param name the command name or alias
   */
  public void unregisterCommand(String name) {
    commands.remove(name.toLowerCase());
  }
  
  /**
   * Unregisters a command.
   *
   * @param command the command to unregister
   */
  public void unregisterCommand(CommandHandler command) {
    commands.values().remove(command);
  }
  
}
