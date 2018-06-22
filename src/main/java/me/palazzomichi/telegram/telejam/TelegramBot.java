package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.text.Text;

/**
 * Interface that handles Telegram updates.
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
          button.onClick();
        }
      }
    } else if (update instanceof ShippingQueryUpdate) {
      onShippingQuery(((ShippingQueryUpdate) update).getShippingQuery());
    } else if (update instanceof PreCheckoutQueryUpdate) {
      onPreCheckoutQuery(((PreCheckoutQueryUpdate) update).getPreCheckoutQuery());
    }
  }
  
  public void onMessage(Message message) throws Throwable {
  }
  
  public void onTextMessage(TextMessage message) throws Throwable {
  }
  
  public void onCommand(String command, String[] args, TextMessage message) throws Throwable {
  }
  
  public void onMessageEdit(Message message, long editDate) throws Throwable {
  }
  
  public void onChannelPost(Message message) throws Throwable {
  }
  
  public void onChannelPostEdit(Message message, long editDate) throws Throwable {
  }
  
  public void onInlineQuery(InlineQuery inlineQuery) throws Throwable {
  }
  
  public void onChosenInlineResult(ChosenInlineResult chosenInlineResult) throws Throwable {
  }
  
  public void onCallbackQuery(CallbackQuery callbackQuery) throws Throwable {
  }
  
  public void onShippingQuery(ShippingQuery shippingQuery) throws Throwable {
  }
  
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
