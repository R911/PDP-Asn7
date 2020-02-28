package model.image;

import dto.image.Canvas;
import model.image.filter.ConvolutionalFilter2D;
import model.image.transform.ColorTransform;

/**
 * Interface for supporting the following operations on an image.
 * <ul>
 *   <li>Filtering.</li>
 *   <li>Color transformation.</li>
 * </ul>
 *
 * <p>Both of the above image operations can be provided with a custom kernel for either
 * filtering or color transformation. Hence, given the kernel, the image operations can be
 * applied to any kind of filtering/color transformation.</p>
 */
public interface ImageModel {
  /**
   * This method applies filter operations on an input image. This method calculates requires
   * value for nearby pixels.
   *
   * @param filter2D to apply
   * @param canvas to apply on
   * @return resultant image
   */
  Canvas applyFilter(ConvolutionalFilter2D filter2D, Canvas canvas);

  /**
   * This method applies color transformations on an input image. This method applies the
   * transformation on the pixel channels.
   *
   * @param colorTransformer to be applied
   * @param canvas input
   * @return Image
   */
  Canvas applyTransformer(ColorTransform colorTransformer, Canvas canvas);
}
