package model.utils;

import dto.color.BaseColor;
import dto.image.Canvas;

/**
 * Utility methods for common use among the model classes.
 */
public class Util {

  /**
   * This method creates a duplicate of the input canvas.
   *
   * @param input canvas
   * @return duplicate canvas
   */
  public static Canvas duplicateCanvas(Canvas input) {
    Canvas newCanvas = new Canvas(input.getHeight(), input.getWidth());

    for (int i = 0; i < input.getHeight(); i++) {
      for (int j = 0; j < input.getWidth(); j++) {
        BaseColor bColor = input.getPixel(i, j);
        newCanvas.setPixel(i, j, bColor.createColor(bColor.getComponents()));
      }
    }
    return newCanvas;
  }
}
