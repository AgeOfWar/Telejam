package me.palazzomichi.telegram.telejam.util.text;

import me.palazzomichi.telegram.telejam.objects.MessageEntity;
import me.palazzomichi.telegram.telejam.objects.User;

import java.io.IOException;
import java.io.StringWriter;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.*;

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
   * Returns a list containing all the bold strings in this text.
   *
   * @return a list containing all the bold strings in this text
   */
  public List<String> getBoldText() {
    return getEntities(TextEntity.BOLD);
  }

  /**
   * Returns a list containing all the italic strings in this text.
   *
   * @return a list containing all the italic strings in this text
   */
  public List<String> getItalicText() {
    return getEntities(TextEntity.ITALIC);
  }

  /**
   * Returns a list containing all the codes in this text.
   *
   * @return a list containing all the codes in this text
   */
  public List<String> getCodeText() {
    return getEntities(TextEntity.CODE);
  }

  /**
   * Returns a list containing all the code blocks in this text.
   *
   * @return a list containing all the code blocks in this text
   */
  public List<String> getCodeBlockText() {
    return getEntities(TextEntity.CODE_BLOCK);
  }

  /**
   * Returns a list containing all the links in this text with their relative urls.
   *
   * @return a list containing all the links in this text with their relative urls
   */
  public List<Map.Entry<String, String>> getLinks() {
    return getEntitiesWithValues(TextEntity.LINK);
  }

  /**
   * Returns a list containing all the urls in this text.
   *
   * @return a list containing all the urls in this text
   */
  public List<String> getUrls() {
    return getEntities(TextEntity.URL);
  }

  /**
   * Returns a list containing all the emails in this text.
   *
   * @return a list containing all the emails in this text
   */
  public List<String> getEmails() {
    return getEntities(TextEntity.EMAIL);
  }

  /**
   * Returns a list containing all the hashtags in this text.
   *
   * @return a list containing all the hashtags in this text
   */
  public List<String> getHashtags() {
    return getEntities(TextEntity.HASHTAG);
  }

  /**
   * Returns a list containing all the mentions in this text.
   *
   * @return a list containing all the mentions in this text
   */
  public List<String> getMentions() {
    return getEntities(TextEntity.MENTION);
  }

  /**
   * Returns a list containing all the text mentions in this text with their relative users.
   *
   * @return a list containing all the text mentions in this text with their relative users
   */
  public List<Map.Entry<String, User>> getTextMentions() {
    return getEntitiesWithValues(TextEntity.TEXT_MENTION);
  }

  /**
   * Returns a list containing all the bot commands in this text.
   *
   * @return a list containing all the bot commands in this text
   */
  public List<String> getBotCommands() {
    return getEntities(TextEntity.BOT_COMMAND);
  }

  private void addEntity(MessageEntity entity) {
    TextEntity type = entity.getType();

    Object value = null;
    if (type == TextEntity.LINK) {
      value = entity.getUrl().orElseThrow(AssertionError::new);
    }
    if (type == TextEntity.TEXT_MENTION) {
      value = entity.getUser().orElseThrow(AssertionError::new);
    }

    int beginIndex = entity.getOffset();
    int endIndex = beginIndex + entity.getLength();

    super.addAttribute(type, value, beginIndex, endIndex);
  }

  @SuppressWarnings("unchecked")
  private <T> List<Map.Entry<String, T>> getEntitiesWithValues(TextEntity entity) {
    AttributedCharacterIterator iterator = getIterator();
    List<Map.Entry<String, T>> entities = new ArrayList<>();
    StringBuilder builder = new StringBuilder();

    Map<AttributedCharacterIterator.Attribute, T> last = Collections.emptyMap();
    while (iterator.getIndex() != iterator.getEndIndex()) {
      Map<AttributedCharacterIterator.Attribute, T> curr =
          (Map<AttributedCharacterIterator.Attribute, T>) iterator.getAttributes();
      if (curr.containsKey(entity)) {
        builder.append(iterator.current());
      } else {
        if (last.containsKey(entity)) {
          entities.add(
              new AbstractMap.SimpleImmutableEntry<>(builder.toString(), last.get(entity))
          );
          builder.setLength(0);
        }
      }
      last = curr;

      iterator.next();
    }
    if (last.containsKey(entity)) {
      entities.add(
          new AbstractMap.SimpleImmutableEntry<>(builder.toString(), last.get(entity))
      );
    }

    return entities;
  }

  private List<String> getEntities(TextEntity entity) {
    AttributedCharacterIterator iterator = getIterator();
    List<String> entities = new ArrayList<>();
    StringBuilder builder = new StringBuilder();

    Map<AttributedCharacterIterator.Attribute, Object> last = Collections.emptyMap();
    while (iterator.getIndex() != iterator.getEndIndex()) {
      Map<AttributedCharacterIterator.Attribute, Object> curr = iterator.getAttributes();
      if (curr.containsKey(entity)) {
        builder.append(iterator.current());
      } else {
        if (last.containsKey(entity)) {
          entities.add(builder.toString());
          builder.setLength(0);
        }
      }
      last = curr;

      iterator.next();
    }
    if (last.containsKey(entity)) {
      entities.add(builder.toString());
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
    if (obj == this) {
      return true;
    }

    if(!(obj instanceof Text)) {
      return false;
    }

    Text text = (Text) obj;

    if (this.length() != text.length()) {
      return false;
    }

    AttributedCharacterIterator it1 = this.getIterator();
    AttributedCharacterIterator it2 = text.getIterator();
    while (true) {
      if (it1.getIndex() == it1.getEndIndex()) {
        break;
      }

      if (it1.next() != it2.next()) {
        return false;
      }
      Map map1 = it1.getAttributes();
      Map map2 = it2.getAttributes();
      if (!map1.equals(map2)) {
        return false;
      }
    }
    return true;
  }

}
