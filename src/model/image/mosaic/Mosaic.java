package model.image.mosaic;

import dto.image.Canvas;

/**
 * Interface representing the mosaic transformation. Mosaic is an effect that gives an image a
 * “stained glass window” effect.
 */
public interface Mosaic {

  /**
   * Applies the mosaic effect on the image and returns a canvas holding the mosaic image data.
   *
   * @return mosaic image data.
   */
  Canvas applyMosaic();
}
