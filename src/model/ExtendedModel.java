package model;

import dto.image.Image;

/**
 * This interface represents the entirety of image processing operations provided by this model.
 * Specifically, the following image processing operations are available to the users of this
 * model:
 * <ul>
 *   <li>Image dithering.</li>
 *   <li>Image mosaic.</li>
 * </ul>
 *
 * <p>The following image processing operations are inherited from {@link Model} interface:
 * <ul>
 *   <li>Image filtering.</li>
 *   <li>Image color transformation.</li>
 *   <li>Draw rainbow of the specified dimensions.</li>
 *   <li>Draw checkerboard of the specified dimensions.</li>
 *   <li>Draw flag of a supported country in the specified dimensions.</li>
 * </ul></p>
 *
 * <p>Hence, this interface is a template for users of this model implementation to use while
 * requesting the above functionality provided by the model.
 */
public interface ExtendedModel extends Model {

  /**
   * Performs dithering on the provided image using the kernel as argument. The operation of
   * breaking down an image that has many colors into an image that is made of dots from just a few
   * colors is known as dithering.
   *
   * @param ditherKernel a 2-D array for performing dithering.
   * @param image image on which dithering is to be applied.
   * @return new dithered image.
   */
  Image applyDither(float[][] ditherKernel, Image image);

  /**
   * Applies the mosaic effect onto the image. Mosaic is an effect that gives an image the
   * "stained-glass" look.
   *
   * @param seedCount number of seeds (or "stained glasses") in the mosaic.
   * @param image the original image on which mosaic effect is to be applied.
   * @return new image with the mosaic effect.
   */
  Image applyMosaic(int seedCount, Image image);
}
