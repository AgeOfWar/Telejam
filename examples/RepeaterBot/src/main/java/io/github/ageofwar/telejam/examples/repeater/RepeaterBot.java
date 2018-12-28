package io.github.ageofwar.telejam.examples.repeater;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.LongPollingBot;

import java.io.IOException;

public class RepeaterBot extends LongPollingBot {
  
  public RepeaterBot(Bot bot) {
    super(bot);
    events.registerTextMessageHandler(new MessageRepeater(bot));
  }
  
  public static void main(String... args) throws IOException {
    if (args.length != 1) {
      System.err.println("Pass the bot token as unique program argument.");
      System.exit(1);
    }
    String token = args[0];
    Bot bot = Bot.fromToken(token);
    RepeaterBot repeaterBot = new RepeaterBot(bot);
    repeaterBot.run();
  }
  
}
