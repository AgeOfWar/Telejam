package me.palazzomichi.telegram.telejam.util.text;

import java.io.IOException;
import java.io.Writer;

/**
 * Interface representing a text writer.
 *
 * @author Michi Palazzo
 */
public interface TextWriter {

  /**
   * Writes a text in a writer.
   *
   * @param text   the text
   * @param writer the writer
   * @throws IOException if an I/O Exception occurs
   */
  void write(Text text, Writer writer) throws IOException;

}
