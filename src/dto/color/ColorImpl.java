package dto.color;

/**
 * Concrete implementation of {@link BaseColor} class. This class stores pixel values in sRGB
 * format. It provides its components in RGBA format.
 */
public class ColorImpl implements BaseColor {
  private int red;
  private int green;
  private int blue;
  private int alpha;

  /**
   * Constructor to create a pixel.
   *
   * @param components should have 4 channels in RGBA format.
   */
  public ColorImpl(int[] components) {
    if (components.length != 4) {
      throw new IllegalArgumentException("Number of color components insufficient!");
    }

    this.red = components[0];
    this.green = components[1];
    this.blue = components[2];
    this.alpha = components[3];

    if (isValueOutOfRange(red) || isValueOutOfRange(green) || isValueOutOfRange(blue)
            || isValueOutOfRange(alpha)) {
      throw new IllegalArgumentException("Input value not in range");
    }
  }

  @Override
  public BaseColor createColor(int[] colArray) {
    for (int i = 0; i < colArray.length; i++) {
      colArray[i] = clampIntValue(colArray[i]);
    }

    return new ColorImpl(colArray);
  }

  @Override
  public BaseColor createColorWithoutAlpha(int[] components) {
    if (components.length != 3) {
      throw new IllegalArgumentException("Need to provide exactly 3 components");
    }

    return createColor(new int[]{components[0], components[1], components[2], alpha});
  }

  @Override
  public int[] getComponents() {
    return new int[]{red, green, blue, alpha};
  }

  @Override
  public int getNumComponents() {
    return 3;
  }

  @Override
  public float[] convertToColorType(int colorType) {
    // TODO implement later
    return null;
  }

  private boolean isValueOutOfRange(int value) {
    return value < 0 || value > 255;
  }

  private int clampIntValue(int value) {
    if (value < 0) {
      return 0;
    } else {
      return Math.min(value, 255);
    }
  }
}
