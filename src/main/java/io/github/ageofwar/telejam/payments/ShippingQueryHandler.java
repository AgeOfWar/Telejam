package io.github.ageofwar.telejam.payments;

import io.github.ageofwar.telejam.updates.PreCheckoutQueryUpdate;
import io.github.ageofwar.telejam.updates.ShippingQueryUpdate;
import io.github.ageofwar.telejam.updates.Update;
import io.github.ageofwar.telejam.updates.UpdateHandler;

/**
 * Interface that handles shipping queries received from a bot.
 */
public interface ShippingQueryHandler extends UpdateHandler {
  
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
  
  @Override
  default void onUpdate(Update update) throws Throwable {
    if (update instanceof ShippingQueryUpdate) {
      onShippingQuery(((ShippingQueryUpdate) update).getShippingQuery());
    } else if (update instanceof PreCheckoutQueryUpdate) {
      onPreCheckoutQuery(((PreCheckoutQueryUpdate) update).getPreCheckoutQuery());
    }
  }
  
}
