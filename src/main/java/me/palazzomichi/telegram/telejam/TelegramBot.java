package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.text.Text;

/**
 * Abstract class that handles Telegram updates.
 */
public abstract class TelegramBot {
  
  /**
   * Bot that receives updates.
   */
  protected final Bot bot;
  
  public TelegramBot(Bot bot) {
    this.bot = bot;
  }
  
  /**
   * Handles an incoming update from the bot.
   *
   * @param update new incoming update
   * @throws Throwable if a throwable is thrown
   */
  public void onUpdate(Update update) throws Throwable {
    if (update instanceof MessageUpdate) {
      Message message = ((MessageUpdate) update).getMessage();
      onMessage(message);
      if (message instanceof TextMessage) {
        TextMessage textMessage = (TextMessage) message;
        onTextMessage(textMessage);
        if (textMessage.isCommand()) {
          Text text = textMessage.getText();
          String command = text.getBotCommands().get(0);
          String[] args = text.toString().substring(command.length() + 1).trim().split("\\s+");
          onCommand(command, args, textMessage);
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
      CallbackQuery callbackQuery = ((CallbackQueryUpdate) update).getCallbackQuery();
      onCallbackQuery(callbackQuery);
      if (callbackQuery.getData().isPresent()) {
        String data = callbackQuery.getData().get();
        CallbackInlineKeyboardButton button = CallbackInlineKeyboardButton.fromData(data);
        if (button != null) {
          button.onClick(callbackQuery);
        }
      }
    } else if (update instanceof ShippingQueryUpdate) {
      onShippingQuery(((ShippingQueryUpdate) update).getShippingQuery());
    } else if (update instanceof PreCheckoutQueryUpdate) {
      onPreCheckoutQuery(((PreCheckoutQueryUpdate) update).getPreCheckoutQuery());
    }
  }
  
  /**
   * Handles an incoming message from the bot.
   *
   * @param message new incoming message
   * @throws Throwable if a throwable is thrown
   */
  public void onMessage(Message message) throws Throwable {
  }
  
  
  /**
   * Handles an incoming text message from the bot.
   *
   * @param message new incoming text message
   * @throws Throwable if a throwable is thrown
   */
  public void onTextMessage(TextMessage message) throws Throwable {
  }
  
  /**
   * Handles an incoming command from the bot.
   *
   * @param command the command name
   * @param args    the command args
   * @param message the command message
   * @throws Throwable if a throwable is thrown
   */
  public void onCommand(String command, String[] args, TextMessage message) throws Throwable {
  }
  
  /**
   * Handles an incoming message edit from the bot.
   *
   * @param message  new incoming edit
   * @param editDate the date of the edit
   * @throws Throwable if a throwable is thrown
   */
  public void onMessageEdit(Message message, long editDate) throws Throwable {
  }
  
  /**
   * Handles an incoming channel post from the bot.
   *
   * @param message new incoming channel post
   * @throws Throwable if a throwable is thrown
   */
  public void onChannelPost(Message message) throws Throwable {
  }
  
  /**
   * Handles an incoming channel post edit from the bot.
   *
   * @param message  new incoming edit
   * @param editDate the edit date
   * @throws Throwable if a throwable is thrown
   */
  public void onChannelPostEdit(Message message, long editDate) throws Throwable {
  }
  
  /**
   * Handles an incoming inline query from the bot.
   *
   * @param inlineQuery new incoming message
   * @throws Throwable if a throwable is thrown
   */
  public void onInlineQuery(InlineQuery inlineQuery) throws Throwable {
  }
  
  /**
   * Handles an incoming chosen inline result from the bot.
   *
   * @param chosenInlineResult new incoming chosen inline result
   * @throws Throwable if a throwable is thrown
   */
  public void onChosenInlineResult(ChosenInlineResult chosenInlineResult) throws Throwable {
  }
  
  /**
   * Handles an incoming callback query from the bot.
   *
   * @param callbackQuery new incoming callback query
   * @throws Throwable if a throwable is thrown
   */
  public void onCallbackQuery(CallbackQuery callbackQuery) throws Throwable {
  }
  
  /**
   * Handles an incoming shipping query from the bot.
   *
   * @param shippingQuery new incoming shipping query
   * @throws Throwable if a throwable is thrown
   */
  public void onShippingQuery(ShippingQuery shippingQuery) throws Throwable {
  }
  
  /**
   * Handles an incoming pre-checkout query from the bot.
   *
   * @param preCheckoutQuery new incoming pre-checkout query
   * @throws Throwable if a throwable is thrown
   */
  public void onPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery) throws Throwable {
  }
  
  /**
   * Handles an exception occurred while receiving or handling updates.
   *
   * @param t the exception to handle
   */
  public void onError(Throwable t) {
    t.printStackTrace();
  }
  
}
