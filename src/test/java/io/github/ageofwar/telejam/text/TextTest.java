package io.github.ageofwar.telejam.text;

import io.github.ageofwar.telejam.messages.MessageEntity;
import io.github.ageofwar.telejam.users.User;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextTest {
  
  private static final Text HELLO_WORLD = new Text(
      "Hello World!",
      new MessageEntity(MessageEntity.Type.BOLD, 0, 5),
      new MessageEntity("www.example.com", 6, 6)
  );
  
  @Test
  public void length() {
    assertEquals(12, HELLO_WORLD.length());
  }
  
  @Test
  public void length_emptyText() {
    Text text = new Text("");
    assertEquals(0, text.length());
  }
  
  @Test
  public void charAt() {
    assertEquals('W', HELLO_WORLD.charAt(6));
  }
  
  @Test
  public void charAt_invalidIndex() {
    assertThrows(IndexOutOfBoundsException.class, () -> HELLO_WORLD.charAt(12));
  }
  
  @Test
  public void charAt_negativeIndex() {
    assertThrows(IndexOutOfBoundsException.class, () -> HELLO_WORLD.charAt(-1));
  }
  
  @Test
  public void subSequence() {
    assertEquals(
        Text.parseHtml("<b>lo</b> <a href=www.example.com>Wor</a>"),
        HELLO_WORLD.subSequence(3, 9)
    );
  
    assertEquals(Text.bold("ell"), HELLO_WORLD.subSequence(1, 4));
  
    Text text = new Text(
        "          Hello world  ",
        new MessageEntity(MessageEntity.Type.BOLD, 9, 12),
        new MessageEntity(MessageEntity.Type.ITALIC, 21, 2)
    );
    assertEquals(Text.bold("Hello world"), text.subSequence(10, 21));
  }
  
  @Test
  public void parseHtml() {
    Text text = new Text(
        "hello world!\nI'm <17> years old",
        new MessageEntity(MessageEntity.Type.BOLD, 13, 8),
        new MessageEntity(MessageEntity.Type.UNDERLINE, 18, 2),
        new MessageEntity(MessageEntity.Type.ITALIC, 21, 6),
        new MessageEntity(MessageEntity.Type.CODE, 27, 3),
        new MessageEntity("www.google.it", 30, 1)
    );
    assertEquals(text, Text.parseHtml("hello world!\n<b>I'm &lt;<ins>17</ins>&gt;</b><i> years</i><code> ol" +
        "</code><a href=www.google.it>d</a>"));
  }
  
  @Test
  public void parseMarkdown() {
    Text text = new Text(
        "hello world!\nI'm *17* years old",
        new MessageEntity(MessageEntity.Type.BOLD, 13, 8),
        new MessageEntity(MessageEntity.Type.UNDERLINE, 18, 2),
        new MessageEntity(MessageEntity.Type.ITALIC, 21, 6),
        new MessageEntity(MessageEntity.Type.CODE, 27, 3),
        new MessageEntity("www.google.it", 30, 1)
    );
    assertEquals(text, Text.parseMarkdown("hello world!\n*I'm \\*__17__\\**_ years_` ol`[d](www.google.it)"));
  }
  
  @Test
  public void toHtmlString() {
    assertEquals(
        "hello world!\n<b>I'm &lt;<u>17</u>&gt;</b><i> years</i><code> ol</code><a href=\"www.google.it\">d</a>",
        new Text(
            "hello world!\nI'm <17> years old",
            new MessageEntity(MessageEntity.Type.BOLD, 13, 8),
            new MessageEntity(MessageEntity.Type.UNDERLINE, 18, 2),
            new MessageEntity(MessageEntity.Type.ITALIC, 21, 6),
            new MessageEntity(MessageEntity.Type.CODE, 27, 3),
            new MessageEntity("www.google.it", 30, 1)
        ).toHtmlString()
    );
  }
  
  @Test
  public void toMarkdownString() {
    assertEquals(
        "hello world!\n*I'm \\*__17__\\**_ years_\r` ol`[d](www.google.it)",
        new Text(
            "hello world!\nI'm *17* years old",
            new MessageEntity(MessageEntity.Type.BOLD, 13, 8),
            new MessageEntity(MessageEntity.Type.UNDERLINE, 18, 2),
            new MessageEntity(MessageEntity.Type.ITALIC, 21, 6),
            new MessageEntity(MessageEntity.Type.CODE, 27, 3),
            new MessageEntity("www.google.it", 30, 1)
        ).toMarkdownString()
    );
  }
  
  @Test
  public void getBoldText() {
    assertEquals(
        asList("Hel", "rld"),
        Text.parseHtml("<b>Hel</b>lo<i> </i>wo<b>rld</b>").getBoldText()
    );
  }
  
  @Test
  public void getHashtags() {
    assertEquals(
        asList("family", "food"),
        new Text(
            "#family\n#food",
            new MessageEntity(MessageEntity.Type.HASHTAG, 0, 7),
            new MessageEntity(MessageEntity.Type.HASHTAG, 8, 5)
        ).getHashtags()
    );
  }
  
  @Test
  public void concat() {
    Text text1 = new Text(
        "Hello Jimmy",
        new MessageEntity(MessageEntity.Type.BOLD, 0, 5),
        new MessageEntity("t.me/Jimmy", 6, 5)
    );
    Text text2 = new Text(
        "!\n\n#hi",
        new MessageEntity(MessageEntity.Type.ITALIC, 0, 1),
        new MessageEntity(MessageEntity.Type.HASHTAG, 3, 3)
    );
    Text text3 = new Text(
        "Hello Jimmy!\n\n#hi",
        new MessageEntity(MessageEntity.Type.BOLD, 0, 5),
        new MessageEntity("t.me/Jimmy", 6, 5),
        new MessageEntity(MessageEntity.Type.ITALIC, 11, 1),
        new MessageEntity(MessageEntity.Type.HASHTAG, 14, 3)
    );
    assertEquals(text3, text1.concat(text2));
  }
  
  @Test
  public void trim() {
    Text text = new Text(
        "          Hello world  ",
        new MessageEntity(MessageEntity.Type.BOLD, 9, 12),
        new MessageEntity(MessageEntity.Type.ITALIC, 21, 2)
    );
    assertEquals(Text.bold("Hello world"), text.trim());
  }
  
  @Test
  public void trim_emptyString() {
    assertEquals(Text.EMPTY, Text.EMPTY.trim());
  }
  
  @Test
  public void trim_noSpaces() {
    assertEquals(new Text("hi"), new Text("hi").trim());
  }
  
}
