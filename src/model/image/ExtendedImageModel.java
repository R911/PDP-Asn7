package model.image;

import dto.image.Canvas;
import model.image.dither.Dither;

/**
 * Interface for supporting additional operations on Images, on top of what is already defined by
 * {@link ImageModel} interface. Specifically, the interface adds methods to perform dithering on
 * images, and for applying mosaic effect onto images.
 */
public interface ExtendedImageModel extends ImageModel {
  /**
   * This method applies the dither kernel represented by the {@link Dither} object onto the
   * image data in the canvas.
   *
   * @param dither wrapper object representing the dither kernel.
   * @param canvas canvas holding the image data onto which dithering is to be applied.
   * @return new Canvas holding the dithered image data.
   */
  Canvas applyDither(Dither dither, Canvas canvas);

  /**
   * This method applies the mosaic effect onto the provided image data. The effect can be
   * controlled by changing the number of seeds.
   *
   * @param seedCount number of seeds in the mosaic.
   * @param canvas canvas holding the image data onto which mosaic is to be applied.
   * @return new Canvas holding the mosaic image data.
   */
  Canvas applyMosaic(int seedCount, Canvas canvas);
}
