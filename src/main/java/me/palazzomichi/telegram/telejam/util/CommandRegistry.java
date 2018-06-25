package me.palazzomichi.telegram.telejam.util;

import me.palazzomichi.telegram.telejam.Bot;
import me.palazzomichi.telegram.telejam.objects.Message;
import me.palazzomichi.telegram.telejam.objects.TextMessage;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that handles commands.
 */
public class CommandRegistry implements MessageHandler, CommandHandler {
  
  private final Bot bot;
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
  
  /**
   * Handles updates received from the bot.
   *
   * @param command the command name
   * @param args    the command args
   * @param message the command message
   * @throws Throwable if a throwable is thrown
   */
  @Override
  public final void onCommand(String command, String args[], TextMessage message) throws Throwable {
    CommandHandler handler = getCommand(command);
    if (handler != null) {
      handler.onCommand(command, args, message);
    }
  }
  
  @Override
  public void onMessage(Message message) throws Throwable {
    if (message instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) message;
      if (textMessage.isCommand()) {
        Text text = textMessage.getText();
        String command = text.getBotCommands().get(0);
        String[] args;
        if (text.length() == command.length() + 1) {
          args = new String[0];
        } else {
          args = text.toString().substring(command.length() + 1).trim().split("\\s+");
        }
        onCommand(command, args, textMessage);
      }
    }
  }
  
  /**
   * Returns the command with ne given name or alias, otherwise <code>null</code>.
   *
   * @param name the name of the command
   * @return the command with ne given name or alias, otherwise <code>null</code>
   */
  public final CommandHandler getCommand(String name) {
    return commands.get(removeSuffix(name, "@" + bot.getUsername()));
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
   * @param aliases the aliases of the command
   */
  public final void registerCommand(CommandHandler command, String name, String... aliases) {
    commands.put(name, command);
    for (String alias : aliases) {
      commands.put(alias, command);
    }
  }
  
  /**
   * Unregisters a command with the given name or alias.
   *
   * @param name the command name or alias
   */
  public final void unregisterCommand(String name) {
    commands.remove(name);
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
