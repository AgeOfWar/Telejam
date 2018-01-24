# Telegram Bot Java Library
A simple to use library to create Telegram Bots in Java.

## Example
This program prints the updates received from the bot.
```java
  package test;
  
  import me.palazzomichi.telegram.telejam.Bot;
  import me.palazzomichi.telegram.telejam.BotThread;
  
  import java.io.IOException;
  
  public class ExampleBot {
    public static void main(String... args) throws IOException {
      Bot bot = new Bot("<token>");
      BotThread thread = new BotThread(bot);
      thread.getUpdateHandlers().add(update -> System.out.println(update.toJson()));
      thread.run();
    }
  }
```
