package dto.color;

/**
 * This interface represents a pixel in an image. It provides various operations on its channels
 * (color and transparency).
 */
public interface BaseColor {

  /**
   * This method provides the channels stored in the pixel in an int array form.
   *
   * @return the channels in the color space.
   */
  int[] getComponents();

  /**
   * This method provides number of channels in a pixel.
   *
   * @return the number of channels in the color space.
   */
  int getNumComponents();

  /**
   * This method provides a pixel of its type.
   *
   * @param components for the pixel.
   * @return new pixel with input components
   */
  BaseColor createColor(int[] components);

  /**
   * This method creates pixel without alpha value.
   *
   * @param components without alpha value
   * @return new pixel with input components with alpha set to 255.
   */
  BaseColor createColorWithoutAlpha(int[] components);

  /**
   * This method provides pixel components in the input color model.
   *
   * @param colorType color model required.
   * @return components
   */
  float[] convertToColorType(int colorType);
}
