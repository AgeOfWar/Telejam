# Telegram Bot Java Library
A simple to use library to create Telegram Bots in Java.

## Example
This program prints the updates received from the bot.
```java
  package test;
  
  import me.palazzomichi.telegram.telejam.Bot;
  import me.palazzomichi.telegram.telejam.LongPollingBot;
  
  import java.io.IOException;
  
  public class ExampleBot extends LongPollingBot {
    
    public static void main(String... args) throws IOException {
      Bot bot = Bot.fromToken(args[0]);
      new ExampleBot(bot).run();
    }
    
    public ExampleBot(Bot bot) {
      super(bot);
    }
    
    @Override
    public void onTextMessage(TextMessage message) throws IOException {
      bot.sendMessage(message, message.getText());
    }
    
  }
```
