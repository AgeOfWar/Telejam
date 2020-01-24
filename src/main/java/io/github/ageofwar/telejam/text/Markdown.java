package io.github.ageofwar.telejam.text;

import io.github.ageofwar.telejam.messages.MessageEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MarkdownV2 text format.
 *
 * @author Michi Palazzo
 */
public class Markdown implements TextFormat {
  
  public static final Markdown INSTANCE = new Markdown();
  
  private Markdown() {
  }
  
  public static String escape(String text) {
    return text.replace("\\", "\\\\")
        .replaceAll("[*_~\\[\\]()`]", "\\\\$0");
  }
  
  public static String unescape(String text) {
    return text.replaceAll("\\\\([*_~\\[\\]()`])", "$1")
        .replace("\\\\", "\\");
  }
  
  @Override
  public Text readText(Reader reader) throws IOException, TextParseException {
    return readText(reader, null);
  }
  
  private Text readText(Reader reader, MessageEntity.Type type) throws IOException, TextParseException {
    StringBuilder builder = new StringBuilder();
    List<MessageEntity> entities = new ArrayList<>();
    int codePoint;
    while ((codePoint = reader.read()) >= 0) {
      if (codePoint == '*') {
        if (type == MessageEntity.Type.BOLD) break;
        readTextEntities(reader, MessageEntity.Type.BOLD, builder, entities);
      } else if (codePoint == '_') {
        reader.mark(1);
        codePoint = reader.read();
        if (codePoint == '_') {
          if (type == MessageEntity.Type.UNDERLINE) break;
          readTextEntities(reader, MessageEntity.Type.UNDERLINE, builder, entities);
        } else {
          reader.reset();
          if (type == MessageEntity.Type.ITALIC) break;
          readTextEntities(reader, MessageEntity.Type.ITALIC, builder, entities);
        }
      } else if (codePoint == '~') {
        if (type == MessageEntity.Type.STRIKETHROUGH) break;
        readTextEntities(reader, MessageEntity.Type.STRIKETHROUGH, builder, entities);
      } else if (codePoint == '[') {
        int offset = builder.length();
        while ((codePoint = reader.read()) != ']') {
          if (codePoint < 0) throw new TextParseException("Incomplete " + MessageEntity.Type.LINK);
          if (codePoint == '\\') {
            codePoint = reader.read();
            switch (codePoint) {
              case ']':
              case '\\':
                builder.append((char) codePoint);
                break;
              default:
                throw new TextParseException("Illegal escape");
            }
          } else {
            builder.append((char) codePoint);
          }
        }
        if (reader.read() != '(') throw new TextParseException("Incomplete " + MessageEntity.Type.LINK);
        StringBuilder url = new StringBuilder();
        while ((codePoint = reader.read()) != ')') {
          if (codePoint < 0) throw new TextParseException("Unclosed " + MessageEntity.Type.LINK);
          if (codePoint == '\\') {
            codePoint = reader.read();
            switch (codePoint) {
              case ')':
              case '\\':
                url.append((char) codePoint);
                break;
              default:
                throw new TextParseException("Illegal escape");
            }
          } else {
            url.append((char) codePoint);
          }
        }
        entities.add(new MessageEntity(MessageEntity.Type.LINK, offset, builder.length() - offset, url.toString(), null, null));
      } else if (codePoint == ']') {
        throw new TextParseException("Unexpected ']'");
      } else if (codePoint == '`') {
        reader.mark(1);
        codePoint = reader.read();
        if (codePoint == '`') {
          reader.mark(1);
          codePoint = reader.read();
          if (codePoint == '`') {
            int offset = builder.length();
            while ((codePoint = reader.read()) != '`') {
              if (codePoint < 0) throw new TextParseException("Unclosed " + MessageEntity.Type.CODE_BLOCK);
              if (codePoint == '\\') {
                codePoint = reader.read();
                switch (codePoint) {
                  case '`':
                  case '\\':
                    builder.append((char) codePoint);
                    break;
                  default:
                    throw new TextParseException("Illegal escape");
                }
              } else {
                builder.append((char) codePoint);
              }
            }
            if (reader.read() != '`') throw new TextParseException("Unclosed " + MessageEntity.Type.CODE_BLOCK);
            if (reader.read() != '`') throw new TextParseException("Unclosed " + MessageEntity.Type.CODE_BLOCK);
            entities.add(new MessageEntity(MessageEntity.Type.CODE_BLOCK, offset, builder.length() - offset));
          } else {
            reader.reset();
          }
        } else {
          reader.reset();
          int offset = builder.length();
          while ((codePoint = reader.read()) != '`') {
            if (codePoint < 0) throw new TextParseException("Unclosed " + MessageEntity.Type.CODE);
            if (codePoint == '\\') {
              codePoint = reader.read();
              switch (codePoint) {
                case '`':
                case '\\':
                  builder.append((char) codePoint);
                  break;
                default:
                  throw new TextParseException("Illegal escape");
              }
            } else {
              builder.append((char) codePoint);
            }
          }
          entities.add(new MessageEntity(MessageEntity.Type.CODE, offset, builder.length() - offset));
        }
      } else if (codePoint == '\\') {
        codePoint = reader.read();
        switch (codePoint) {
          case '*':
          case '_':
          case '~':
          case '[':
          case ']':
          case '(':
          case ')':
          case '`':
          case '\\':
            builder.append((char) codePoint);
            break;
          default:
            throw new TextParseException("Illegal escape");
        }
      } else {
        builder.append((char) codePoint);
      }
    }
    
    return new Text(builder.toString(), entities.toArray(new MessageEntity[0]));
  }
  
  private void readTextEntities(Reader reader, MessageEntity.Type type, StringBuilder builder, List<MessageEntity> entities) throws IOException {
    int offset = builder.length();
    Text entity = readText(reader, type);
    builder.append(entity);
    entities.add(new MessageEntity(type, offset, entity.length()));
    for (MessageEntity messageEntity : entity.getEntities()) {
      entities.add(messageEntity.move(offset + messageEntity.getOffset(), messageEntity.getLength()));
    }
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
        writer.write("*");
        break;
      case ITALIC:
        writer.write("_");
        break;
      case UNDERLINE:
        writer.write("__");
        break;
      case STRIKETHROUGH:
        writer.write("~");
        break;
      case CODE:
        writer.write("`");
        break;
      case CODE_BLOCK:
        writer.write("```");
        break;
      case LINK:
      case TEXT_MENTION:
        writer.write("[");
        break;
    }
  }
  
  private void endEntity(Token token, Writer writer) throws IOException {
    switch (token.getType()) {
      case BOLD:
        writer.write("*");
        break;
      case ITALIC:
        writer.write("_\r");
        break;
      case UNDERLINE:
        writer.write("__");
        break;
      case STRIKETHROUGH:
        writer.write("~");
        break;
      case CODE:
        writer.write("`");
        break;
      case CODE_BLOCK:
        writer.write("```");
        break;
      case LINK:
        writer.write("](" + escape(token.getUrl()) + ")");
        break;
      case TEXT_MENTION:
        writer.write("](tg://user?id=" + token.getUser().getId() + ")");
        break;
    }
  }
  
}