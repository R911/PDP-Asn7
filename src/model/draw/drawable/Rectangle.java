package model.draw.drawable;

import dto.color.BaseColor;
import dto.image.Canvas;

/**
 * Concrete implementation of {@link Drawable} interface. It represents a quadrilateral with each
 * angle being 90 degrees and equal opposing sides. Note, the definition of origin for this
 * object it's top left corner.
 */
public class Rectangle extends ShapeDrawable {

  /**
   * Instantiates a new rectangle object.
   *
   * @param centerX x coordinate of the rectangle origin.
   * @param centerY y coordinate of the rectangle origin.
   * @param color color of the rectangle.
   */
  public Rectangle(int centerX, int centerY, BaseColor color) {
    super(centerX, centerY, color);
  }

  @Override
  public void draw(Canvas canvas) {
    // logic to draw a Rectangle on the canvas.
    for (int i = centerY; i < centerY + height; i++) {
      for (int j = centerX; j < centerX + width; j++) {
        if (canvas.isWithinBounds(i, j)) {
          canvas.setPixel(i, j, color);
        } else {
          throw new IllegalStateException(String.format("Canvas too small to draw image on "
                  + "indices: height = %s and width = %s", i, j));
        }
      }
    }
  }
}
