package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import me.palazzomichi.telegram.telejam.objects.MaskPosition;
import me.palazzomichi.telegram.telejam.objects.User;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Use this method to create new sticker set owned by a user.
 * The bot will be able to edit the created sticker set.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class CreateNewStickerSet implements TelegramMethod<Boolean> {

  public static final String NAME = "createNewStickerSet";

  static final String USER_ID_FIELD = "user_id";
  static final String NAME_FIELD = "name";
  static final String TITLE_FIELD = "title";
  static final String STICKER_FIELD = "png_sticker";
  static final String EMOJIS_FIELD = "emojis";
  static final String CONTAINS_MASKS_FIELD = "contains_masks";
  static final String MASK_POSITION_FIELD = "mask_position";

  /**
   * User identifier of created sticker set owner.
   */
  private Long userId;

  /**
   * Short name of sticker set, to be used in <code>t.me/addstickers/</code> URLs (e.g., animals).
   * Can contain only english letters, digits and underscores.
   * Must begin with a letter, can't contain consecutive underscores and must
   * end in <code>"_by_&lt;bot username&gt;"</code>.
   * <code>&lt;bot_username&gt;</code> is case insensitive.
   * 1-64 characters.
   */
  private String name;

  /**
   * Sticker set title, 1-64 characters.
   */
  private String title;

  /**
   * Png image with the sticker, must be up to 512 kilobytes in size,
   * dimensions must not exceed 512px, and either width or height must be
   * exactly 512px. Pass a file_id as a String to send a file that already
   * exists on the Telegram servers, pass an HTTP URL as a String for
   * Telegram to get a file from the Internet, or upload a new one using multipart/form-data.
   */
  private String sticker;

  /**
   * One or more emoji corresponding to the sticker.
   */
  private String emojis;

  /**
   * Pass True, if a set of mask stickers should be created.
   */
  private Boolean containsMasks;

  /**
   * Point where the mask should be placed on faces.
   */
  private MaskPosition maskPosition;

  /**
   * True if the sticker is not present in Telegram servers.
   */
  private boolean newSticker;


  public CreateNewStickerSet user(long userId) {
    this.userId = userId;
    return this;
  }

  public CreateNewStickerSet user(User user) {
    this.userId = user.getId();
    return this;
  }

  public CreateNewStickerSet name(String name) {
    this.name = name;
    return this;
  }

  public CreateNewStickerSet title(String title) {
    this.title = title;
    return this;
  }

  public CreateNewStickerSet sticker(String sticker) {
    this.newSticker = false;
    this.sticker = sticker;
    return this;
  }

  public CreateNewStickerSet sticker(File sticker) {
    this.newSticker = true;
    this.sticker = sticker.getPath();
    return this;
  }

  public CreateNewStickerSet sticker(Path sticker) {
    this.newSticker = true;
    this.sticker = sticker.toString();
    return this;
  }

  public CreateNewStickerSet emojis(String emojis) {
    this.emojis = emojis;
    return this;
  }

  public CreateNewStickerSet containsMasks(Boolean containsMasks) {
    this.containsMasks = containsMasks;
    return this;
  }

  public CreateNewStickerSet containsMask() {
    this.containsMasks = true;
    return this;
  }

  public CreateNewStickerSet maskPosition(MaskPosition maskPosition) {
    this.maskPosition = maskPosition;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Boolean> getReturnType(JsonElement response) {
    return Boolean.class;
  }

  @Override
  public HttpEntity getHttpEntity() {
    if (newSticker) {
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();

      if (userId != null)
        builder.addTextBody(USER_ID_FIELD, userId.toString());

      if (name != null)
        builder.addTextBody(NAME, name);

      if (title != null)
        builder.addTextBody(TITLE_FIELD, title);

      if (sticker != null)
        builder.addBinaryBody(STICKER_FIELD, new File(sticker));

      if (emojis != null)
        builder.addTextBody(EMOJIS_FIELD, emojis);

      if (containsMasks != null)
        builder.addTextBody(CONTAINS_MASKS_FIELD, containsMasks.toString());

      if (maskPosition != null)
        builder.addTextBody(MASK_POSITION_FIELD, maskPosition.toJson());

      return builder.build();
    } else {
      List<NameValuePair> params = new ArrayList<>();

      if (userId != null)
        params.add(
            new BasicNameValuePair(USER_ID_FIELD, userId.toString())
        );

      if(name != null)
        params.add(
            new BasicNameValuePair(NAME_FIELD, name)
        );

      if(title != null)
        params.add(
            new BasicNameValuePair(TITLE_FIELD, title)
        );

      if(sticker != null)
        params.add(
            new BasicNameValuePair(STICKER_FIELD, sticker)
        );

      if (emojis != null)
        params.add(
            new BasicNameValuePair(EMOJIS_FIELD, emojis)
        );

      if (containsMasks != null)
        params.add(
            new BasicNameValuePair(CONTAINS_MASKS_FIELD, containsMasks.toString())
        );

      if (maskPosition != null)
        params.add(
            new BasicNameValuePair(MASK_POSITION_FIELD, maskPosition.toJson())
        );

      return EntityBuilder.create()
          .setParameters(params)
          .build();
    }
  }

}
