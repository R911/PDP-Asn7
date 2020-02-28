package model.image;

import dto.image.Canvas;
import enums.TransformEnum;
import factory.transform.TransformFactory;
import model.image.dither.Dither;
import model.image.mosaic.Mosaic;
import model.image.mosaic.MosaicImpl;
import model.image.transform.FloatingTransform2D;

/**
 * Implementation of the {@link ExtendedImageModel} interface. It inherits the image processing
 * operations defined by the {@link ImageModel} interface from the {@link ImageModelImpl} class
 * that it extends. Namely, filtering and color transformation of images are inherited from the
 * parent class. This class also adds implementations of image dithering and mosaic effect.
 */
public class ExtendedImageModelImpl extends ImageModelImpl implements ExtendedImageModel {

  @Override
  public Canvas applyDither(Dither dither, Canvas canvas) {
    if (dither == null) {
      throw new IllegalArgumentException("Dither kernel object cannot be null");
    }

    if (canvas == null) {
      throw new IllegalArgumentException("Image data cannot be null");
    }

    Canvas imageData = this.applyTransformer(
            new FloatingTransform2D(TransformFactory.getTransformer(TransformEnum.GREYSCALE)),
            canvas);

    for (int i = 0; i < imageData.getHeight(); i++) {
      for (int j = 0; j < imageData.getWidth(); j++) {
        imageData = dither.applyDither(imageData, i, j);
      }
    }

    return imageData;
  }

  @Override
  public Canvas applyMosaic(int seedCount, Canvas canvas) {
    if (canvas == null || canvas.getWidth() == 0 || canvas.getHeight() == 0) {
      throw new IllegalArgumentException("Image data cannot be null or empty!");
    }

    if (seedCount <= 0) {
      throw new IllegalArgumentException("Seed count cannot be non positive!");
    }

    Mosaic mosaic = new MosaicImpl(canvas, seedCount);
    return mosaic.applyMosaic();
  }
}
