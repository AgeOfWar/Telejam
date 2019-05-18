package io.github.ageofwar.telejam.commands;

import io.github.ageofwar.telejam.messages.TextMessage;
import io.github.ageofwar.telejam.messages.TextMessageHandler;
import io.github.ageofwar.telejam.text.Text;

/**
 * Interface that handles commands received from a bot.
 */
@FunctionalInterface
public interface CommandHandler extends TextMessageHandler {
  
  /**
   * Handles a command.
   *
   * @param command the command
   * @param message the command message
   * @throws Throwable if a throwable is thrown
   */
  void onCommand(Command command, TextMessage message) throws Throwable;
  
  @Override
  default void onTextMessage(TextMessage message) throws Throwable {
    if (!message.isCommand()) {
      return;
    }
    Text text = message.getText();
    String name = text.getBotCommands().get(0);
    Text args = text.subSequence(name.length() + 1).trim();
    onCommand(new Command(name, args), message);
  }
  
}
