package io.github.ageofwar.telejam.text;

import io.github.ageofwar.telejam.messages.MessageEntity;
import io.github.ageofwar.telejam.users.User;

import java.util.ArrayList;
import java.util.List;

class Token {
  private final MessageEntity.Type type;
  private final boolean start;
  private final int index;
  private final String url;
  private final User user;
  
  public Token(MessageEntity.Type type, boolean start, int index, String url, User user) {
    this.type = type;
    this.start = start;
    this.index = index;
    this.url = url;
    this.user = user;
  }
  
  public static List<Token> fromEntities(List<MessageEntity> entities) {
    List<Token> tokens = new ArrayList<>();
    for (MessageEntity entity : entities) {
      tokens.add(new Token(entity.getType(), true, entity.getOffset(), entity.getUrl().orElse(null), entity.getUser().orElse(null)));
      tokens.add(new Token(entity.getType(), false, entity.getOffset() + entity.getLength(), entity.getUrl().orElse(null), entity.getUser().orElse(null)));
    }
    tokens.sort((t1, t2) -> t1.getIndex() - t2.getIndex());
    return tokens;
  }
  
  public MessageEntity.Type getType() {
    return type;
  }
  
  public boolean isStart() {
    return start;
  }
  
  public int getIndex() {
    return index;
  }
  
  public String getUrl() {
    return url;
  }
  
  public User getUser() {
    return user;
  }
}
