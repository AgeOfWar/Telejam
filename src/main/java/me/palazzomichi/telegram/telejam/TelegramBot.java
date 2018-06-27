package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.util.*;

/**
 * Abstract class that handles Telegram updates.
 */
public abstract class TelegramBot implements
    UpdateHandler,
    MessageHandler,
    MessageEditHandler,
    InlineQueryHandler,
    CallbackQueryHandler,
    ShippingQueryHandler {
  
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
  @Override
  public void onUpdate(Update update) throws Throwable {
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
    }
  }
  
  @Override
  public void onMessage(Message message) throws Throwable {
  }
  
  @Override
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
  
  /**
   * Handles an exception occurred while receiving or handling updates.
   *
   * @param t the exception to handle
   */
  public void onError(Throwable t) {
    t.printStackTrace();
  }
  
}
