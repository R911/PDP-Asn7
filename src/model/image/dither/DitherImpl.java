package model.image.dither;

import dto.color.BaseColor;
import dto.image.Canvas;

/**
 * Concrete implementation of {@link Dither} interface. This class applies dithering to an
 * individual pixel and spreads the error of that pixel based upon its kernel.
 */
public class DitherImpl implements Dither {

  private float[][] ditherKernel;

  /**
   * Constructor for initializing the dither kernel used by this implementation.
   *
   * @param ditherKernel to be initialized
   */
  public DitherImpl(float[][] ditherKernel) {
    if (ditherKernel == null || ditherKernel.length == 0 || ditherKernel[0].length == 0) {
      throw new IllegalArgumentException("Illegal dither kernel");
    }
    this.ditherKernel = ditherKernel;
  }

  private int findClosestPaletteColor(int[] colorComponent) {
    int color = 0;
    for (int i = 0; i < 3; i++) {
      color = colorComponent[i] + color;
    }
    color = color / 3;

    if (color < 128) {
      return 0;
    }
    return 255;
  }

  /**
   * This class applies dithering on an input pixel (row and column) by changing its color to an
   * average of all color components and then spreading the difference of new and old color to
   * the nearby pixels based upon the kernel.
   *
   * @param input canvas
   * @param row of pixel
   * @param column of pixel
   * @return resultant canvas
   */
  @Override
  public Canvas applyDither(Canvas input, int row, int column) {
    BaseColor color = input.getPixel(row, column);
    int[] colorComponents = color.getComponents();
    int newColor = findClosestPaletteColor(colorComponents);
    int[] newColorComponent = new int[]{newColor, newColor, newColor, 255};
    int[] errorColorComponent = new int[color.getNumComponents()];

    for (int i = 0; i < color.getNumComponents(); i++) {
      errorColorComponent[i] = colorComponents[i] - newColor;
    }

    input.setPixel(row, column, color.createColor(newColorComponent));

    boolean ditherStart = false;
    int ditherStartRow = 0;
    int ditherStarColumn = 0;

    for (int i = 0; i < ditherKernel.length; i++) {
      for (int j = 0; j < ditherKernel[i].length; j++) {
        if (ditherKernel[i][j] == 0.0f) {
          continue;
        }
        if (!ditherStart) {
          ditherStart = true;
          ditherStartRow = i;
          ditherStarColumn = j - 1;
        }

        int imageRow = row + (i - ditherStartRow);
        int imageColumn = column + (j - ditherStarColumn);

        if (imageRow < 0 || imageRow >= input.getHeight() || imageColumn < 0 || imageColumn >= input
                .getWidth()) {
          continue;
        }

        BaseColor pixel = input.getPixel(imageRow, imageColumn);
        int[] pixelComponentWithError = new int[pixel.getNumComponents()];
        int[] pixelColor = pixel.getComponents();

        for (int k = 0; k < pixel.getNumComponents(); k++) {
          pixelComponentWithError[k] =
                  Math.round(ditherKernel[i][j] * errorColorComponent[k]) + pixelColor[k];

        }
        input.setPixel(imageRow, imageColumn,
                pixel.createColorWithoutAlpha(pixelComponentWithError));
      }
    }

    return input;
  }
}
