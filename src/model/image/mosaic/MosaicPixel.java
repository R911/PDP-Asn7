package model.image.mosaic;

import dto.color.BaseColor;

/**
 * Container class for holding the coordinates for a pixel in the image, as well as the color
 * value for that pixel. This class is used to construct tiles in a Mosaic image.
 */
class MosaicPixel {
  private int x;
  private int y;
  private BaseColor baseColor;

  /**
   * Instantiates a new MosaicPixel with the provided coordinates and color.
   *
   * @param x x coordinate of the pixel.
   * @param y y coordinate of the pixel.
   * @param baseColor color of the pixel.
   */
  MosaicPixel(int x, int y, BaseColor baseColor) {
    this.x = x;
    this.y = y;
    this.baseColor = baseColor;
  }

  /**
   * Returns the X coordinate of the pixel.
   *
   * @return x coordinate of the pixel.
   */
  int getX() {
    return x;
  }

  /**
   * Returns the y coordinate of the pixel.
   *
   * @return y coordinate of the pixel.
   */
  int getY() {
    return y;
  }

  /**
   * Returns the color value of the pixel.
   *
   * @return the color value of the pixel.
   */
  BaseColor getColor() {
    return baseColor;
  }

  /**
   * Sets the color of the pixel to the provided value.
   *
   * @param baseColor new color value.
   */
  public void setColor(BaseColor baseColor) {
    this.baseColor = baseColor;
  }
}
