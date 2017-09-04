package me.palazzomichi.telegram.telejam.util.text;

import me.palazzomichi.telegram.telejam.objects.User;

import java.io.IOException;
import java.io.Writer;
import java.text.AttributedCharacterIterator;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Markdown text writer.
 *
 * @author Michi Palazzo
 */
final class MarkdownTextWriter implements TextWriter {

  public static final MarkdownTextWriter INSTANCE = new MarkdownTextWriter();

  private MarkdownTextWriter() {
  }

  @Override
  public void write(Text text, Writer writer) throws IOException {
    AttributedCharacterIterator iterator = text.getIterator();

    Entry<Attribute, Object> lastAttribute = null;
    while (true) {
      if (iterator.getIndex() == iterator.getEndIndex()) {
        break;
      }

      Entry<Attribute, Object> entry = getAttribute(iterator);

      if (!Objects.equals(entry, lastAttribute)) {
        endEntity(lastAttribute, writer);
        beginEntity(entry, writer);
      }

      writer.write(iterator.current());

      lastAttribute = entry;
      iterator.next();
    }

    endEntity(lastAttribute, writer);
  }

  private void beginEntity(Entry<Attribute, Object> entry, Writer writer) throws IOException {
    if (entry == null) {
      return;
    }

    Attribute entity = entry.getKey();
    if (entity == TextEntity.BOLD) {
      writer.write('*');
    } else if (entity == TextEntity.ITALIC) {
      writer.write('_');
    } else if (entity == TextEntity.CODE) {
      writer.write('`');
    } else if (entity == TextEntity.CODE_BLOCK) {
      writer.write("```");
    } else if (entity == TextEntity.LINK || entity == TextEntity.TEXT_MENTION) {
      writer.write("[");
    }
  }

  private void endEntity(Entry<Attribute, Object> entry, Writer writer) throws IOException {
    if (entry == null) {
      return;
    }

    Attribute entity = entry.getKey();
    if (entity == TextEntity.BOLD) {
      writer.write('*');
    } else if (entity == TextEntity.ITALIC) {
      writer.write('_');
    } else if (entity == TextEntity.CODE) {
      writer.write('`');
    } else if (entity == TextEntity.CODE_BLOCK) {
      writer.write("```");
    } else if (entity == TextEntity.LINK) {
      writer.write("](" + entry.getValue() + ')');
    } else if (entity == TextEntity.TEXT_MENTION) {
      User user = (User) entry.getValue();
      writer.write("](" + TextEntity.TEXT_MENTION_LINK + user.getId() + ")");
    }
  }

  private Entry<Attribute, Object> getAttribute(AttributedCharacterIterator iterator) {
    Iterator<Entry<Attribute, Object>> it = iterator.getAttributes().entrySet().iterator();
    return it.hasNext() ? it.next() : null;
  }

}
