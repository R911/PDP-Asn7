package model.image.dither;

import dto.image.Canvas;

/**
 * This interface represents a dither operation. The operation of breaking down an image that has
 * many colors into an image that is made of dots from just a few colors is known as dithering.
 */
public interface Dither {
  /**
   * This method diffuses the error calculated for a pixel to the nearby neighbours based upon
   * the size of the dither kernel.
   *
   * @param input canvas
   * @param row of pixel
   * @param column of pixel
   * @return Resultant canvas
   */
  Canvas applyDither(Canvas input, int row, int column);
}
