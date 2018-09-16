# Telegram Bot Java Library
A simple to use library to create Telegram Bots in Java.
This library uses the [Telegram Bot API](https://core.telegram.org/bots/api)

## Example
This program re-sends messages.
```java
  package test;
  
  import me.palazzomichi.telegram.telejam.Bot;
  import me.palazzomichi.telegram.telejam.LongPollingBot;
  
  import java.io.IOException;
  
  public class ExampleBot extends LongPollingBot {
    
    public static void main(String... args) throws IOException {
      if (args.length != 1) {
        System.err.println("Pass the bot token as unique program argument");
        System.exit(1);
      }
      Bot bot = Bot.fromToken(args[0]);
      try (ExampleBot exampleBot = new ExampleBot(bot)) {
        exampleBot.run();
      }
    }
    
    public ExampleBot(Bot bot) {
      super(bot);
    }
    
    @Override
    public void onMessage(Message message) throws IOException {
      if (message instanceof TextMessage) {
        SendMessage sendMessage = new SendMessage()
          .replyToMessage(message)
          .text(((TextMessage) message).getText());
        bot.execute(sendMessage);
      }
    }
    
  }
```
Read the [wiki](https://github.com/AgeOfWar/Telejam/wiki)
for more information.
