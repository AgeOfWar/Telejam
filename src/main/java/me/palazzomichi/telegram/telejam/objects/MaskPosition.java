package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

/**
 * This object describes the position on faces
 * where a mask should be placed by default.
 *
 * @author Michi Palazzo
 */
public class MaskPosition implements TelegramObject {

  static final String POINT_FIELD = "point";
  static final String X_SHIFT_FIELD = "x_shift";
  static final String Y_SHIFT_FIELD = "y_shift";
  static final String SCALE_FIELD = "scale";

  /**
   * The part of the face relative to which the mask should be placed.
   * One of "forehead", "eyes", "mouth", or "chin".
   */
  @SerializedName(POINT_FIELD)
  private Point point;

  /**
   * Shift by X-axis measured in widths of the mask scaled to the face size,
   * from left to right. For example, choosing -1.0 will place mask just
   * to the left of the default mask position.
   */
  @SerializedName(X_SHIFT_FIELD)
  private float xShift;

  /**
   * Shift by Y-axis measured in heights of the mask scaled to the face size,
   * from top to bottom. For example, 1.0 will place the mask just below the
   * default mask position.
   */
  @SerializedName(Y_SHIFT_FIELD)
  private float yShift;

  /**
   * Mask scaling coefficient. For example, 2.0 means double size.
   */
  @SerializedName(SCALE_FIELD)
  private float scale = 1f;


  public MaskPosition(Point point, float xShift, float yShift, float scale) {
    this.point = point;
    this.xShift = xShift;
    this.yShift = yShift;
    this.scale = scale;
  }


  /**
   * Getter for property {@link #point}.
   *
   * @return value for property {@link #point}
   */
  public Point getPoint() {
    return point;
  }

  /**
   * Getter for property {@link #xShift}.
   *
   * @return value for property {@link #xShift}
   */
  public float getShiftX() {
    return xShift;
  }

  /**
   * Getter for property {@link #yShift}.
   *
   * @return value for property {@link #yShift}
   */
  public float getShiftY() {
    return yShift;
  }

  /**
   * Getter for property {@link #scale}.
   *
   * @return value for property {@link #scale}
   */
  public float getScale() {
    return scale;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof MaskPosition)) {
      return false;
    }
    
    MaskPosition maskPosition = (MaskPosition) obj;
    return point == maskPosition.point &&
        xShift == maskPosition.xShift &&
        yShift == maskPosition.yShift &&
        scale == maskPosition.scale;
  }
  
  @Override
  public int hashCode() {
    int result = 1;
    result = 37 * result + point.hashCode();
    result = 37 * result + Float.hashCode(xShift);
    result = 37 * result + Float.hashCode(yShift);
    result = 37 * result + Float.hashCode(scale);
    return result;
  }
  
  public enum Point {
    @SerializedName("forehead") FOREHEAD,
    @SerializedName("eyes") EYES,
    @SerializedName("mouth") MOUTH,
    @SerializedName("chin") CHIN
  }

}
