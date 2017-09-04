package me.palazzomichi.telegram.telejam.util.text;

import me.palazzomichi.telegram.telejam.objects.MessageEntity;
import me.palazzomichi.telegram.telejam.objects.User;

import java.util.ArrayList;

/**
 * Factory class for {@link Text}.
 *
 * @author Michi Palazzo
 * @see Text
 */
public class TextBuilder {

  /**
   * Builder of the string of the Text.
   */
  private StringBuilder builder;

  /**
   * Array of entities.
   */
  private ArrayList<MessageEntity> entities;


  /**
   * Constructs a TextBuilder.
   */
  public TextBuilder() {
    entities = new ArrayList<>();
    builder = new StringBuilder();
  }

  /**
   * Constructs a TextBuilder.
   *
   * @param text initial text
   */
  public TextBuilder(String text) {
    this();
    append(text);
  }


  /**
   * Appends a text to this TextBuilder.
   *
   * @param text the text to append
   * @return this instance
   */
  public TextBuilder append(String text) {
    builder.append(text);
    return this;
  }

  /**
   * Appends a bold text to this TextBuilder.
   *
   * @param text the text to append
   * @return this instance
   */
  public TextBuilder appendBold(String text) {
    int offset = builder.length();
    builder.append(text);
    int length = builder.length() - offset;
    if (length > 0)
      entities.add(new MessageEntity(TextEntity.BOLD, offset, length));
    return this;
  }

  /**
   * Appends a italic text to this TextBuilder.
   *
   * @param text the text to append
   * @return this instance
   */
  public TextBuilder appendItalic(String text) {
    int offset = builder.length();
    builder.append(text);
    int length = builder.length() - offset;
    if (length > 0)
      entities.add(new MessageEntity(TextEntity.ITALIC, offset, length));
    return this;
  }

  /**
   * Appends a code text to this TextBuilder.
   *
   * @param text the text to append
   * @return this instance
   */
  public TextBuilder appendCode(String text) {
    int offset = builder.length();
    builder.append(text);
    int length = builder.length() - offset;
    if (length > 0)
      entities.add(new MessageEntity(TextEntity.CODE, offset, length));
    return this;
  }

  /**
   * Appends a code block text to this TextBuilder.
   *
   * @param text the text to append
   * @return this instance
   */
  public TextBuilder appendCodeBlock(String text) {
    int offset = builder.length();
    builder.append(text);
    int length = builder.length() - offset;
    if (length > 0)
      entities.add(new MessageEntity(TextEntity.CODE_BLOCK, offset, length));
    return this;
  }

  /**
   * Appends a link to this TextBuilder.
   *
   * @param text the text of the link
   * @param link the url of the link
   * @return this instance
   */
  public TextBuilder appendLink(String text, String link) {
    int offset = builder.length();
    builder.append(text);
    int length = builder.length() - offset;
    if (length > 0)
      entities.add(new MessageEntity(TextEntity.LINK, offset, length, link));
    return this;
  }

  /**
   * Appends a mention to this TextBuilder.
   *
   * @param text    the text of the mention
   * @param mention the mention
   * @return this instance
   */
  public TextBuilder appendMention(String text, User mention) {
    int offset = builder.length();
    builder.append(text);
    int length = builder.length() - offset;
    if (length > 0)
      entities.add(new MessageEntity(TextEntity.TEXT_MENTION, offset, length, mention));
    return this;
  }

  /**
   * Appends a mention to this TextBuilder.
   *
   * @param text    the text of the mention
   * @param mention the id of the mentioned user
   * @return this instance
   * @deprecated use this method only if you can't
   *             obtain the user relative to its id
   */
  @Deprecated
  public TextBuilder appendMention(String text, long mention) {
    return appendLink(text, TextEntity.TEXT_MENTION_LINK + mention);
  }

  /**
   * Returns the length of the text.
   *
   * @return the length of the text
   */
  public int length() {
    return builder.length();
  }

  /**
   * Sets the length of the text.
   *
   * @param length the new length
   * @throws StringIndexOutOfBoundsException if the {@code newLength} argument is negative.
   */
  public void setLength(int length) {
    if (length < 0)
      throw new StringIndexOutOfBoundsException(length);

    builder.setLength(length);

    for (int i = entities.size() - 1; i >= 0; i--) {
      MessageEntity entity = entities.get(i);
      if (entity.getOffset() > length) {
        entities.remove(entity);
      } else if (entity.getOffset() + entity.getLength() > length) {
        entities.remove(entity);
        int newLength = length - entity.getOffset();
        entities.add(new MessageEntity(
            entity.getType(),
            entity.getOffset(),
            newLength,
            entity.getUrl().orElse(null),
            entity.getUser().orElse(null)
        ));
      } else {
        break;
      }
    }
  }

  /**
   * Builds the Text.
   *
   * @return the created text
   */
  public Text build() {
    return new Text(builder.toString(), entities.toArray(new MessageEntity[entities.size()]));
  }

}
