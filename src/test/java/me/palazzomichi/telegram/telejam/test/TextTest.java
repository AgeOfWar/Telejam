package me.palazzomichi.telegram.telejam.test;

import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.util.text.ParseMode;
import me.palazzomichi.telegram.telejam.util.text.Text;
import me.palazzomichi.telegram.telejam.util.text.TextEntity;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Michi Palazzo
 */
public class TextTest {

  private static final TextEntity[] SERIALIZABLE_ENTITIES = {
      TextEntity.BOLD,
      TextEntity.ITALIC,
      TextEntity.CODE,
      TextEntity.CODE_BLOCK,
      TextEntity.LINK,
  };

  @Test
  public void testTextHtmlSerializationAndDeserialization_randomText() {
    Text text = randomText();
    assertEquals(text, Text.parse(text.toString(ParseMode.HTML), ParseMode.HTML));
  }

  private Text randomText() {
    Random random = new Random();

    String string = randomString();
    Text text = new Text(string);

    for (int i = 0; i < string.length(); i++) {
      if (random.nextBoolean()) {
        TextEntity type = SERIALIZABLE_ENTITIES[random.nextInt(SERIALIZABLE_ENTITIES.length)];
        Object value =
            type == TextEntity.TEXT_MENTION ?
            new User(random.nextLong(),randomString(),randomString(),randomString(),null,false) :
            (type == TextEntity.LINK ? randomString() : null);
        text.addAttribute(type, value, i, i + 1);
      }
    }

    return text;
  }

  private String randomString() {
    final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789<>&\"";

    StringBuilder builder = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < random.nextInt(100); i++) {
      builder.append(characters.charAt(random.nextInt(characters.length())));
    }
    return builder.toString();
  }

}
