package me.palazzomichi.telegram.telejam.text;

import me.palazzomichi.telegram.telejam.objects.User;

import java.util.Objects;

/**
 * Class representing a text mention.
 */
public class Mention {
  
  private final String text;
  private final User user;
  
  /**
   * Constructs a text mention.
   * @param text the text of the mention
   * @param user the mentioned user
   */
  public Mention(String text, User user) {
    this.text = text;
    this.user = user;
  }
  
  /**
   * Returns the text of the mention.
   */
  public String getText() {
    return text;
  }
  
  /**
   * Returns the mentioned user.
   */
  public User getUser() {
    return user;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Mention)) return false;
    Mention mention = (Mention) obj;
    return text.equals(mention.text) && user.equals(mention.user);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(text, user);
  }
  
}
