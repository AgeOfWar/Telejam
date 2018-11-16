package io.github.ageofwar.telejam.text;

import io.github.ageofwar.telejam.messages.MessageEntity;

import java.io.*;

import static io.github.ageofwar.telejam.text.TextBuilder.TEXT_MENTION_LINK;

/**
 * Markdown text parser and writer.
 *
 * @author Michi Palazzo
 */
public final class Markdown {
  
  public static Text readText(Reader reader) throws IOException, TextParseException {
    TextBuilder builder = new TextBuilder();
  
    int c;
    while ((c = reader.read()) >= 0) {
      switch (c) {
        case '*':
          builder.appendBold(readUntil(reader, '*'));
          break;
        case '_':
          builder.appendItalic(readUntil(reader, '_'));
          break;
        case '[':
          String text = readUntil(reader, ']');
          expect(reader, '(');
          String link = readUntil(reader, ')');
          builder.appendLink(text, link);
          break;
        case ']':
          throw new TextParseException("Unexpected ']'");
        case '`':
          String code = readUntil(reader, '`');
          if (code.contains("\n")) {
            builder.appendCodeBlock(code);
          } else {
            builder.appendCode(code);
          }
          break;
        case '\\':
          int escape = reader.read();
          switch (escape) {
            case '*':
            case '_':
            case '[':
            case ']':
            case '(':
            case ')':
            case '`':
            case '\\':
              builder.append(String.valueOf((char) escape));
              break;
            default:
              throw new TextParseException("Illegal escape");
          }
          break;
        default:
          builder.append(String.valueOf((char) c));
      }
    }
    
    return builder.build();
  }
  
  public static Text parseText(String s) throws TextParseException {
    try (StringReader reader = new StringReader(s)) {
      return readText(reader);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }
  
  private static String readUntil(Reader reader, char delimiter) throws IOException {
    StringBuilder builder = new StringBuilder();
    int c;
    while ((c = reader.read()) != delimiter) {
      if (c <= 0) {
        throw new TextParseException("Expected '" + delimiter + "'");
      } else if (c == '\\') {
        int escape = reader.read();
        switch (escape) {
          case '*':
          case '_':
          case '[':
          case ']':
          case '(':
          case ')':
          case '`':
          case '\\':
            builder.appendCodePoint(escape);
            break;
          default:
            throw new TextParseException("Illegal escape");
        }
      } else {
        builder.appendCodePoint(c);
      }
    }
    return builder.toString();
  }
  
  private static void expect(Reader reader, char c) throws IOException {
    if (reader.read() != c) {
      throw new TextParseException("Expected '" + c + "'");
    }
  }
  
  public static void write(Text text, Writer writer) throws IOException {
    String s = text.toString();
    int offset = 0;
    for (MessageEntity entity : text.getEntities()) {
      writer.write(escape(s.substring(offset, entity.getOffset())));
      beginEntity(entity, writer);
      writer.write(escape(s.substring(entity.getOffset(), entity.getOffset() + entity.getLength())));
      endEntity(entity, writer);
      offset = entity.getOffset() + entity.getLength();
    }
    writer.write(escape(s.substring(offset)));
  }
  
  public static String toString(Text text) {
    try (StringWriter writer = new StringWriter()) {
      write(text, writer);
      return writer.toString();
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }
  
  private static void beginEntity(MessageEntity entity, Writer writer) throws IOException {
    switch (entity.getType()) {
      case BOLD:
        writer.write("*");
        break;
      case ITALIC:
        writer.write("_");
        break;
      case CODE:
        writer.write("`");
        break;
      case CODE_BLOCK:
        writer.write("`");
        break;
      case LINK:
      case TEXT_MENTION:
        writer.write("[");
        break;
    }
  }
  
  private static void endEntity(MessageEntity entity, Writer writer) throws IOException {
    switch (entity.getType()) {
      case BOLD:
        writer.write("*");
        break;
      case ITALIC:
        writer.write("_");
        break;
      case CODE:
        writer.write("`");
        break;
      case CODE_BLOCK:
        writer.write("`");
        break;
      case LINK:
        writer.write("](" + escape(entity.getUrl().get()) + ")");
        break;
      case TEXT_MENTION:
        writer.write("](" + TEXT_MENTION_LINK + entity.getUser().get().getId() + ")");
        break;
    }
  }
  
  private static String escape(String s) {
    return s.replace("*", "\\*")
        .replace("_", "\\_")
        .replace("[", "\\[")
        .replace("]", "\\]")
        .replace("(", "\\(")
        .replace(")", "\\)")
        .replace("`", "\\`")
        .replace("\\", "\\");
  }
  
  private Markdown() {
    throw new AssertionError();
  }
  
}
