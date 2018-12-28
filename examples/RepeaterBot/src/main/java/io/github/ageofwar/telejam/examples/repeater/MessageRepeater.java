package io.github.ageofwar.telejam.examples.repeater;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.messages.TextMessage;
import io.github.ageofwar.telejam.messages.TextMessageHandler;
import io.github.ageofwar.telejam.methods.SendMessage;

public class MessageRepeater implements TextMessageHandler {
  
  private final Bot bot;
  
  public MessageRepeater(Bot bot) {
    this.bot = bot;
  }
  
  @Override
  public void onTextMessage(TextMessage message) throws Throwable {
    SendMessage sendMessage = new SendMessage()
        .replyToMessage(message)
        .text(message.getText());
    bot.execute(sendMessage);
  }
  
}
