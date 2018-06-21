package me.palazzomichi.telegram.telejam.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.text.Text;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static me.palazzomichi.telegram.telejam.json.Json.fromJson;
import static me.palazzomichi.telegram.telejam.json.Json.toJson;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
  
  private static final JsonParser PARSER = new JsonParser();
  
  @Test
  public void serializeAudioMessage() {
    AudioMessage message = new AudioMessage(
        123456L,
        new User(8437262L, "AgeOfWar", null, null, Locale.ITALIAN, false),
        0L,
        new Channel(-34569070986L, "Times", "times_official"),
        null,
        null,
        null,
        new Audio("AwGDJSHSshhdhShSHSHhhshshSHHSHshSshHS-N0C", 10, null, "Hello", null, null),
        Text.parseHtml("<b>Hello</b>\n<a href=www.google.it>World</a>!")
    );
    JsonObject jsonUser = new JsonObject();
    jsonUser.addProperty("id", 8437262L);
    jsonUser.addProperty("first_name", "AgeOfWar");
    jsonUser.addProperty("language_code", "it");
    jsonUser.addProperty("is_bot", false);
    JsonObject jsonChat = new JsonObject();
    jsonChat.addProperty("type", "channel");
    jsonChat.addProperty("id", -34569070986L);
    jsonChat.addProperty("title", "Times");
    jsonChat.addProperty("username", "times_official");
    JsonObject jsonAudio = new JsonObject();
    jsonAudio.addProperty("file_id", "AwGDJSHSshhdhShSHSHhhshshSHHSHshSshHS-N0C");
    jsonAudio.addProperty("duration", 10);
    jsonAudio.addProperty("title", "Hello");
    JsonElement jsonEntities = PARSER.parse(toJson(new MessageEntity[] {
        new MessageEntity(MessageEntity.Type.BOLD, 0, 5),
        new MessageEntity("www.google.it", 6, 5)
    }));
    JsonObject json = new JsonObject();
    json.addProperty("message_id", 123456L);
    json.add("from", jsonUser);
    json.addProperty("date", 0L);
    json.add("chat", jsonChat);
    json.add("audio", jsonAudio);
    json.addProperty("caption", "Hello\nWorld!");
    json.add("caption_entities", jsonEntities);
    assertEquals(json, PARSER.parse(toJson(message)));
  }
  
  @Test
  public void serializeUser() {
    User user = new User(8437262L, "AgeOfWar", null, "AgeOfWar01", Locale.ROOT, false);
    JsonObject json = new JsonObject();
    json.addProperty("id", 8437262L);
    json.addProperty("first_name", "AgeOfWar");
    json.addProperty("username", "AgeOfWar01");
    json.addProperty("is_bot", false);
    assertEquals(json, PARSER.parse(toJson(user)));
  }
  
  @Test
  public void deserializeUser() {
    String json = "{\"id\": 8437262, \"first_name\": \"AgeOfWar\", \"username\": \"AgeOfWar01\", \"is_bot\": false}";
    assertEquals(new User(8437262L, "AgeOfWar", null, "AgeOfWar01", Locale.ROOT, false), fromJson(json, User.class));
  }
  
}
