<p align="center">
  <img src="https://ageofwar.github.io/Telejam/telejam.png" alt="Telejam logo" width="25%" height="25%" />
</p>

# Telegram Bot Java Library
A simple to use library to create Telegram Bots in Java.
This library uses the [Telegram Bot API](https://core.telegram.org/bots/api)

## Example
This program re-sends messages.

```java
  package test;
  
  import io.github.ageofwar.telejam.Bot;
  import io.github.ageofwar.telejam.LongPollingBot;
  
  import java.io.IOException;
  
  public class RepeaterBot extends LongPollingBot {
    
    public static void main(String... args) throws IOException {
      if (args.length != 1) {
        System.err.println("Pass the bot token as unique program argument");
        System.exit(1);
      }
      String token = args[0];
      Bot bot = Bot.fromToken(token);
      RepeaterBot repeaterBot = new RepeaterBot(bot);
      repeaterBot.run();
    }
    
    public RepeaterBot(Bot bot) {
      super(bot);
      events.registerUpdateHandler(new MessageRepeater(bot));
    }
    
  }
```
```java
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
```

You can see other examples [here](https://github.com/AgeOfWar/Telejam/tree/master/examples).

--------------------------------------------------

Read the [wiki](https://github.com/AgeOfWar/Telejam/wiki)
for more information.
JavaDocs can be found [here](https://ageofwar.github.io/Telejam/docs/).
