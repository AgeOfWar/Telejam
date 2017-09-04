package me.palazzomichi.telegram.telejam.util.text;

import java.io.IOException;
import java.io.StringReader;

/**
 * Markdown text parser.
 *
 * @author Michi Palazzo
 */
final class MarkdownTextParser implements TextParser {

  public static final MarkdownTextParser INSTANCE = new MarkdownTextParser();

  private MarkdownTextParser() {
  }

  @Override
  public Text readText(String s) {
    try (StringReader reader = new StringReader(s)) {
      TextBuilder builder = new TextBuilder();

      int c;
      while ((c = reader.read()) >= 0) {
        StringBuilder sb = new StringBuilder();
        cs:
        switch (c) {
          case '*':
            reader.mark(Integer.MAX_VALUE);
            while ((c = reader.read()) != '*') {
              if (c <= 0) {
                builder.append("*");
                reader.reset();
                break cs;
              }
              sb.append((char) c);
            }
            builder.appendBold(sb.toString());
            break;

          case '_':
            reader.mark(Integer.MAX_VALUE);
            while ((c = reader.read()) != '_') {
              if (c <= 0) {
                builder.append("_");
                reader.reset();
                break cs;
              }
              sb.append((char) c);
            }
            builder.appendItalic(sb.toString());
            break;

          case '`':
            reader.mark(Integer.MAX_VALUE);
            if ((c = reader.read()) == '`') {
              if ((c = reader.read()) == '`') {
                while ((c = reader.read()) != '`' || (c = reader.read()) != '`' || (c = reader.read()) != '`') {
                  if (c <= 0) {
                    builder.append("`");
                    reader.reset();
                    break cs;
                  }
                  sb.append((char) c);
                }
                builder.appendCodeBlock(sb.toString());
              } else {
                reader.reset();
                reader.skip(1);
              }
            } else {
              sb.append((char) c);
              while ((c = reader.read()) != '`') {
                if (c <= 0) {
                  builder.append("`");
                  reader.reset();
                  break cs;
                }
                sb.append((char) c);
              }
              builder.appendCode(sb.toString());
            }
            break;

          case '[':
            reader.mark(Integer.MAX_VALUE);
            while ((c = reader.read()) != ']') {
              if (c <= 0) {
                builder.append("[");
                reader.reset();
                break cs;
              }
              sb.append((char) c);
            }
            String text = sb.toString();
            sb.setLength(0);
            if ((c = reader.read()) != '(') {
              builder.append("(");
              reader.reset();
              break;
            }
            while ((c = reader.read()) != ')') {
              if (c <= 0) {
                builder.append("(");
                reader.reset();
                break cs;
              }
              sb.append((char) c);
            }
            builder.appendLink(text, sb.toString());
            break;

          default:
            builder.append(String.valueOf((char) c));
        }
      }
      return builder.build();
    } catch (IOException e) {
      throw new AssertionError();
    }
  }

}
