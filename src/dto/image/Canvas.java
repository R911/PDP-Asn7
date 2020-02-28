package dto.image;

import dto.color.BaseColor;

/**
 * This class stores image data in a 2d array of pixel colors.
 */
public class Canvas {
  private BaseColor[][] data;
  private int width;
  private int height;

  /**
   * Constructor to create an image.
   *
   * @param height of image
   * @param width of image
   */
  public Canvas(int height, int width) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Cannot create canvas with non-positive dimensions");
    }

    this.data = new BaseColor[height][width];
    this.width = width;
    this.height = height;
  }

  /**
   * This method provides the width of image.
   *
   * @return width of image
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * This method provides height of image.
   *
   * @return height of image
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * This method sets value of an individual pixel.
   *
   * @param y position of pixel in 2d array.
   * @param x position of pixel in 2d array
   * @param pixelValue color of pixel
   * @throws ArrayIndexOutOfBoundsException for wrong pixel position.
   */
  public void setPixel(int y, int x, BaseColor pixelValue) throws ArrayIndexOutOfBoundsException {
    if (isWithinBounds(y, x)) {
      this.data[y][x] = pixelValue;
    } else {
      throw new ArrayIndexOutOfBoundsException("Provided indices are out of bounds!");
    }
  }

  /**
   * Method to check if user input are within bounds of the image.
   *
   * @param height of pixel
   * @param width of pixel
   * @return true/false
   */
  public boolean isWithinBounds(int height, int width) {
    return height >= 0 && width >= 0 && width < this.width && height < this.height;
  }

  /**
   * Pixel value at given index.
   *
   * @param height of pixel
   * @param width of pixel
   * @return pixel color
   * @throws ArrayIndexOutOfBoundsException for wrong position
   */
  public BaseColor getPixel(int height, int width) throws ArrayIndexOutOfBoundsException {
    if (isWithinBounds(height, width)) {
      return this.data[height][width];
    } else {
      throw new ArrayIndexOutOfBoundsException("Provided indices are out of bounds!");
    }
  }
}
