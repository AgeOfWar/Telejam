package me.palazzomichi.telegram.telejam.util;

import me.palazzomichi.telegram.telejam.Bot;
import me.palazzomichi.telegram.telejam.objects.Message;
import me.palazzomichi.telegram.telejam.objects.TextMessage;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that handles commands.
 * Override the {@link #getCommand(TextMessage)} method to change the
 * syntax of commands or to perform checks before the command execution.
 */
public class CommandRegistry implements MessageHandler, CommandHandler {
  
  protected final Bot bot;
  private Map<String, CommandHandler> commands;
  
  /**
   * Creates a command registry.
   *
   * @param bot the bot that receives commands
   */
  public CommandRegistry(Bot bot) {
    this.bot = bot;
    commands = new HashMap<>();
  }
  
  @Override
  public final void onCommand(Command command, TextMessage message) throws Throwable {
    CommandHandler handler = getCommandHandler(command.getName());
    if (handler != null) {
      handler.onCommand(command, message);
    }
  }
  
  @Override
  public final void onMessage(Message message) throws Throwable {
    if (message instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) message;
      onCommand(getCommand(textMessage), textMessage);
    }
  }
  
  public Command getCommand(TextMessage message) {
    if (!message.isCommand()) {
      return null;
    }
    Text text = message.getText();
    String name = text.getBotCommands().get(0);
    Text args = text.subSequence(name.length() + 1).trim();
    return new Command(name, args);
  }
  
  /**
   * Returns the command with ne given name or alias, otherwise <code>null</code>.
   *
   * @param name the name of the command
   * @return the command with ne given name or alias, otherwise <code>null</code>
   */
  public final CommandHandler getCommandHandler(String name) {
    return commands.get(removeSuffix(name, "@" + bot.getUsername()).toLowerCase());
  }
  
  private String removeSuffix(String s, String suffix) {
    if (s.endsWith(suffix)) {
      return s.substring(0, s.length() - suffix.length());
    }
    return s;
  }
  
  /**
   * Registers a command.
   *
   * @param command the command to register
   * @param name    the name of the command
   */
  public final void registerCommand(CommandHandler command, String name) {
    commands.put(name.toLowerCase(), command);
  }
  
  /**
   * Registers a command.
   *
   * @param command the command to register
   * @param name    the name of the command
   * @param aliases the aliases of the command
   */
  public final void registerCommand(CommandHandler command, String name, String... aliases) {
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
  public final void unregisterCommand(String name) {
    commands.remove(name.toLowerCase());
  }
  
  /**
   * Unregisters a command.
   *
   * @param command the command to unregister
   */
  public final void unregisterCommand(CommandHandler command) {
    commands.values().remove(command);
  }
  
}
