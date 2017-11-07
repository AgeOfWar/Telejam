package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.TelegramException;
import me.palazzomichi.telegram.telejam.objects.ResponseParameters;

import java.util.OptionalInt;
import java.util.function.Consumer;

/**
 * Handler for response parameters contained in {@link TelegramException}s.
 *
 * @author Michi Palazzo
 */
public class ResponseParametersHandler implements Consumer<Throwable> {
  
  @Override
  public void accept(Throwable throwable) {
    if (!(throwable instanceof TelegramException)) {
      return;
    }
  
    TelegramException exception = (TelegramException) throwable;
  
    int retryAfter = exception.getResponseParameters()
        .map(ResponseParameters::getRetryAfter)
        .orElseGet(OptionalInt::empty)
        .orElse(0);
  
    try {
      Thread.sleep(retryAfter * 1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
  
}
