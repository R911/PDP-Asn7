package model;

import dto.image.Canvas;
import dto.image.Image;
import dto.image.Image2D;
import model.image.ExtendedImageModel;
import model.image.ExtendedImageModelImpl;
import model.image.dither.Dither;
import model.image.dither.DitherImpl;
import model.utils.Util;

/**
 * Implementation of the {@link ExtendedModelImpl} interface. This class implements the new
 * image processing operations defined by {@link ExtendedModel}, and inherits the existing
 * methods implementations of the {@link Model} interface from the ModelImpl class.
 */
public class ExtendedModelImpl extends ModelImpl implements ExtendedModel {

  private ExtendedImageModel extendedImageModel;

  /**
   * Instantiates a new extended model impl object.
   */
  public ExtendedModelImpl() {
    super();
    this.extendedImageModel = new ExtendedImageModelImpl();
  }

  @Override
  public Image applyDither(float[][] ditherKernel, Image image) {
    Dither dither = new DitherImpl(ditherKernel);
    Canvas duplicateCanvas = Util.duplicateCanvas(image.getData());
    return new Image2D(extendedImageModel.applyDither(dither, duplicateCanvas),
            image.getBaseImageColorType(), image.getBaseImageType());
  }

  @Override
  public Image applyMosaic(int seedCount, Image image) {
    Canvas duplicateCanvas = Util.duplicateCanvas(image.getData());
    return new Image2D(extendedImageModel.applyMosaic(seedCount, duplicateCanvas),
            image.getBaseImageColorType(), image.getBaseImageType());
  }
}
