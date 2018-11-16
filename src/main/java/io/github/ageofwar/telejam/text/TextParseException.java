package io.github.ageofwar.telejam.text;

/**
 * Unchecked exception thrown when an error
 * occurs while parsing a string.
 *
 * @author Michi Palazzo
 */
public class TextParseException extends RuntimeException {

  public TextParseException(String message) {
    super(message);
  }

}
