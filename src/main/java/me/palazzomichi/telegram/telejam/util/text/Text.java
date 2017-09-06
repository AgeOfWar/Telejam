package me.palazzomichi.telegram.telejam.util.text;

import me.palazzomichi.telegram.telejam.objects.MessageEntity;
import me.palazzomichi.telegram.telejam.objects.User;

import java.io.IOException;
import java.io.StringWriter;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class representing a text with entities.
 *
 * @author Michi Palazzo
 * @see TextBuilder
 */
public final class Text extends AttributedString implements CharSequence {

  /**
   * The text without entities.
   */
  private String text;

  /**
   * Converts a String into a Text.
   *
   * @param text      the text to convert
   * @param parseMode the parse mode
   * @return the text
   * @throws TextParseException if an error occurs when parsing the string
   */
  public static Text parse(String text, ParseMode parseMode) throws TextParseException {
    if (parseMode == null) {
      return new Text(text);
    }
    return parseMode.getParser().readText(text);
  }


  /**
   * Constructs a text.
   *
   * @param text the text.
   */
  public Text(String text, MessageEntity... entities) {
    super(text);
    this.text = text;
    for (MessageEntity entity : entities) {
      addEntity(entity);
    }
  }

  /**
   * Constructs an empty text.
   */
  public Text() {
    this("");
  }

  private Text(AttributedCharacterIterator iterator) {
    super(iterator);
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
   * Returns a set containing all the bold strings in this text.
   *
   * @return a set containing all the bold strings in this text
   */
  public Set<String> getBoldText() {
    return getTextEntities(TextEntity.BOLD).keySet();
  }

  /**
   * Returns a set containing all the italic strings in this text.
   *
   * @return a set containing all the italic strings in this text
   */
  public Set<String> getItalicText() {
    return getTextEntities(TextEntity.ITALIC).keySet();
  }

  /**
   * Returns a set containing all the codes in this text.
   *
   * @return a set containing all the codes in this text
   */
  public Set<String> getCodeText() {
    return getTextEntities(TextEntity.CODE).keySet();
  }

  /**
   * Returns a set containing all the code blocks in this text.
   *
   * @return a set containing all the code blocks in this text
   */
  public Set<String> getCodeBlockText() {
    return getTextEntities(TextEntity.CODE_BLOCK).keySet();
  }

  /**
   * Returns a map containing all the links in this text with their relative urls.
   *
   * @return a map containing all the links in this text with their relative urls
   */
  @SuppressWarnings("unchecked")
  public Map<String, String> getLinks() {
    return (Map<String, String>) (Map<String, ?>) getTextEntities(TextEntity.LINK);
  }

  /**
   * Returns a set containing all the urls in this text.
   *
   * @return a set containing all the urls in this text
   */
  public Set<String> getUrls() {
    return getTextEntities(TextEntity.URL).keySet();
  }

  /**
   * Returns a set containing all the emails in this text.
   *
   * @return a set containing all the emails in this text
   */
  public Set<String> getEmails() {
    return getTextEntities(TextEntity.EMAIL).keySet();
  }

  /**
   * Returns a set containing all the hashtags in this text.
   *
   * @return a set containing all the hashtags in this text
   */
  public Set<String> getHashtags() {
    return getTextEntities(TextEntity.HASHTAG).keySet();
  }

  /**
   * Returns a set containing all the mentions in this text.
   *
   * @return a set containing all the mentions in this text
   */
  public Set<String> getMentions() {
    return getTextEntities(TextEntity.MENTION).keySet();
  }

  /**
   * Returns a map containing all the text mentions in this text with their relative users.
   *
   * @return a map containing all the text mentions in this text with their relative users
   */
  @SuppressWarnings("unchecked")
  public Map<String, User> getTextMentions() {
    return (Map<String, User>) (Map<String, ?>) getTextEntities(TextEntity.TEXT_MENTION);
  }

  /**
   * Returns a set containing all the bot commands in this text.
   *
   * @return a set containing all the bot commands in this text
   */
  public Set<String> getBotCommands() {
    return getTextEntities(TextEntity.BOT_COMMAND).keySet();
  }

  private void addEntity(MessageEntity entity) {
    TextEntity type = entity.getType();

    Object value = null;
    if (type == TextEntity.LINK) {
      value = entity.getUrl();
    }
    if (type == TextEntity.TEXT_MENTION) {
      value = entity.getUser();
    }

    int beginIndex = entity.getOffset();
    int endIndex = beginIndex + entity.getLength();

    super.addAttribute(type, value, beginIndex, endIndex);
  }

  private Map<String, Object> getTextEntities(TextEntity entity) {
    AttributedCharacterIterator iterator = getIterator();
    HashMap<String, Object> entities = new HashMap<>();
    StringBuilder builder = new StringBuilder();

    Map<AttributedCharacterIterator.Attribute, Object> last = Collections.emptyMap();
    while (iterator.getIndex() != iterator.getEndIndex()) {
      Map<AttributedCharacterIterator.Attribute, Object> curr = iterator.getAttributes();
      if (curr.containsKey(entity)) {
        builder.append(iterator.current());
      } else {
        if (last.containsKey(entity)) {
          entities.put(builder.toString(), last.get(entity));
          builder.setLength(0);
        }
      }
      last = curr;

      iterator.next();
    }
    if (last.containsKey(entity)) {
      entities.put(builder.toString(), last.get(entity));
    }

    return entities;
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
    return new Text(getIterator(null, start, end));
  }

  @Override
  public void addAttribute(AttributedCharacterIterator.Attribute attribute, Object value) {
    if (!(attribute instanceof TextEntity)) {
      throw new IllegalArgumentException("Invalid attribute");
    }
    super.addAttribute(attribute, value);
  }

  @Override
  public void addAttribute(AttributedCharacterIterator.Attribute attribute, Object value, int beginIndex, int endIndex) {
    if (!(attribute instanceof TextEntity)) {
      throw new IllegalArgumentException("Invalid attribute");
    }
    super.addAttribute(attribute, value, beginIndex, endIndex);
  }

  @Override
  public void addAttributes(Map<? extends AttributedCharacterIterator.Attribute, ?> attributes, int beginIndex, int endIndex) {
    for (AttributedCharacterIterator.Attribute attribute : attributes.keySet()) {
      if (!(attribute instanceof TextEntity)) {
        throw new IllegalArgumentException("Invalid attribute(s)");
      }
    }
    super.addAttributes(attributes, beginIndex, endIndex);
  }

  @Override
  public String toString() {
    return text;
  }

  /**
   * Return this text with the specified parse mode.
   *
   * @param parseMode the parse mode
   * @return this text with the specified parse mode.
   */
  public String toString(ParseMode parseMode) {
    try (StringWriter string = new StringWriter()) {
      parseMode.getWriter().write(this, string);
      return string.toString();
    } catch (IOException e) {
      throw new AssertionError();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Text)) {
      return false;

    }
    Text text = (Text) obj;
    return toString(ParseMode.HTML).equals(text.toString(ParseMode.HTML));
  }

}
