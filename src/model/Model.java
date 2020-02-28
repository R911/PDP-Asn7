package model;

import dto.image.Image;
import enums.FlagEnum;

/**
 * This interface represents image processing and drawing operations.
 * Specifically, this interface supports the application of transforms, filters, as well as drawing
 * rainbows, flags and checkerboard patterns. The description of each of the operations provided
 * by this interface can be seen in the method description.
 */
public interface Model {
  /**
   * Operation for performing a simple linear color transform. Specifically, the color
   * transformation supported by this method is that of the simple additive type described here:
   * https://course.ccs.neu.edu/cs5010/assignment7.html.
   *
   * @param transformArr 2-d floating point array containing filter details to apply.
   * @param image image on which the transform should be applied.
   * @return resulting image from applying the color transform.
   */
  Image applyTransform(float[][] transformArr, Image image);

  /**
   * Operation for performing simple linear convolutional 2d filter on an image. More details on
   * the type of supported filters can be found here:
   * https://course.ccs.neu.edu/cs5010/assignment7.html.
   *
   * @param filterArr 2d filter to be applied.
   * @param image image on which the filter should be applied.
   * @return resulting image.
   */
  Image applyFilter(float[][] filterArr, Image image);

  /**
   * Operation for drawing a checkerboard pattern from scratch. A checkerboard is always square
   * in shape, and so are the units making up the square.
   *
   * @param boardSize size of the board.
   * @param squareSize size of the inner unit square.
   * @return image representing the checkerboard.
   */
  Image drawCheckerBoard(int boardSize, int squareSize);

  /**
   * Operation for drawing a rainbow pattern from scratch.
   *
   * @param width horizontal dimension of the pattern.
   * @param height vertical dimension of the pattern.
   * @param stripeSize size of the stripe. It can be width or height depending on the
   *         orientation.
   * @param orientation 'H' for horizontal and 'V' for vertical.
   * @param repeat true if repetition should be for extra space, false otherwise.
   * @return image representing the rainbow.
   */
  Image drawRainbow(int width, int height, int stripeSize, char orientation, boolean repeat);

  /**
   * Operation for drawing a flag of a supported country.
   *
   * @param countryName enum representing the country name.
   * @param flagWidth width of the flag. The other dimension is determined by the
   *         proportions of the flag.
   * @return image representing the flag drawn.
   */
  Image drawFlag(FlagEnum countryName, int flagWidth);
}
