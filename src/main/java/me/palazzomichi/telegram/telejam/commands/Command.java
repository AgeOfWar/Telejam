package me.palazzomichi.telegram.telejam.commands;

import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Objects;

/**
 * Class representing a bot command.
 *
 * @see CommandRegistry
 */
public final class Command {
  
  private final String name;
  private final Text args;
  
  public Command(String name, Text args) {
    this.name = Objects.requireNonNull(name);
    this.args = args;
  }
  
  public Command(String name) {
    this(name, null);
  }
  
  /**
   * Returns the name of the command.
   *
   * @return the name of the command
   */
  public String getName() {
    return name;
  }
  
  /**
   * Returns the args of the command.
   *
   * @return the args of the command
   */
  public Text getArgs() {
    return args;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Command)) return false;
    Command command = (Command) obj;
    return name.equals(command.name) && args.equals(command.args);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(name, args);
  }
  
}
