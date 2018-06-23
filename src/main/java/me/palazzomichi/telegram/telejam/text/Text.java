package me.palazzomichi.telegram.telejam.text;

import me.palazzomichi.telegram.telejam.objects.MessageEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

/**
 * Class representing a text with entities.
 *
 * @author Michi Palazzo
 * @see TextBuilder
 */
public final class Text implements CharSequence {
  
  public static final Text EMPTY = new Text("");
  
  private final String text;
  private final List<MessageEntity> entities;
  
  /**
   * Converts an HTML String into a Text.
   * The following tags are currently supported:
   * <pre>
   * &lt;b&gt;bold&lt;/b&gt;, &lt;strong&gt;bold&lt;/strong&gt;
   * &lt;i&gt;italic&lt;/i&gt;, &lt;em&gt;italic&lt;/em&gt;
   * &lt;a href="http://www.example.com/"&gt;inline URL&lt;/a&gt;
   * &lt;a href="tg://user?id=123456789"&gt;inline mention of a user&lt;/a&gt;
   * &lt;code&gt;inline fixed-width code&lt;/code&gt;
   * &lt;pre&gt;pre-formatted fixed-width code block&lt;/pre&gt;
   * </pre>
   *
   * @param text the string to convert
   * @return the parsed text
   * @throws TextParseException if an error occurs when parsing the string
   */
  public static Text parseHtml(String text) throws TextParseException {
    if (text == null) {
      return null;
    }
    return Html.parseText(text);
  }
  
  
  /**
   * Constructs a text.
   *
   * @param text     the text
   * @param entities text entities
   * @see TextBuilder
   */
  public Text(String text, List<MessageEntity> entities) {
    this.text = text;
    this.entities = entities != null ? unmodifiableList(entities) : emptyList();
  }
  
  /**
   * Constructs a text.
   *
   * @param text the text
   * @see TextBuilder
   */
  public Text(String text) {
    this(text, emptyList());
  }
  
  
  /**
   * Returns {@code true} if, and only if, {@link #text} length is {@code 0}.
   *
   * @return {@code true} if {@link #text} length is {@code 0}, otherwise {@code false}
   */
  public boolean isEmpty() {
    return text.isEmpty();
  }
  
  /**
   * Returns a list containing all the bold strings in this text.
   *
   * @return a list containing all the bold strings in this text
   */
  public List<String> getBoldText() {
    return getEntities(MessageEntity.Type.BOLD);
  }
  
  /**
   * Returns a list containing all the italic strings in this text.
   *
   * @return a list containing all the italic strings in this text
   */
  public List<String> getItalicText() {
    return getEntities(MessageEntity.Type.ITALIC);
  }
  
  /**
   * Returns a list containing all the codes in this text.
   *
   * @return a list containing all the codes in this text
   */
  public List<String> getCodeText() {
    return getEntities(MessageEntity.Type.CODE);
  }
  
  /**
   * Returns a list containing all the code blocks in this text.
   *
   * @return a list containing all the code blocks in this text
   */
  public List<String> getCodeBlocks() {
    return getEntities(MessageEntity.Type.CODE_BLOCK);
  }
  
  /**
   * Returns a list containing all the links in this text with their relative urls.
   *
   * @return a list containing all the links in this text with their relative urls
   */
  public List<Link> getLinks() {
    return getEntities(MessageEntity.Type.LINK, entity -> new Link(
        text.substring(entity.getOffset(), entity.getOffset() + entity.getLength()),
        entity.getUrl().orElseThrow(AssertionError::new)
    ));
  }
  
  /**
   * Returns a list containing all the urls in this text.
   *
   * @return a list containing all the urls in this text
   */
  public List<String> getUrls() {
    return getEntities(MessageEntity.Type.URL);
  }
  
  /**
   * Returns a list containing all the emails in this text.
   *
   * @return a list containing all the emails in this text
   */
  public List<String> getEmails() {
    return getEntities(MessageEntity.Type.EMAIL);
  }
  
  /**
   * Returns a list containing all the hashtags in this text.
   *
   * @return a list containing all the hashtags in this text
   */
  public List<String> getHashtags() {
    return getEntities(
        MessageEntity.Type.HASHTAG,
        entity -> text.substring(entity.getOffset() + 1, entity.getOffset() + entity.getLength())
    );
  }
  
  /**
   * Returns a list containing all the mentions in this text.
   *
   * @return a list containing all the mentions in this text
   */
  public List<String> getMentions() {
    return getEntities(
        MessageEntity.Type.MENTION,
        entity -> text.substring(entity.getOffset() + 1, entity.getOffset() + entity.getLength())
    );
  }
  
  /**
   * Returns a list containing all the text mentions in this text with their relative users.
   *
   * @return a list containing all the text mentions in this text with their relative users
   */
  public List<Mention> getTextMentions() {
    return getEntities(MessageEntity.Type.TEXT_MENTION, entity -> new Mention(
        text.substring(entity.getOffset(), entity.getOffset() + entity.getLength()),
        entity.getUser().orElseThrow(AssertionError::new)
    ));
  }
  
  /**
   * Returns a list containing all the bot commands in this text.
   *
   * @return a list containing all the bot commands in this text
   */
  public List<String> getBotCommands() {
    return getEntities(
        MessageEntity.Type.BOT_COMMAND,
        entity -> text.substring(entity.getOffset() + 1, entity.getOffset() + entity.getLength())
    );
  }
  
  private <T> List<T> getEntities(MessageEntity.Type type, Function<MessageEntity, T> mapper) {
    return entities.stream()
        .filter(e -> e.getType() == type)
        .map(mapper)
        .collect(Collectors.toList());
  }
  
  private List<String> getEntities(MessageEntity.Type type) {
    return getEntities(
        type,
        entity -> text.substring(entity.getOffset(), entity.getOffset() + entity.getLength())
    );
  }
  
  /**
   * Return this text in HTML format.
   *
   * @return this text in HTML format
   */
  public String toHtmlString() {
    return Html.toString(this);
  }
  
  @Override
  public int length() {
    return text.length();
  }
  
  @Override
  public char charAt(int index) {
    return text.charAt(index);
  }
  
  @Override
  public Text subSequence(int start, int end) {
    if (start == 0 && end == text.length())
      return this;
    if (start == end)
      return EMPTY;
    String text = this.text.substring(start, end);
    
    List<MessageEntity> entities = new ArrayList<>();
    for (MessageEntity entity : this.entities) {
      if (entity.getOffset() >= end) {
        break;
      } else if (entity.getOffset() < start && entity.getOffset() + entity.getLength() > end) {
        entities.add(entity.move(0, end - start));
        break;
      } else if (entity.getOffset() < start && entity.getOffset() + entity.getLength() >= start) {
        entities.add(entity.move(0, entity.getLength() - start));
      } else if (entity.getOffset() >= start && entity.getOffset() + entity.getLength() <= end) {
        entities.add(entity.move(entity.getOffset() - start, entity.getLength()));
      } else if (entity.getOffset() >= start && entity.getOffset() + entity.getLength() > end) {
        entities.add(entity.move(entity.getOffset() - start, end - entity.getOffset()));
      } else break;
    }
    return new Text(text, entities);
  }
  
  @Override
  public String toString() {
    return text;
  }
  
  /**
   * Returns the text entities.
   *
   * @return the text entities
   */
  public List<MessageEntity> getEntities() {
    return entities;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Text)) {
      return false;
    }
    Text text = (Text) obj;
    return this.text.equals(text.text) && entities.equals(text.entities);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(text, entities);
  }
  
}
