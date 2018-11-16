package io.github.ageofwar.telejam.text;

import io.github.ageofwar.telejam.users.User;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextTest {
  
  private static final Text HELLO_WORLD = new TextBuilder()
      .appendBold("Hello")
      .append(" ")
      .appendLink("World!", "www.example.com")
      .build();
  
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
  
    Text text = new TextBuilder()
        .append("         ")
        .appendBold(" Hello world")
        .appendItalic("  ")
        .build();
    assertEquals(Text.bold("Hello world"), text.subSequence(10, 21));
  }
  
  @Test
  public void parseHtml() {
    Text text = new TextBuilder()
        .append("hello")
        .append(" world!\n")
        .appendBold("I'm ")
        .appendBold("<17>")
        .appendItalic(" years")
        .appendCode(" ol")
        .appendLink("d", "www.google.it")
        .build();
    assertEquals(text, Text.parseHtml("hello world!\n<b>I'm &lt;17&gt;</b><i> years</i><code> ol" +
        "</code><a href=www.google.it>d</a>"));
  }
  
  @Test
  public void parseMarkdown() {
    Text text = new TextBuilder()
        .append("hello")
        .append(" world!\n")
        .appendBold("I'm ")
        .appendBold("*17*")
        .appendItalic(" years")
        .appendCode(" ol")
        .appendLink("d", "www.google.it")
        .build();
    assertEquals(text, Text.parseMarkdown("hello world!\n*I'm \\*17\\**_ years_` ol`[d](www.google.it)"));
  }
  
  @Test
  public void toHtmlString() {
    assertEquals(
        "hello world!\n<b>I'm &lt;17&gt;</b><i> years</i><code> ol</code><a href=\"www.google.it\">d</a>",
        new TextBuilder()
            .append("hello")
            .append(" world!\n")
            .appendBold("I'm ")
            .appendBold("<17>")
            .appendItalic(" years")
            .appendCode(" ol")
            .appendLink("d", "www.google.it")
            .build()
            .toHtmlString()
    );
  }
  
  @Test
  public void toMarkdownString() {
    assertEquals(
        "hello world!\n*I'm \\*17\\**_ years_` ol`[d](www.google.it)",
        new TextBuilder()
            .append("hello")
            .append(" world!\n")
            .appendBold("I'm ")
            .appendBold("*17*")
            .appendItalic(" years")
            .appendCode(" ol")
            .appendLink("d", "www.google.it")
            .build()
            .toMarkdownString()
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
        new TextBuilder()
            .appendHashtag("family")
            .append("\n")
            .appendHashtag("food")
            .build()
            .getHashtags()
    );
  }
  
  @Test
  public void getTextMentions() {
    User ageOfWar = new User(12344L, "AgeOfWar", null, null, Locale.ITALIAN, false);
    assertEquals(
        asList(new Mention("AgeOfWar", ageOfWar), new Mention("pallinopix", ageOfWar)),
        new TextBuilder()
            .append("Contact ")
            .appendTextMention(ageOfWar)
            .append(" or ")
            .appendTextMention("pallinopix", ageOfWar)
            .append(" for more details.")
            .build()
            .getTextMentions()
    );
  }
  
  @Test
  public void concat() {
    Text text1 = new TextBuilder()
        .appendBold("Hello")
        .append(" ")
        .appendLink("Jimmy", "t.me/Jimmy")
        .build();
    Text text2 = new TextBuilder()
        .appendItalic("!")
        .append("\n\n")
        .appendHashtag("hi")
        .build();
    Text text3 = new TextBuilder()
        .appendBold("Hello")
        .append(" ")
        .appendLink("Jimmy", "t.me/Jimmy")
        .appendItalic("!")
        .append("\n\n")
        .appendHashtag("hi")
        .build();
    assertEquals(text3, text1.concat(text2));
  }
  
  @Test
  public void trim() {
    Text text = new TextBuilder()
        .append("         ")
        .appendBold(" Hello world")
        .appendItalic("  ")
        .build();
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
