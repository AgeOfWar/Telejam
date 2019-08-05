package io.github.ageofwar.telejam.events;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.callbacks.CallbackDataHandler;
import io.github.ageofwar.telejam.commands.Command;
import io.github.ageofwar.telejam.commands.CommandHandler;
import io.github.ageofwar.telejam.messages.NewChatMemberHandler;
import io.github.ageofwar.telejam.updates.Update;
import io.github.ageofwar.telejam.updates.UpdateHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class that handles events.
 * The order of execution of registered events depends on the order of
 * registration of the event.
 */
public class EventRegistry implements UpdateHandler {
  
  private final Bot bot;
  private final List<UpdateHandler> updateHandlers;
  
  public EventRegistry(Bot bot) {
    this.bot = bot;
    updateHandlers = new ArrayList<>();
  }
  
  @Override
  public void onUpdate(Update update) throws Throwable {
    for (UpdateHandler handler : updateHandlers) {
      handler.onUpdate(update);
    }
  }
  
  /**
   * Registers an update handler.
   *
   * @param handler the handler
   * @return the registered handler
   */
  public <T extends UpdateHandler> T registerUpdateHandler(T handler) {
    updateHandlers.add(handler);
    return handler;
  }
  
  /**
   * Registers an array of update handlers.
   *
   * @param handlers handlers to register
   */
  public void registerUpdateHandlers(UpdateHandler... handlers) {
    Collections.addAll(updateHandlers, handlers);
  }
  
  /**
   * Removes an update handler.
   *
   * @param handler the handler
   */
  public void unregisterUpdateHandler(UpdateHandler handler) {
    updateHandlers.remove(handler);
  }
  
  /**
   * Registers a CommandHandler that filters command with the specified
   * name or aliases.
   *
   * @param handler the handler to register
   * @param name    the name of the commands to handle
   * @param aliases the aliases of the command to handle
   * @return the registered handler
   */
  public CommandHandler registerCommand(CommandHandler handler, String name, String... aliases) {
    CommandHandler commandHandler = (command, message) -> {
      String commandName = removeSuffix(command.getName(), "@" + bot.getUsername());
      if (commandName.equals(name) || Arrays.asList(aliases).contains(commandName)) {
        handler.onCommand(new Command(commandName, command.getArgs()), message);
      }
    };
    return registerUpdateHandler(commandHandler);
  }
  
  private String removeSuffix(String s, String suffix) {
    return s.endsWith(suffix) ? s.substring(0, s.length() - suffix.length()) : s;
  }
  
  /**
   * Registers a CallbackDataHandler that filters callbacks with the specified
   * name.
   *
   * @param handler the handler to register
   * @param name    the name of the commands to handle
   * @return the registered handler
   */
  public CallbackDataHandler registerCallbackDataHandler(CallbackDataHandler handler, String name) {
    return registerUpdateHandler(handler.withName(name));
  }
  
  /**
   * Registers a NewChatMemberHandler that handles only the bot.
   *
   * @param handler the handler to register
   * @return the registered handler
   */
  public NewChatMemberHandler registerBotChatJoinHandler(NewChatMemberHandler handler) {
    NewChatMemberHandler newChatMemberHandler = (chat, user, message) -> {
      if (user.getId() == bot.getId()) {
        handler.onNewChatMember(chat, user, message);
      }
    };
    return registerUpdateHandler(newChatMemberHandler);
  }
  
}
