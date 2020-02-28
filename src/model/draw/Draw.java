package model.draw;

import dto.image.Image;
import enums.FlagEnum;

/**
 * Interface describing the drawing operations supported by this model implementation.
 */
public interface Draw {

  /**
   * Method supporting drawing a rainbow, given the dimensions.
   *
   * @param width horizontal dimension of the pattern.
   * @param height vertical dimension of the pattern.
   * @param stripeSize size of the stripe. It can be width or height depending on the
   *         orientation.
   * @param orientation 'H' for horizontal and 'V' for vertical.
   * @param repeat true if repetition should be for extra space, false otherwise.
   * @return image representation of the rainbow.
   */
  Image drawRainbow(int width, int height, int stripeSize, char orientation, boolean repeat);

  /**
   * Method supporting drawing a checkerboard pattern, given the dimensions.
   *
   * @param outerSquareSide size of the board.
   * @param innerSquareSide size of the square.
   * @return image representation of the checkerboard.
   */
  Image drawCheckerBoard(int outerSquareSide, int innerSquareSide);

  /**
   * Model supporting drawing a flag, given the country name.
   *
   * @param countryName name of the country.
   * @param requiredWidth required width of the flag, in pixels.
   * @return image representation of the flag.
   */
  Image drawFlag(FlagEnum countryName, int requiredWidth);
}
