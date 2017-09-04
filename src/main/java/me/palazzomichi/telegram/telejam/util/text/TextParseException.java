package me.palazzomichi.telegram.telejam.util.text;

/**
 * Unchecked exception thrown when an error
 * occurs when parsing a string.
 *
 * @author Michi Palazzo
 */
public class TextParseException extends RuntimeException {

  public TextParseException(String message) {
    super(message);
  }

}
