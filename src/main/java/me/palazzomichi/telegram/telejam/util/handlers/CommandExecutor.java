package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.Bot;
import me.palazzomichi.telegram.telejam.objects.messages.TextMessage;

/**
 * Represents an operation that accepts a command and returns no
 * result. Used to handle updates received from a bot.
 *
 * @author Michi Palazzo
 */
public abstract class CommandExecutor implements CommandHandler {

  /**
   * The bot that will receive the command.
   */
  protected final Bot bot;

  /**
   * The name of the command.
   */
  private String name;

  /**
   * The aliases of the command.
   */
  private String[] aliases;


  /**
   * Constructs a CommandExecutor.
   *
   * @param bot     the bot that will receive the command
   * @param name    the name of the command
   * @param aliases the aliases of the command
   */
  public CommandExecutor(Bot bot, String name, String... aliases) {
    this.bot = bot;
    this.name = name;
    this.aliases = aliases;
  }
  
  /**
   * Constructs a CommandExecutor.
   *
   * @param bot     the bot that will receive the command
   * @param name    the name of the command
   */
  public CommandExecutor(Bot bot, String name) {
    this(bot, name, new String[0]);
  }
  
  
  /**
   * Executes a command.
   *
   * @param command the command name
   * @param args    the arguments of the command
   * @param message the command message
   */
  public abstract void execute(String command, String[] args, TextMessage message) throws Throwable;

  @Override
  public void accept(String command, String[] args, TextMessage message) throws Throwable {
    if (isThisCommand(command)) {
      execute(command, args, message);
    }
  }
  
  private boolean isThisCommand(String command) {
    if (command.equals(name) || command.equals(name + "@" + bot.getUsername())) {
      return true;
    }
    for (String alias : aliases) {
      if (command.equals(alias) || command.equals(alias + "@" + bot.getUsername())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Getter for property {@link #bot}.
   *
   * @return value for property {@link #bot}
   */
  public Bot getBot() {
    return bot;
  }

  /**
   * Getter for property {@link #name}.
   *
   * @return value for property {@link #name}
   */
  public String getCommandName() {
    return name;
  }

  /**
   * Setter for property {@link #name}.
   *
   * @param name value for property {@link #name}
   */
  public void setCommandName(String name) {
    this.name = name;
  }

  /**
   * Getter for property {@link #aliases}.
   *
   * @return value for property {@link #aliases}
   */
  public String[] getCommandAliases() {
    return aliases;
  }

  /**
   * Setter for property {@link #aliases}.
   *
   * @param aliases value for property {@link #aliases}
   */
  public void setCommandAliases(String... aliases) {
    this.aliases = aliases;
  }

}
