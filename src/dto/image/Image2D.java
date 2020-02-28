package dto.image;

/**
 * Concrete implementation of {@link Image} interface. This class stores data in a {@link Canvas}
 * and also stores its color coding, and type.
 */
public class Image2D implements Image {
  private Canvas data;
  private int imageColorType;
  private String imageType;

  /**
   * Constructor to create an image.
   *
   * @param data pixel level data
   * @param imageColorType color coding
   * @param imageType image type
   */
  public Image2D(Canvas data, int imageColorType, String imageType) {
    if (data.getHeight() == 0 || data.getWidth() == 0) {
      throw new IllegalArgumentException("Image data cannot be empty!");
    }

    this.data = data;
    this.imageColorType = imageColorType;
    this.imageType = imageType;
  }

  @Override
  public int getWidth() {
    return this.data.getWidth();
  }

  @Override
  public int getHeight() {
    return this.data.getHeight();
  }

  @Override
  public int getBaseImageColorType() {
    return this.imageColorType;
  }

  @Override
  public String getBaseImageType() {
    return imageType;
  }

  @Override
  public Canvas getData() {
    return this.data;
  }
}
