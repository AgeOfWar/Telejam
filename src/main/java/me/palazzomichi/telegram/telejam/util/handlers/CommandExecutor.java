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
  protected CommandExecutor(Bot bot, String name, String... aliases) {
    this.bot = bot;
    this.name = name;
    this.aliases = aliases;
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
  public void acceptCommand(String command, String[] args, TextMessage message) throws Throwable {
    if (isThisCommand(command)) {
      execute(command, args, message);
    }
  }
  
  private boolean isThisCommand(String command) {
    String botUsername = bot.getUsername();
    if (command.equalsIgnoreCase(name) || command.equalsIgnoreCase(name + "@" + botUsername)) {
      return true;
    }
    for (String alias : aliases) {
      if (command.equalsIgnoreCase(alias) || command.equalsIgnoreCase(alias + "@" + botUsername)) {
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
  public class CommandSyntaxException extends Exception {
    
    /**
     * The command.
     */
    private final TextMessage message;
    
    /**
     * The command name;
     */
    private final String commandName;
    
    /**
     * Valid command args.
     */
    private final String[] validArgs;
    
    
    /**
     * Constructs a CommandSyntaxException.
     *
     * @param message     the command message
     * @param commandName the command name
     * @param validArgs   valid command args
     */
    public CommandSyntaxException(TextMessage message, String commandName, String... validArgs) {
      super();
      this.message = message;
      this.commandName = commandName;
      this.validArgs = validArgs;
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
      return commandName + " " + String.join(" ", validArgs);
    }
    
    /**
     * Getter for property {@link #message}.
     *
     * @return value for property {@link #message}
     */
    public TextMessage getCommandMessage() {
      return message;
    }
    
    /**
     * Getter for property {@link #name}.
     *
     * @return value for property {@link #name}
     */
    public String getCommandName() {
      return commandName;
    }
    
    /**
     * Getter for property {@link #validArgs}.
     *
     * @return value for property {@link #validArgs}
     */
    public String[] getValidArgs() {
      return validArgs;
    }
    
  }
  
}
