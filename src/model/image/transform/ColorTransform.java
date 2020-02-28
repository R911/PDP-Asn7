package model.image.transform;

import dto.color.BaseColor;

/**
 * Interface representing a simple 2D color transform. A color transformation modifies the color
 * of a pixel based on its own color. Consider a pixel with color (r,g,b). A color transformation
 * results in the new color of this pixel to be (r’,g’,b’) such that each of them are dependent only
 * on the values (r,g,b).
 */
public interface ColorTransform {
  /**
   * Method for taking in a pixel color value and performing the color transformation on it.
   *
   * @param input the original color of the pixel being transformed.
   * @return new color value for the pixel.
   */
  BaseColor transform(BaseColor input);
}
