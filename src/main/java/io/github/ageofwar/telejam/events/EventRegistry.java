package io.github.ageofwar.telejam.events;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.callbacks.CallbackDataHandler;
import io.github.ageofwar.telejam.callbacks.CallbackQuery;
import io.github.ageofwar.telejam.callbacks.CallbackQueryHandler;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.commands.Command;
import io.github.ageofwar.telejam.commands.CommandHandler;
import io.github.ageofwar.telejam.games.CallbackGameHandler;
import io.github.ageofwar.telejam.inline.ChosenInlineResult;
import io.github.ageofwar.telejam.inline.InlineQuery;
import io.github.ageofwar.telejam.inline.InlineQueryHandler;
import io.github.ageofwar.telejam.messages.*;
import io.github.ageofwar.telejam.payments.PreCheckoutQuery;
import io.github.ageofwar.telejam.payments.ShippingQuery;
import io.github.ageofwar.telejam.payments.ShippingQueryHandler;
import io.github.ageofwar.telejam.text.Text;
import io.github.ageofwar.telejam.updates.*;
import io.github.ageofwar.telejam.users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class that handles events.
 */
public class EventRegistry implements
    UpdateHandler,
    MessageHandler,
    MessageEditHandler,
    ChannelPostHandler,
    ChannelPostEditHandler,
    InlineQueryHandler,
    CallbackQueryHandler,
    CallbackDataHandler,
    CallbackGameHandler,
    ShippingQueryHandler,
    TextMessageHandler,
    CommandHandler,
    NewChatMemberHandler,
    ChatJoinHandler,
    PollHandler {
  
  private final Bot bot;
  private final List<UpdateHandler> updateHandlers;
  private final List<MessageHandler> messageHandlers;
  private final List<TextMessageHandler> textMessageHandlers;
  private final List<CommandHandler> commandHandlers;
  private final List<NewChatMemberHandler> newChatMemberHandlers;
  private final List<ChatJoinHandler> chatJoinHandlers;
  private final List<MessageEditHandler> messageEditHandlers;
  private final List<ChannelPostHandler> channelPostHandlers;
  private final List<ChannelPostEditHandler> channelPostEditHandlers;
  private final List<InlineQueryHandler> inlineQueryHandlers;
  private final List<CallbackQueryHandler> callbackQueryHandlers;
  private final List<CallbackDataHandler> callbackDataHandlers;
  private final List<CallbackGameHandler> callbackGameHandlers;
  private final List<ShippingQueryHandler> shippingQueryHandlers;
  private final List<PollHandler> pollHandlers;
  
  public EventRegistry(Bot bot) {
    this.bot = bot;
    updateHandlers = new ArrayList<>();
    messageHandlers = new ArrayList<>();
    textMessageHandlers = new ArrayList<>();
    commandHandlers = new ArrayList<>();
    newChatMemberHandlers = new ArrayList<>();
    chatJoinHandlers = new ArrayList<>();
    messageEditHandlers = new ArrayList<>();
    channelPostHandlers = new ArrayList<>();
    channelPostEditHandlers = new ArrayList<>();
    inlineQueryHandlers = new ArrayList<>();
    callbackQueryHandlers = new ArrayList<>();
    callbackDataHandlers = new ArrayList<>();
    callbackGameHandlers = new ArrayList<>();
    shippingQueryHandlers = new ArrayList<>();
    pollHandlers = new ArrayList<>();
  }
  
  @Override
  public void onUpdate(Update update) throws Throwable {
    for (UpdateHandler handler : updateHandlers) {
      handler.onUpdate(update);
    }
    
    if (update instanceof MessageUpdate) {
      onMessage(((MessageUpdate) update).getMessage());
    } else if (update instanceof EditedMessageUpdate) {
      Message message = ((EditedMessageUpdate) update).getMessage();
      onMessageEdit(message, message.getEditDate().getAsLong());
    } else if (update instanceof ChannelPostUpdate) {
      onChannelPost(((ChannelPostUpdate) update).getChannelPost());
    } else if (update instanceof EditedChannelPostUpdate) {
      Message message = ((EditedChannelPostUpdate) update).getEditedChannelPost();
      onChannelPostEdit(message, message.getEditDate().getAsLong());
    } else if (update instanceof InlineQueryUpdate) {
      onInlineQuery(((InlineQueryUpdate) update).getInlineQuery());
    } else if (update instanceof ChosenInlineResultUpdate) {
      onChosenInlineResult(((ChosenInlineResultUpdate) update).getChosenInlineResult());
    } else if (update instanceof CallbackQueryUpdate) {
      onCallbackQuery(((CallbackQueryUpdate) update).getCallbackQuery());
    } else if (update instanceof ShippingQueryUpdate) {
      onShippingQuery(((ShippingQueryUpdate) update).getShippingQuery());
    } else if (update instanceof PreCheckoutQueryUpdate) {
      onPreCheckoutQuery(((PreCheckoutQueryUpdate) update).getPreCheckoutQuery());
    } else if (update instanceof PollUpdate) {
      onPollUpdate(((PollUpdate) update).getPoll());
    }
  }
  
  @Override
  public void onMessage(Message message) throws Throwable {
    for (MessageHandler handler : messageHandlers) {
      handler.onMessage(message);
    }
    
    if (message instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) message;
      onTextMessage(textMessage);
    } else if (message instanceof NewChatMembersMessage) {
      NewChatMembersMessage newChatMembersMessage = (NewChatMembersMessage) message;
      for (User user : newChatMembersMessage.getNewChatMembers()) {
        onNewChatMember(newChatMembersMessage.getChat(), user, newChatMembersMessage);
      }
    }
  }
  
  @Override
  public void onMessageEdit(Message message, long editDate) throws Throwable {
    for (MessageEditHandler handler : messageEditHandlers) {
      handler.onMessageEdit(message, editDate);
    }
  }
  
  @Override
  public void onChannelPost(Message message) throws Throwable {
    for (ChannelPostHandler handler : channelPostHandlers) {
      handler.onChannelPost(message);
    }
  }
  
  @Override
  public void onChannelPostEdit(Message message, long editDate) throws Throwable {
    for (ChannelPostEditHandler handler : channelPostEditHandlers) {
      handler.onChannelPostEdit(message, editDate);
    }
  }
  
  @Override
  public void onInlineQuery(InlineQuery inlineQuery) throws Throwable {
    for (InlineQueryHandler handler : inlineQueryHandlers) {
      handler.onInlineQuery(inlineQuery);
    }
  }
  
  @Override
  public void onChosenInlineResult(ChosenInlineResult chosenInlineResult) throws Throwable {
    for (InlineQueryHandler handler : inlineQueryHandlers) {
      handler.onChosenInlineResult(chosenInlineResult);
    }
  }
  
  @Override
  public void onCallbackQuery(CallbackQuery callbackQuery) throws Throwable {
    for (CallbackQueryHandler handler : callbackQueryHandlers) {
      handler.onCallbackQuery(callbackQuery);
    }
    
    if (callbackQuery.getData().isPresent()) {
      String[] data = callbackQuery.getData().get().split("\\s+", 2);
      String name = data[0];
      String args = data.length > 1 ? data[1] : "";
      onCallbackData(callbackQuery, name, args);
    } else if (callbackQuery.getGameShortName().isPresent()) {
      String gameShortName = callbackQuery.getGameShortName().get();
      onCallbackGame(callbackQuery, gameShortName);
    }
  }
  
  @Override
  public void onCallbackData(CallbackQuery callbackQuery, String name, String args) throws Throwable {
    for (CallbackDataHandler handler : callbackDataHandlers) {
      handler.onCallbackData(callbackQuery, name, args);
    }
  }
  
  @Override
  public void onCallbackGame(CallbackQuery callbackQuery, String gameShortName) throws Throwable {
    for (CallbackGameHandler handler : callbackGameHandlers) {
      handler.onCallbackGame(callbackQuery, gameShortName);
    }
  }
  
  @Override
  public void onShippingQuery(ShippingQuery shippingQuery) throws Throwable {
    for (ShippingQueryHandler handler : shippingQueryHandlers) {
      handler.onShippingQuery(shippingQuery);
    }
  }
  
  @Override
  public void onPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery) throws Throwable {
    for (ShippingQueryHandler handler : shippingQueryHandlers) {
      handler.onPreCheckoutQuery(preCheckoutQuery);
    }
  }
  
  @Override
  public void onTextMessage(TextMessage message) throws Throwable {
    for (TextMessageHandler handler : textMessageHandlers) {
      handler.onTextMessage(message);
    }
    
    Command command = getCommand(message);
    if (command != null) {
      onCommand(command, message);
    }
  }
  
  /**
   * Returns the {@link Command} contained in the specified message,
   * or <code>null</code> if the message is not a command.
   * Override this method if you want to change the syntax of commands.
   *
   * @param message the message
   * @return the command contained in the specified message,
   * or <code>null</code> if the message is not a command.
   */
  protected Command getCommand(TextMessage message) {
    if (!message.isCommand()) {
      return null;
    }
    Text text = message.getText();
    String name = text.getBotCommands().get(0);
    Text args = text.subSequence(name.length() + 1).trim();
    return new Command(removeSuffix(name, "@" + bot.getUsername()), args);
  }
  
  private String removeSuffix(String s, String suffix) {
    if (s.endsWith(suffix)) {
      return s.substring(0, s.length() - suffix.length());
    }
    return s;
  }
  
  @Override
  public void onCommand(Command command, TextMessage message) throws Throwable {
    for (CommandHandler handler : commandHandlers) {
      handler.onCommand(command, message);
    }
  }
  
  @Override
  public void onNewChatMember(Chat chat, User user, NewChatMembersMessage message) throws Throwable {
    for (NewChatMemberHandler handler : newChatMemberHandlers) {
      handler.onNewChatMember(chat, user, message);
    }
    
    if (user.getId() == bot.getId()) {
      onJoin(chat, message);
    }
  }
  
  @Override
  public void onJoin(Chat chat, NewChatMembersMessage message) throws Throwable {
    for (ChatJoinHandler handler : chatJoinHandlers) {
      handler.onJoin(chat, message);
    }
  }
  
  @Override
  public void onPollUpdate(Poll poll) throws Throwable {
    for (PollHandler handler : pollHandlers) {
      handler.onPollUpdate(poll);
    }
  }
  
  /**
   * Registers an update handler.
   *
   * @param handler the handler
   */
  public void registerUpdateHandler(UpdateHandler handler) {
    updateHandlers.add(handler);
  }
  
  /**
   * Registers update handlers.
   *
   * @param handlers the handlers
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
   * Registers a message handler.
   *
   * @param handler the handler
   */
  public void registerMessageHandler(MessageHandler handler) {
    messageHandlers.add(handler);
  }
  
  /**
   * Registers message handlers.
   *
   * @param handlers the handlers
   */
  public void registerMessageHandlers(MessageHandler... handlers) {
    Collections.addAll(messageHandlers, handlers);
  }
  
  /**
   * Removes a message handler.
   *
   * @param handler the handler
   */
  public void unregisterMessageHandler(MessageHandler handler) {
    messageHandlers.remove(handler);
  }
  
  /**
   * Registers a text message handler.
   *
   * @param handler the handler
   */
  public void registerTextMessageHandler(TextMessageHandler handler) {
    textMessageHandlers.add(handler);
  }
  
  /**
   * Registers text message handlers.
   *
   * @param handlers the handlers
   */
  public void registerTextMessageHandlers(TextMessageHandler... handlers) {
    Collections.addAll(textMessageHandlers, handlers);
  }
  
  /**
   * Removes a text message handler.
   *
   * @param handler the handler
   */
  public void unregisterTextMessageHandler(TextMessageHandler handler) {
    textMessageHandlers.remove(handler);
  }
  
  /**
   * Registers a command handler.
   *
   * @param handler the handler
   */
  public void registerCommandHandler(CommandHandler handler) {
    commandHandlers.add(handler);
  }
  
  /**
   * Registers command handlers.
   *
   * @param handlers the handlers
   */
  public void registerCommandHandlers(CommandHandler... handlers) {
    Collections.addAll(commandHandlers, handlers);
  }
  
  /**
   * Removes a command handler.
   *
   * @param handler the handler
   */
  public void unregisterCommandHandler(CommandHandler handler) {
    commandHandlers.remove(handler);
  }
  
  /**
   * Registers a new chat member handler.
   *
   * @param handler the handler
   */
  public void registerNewChatMemberHandler(NewChatMemberHandler handler) {
    newChatMemberHandlers.add(handler);
  }
  
  /**
   * Registers new chat member handlers.
   *
   * @param handlers the handlers
   */
  public void registerNewChatMemberHandlers(NewChatMemberHandler... handlers) {
    Collections.addAll(newChatMemberHandlers, handlers);
  }
  
  /**
   * Removes a new chat member handler.
   *
   * @param handler the handler
   */
  public void unregisterNewChatMemberHandler(NewChatMemberHandler handler) {
    newChatMemberHandlers.remove(handler);
  }
  
  /**
   * Registers a chat join handler.
   *
   * @param handler the handler
   */
  public void registerChatJoinHandler(ChatJoinHandler handler) {
    chatJoinHandlers.add(handler);
  }
  
  /**
   * Registers chat join handlers.
   *
   * @param handlers the handlers
   */
  public void registerChatJoinHandlers(ChatJoinHandler... handlers) {
    Collections.addAll(chatJoinHandlers, handlers);
  }
  
  /**
   * Removes a chat join handler.
   *
   * @param handler the handler
   */
  public void unregisterChatJoinHandler(ChatJoinHandler handler) {
    chatJoinHandlers.remove(handler);
  }
  
  /**
   * Registers a message edit handler.
   *
   * @param handler the handler
   */
  public void registerMessageEditHandler(MessageEditHandler handler) {
    messageEditHandlers.add(handler);
  }
  
  /**
   * Registers message edit handlers.
   *
   * @param handlers the handlers
   */
  public void registerMessageEditHandlers(MessageEditHandler... handlers) {
    Collections.addAll(messageEditHandlers, handlers);
  }
  
  /**
   * Removes a message edit handler.
   *
   * @param handler the handler
   */
  public void unregisterMessageEditHandler(MessageEditHandler handler) {
    messageEditHandlers.remove(handler);
  }
  
  /**
   * Registers a channel post handler.
   *
   * @param handler the handler
   */
  public void registerChannelPostHandler(ChannelPostHandler handler) {
    channelPostHandlers.add(handler);
  }
  
  /**
   * Registers channel post handlers.
   *
   * @param handlers the handlers
   */
  public void registerChannelPostHandlers(ChannelPostHandler... handlers) {
    Collections.addAll(channelPostHandlers, handlers);
  }
  
  /**
   * Removes a channel post handler.
   *
   * @param handler the handler
   */
  public void unregisterChannelPostHandler(ChannelPostHandler handler) {
    channelPostHandlers.remove(handler);
  }
  
  /**
   * Registers a channel post edit handler.
   *
   * @param handler the handler
   */
  public void registerChannelPostEditHandler(ChannelPostEditHandler handler) {
    channelPostEditHandlers.add(handler);
  }
  
  /**
   * Registers channel post edit handlers.
   *
   * @param handlers the handlers
   */
  public void registerChannelPostEditHandlers(ChannelPostEditHandler... handlers) {
    Collections.addAll(channelPostEditHandlers, handlers);
  }
  
  /**
   * Removes a channel post edit handler.
   *
   * @param handler the handler
   */
  public void unregisterChannelPostEditHandler(ChannelPostEditHandler handler) {
    channelPostEditHandlers.remove(handler);
  }
  
  /**
   * Registers a inline query handler.
   *
   * @param handler the handler
   */
  public void registerInlineQueryHandler(InlineQueryHandler handler) {
    inlineQueryHandlers.add(handler);
  }
  
  /**
   * Registers inline query handlers.
   *
   * @param handlers the handlers
   */
  public void registerInlineQueryHandlers(InlineQueryHandler... handlers) {
    Collections.addAll(inlineQueryHandlers, handlers);
  }
  
  /**
   * Removes a inline query handler.
   *
   * @param handler the handler
   */
  public void unregisterInlineQueryHandler(InlineQueryHandler handler) {
    inlineQueryHandlers.remove(handler);
  }
  
  /**
   * Registers a callback query handler.
   *
   * @param handler the handler
   */
  public void registerCallbackQueryHandler(CallbackQueryHandler handler) {
    callbackQueryHandlers.add(handler);
  }
  
  /**
   * Registers callback query handlers.
   *
   * @param handlers the handlers
   */
  public void registerCallbackQueryHandlers(CallbackQueryHandler... handlers) {
    Collections.addAll(callbackQueryHandlers, handlers);
  }
  
  /**
   * Removes a callback query handler.
   *
   * @param handler the handler
   */
  public void unregisterCallbackQueryHandler(CallbackQueryHandler handler) {
    callbackQueryHandlers.remove(handler);
  }
  
  /**
   * Registers a callback data handler.
   *
   * @param handler the handler
   */
  public void registerCallbackDataHandler(CallbackDataHandler handler) {
    callbackDataHandlers.add(handler);
  }
  
  /**
   * Registers callback data handlers.
   *
   * @param handlers the handlers
   */
  public void registerCallbackDataHandlers(CallbackDataHandler... handlers) {
    Collections.addAll(callbackDataHandlers, handlers);
  }
  
  /**
   * Removes a callback data handler.
   *
   * @param handler the handler
   */
  public void unregisterCallbackDataHandler(CallbackDataHandler handler) {
    callbackDataHandlers.remove(handler);
  }
  
  /**
   * Registers a callback game handler.
   *
   * @param handler the handler
   */
  public void registerCallbackGameHandler(CallbackGameHandler handler) {
    callbackGameHandlers.add(handler);
  }
  
  /**
   * Registers callback game handlers.
   *
   * @param handlers the handlers
   */
  public void registerCallbackGameHandlers(CallbackGameHandler... handlers) {
    Collections.addAll(callbackGameHandlers, handlers);
  }
  
  /**
   * Removes a callback game handler.
   *
   * @param handler the handler
   */
  public void unregisterCallbackGameHandler(CallbackGameHandler handler) {
    callbackGameHandlers.remove(handler);
  }
  
  /**
   * Registers a shipping query handler.
   *
   * @param handler the handler
   */
  public void registerShippingQueryHandler(ShippingQueryHandler handler) {
    shippingQueryHandlers.add(handler);
  }
  
  /**
   * Registers shipping query handlers.
   *
   * @param handlers the handlers
   */
  public void registerShippingQueryHandlers(ShippingQueryHandler... handlers) {
    Collections.addAll(shippingQueryHandlers, handlers);
  }
  
  /**
   * Removes a shipping query handler.
   *
   * @param handler the handler
   */
  public void unregisterShippingQueryHandler(ShippingQueryHandler handler) {
    shippingQueryHandlers.remove(handler);
  }
  
  /**
   * Registers a poll handler.
   *
   * @param handler the handler
   */
  public void registerPollHandler(PollHandler handler) {
    pollHandlers.add(handler);
  }
  
  /**
   * Registers poll handlers.
   *
   * @param handler the handler
   */
  public void registerPollHandlers(PollHandler... handler) {
    Collections.addAll(pollHandlers, handler);
  }
  
  /**
   * Removes a poll handler.
   *
   * @param handler the handler
   */
  public void unregisterPollHandler(PollHandler handler) {
    pollHandlers.remove(handler);
  }
  
}
