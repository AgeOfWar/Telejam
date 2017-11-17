package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.Bot;
import me.palazzomichi.telegram.telejam.objects.messages.TextMessage;

import java.util.Arrays;

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
   * Sub-commands of this command.
   * For example, in the command <code>/foo bar 1234</code>,
   * <code>bar 1234</code>bar is sub-command of foo.
   */
  private final CommandExecutor[] subCommands;
  
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
   * @param bot         the bot that will receive the command
   * @param name        the name of the command
   * @param aliases     the aliases of the command
   * @param subCommands sub-commands of this command
   */
  protected CommandExecutor(Bot bot, String name, String[] aliases, CommandExecutor... subCommands) {
    this.bot = bot;
    this.name = name;
    this.aliases = aliases;
    this.subCommands = subCommands;
  }
  
  /**
   * Constructs a CommandExecutor.
   *
   * @param bot     the bot that will receive the command
   * @param name    the name of the command
   * @param aliases the aliases of the command
   */
  public CommandExecutor(Bot bot, String name, String... aliases) {
    this(bot, name, aliases, new CommandExecutor[0]);
  }
  
  
  /**
   * Executes a command.
   *
   * @param command the command name
   * @param args    the arguments of the command
   * @param message the command message
   * @throws CommandSyntaxException if the command syntax is wrong
   * @throws Throwable              if a throwable is thrown
   */
  public abstract void execute(String command, String[] args, TextMessage message) throws Throwable;
  
  @Override
  public void accept(String command, String[] args, TextMessage message) throws Throwable {
    if (isThisCommand(command)) {
      boolean executed = false;
      if (args.length > 0) {
        for (CommandExecutor subCommand : subCommands) {
          if (executed = subCommand.isThisCommand(args[0])) {
            try {
              subCommand.execute(args[0], Arrays.copyOfRange(args, 1, args.length), message);
              break;
            } catch (CommandSyntaxException syntaxException) {
              throw new CommandSyntaxException(command, syntaxException);
            }
          }
        }
      }
      if (!executed) {
        execute(command, args, message);
      }
    }
  }
  
  private boolean isThisCommand(String command) {
    if (command.equalsIgnoreCase(name) || command.equalsIgnoreCase(name + "@" + bot.getUsername())) {
      return true;
    }
    for (String alias : aliases) {
      if (command.equalsIgnoreCase(alias) || command.equalsIgnoreCase(alias + "@" + bot.getUsername())) {
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
  
  
  /**
   * Exception thrown when the syntax of a command received from a bot is incorrect.
   *
   * @author Michi Palazzo
   */
  public static class CommandSyntaxException extends Exception {
    
    /**
     * The bot that received the command.
     */
    private final Bot bot;
    
    /**
     * The command.
     */
    private final TextMessage message;
    
    /**
     * The command name.
     */
    private final String command;
    
    /**
     * Valid command args.
     */
    private final String[] args;
    
    
    /**
     * Constructs a CommandSyntaxException.
     *
     * @param bot     the bot that received the command
     * @param message the command
     * @param command the command name
     * @param args    valid command args
     */
    public CommandSyntaxException(Bot bot, TextMessage message, String command, String... args) {
      super();
      this.bot = bot;
      this.message = message;
      this.command = command;
      this.args = args;
    }
    
    /**
     * Constructs a CommandSyntaxException.
     *
     * @param command the command name
     * @param cause   the cause
     */
    public CommandSyntaxException(String command, CommandSyntaxException cause) {
      super(cause);
      this.bot = cause.getBot();
      this.message = cause.getCommand();
      this.command = command;
      
      String[] a = cause.getArgs();
      args = new String[a.length + 1];
      args[0] = cause.getCommandName();
      System.arraycopy(a, 0, args, 1, a.length);
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
     * Returns the correct syntax of the command.
     *
     * @return the correct syntax of the command
     */
    public String getSyntax() {
      final String delimiter = " ";
      return command + delimiter + String.join(delimiter, args);
    }
    
    /**
     * Getter for property {@link #message}.
     *
     * @return value for property {@link #message}
     */
    public TextMessage getCommand() {
      return message;
    }
    
    /**
     * Getter for property {@link #command}.
     *
     * @return value for property {@link #command}
     */
    public String getCommandName() {
      return command;
    }
    
    /**
     * Getter for property {@link #args}.
     *
     * @return value for property {@link #args}
     */
    public String[] getArgs() {
      return args;
    }
    
  }
  
}
