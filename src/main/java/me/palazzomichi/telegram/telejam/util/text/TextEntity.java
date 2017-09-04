package me.palazzomichi.telegram.telejam.util.text;

import java.io.InvalidObjectException;
import java.text.AttributedCharacterIterator;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines attribute keys that are used to identify text attributes.
 * These keys are used in {@code Text}.
 *
 * @author Michi Palazzo
 */
public class TextEntity extends AttributedCharacterIterator.Attribute {

  private static final Map<String, TextEntity> instanceMap = new HashMap<>(11);

  static final String TEXT_MENTION_LINK = "tg://user?id=";

  public static final TextEntity BOLD = new TextEntity("bold");
  public static final TextEntity ITALIC = new TextEntity("italic");
  public static final TextEntity CODE = new TextEntity("code");
  public static final TextEntity CODE_BLOCK = new TextEntity("pre");
  public static final TextEntity LINK = new TextEntity("text_link");
  public static final TextEntity MENTION = new TextEntity("mention");
  public static final TextEntity TEXT_MENTION = new TextEntity("text_mention");
  public static final TextEntity HASHTAG = new TextEntity("hashtag");
  public static final TextEntity BOT_COMMAND = new TextEntity("bot_command");
  public static final TextEntity URL = new TextEntity("url");
  public static final TextEntity EMAIL = new TextEntity("email");

  protected TextEntity(String name) {
    super(name);
    instanceMap.put(name, this);
  }

  public static TextEntity valueOf(String name) {
    return instanceMap.get(name);
  }

  @Override
  protected Object readResolve() throws InvalidObjectException {
    TextEntity instance = instanceMap.get(getName());
    if (instance != null) {
      return instance;
    } else {
      throw new InvalidObjectException("unknown attribute name");
    }
  }

  @Override
  public String getName() {
    return super.getName();
  }

}
