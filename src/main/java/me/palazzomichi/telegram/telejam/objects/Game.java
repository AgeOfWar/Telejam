package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.util.text.Text;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a game.
 *
 * @author Michi Palazzo
 */
public class Game implements TelegramObject {

  static final String TITLE_FIELD = "title";
  static final String DESCRIPTION_FIELD = "description";
  static final String PHOTO_FIELD = "photo";
  static final String TEXT_FIELD = "text";
  static final String TEXT_ENTITIES_FIELD = "text_entities";
  static final String ANIMATION_FIELD = "animation";

  /**
   * Title of the game.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Description of the game.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private String description;

  /**
   * Photo that will be displayed in the game message in chats.
   */
  @SerializedName(PHOTO_FIELD)
  private PhotoSize[] photo;

  /**
   * Brief description of the game or high scores included in the game message.
   * Can be automatically edited to include current high scores for the game when
   * the bot calls setGameScore, or manually edited using editMessageText.
   * 0-4096 characters.
   */
  @SerializedName(TEXT_FIELD)
  private String text;

  /**
   * Special entities that appear in text, such as usernames, URLs, bot commands, etc.
   */
  @SerializedName(TEXT_ENTITIES_FIELD)
  private MessageEntity[] textEntities;

  /**
   * Animation that will be displayed in the game message in chats.
   */
  @SerializedName(ANIMATION_FIELD)
  private Animation animation;


  public Game(String title,
              String description,
              PhotoSize[] photo,
              String text,
              MessageEntity[] textEntities,
              Animation animation) {
    this.title = Objects.requireNonNull(title);
    this.description = Objects.requireNonNull(description);
    this.photo = Objects.requireNonNull(photo);
    this.text = text;
    this.textEntities = textEntities;
    this.animation = animation;
  }

  public Game(String title, String description, PhotoSize[] photo) {
    this.title = Objects.requireNonNull(title);
    this.description = Objects.requireNonNull(description);
    this.photo = Objects.requireNonNull(photo);
  }


  /**
   * Getter for property {@link #title}.
   *
   * @return value for property {@link #title}
   */
  public String getTitle() {
    return title;
  }

  /**
   * Getter for property {@link #description}.
   *
   * @return value for property {@link #description}
   */
  public String getDescription() {
    return description;
  }

  /**
   * Getter for property {@link #photo}.
   *
   * @return value for property {@link #photo}
   */
  public PhotoSize[] getPhoto() {
    return photo;
  }

  /**
   * Returns the text of the message.
   *
   * @return the optional text of the message
   */
  public Optional<Text> getText() {
    if (text == null) {
      return Optional.empty();
    }
    
    return Optional.of(
        new Text(text, textEntities == null ? new MessageEntity[0] : textEntities)
    );
  }

  /**
   * Getter for property {@link #animation}.
   *
   * @return optional value for property {@link #animation}
   */
  public Optional<Animation> getAnimation() {
    return Optional.ofNullable(animation);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof Game)) {
      return false;
    }
    
    Game game = (Game) obj;
    return title.equals(game.getTitle()) &&
        description.equals(game.getDescription()) &&
        Arrays.equals(photo, game.getPhoto()) &&
        Objects.equals(text, game.text) &&
        Arrays.equals(textEntities, game.textEntities) &&
        Objects.equals(animation, game.animation);
  }
  
  @Override
  public int hashCode() {
    int result = 1;
    result = 37 * result + title.hashCode();
    result = 37 * result + description.hashCode();
    result = 37 * result + Arrays.hashCode(photo);
    result = 37 * result + Objects.hashCode(text);
    result = 37 * result + Arrays.hashCode(textEntities);
    result = 37 * result + animation.hashCode();
    return result;
  }

}
