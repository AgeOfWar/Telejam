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
 * HTML text writer.
 *
 * @author Michi Palazzo
 */
final class HtmlTextWriter implements TextWriter {

  public static final HtmlTextWriter INSTANCE = new HtmlTextWriter();

  private HtmlTextWriter() {
  }

  @Override
  public void write(Text text, Writer writer) throws IOException {
    AttributedCharacterIterator iterator = text.getIterator();
    StringBuilder builder = new StringBuilder();

    Entry<Attribute, Object> lastAttribute = null;
    while (true) {
      if (iterator.getIndex() == iterator.getEndIndex()) {
        break;
      }

      Entry<Attribute, Object> entry = getAttribute(iterator);

      if (!Objects.equals(entry, lastAttribute)) {
        writer.write(escape(builder.toString()));
        builder.setLength(0);
        endEntity(lastAttribute, writer);
        beginEntity(entry, writer);
      }

      builder.append(iterator.current());

      lastAttribute = entry;
      iterator.next();
    }
    writer.write(escape(builder.toString()));
    endEntity(lastAttribute, writer);
  }

  private void beginEntity(Entry<Attribute, Object> entry, Writer writer) throws IOException {
    if (entry == null) {
      return;
    }

    Attribute entity = entry.getKey();
    if (entity == TextEntity.BOLD) {
      writer.write("<b>");
    } else if (entity == TextEntity.ITALIC) {
      writer.write("<i>");
    } else if (entity == TextEntity.CODE) {
      writer.write("<code>");
    } else if (entity == TextEntity.CODE_BLOCK) {
      writer.write("<pre>");
    } else if (entity == TextEntity.LINK) {
      writer.write("<a href=\"" + escape(entry.getValue().toString()) + "\">");
    } else if (entity == TextEntity.TEXT_MENTION) {
      User user = (User) entry.getValue();
      writer.write("<a href=\"" + escape(TextEntity.TEXT_MENTION_LINK) + user.getId() + "\">");
    }
  }

  private void endEntity(Entry<Attribute, Object> entry, Writer writer) throws IOException {
    if (entry == null) {
      return;
    }

    Attribute entity = entry.getKey();
    if (entity == TextEntity.BOLD) {
      writer.write("</b>");
    } else if (entity == TextEntity.ITALIC) {
      writer.write("</i>");
    } else if (entity == TextEntity.CODE) {
      writer.write("</code>");
    } else if (entity == TextEntity.CODE_BLOCK) {
      writer.write("</pre>");
    } else if (entity == TextEntity.LINK || entity == TextEntity.TEXT_MENTION) {
      writer.write("</a>");
    }
  }

  private String escape(String s) {
    return s.replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll("\"", "&quot;");
  }

  private Entry<Attribute, Object> getAttribute(AttributedCharacterIterator iterator) {
    Iterator<Entry<Attribute, Object>> it = iterator.getAttributes().entrySet().iterator();
    return it.hasNext() ? it.next() : null;
  }

}
