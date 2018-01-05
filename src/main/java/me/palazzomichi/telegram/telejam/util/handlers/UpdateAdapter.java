package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.CallbackQuery;
import me.palazzomichi.telegram.telejam.objects.inline.ChosenInlineResult;
import me.palazzomichi.telegram.telejam.objects.inline.InlineQuery;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.payments.PreCheckoutQuery;
import me.palazzomichi.telegram.telejam.objects.payments.ShippingQuery;
import me.palazzomichi.telegram.telejam.objects.updates.*;

/**
 * Convenience implementation of {@link UpdateHandler}.
 * Derive from this and only override what you need.
 *
 * @author Michi Palazzo
 */
public interface UpdateAdapter extends UpdateHandler {
  
  /**
   * Performs this operation on the given message.
   *
   * @param message the message
   * @throws Throwable if a throwable is thrown
   */
  default void acceptMessage(Message message) throws Throwable {
  }
  
  /**
   * Performs this operation on the given edited message.
   *
   * @param message the message
   * @throws Throwable if a throwable is thrown
   */
  default void acceptEditedMessage(Message message) throws Throwable {
  }
  
  /**
   * Performs this operation on the given channel post.
   *
   * @param message the message
   * @throws Throwable if a throwable is thrown
   */
  default void acceptChannelPost(Message message) throws Throwable {
  }
  
  /**
   * Performs this operation on the given edited channel post.
   *
   * @param message the message
   * @throws Throwable if a throwable is thrown
   */
  default void acceptEditedChannelPost(Message message) throws Throwable {
  }
  
  /**
   * Performs this operation on the given callback query.
   *
   * @param callbackQuery the callback query
   * @throws Throwable if a throwable is thrown
   */
  default void acceptCallbackQuery(CallbackQuery callbackQuery) throws Throwable {
  }
  
  /**
   * Performs this operation on the given inline query.
   *
   * @param inlineQuery the inline query
   * @throws Throwable if a throwable is thrown
   */
  default void acceptInlineQuery(InlineQuery inlineQuery) throws Throwable {
  }
  
  /**
   * Performs this operation on the given chosen inline result.
   *
   * @param chosenInlineResult the chosen inline result
   * @throws Throwable if a throwable is thrown
   */
  default void acceptChosenInlineResult(ChosenInlineResult chosenInlineResult) throws Throwable {
  }
  
  /**
   * Performs this operation on the given shipping query.
   *
   * @param shippingQuery the shipping query
   * @throws Throwable if a throwable is thrown
   */
  default void acceptShippingQuery(ShippingQuery shippingQuery) throws Throwable {
  }
  
  /**
   * Performs this operation on the given pre-checkout query.
   *
   * @param preCheckoutQuery the pre-checkout query
   * @throws Throwable if a throwable is thrown
   */
  default void acceptPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery) throws Throwable {
  }
  
  @Override
  default void accept(Update update) throws Throwable {
    if (update instanceof MessageUpdate) {
      Message message = ((MessageUpdate) update).getMessage();
      acceptMessage(message);
    }
    if (update instanceof EditedMessageUpdate) {
      Message message = ((EditedMessageUpdate) update).getMessage();
      acceptEditedMessage(message);
    }
    if (update instanceof ChannelPostUpdate) {
      Message message = ((ChannelPostUpdate) update).getChannelPost();
      acceptChannelPost(message);
    }
    if (update instanceof EditedChannelPostUpdate) {
      Message message = ((EditedChannelPostUpdate) update).getEditedChannelPost();
      acceptEditedMessage(message);
    }
    if (update instanceof CallbackQueryUpdate) {
      CallbackQuery callbackQuery = ((CallbackQueryUpdate) update).getCallbackQuery();
      acceptCallbackQuery(callbackQuery);
    }
    if (update instanceof InlineQueryUpdate) {
      InlineQuery inlineQuery = ((InlineQueryUpdate) update).getInlineQuery();
      acceptInlineQuery(inlineQuery);
    }
    if (update instanceof ChosenInlineResultUpdate) {
      ChosenInlineResult chosenInlineResult = ((ChosenInlineResultUpdate) update).getChosenInlineResult();
      acceptChosenInlineResult(chosenInlineResult);
    }
    if (update instanceof ShippingQueryUpdate) {
      ShippingQuery shippingQuery = ((ShippingQueryUpdate) update).getShippingQuery();
      acceptShippingQuery(shippingQuery);
    }
    if (update instanceof PreCheckoutQueryUpdate) {
      PreCheckoutQuery preCheckoutQuery = ((PreCheckoutQueryUpdate) update).getPreCheckoutQuery();
      acceptPreCheckoutQuery(preCheckoutQuery);
    }
  }
  
}
