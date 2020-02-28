package model.draw.drawable;

import dto.image.Canvas;

/**
 * A Drawable is a general abstraction for "something that can be drawn".
 * Drawables are objects that can work draw themselves given a {@link Canvas} to draw over. This
 * gives us a set of objects that can be used in a variety of use-cases and can fully hide the
 * details of how they are drawn.
 */
public interface Drawable {
  /**
   * Instructs the Drawable to draw itself onto a canvas provided as the input.
   *
   * <p>Note that any dimension changes needed should be made before calling this method. If the
   * dimensions of the drawable haven't been set, it simply draws itself with the default (0) as
   * the dimensions.
   * </p>
   *
   * @param canvas the canvas onto which the drawable should draw itself.
   */
  void draw(Canvas canvas);

  /**
   * Returns the width of the drawable, i.e. the horizontal dimension of the drawable.
   *
   * @return width of the drawable
   */
  int getWidth();

  /**
   * Sets the width of the drawable, i.e. the horizontal dimension of the drawable.
   *
   * @param width width of the drawable
   */
  void setWidth(int width);

  /**
   * Returns the height of the drawable, i.e. the vertical dimension of the drawable.
   *
   * @return height of the drawable
   */
  int getHeight();

  /**
   * Sets the height of the drawable, i.e. the veritcal dimension of the drawable.
   *
   * @param height height of the drawable
   */
  void setHeight(int height);

  /**
   * Returns the horizontal origin of the shape. Do note that different shapes might have
   * different interpretation of their centers, and so, these might get used differently. For
   * example, the {@link Rectangle} shape considers the top left corner as its center, and thus,
   * the center coordinates and the dimensions should be provided accordingly.
   *
   * @return the horizontal origin of the shape.
   */
  int getCenterX();

  /**
   * Sets the horizontal origin of the shape.
   *
   * @param centerX the horizontal origin of the shape.
   */
  void setCenterX(int centerX);

  /**
   * Returns the vertical origin of the shape. Do note that different shapes might have
   * different interpretation of their centers, and so, these might get used differently. For
   * example, the {@link Rectangle} shape considers the top left corner as its center, and thus,
   * the center coordinates and the dimensions should be provided accordingly.
   *
   * @return the vertical origin of the shape.
   */
  int getCenterY();

  /**
   * Sets the horizontal origin of the shape.
   *
   * @param centerY the vertical origin of the shape.
   */
  void setCenterY(int centerY);
}
