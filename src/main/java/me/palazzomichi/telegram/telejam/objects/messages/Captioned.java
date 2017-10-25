package me.palazzomichi.telegram.telejam.objects.messages;

import me.palazzomichi.telegram.telejam.util.text.Text;

import java.util.Optional;

/**
 * Class representing a message that contains an optional caption.
 *
 * @author Michi Palazzo
 */
public interface Captioned {
  
  /**
   * Returns the caption of this message.
   *
   * @return the optional caption of this message.
   */
  Optional<Text> getCaption();
  
}
