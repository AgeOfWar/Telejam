package io.github.ageofwar.telejam.text;

import java.io.*;

/**
 * Text parser and writer.
 */
public interface TextFormat {
  
  Text readText(Reader reader) throws IOException, TextParseException;
  
  void write(Text text, Writer writer) throws IOException;
  
  default Text parseText(String s) throws TextParseException {
    try (StringReader reader = new StringReader(s)) {
      return readText(reader);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }
  
  default String toString(Text text) {
    try (StringWriter writer = new StringWriter()) {
      write(text, writer);
      return writer.toString();
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }
  
}
