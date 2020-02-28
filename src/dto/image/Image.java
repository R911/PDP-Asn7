package dto.image;

/**
 * This interface represents an image and all the methods supported by it.
 */
public interface Image {
  /**
   * This method provides width of the image.
   *
   * @return width
   */
  int getWidth();

  /**
   * This method provides height of the image.
   *
   * @return height of the image.
   */
  int getHeight();

  /**
   * This method provides the color coding of the image.
   *
   * @return color coding
   */
  int getBaseImageColorType();

  /**
   * This method provides the format of the image.
   *
   * @return image format
   */
  String getBaseImageType();

  /**
   * This method provides the pixel level data stored in the image.
   *
   * @return pixel level data
   */
  Canvas getData();
}
