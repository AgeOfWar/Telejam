package io.github.ageofwar.telejam.json;

/**
 * This exception is raised when {@link Json} attempts to read (or write) a
 * malformed JSON element.
 */
public class JsonSyntaxException extends RuntimeException {
  
  public JsonSyntaxException(String msg) {
    super(msg);
  }
  
  public JsonSyntaxException(String msg, Throwable cause) {
    super(msg, cause);
  }
  
  public JsonSyntaxException(Throwable cause) {
    super(cause);
  }
  
}
