package me.palazzomichi.telegram.telejam.util;

import me.palazzomichi.telegram.telejam.objects.PreCheckoutQuery;
import me.palazzomichi.telegram.telejam.objects.ShippingQuery;

/**
 * Interface that handles shipping queries received from a bot.
 */
public interface ShippingQueryHandler {
  
  /**
   * Handles an incoming shipping query.
   *
   * @param shippingQuery new incoming shipping query
   * @throws Throwable if a throwable is thrown
   */
  void onShippingQuery(ShippingQuery shippingQuery) throws Throwable;
  
  /**
   * Handles an incoming pre-checkout query.
   *
   * @param preCheckoutQuery new incoming pre-checkout query
   * @throws Throwable if a throwable is thrown
   */
  void onPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery) throws Throwable;
  
}
