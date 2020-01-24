package io.github.ageofwar.telejam.text;

import io.github.ageofwar.telejam.messages.MessageEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * HTML text format.
 *
 * @author Michi Palazzo
 */
public class Html implements TextFormat {
  
  public static final Html INSTANCE = new Html();
  
  private Html() {
  }
  
  private static void closeTag(Reader reader, String tag) throws IOException, TextParseException {
    int c;
    while (Character.isWhitespace(c = reader.read())) ;
    if (c != '>') {
      throw new TextParseException("Expected '>' after tag \"" + tag + '"');
    }
  }
  
  public static String escape(String s) {
    return s.replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll("\"", "&quot;");
  }
  
  public static String unescape(String s) {
    return s.replaceAll("&lt;", "<")
        .replaceAll("&gt;", ">")
        .replaceAll("&quot;", "\"")
        .replaceAll("&amp;", "&");
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
  
  @Override
  public Text readText(Reader reader) throws IOException, TextParseException {
    return readText(reader, null);
  }
  
  private Text readText(Reader reader, String tagContext) throws IOException, TextParseException {
    StringBuilder builder = new StringBuilder();
    List<MessageEntity> entities = new ArrayList<>();
    int codePoint;
    while ((codePoint = reader.read()) >= 0) {
      if (codePoint == '<') {
        reader.mark(1);
        codePoint = reader.read();
        if (codePoint == '/') {
          String tag = getTag(reader);
          closeTag(reader, tag);
          if (tag.equals(tagContext)) {
            break;
          } else {
            throw new TextParseException("Unmatched closing tag " + tag);
          }
        } else {
          reader.reset();
          String tag = getTag(reader);
          if (tag.equals("b") || tag.equals("strong")) {
            closeTag(reader, tag);
            readTextEntities(reader, MessageEntity.Type.BOLD, tag, builder, entities);
          } else if (tag.equals("i") || tag.equals("em")) {
            closeTag(reader, tag);
            readTextEntities(reader, MessageEntity.Type.ITALIC, tag, builder, entities);
          } else if (tag.equals("u") || tag.equals("ins")) {
            closeTag(reader, tag);
            readTextEntities(reader, MessageEntity.Type.UNDERLINE, tag, builder, entities);
          } else if (tag.equals("s") || tag.equals("strike") || tag.equals("del")) {
            closeTag(reader, tag);
            readTextEntities(reader, MessageEntity.Type.STRIKETHROUGH, tag, builder, entities);
          } else if (tag.equals("code")) {
            closeTag(reader, tag);
            readTextEntities(reader, MessageEntity.Type.CODE, tag, builder, entities);
          } else if (tag.equals("pre")) {
            closeTag(reader, tag);
            readTextEntities(reader, MessageEntity.Type.CODE_BLOCK, tag, builder, entities);
          } else if (tag.equals("a")) {
            int offset = builder.length();
            while (Character.isWhitespace(codePoint = reader.read())) ;
            String attr = "href";
            for (int i = 0; i < attr.length(); i++) {
              if (codePoint != attr.charAt(i)) {
                throw new TextParseException("Expected \"" + attr + "\" attribute in tag \"" + tag + '"');
              }
              codePoint = reader.read();
            }
            while (Character.isWhitespace(codePoint)) {
              codePoint = reader.read();
            }
            if (codePoint != '=') {
              throw new TextParseException("Invalid tag \"" + tag + '"');
            }
            while (Character.isWhitespace(codePoint = reader.read())) ;
            StringBuilder link = new StringBuilder();
            if (codePoint == '"') {
              while ((codePoint = reader.read()) != '"') {
                if (codePoint <= 0) {
                  throw new TextParseException("Unclosed string literal");
                }
                link.append((char) codePoint);
              }
            } else if (codePoint == '\'') {
              while ((codePoint = reader.read()) != '\'') {
                if (codePoint <= 0) {
                  throw new TextParseException("Unclosed string literal");
                }
                link.append((char) codePoint);
              }
            } else {
              while (codePoint != '>' || Character.isWhitespace(codePoint)) {
                if (codePoint <= 0) {
                  break;
                }
                reader.mark(1);
                link.append((char) codePoint);
                codePoint = reader.read();
              }
              reader.reset();
            }
            closeTag(reader, tag);
            while ((codePoint = reader.read()) != '<') {
              if (codePoint == '&') {
                codePoint = reader.read();
                if (codePoint == 'l' && reader.read() == 't' && reader.read() == ';') {
                  builder.append("<");
                } else if (codePoint == 'g' && reader.read() == 't' && reader.read() == ';') {
                  builder.append(">");
                } else if (codePoint == 'a' && reader.read() == 'm' && reader.read() == 'p' && reader.read() == ';') {
                  builder.append("&");
                } else if (codePoint == 'q' && reader.read() == 'u' && reader.read() == 'o' && reader.read() == 't' && reader.read() == ';') {
                  builder.append("\"");
                } else {
                  throw new TextParseException("Illegal escape");
                }
              } else {
                builder.append((char) codePoint);
              }
            }
            endTag(reader, tag);
            entities.add(new MessageEntity(MessageEntity.Type.LINK, offset, builder.length() - offset, unescape(link.toString()), null, null));
          }
        }
      } else if (codePoint == '&') {
        codePoint = reader.read();
        if (codePoint == 'l' && reader.read() == 't' && reader.read() == ';') {
          builder.append("<");
        } else if (codePoint == 'g' && reader.read() == 't' && reader.read() == ';') {
          builder.append(">");
        } else if (codePoint == 'a' && reader.read() == 'm' && reader.read() == 'p' && reader.read() == ';') {
          builder.append("&");
        } else if (codePoint == 'q' && reader.read() == 'u' && reader.read() == 'o' && reader.read() == 't' && reader.read() == ';') {
          builder.append("\"");
        } else {
          throw new TextParseException("Illegal escape");
        }
      } else {
        builder.append((char) codePoint);
      }
    }
    return new Text(builder.toString(), entities.toArray(new MessageEntity[0]));
  }
  
  private void readTextEntities(Reader reader, MessageEntity.Type type, String tag, StringBuilder builder, List<MessageEntity> entities) throws IOException {
    int offset = builder.length();
    Text entity = readText(reader, tag);
    builder.append(entity);
    entities.add(new MessageEntity(type, offset, entity.length()));
    for (MessageEntity messageEntity : entity.getEntities()) {
      entities.add(messageEntity.move(offset + messageEntity.getOffset(), messageEntity.getLength()));
    }
  }
  
  private String getTag(Reader reader) throws IOException, TextParseException {
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
  
  @Override
  public void write(Text text, Writer writer) throws IOException {
    String s = text.toString();
    int offset = 0;
    for (Token token : Token.fromEntities(text.getEntities())) {
      writer.write(escape(s.substring(offset, token.getIndex())));
      if (token.isStart()) {
        beginEntity(token, writer);
      } else {
        endEntity(token, writer);
      }
      offset = token.getIndex();
    }
    writer.write(escape(s.substring(offset)));
  }
  
  private void beginEntity(Token token, Writer writer) throws IOException {
    switch (token.getType()) {
      case BOLD:
        writer.write("<b>");
        break;
      case ITALIC:
        writer.write("<i>");
        break;
      case UNDERLINE:
        writer.write("<u>");
        break;
      case STRIKETHROUGH:
        writer.write("<s>");
        break;
      case CODE:
        writer.write("<code>");
        break;
      case CODE_BLOCK:
        writer.write("<pre>");
        break;
      case LINK:
        writer.write("<a href=\"" + escape(token.getUrl()) + "\">");
        break;
      case TEXT_MENTION:
        writer.write("<a href=\"tg://user?id=" + token.getUser().getId() + "\">");
        break;
    }
  }
  
  private void endEntity(Token token, Writer writer) throws IOException {
    switch (token.getType()) {
      case BOLD:
        writer.write("</b>");
        break;
      case ITALIC:
        writer.write("</i>");
        break;
      case UNDERLINE:
        writer.write("</u>");
        break;
      case STRIKETHROUGH:
        writer.write("</s>");
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
}
