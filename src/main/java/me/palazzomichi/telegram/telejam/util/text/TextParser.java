package me.palazzomichi.telegram.telejam.util.text;

/**
 * Interface representing a text reader.
 *
 * @author Michi Palazzo
 */
public interface TextParser {

  /**
   * Reads a text from a string.
   *
   * @param s the string
   * @return the read text
   * @throws TextParseException if an error occurs during the text parsing
   */
  Text readText(String s) throws TextParseException;

}
