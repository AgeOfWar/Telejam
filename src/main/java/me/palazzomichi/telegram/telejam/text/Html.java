package me.palazzomichi.telegram.telejam.text;

import me.palazzomichi.telegram.telejam.objects.MessageEntity;

import java.io.*;

import static me.palazzomichi.telegram.telejam.text.TextBuilder.TEXT_MENTION_LINK;

/**
 * HTML text parser and writer.
 *
 * @author Michi Palazzo
 */
final class Html {
  
  public static Text readText(Reader reader) throws IOException, TextParseException {
    TextBuilder builder = new TextBuilder();
    StringBuilder sb = new StringBuilder();
  
    int c;
    while ((c = reader.read()) >= 0) {
      if (c == '<') {
        builder.append(unescape(sb.toString()));
        sb.setLength(0);
        String tag = getTag(reader);
        switch (tag) {
          case "b":
          case "strong":
            closeTag(reader, tag);
            while ((c = reader.read()) != '<')
              sb.append((char) c);
            endTag(reader, tag);
            builder.appendBold(unescape(sb.toString()));
            sb.setLength(0);
            break;
        
          case "i":
          case "em":
            closeTag(reader, tag);
            while ((c = reader.read()) != '<')
              sb.append((char) c);
            endTag(reader, tag);
            builder.appendItalic(unescape(sb.toString()));
            sb.setLength(0);
            break;
        
          case "a":
            while (Character.isWhitespace(c = reader.read()));
            String attr = "href";
            for (int i = 0; i < attr.length(); i++) {
              if (c != attr.charAt(i)) {
                throw new TextParseException("Expected \"" + attr + "\" attribute in tag \"" + tag + '"');
              }
              c = reader.read();
            }
            while (Character.isWhitespace(c)) {
              c = reader.read();
            }
            if (c != '=') {
              throw new TextParseException("Invalid tag \"" + tag + '"');
            }
            while (Character.isWhitespace(c = reader.read())) ;
            StringBuilder link = new StringBuilder();
            if (c == '"') {
              while ((c = reader.read()) != '"') {
                if (c <= 0) {
                  throw new TextParseException("Unclosed string literal");
                }
                link.append((char) c);
              }
            } else if (c == '\'') {
              while ((c = reader.read()) != '\'') {
                if (c <= 0) {
                  throw new TextParseException("Unclosed string literal");
                }
                link.append((char) c);
              }
            } else {
              while (c != '>' || Character.isWhitespace(c)) {
                if (c <= 0) {
                  break;
                }
                reader.mark(1);
                link.append((char) c);
                c = reader.read();
              }
              reader.reset();
            }
            closeTag(reader, tag);
            while ((c = reader.read()) != '<')
              sb.append((char) c);
            endTag(reader, tag);
            builder.appendLink(unescape(sb.toString()), unescape(link.toString()));
            sb.setLength(0);
            break;
        
          case "code":
            closeTag(reader, tag);
            while ((c = reader.read()) != '<')
              sb.append((char) c);
            endTag(reader, tag);
            builder.appendCode(unescape(sb.toString()));
            sb.setLength(0);
            break;
        
          case "pre":
            closeTag(reader, tag);
            while ((c = reader.read()) != '<')
              sb.append((char) c);
            endTag(reader, tag);
            builder.appendCodeBlock(unescape(sb.toString()));
            sb.setLength(0);
            break;
        
          default:
            throw new TextParseException("Unknown tag\"" + tag + '"');
        }
      } else {
        sb.append((char) c);
      }
    }
    builder.append(unescape(sb.toString()));
  
    return builder.build();
  }
  
  public static Text parseText(String s) throws TextParseException {
    try (StringReader reader = new StringReader(s)) {
      return readText(reader);
    } catch (IOException e) {
      throw new AssertionError();
    }
  }

  private static String getTag(Reader reader) throws IOException, TextParseException {
    StringBuilder builder = new StringBuilder();

    int c;
    reader.mark(1);
    while ((c = reader.read()) != '>' && !Character.isWhitespace(c)) {
      if (c <= 0) {
        throw new TextParseException("Unterminated tag");
      }
      reader.mark(1);
      builder.append((char) c);
    }
    reader.reset();

    return builder.toString();
  }

  private static void closeTag(Reader reader, String tag) throws IOException, TextParseException {
    int c;
    while (Character.isWhitespace(c = reader.read())) {
    }
    if (c != '>') {
      throw new TextParseException("Expected '>' after tag \"" + tag + '"');
    }
  }

  private static void endTag(Reader reader, String tag) throws IOException, TextParseException {
    int c;
    if (reader.read() != '/') {
      throw new TextParseException("Expected '/' in \"" + tag + "\" closing tag");
    }
    for (int i = 0; i < tag.length(); i++) {
      if (reader.read() != tag.charAt(i)) {
        throw new TextParseException("Unclosed tag \"" + tag + '"');
      }
    }
    if (reader.read() != '>') {
      throw new TextParseException("Expected '>' after closing tag \"" + tag + '"');
    }
  }

  private static String unescape(String s) {
    return s.replaceAll("&lt;", "<")
        .replaceAll("&gt;", ">")
        .replaceAll("&quot;", "\"")
        .replaceAll("&amp;", "&");
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
      throw new AssertionError();
    }
  }
  
  private static void beginEntity(MessageEntity entity, Writer writer) throws IOException {
    switch (entity.getType()) {
      case BOLD:
        writer.write("<b>");
        break;
      case ITALIC:
        writer.write("<i>");
        break;
      case CODE:
        writer.write("<code>");
        break;
      case CODE_BLOCK:
        writer.write("<pre>");
        break;
      case LINK:
        writer.write("<a href=\"" + escape(entity.getUrl().get()) + "\">");
        break;
      case TEXT_MENTION:
        writer.write("<a href=\"" + TEXT_MENTION_LINK + entity.getUser().get().getId() + "\">");
        break;
    }
  }
  
  private static void endEntity(MessageEntity entity, Writer writer) throws IOException {
    switch (entity.getType()) {
      case BOLD:
        writer.write("</b>");
        break;
      case ITALIC:
        writer.write("</i>");
        break;
      case CODE:
        writer.write("</code>");
        break;
      case CODE_BLOCK:
        writer.write("</pre>");
        break;
      case LINK:
      case TEXT_MENTION:
        writer.write("</a>");
        break;
    }
  }
  
  private static String escape(String s) {
    return s.replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll("\"", "&quot;");
  }
  
  private Html() {
    throw new AssertionError();
  }

}
