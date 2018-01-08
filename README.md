# Telegram Bot Java Library
A simple to use library to create Telegram Bots in Java.

## Hello World
```java
  package test;
  
  import me.palazzomichi.telegram.telejam.Bot;
  import me.palazzomichi.telegram.telejam.BotThread;
  
  import java.io.IOException;
  
  public class HelloWorldBot {
  
    public static void main(String... args) {
      try {
        Bot bot = new Bot("<token>");
        BotThread thread = new BotThread(bot);
        thread.getUpdateHandlers().add(update -> System.out.println(update.toJson()));
        thread.getErrorHandlers().add(Throwable::printStackTrace);
        thread.run();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
  }
```
