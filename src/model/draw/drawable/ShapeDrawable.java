package model.draw.drawable;

import dto.color.BaseColor;

/**
 * Abstract class representing general 2D shapes that can be drawn onto a provided canvas.
 * All common aspects of a shape, i.e. color, dimensions and coordinates of the center are stored
 * by this class.
 */
public abstract class ShapeDrawable implements Drawable {
  protected BaseColor color;
  protected int width;
  protected int height;
  int centerX;
  int centerY;

  /**
   * Instantiates the common state of all simple drawables.
   *
   * @param centerX x coordinate of the shape origin.
   * @param centerY y coordinate of the shape origin.
   * @param color color of the shape on the canvas once drawn.
   */
  ShapeDrawable(int centerX, int centerY, BaseColor color) {
    this.centerX = centerX;
    this.centerY = centerY;
    this.color = color;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public void setWidth(int width) {
    if (width < 0) {
      throw new IllegalArgumentException("Width of a drawable cannot be negative!");
    }
    this.width = width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void setHeight(int height) {
    if (height < 0) {
      throw new IllegalArgumentException("Height of a drawable cannot be negative!");
    }
    this.height = height;
  }

  @Override
  public int getCenterX() {
    return this.centerX;
  }

  @Override
  public void setCenterX(int centerX) {
    if (centerX < 0) {
      throw new IllegalArgumentException("X coordinate of drawable origin cannot be negative!");
    }
    this.centerX = centerX;
  }

  @Override
  public int getCenterY() {
    return this.centerY;
  }

  @Override
  public void setCenterY(int centerY) {
    if (centerY < 0) {
      throw new IllegalArgumentException("Y coordinate of drawable origin cannot be negative!");
    }
    this.centerY = centerY;
  }
}
