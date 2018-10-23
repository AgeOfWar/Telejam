package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.text.Text;
import me.palazzomichi.telegram.telejam.util.*;

/**
 * Abstract class that handles Telegram updates.
 */
public abstract class TelegramBot implements
    Runnable,
    UpdateHandler,
    MessageHandler,
    MessageEditHandler,
    ChannelPostHandler,
    ChannelPostEditHandler,
    InlineQueryHandler,
    CallbackQueryHandler,
    ShippingQueryHandler,
    TextMessageHandler,
    CommandHandler,
    NewChatMemberHandler,
    ChatJoinHandler {
  
  /**
   * Bot that receives updates.
   */
  protected final Bot bot;
  
  /**
   * Commands of the bot.
   */
  protected final CommandRegistry commands;
  
  public TelegramBot(Bot bot) {
    this.bot = bot;
    commands = new CommandRegistry();
  }
  
  @Override
  public void onUpdate(Update update) throws Throwable {
    if (update instanceof MessageUpdate) {
      Message message = ((MessageUpdate) update).getMessage();
      onMessage(message);
      if (message instanceof TextMessage) {
        TextMessage textMessage = (TextMessage) message;
        onTextMessage(textMessage);
        Command command = getCommand(textMessage);
        if (command != null) {
          onCommand(command, textMessage);
          commands.onCommand(command, textMessage);
        }
      } else if (message instanceof NewChatMembersMessage) {
        NewChatMembersMessage newChatMembersMessage = (NewChatMembersMessage) message;
        for (User user : newChatMembersMessage.getNewChatMembers()) {
          onNewChatMember(newChatMembersMessage.getChat(), user, newChatMembersMessage);
          if (user.getId() == bot.getId()) {
            onJoin(newChatMembersMessage.getChat(), newChatMembersMessage);
          }
        }
      }
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
    }
  }
  
  @Override
  public void onMessage(Message message) throws Throwable {
  }
  
  @Override
  public void onMessageEdit(Message message, long editDate) throws Throwable {
  }
  
  @Override
  public void onChannelPost(Message message) throws Throwable {
  }
  
  @Override
  public void onChannelPostEdit(Message message, long editDate) throws Throwable {
  }
  
  @Override
  public void onInlineQuery(InlineQuery inlineQuery) throws Throwable {
  }
  
  @Override
  public void onCallbackQuery(CallbackQuery callbackQuery) throws Throwable {
  }
  
  @Override
  public void onShippingQuery(ShippingQuery shippingQuery) throws Throwable {
  }
  
  @Override
  public void onPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery) throws Throwable {
  }
  
  @Override
  public void onTextMessage(TextMessage message) throws Throwable {
  }
  
  @Override
  public void onCommand(Command command, TextMessage message) throws Throwable {
  }
  
  @Override
  public void onNewChatMember(Chat chat, User user, NewChatMembersMessage message) throws Throwable {
  }
  
  @Override
  public void onJoin(Chat chat, NewChatMembersMessage message) throws Throwable {
  }
  
  /**
   * Handles an exception occurred while receiving or handling updates.
   *
   * @param t the exception to handle
   */
  public void onError(Throwable t) {
    t.printStackTrace();
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
  
}
