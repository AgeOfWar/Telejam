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
 * Use this method to add a new sticker to a set created by the bot.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class AddStickerToSet implements TelegramMethod<Boolean> {

  public static final String NAME = "addStickerToSet";

  static final String USER_ID_FIELD = "user_id";
  static final String NAME_FIELD = "name";
  static final String STICKER_FIELD = "png_sticker";
  static final String EMOJIS_FIELD = "emojis";
  static final String MASK_POSITION_FIELD = "mask_position";

  /**
   * User identifier of sticker set owner.
   */
  private Long userId;

  /**
   * Sticker set name.
   */
  private String name;

  /**
   * Png image with the sticker, must be up to 512 kilobytes in size,
   * dimensions must not exceed 512px, and either width or height must be exactly 512px.
   * Pass a file_id as a String to send a file that already exists on the Telegram servers,
   * pass an HTTP URL as a String for Telegram to get a file from the Internet,
   * or upload a new one using multipart/form-data.
   */
  private String sticker;

  /**
   * One or more emoji corresponding to the sticker.
   */
  private String emojis;

  /**
   * A JSON-serialized object for position where the mask should be placed on faces.
   */
  private MaskPosition maskPosition;

  /**
   * True if the sticker is not present in Telegram servers.
   */
  private boolean newSticker;


  public AddStickerToSet user(long userId) {
    this.userId = userId;
    return this;
  }

  public AddStickerToSet user(User user) {
    this.userId = user.getId();
    return this;
  }

  public AddStickerToSet name(String name) {
    this.name = name;
    return this;
  }

  public AddStickerToSet sticker(String sticker) {
    this.newSticker = false;
    this.sticker = sticker;
    return this;
  }

  public AddStickerToSet sticker(File sticker) {
    this.newSticker = true;
    this.sticker = sticker.getPath();
    return this;
  }

  public AddStickerToSet sticker(Path sticker) {
    this.newSticker = true;
    this.sticker = sticker.toString();
    return this;
  }

  public AddStickerToSet emojis(String emojis) {
    this.emojis = emojis;
    return this;
  }

  public AddStickerToSet maskPosition(MaskPosition maskPosition) {
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

      if (sticker != null)
        builder.addBinaryBody(STICKER_FIELD, new File(sticker));

      if (emojis != null)
        builder.addTextBody(EMOJIS_FIELD, emojis);

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

      if(sticker != null)
        params.add(
            new BasicNameValuePair(STICKER_FIELD, sticker)
        );

      if (emojis != null)
        params.add(
            new BasicNameValuePair(EMOJIS_FIELD, emojis)
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
