package controller;

/**
 * Interface representing all the image processing operations offered by the application.
 */
public interface Features {

  /**
   * Applies blur filter on the loaded image. Actions of the feature object if the image is not
   * loaded depends on the implementation.
   */
  void blur();

  /**
   * Applies sharpen filter on the loaded image. Actions of the feature object if the image is not
   * loaded depends on the implementation.
   */
  void sharpen();

  /**
   * Applies greyscale color transformation on the loaded image. Actions of the feature object if
   * the image is not loaded depends on the implementation.
   */
  void greyScale();

  /**
   * Applies sepia color transformation on the loaded image. Actions of the feature object if the
   * image is not loaded depends on the implementation.
   */
  void sepia();

  /**
   * Applies dither effect on the loaded image. Actions of the feature object if the image is not
   * loaded depends on the implementation.
   */
  void dither();

  /**
   * Applies mosaic effect on the loaded image. Actions of the feature object if the image is not
   * loaded depends on the implementation.
   *
   * @param seedCount number of seeds in the mosaic.
   */
  void mosaic(int seedCount);

  /**
   * Draws a checkerboard image given the dimensions of the user, and saves the resulting image
   * to the file path specified.
   *
   * @param boardSize size of the board.
   * @param squareSize size of each square on the board.
   * @param filePath path to the file where the image should be saved.
   */
  void checkerBoard(int boardSize, int squareSize, String filePath);

  /**
   * Draws a rainbow image given the inputs and saves the resulting image to the file path
   * specified.
   *
   * @param width width of the rainbow.
   * @param height height of the rainbow.
   * @param stripeSize size of an individual strip in the rainbow.
   * @param orientation orientation of the rainbow. Accepted values are 'H', 'h', 'V' or
   *         'v'.
   * @param repeat true if repetition of strips is allowed, false otherwise.
   * @param filepath path to the file where the image should be saved.
   */
  void rainbow(int width, int height, int stripeSize, char orientation, boolean repeat,
          String filepath);

  /**
   * Draws a flag of the specified country with the specified dimensions.
   *
   * @param country name of the country.
   * @param flagWidth width of the flag.
   * @param filePath path to the file where the image should be saved.
   */
  void flag(String country, int flagWidth, String filePath);

  /**
   * Loads an image given the path to the file. If the file is not found, the implementations may
   * throw exceptions or deal with the errors in some other way, which is dependent on the
   * implementation. The resulting image object is then set as the current image object.
   *
   * @param filePath path to the file where the image is located.
   */
  void loadImage(String filePath);

  /**
   * Save the current image to the file path specified.
   *
   * @param filePath path to the file where the image should be saved.
   */
  void saveImage(String filePath);
}
