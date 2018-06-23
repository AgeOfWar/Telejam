package me.palazzomichi.telegram.telejam.text;

import java.util.Objects;

/**
 * Class representing a link.
 */
public class Link {
  
  private final String text;
  private final String url;
  
  /**
   * Constructs a link.
   *
   * @param text the text of the link
   * @param url  the url of the link
   */
  public Link(String text, String url) {
    this.text = Objects.requireNonNull(text);
    this.url = Objects.requireNonNull(url);
  }
  
  /**
   * Returns the text of the link.
   *
   * @return the text of the link
   */
  public String getText() {
    return text;
  }
  
  /**
   * Returns the url of the link.
   *
   * @return the url of the link
   */
  public String getUrl() {
    return url;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Link)) return false;
    Link link = (Link) obj;
    return text.equals(link.text) && url.equals(link.url);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(text, url);
  }
  
}
