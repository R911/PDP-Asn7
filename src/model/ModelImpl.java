package model;

import dto.image.Canvas;
import dto.image.Image;
import dto.image.Image2D;
import enums.FlagEnum;
import model.draw.Draw;
import model.draw.DrawImpl;
import model.image.ImageModel;
import model.image.ImageModelImpl;
import model.image.filter.ConvolutionalFilter2D;
import model.image.filter.SimpleProductFilter2D;
import model.image.transform.ColorTransform;
import model.image.transform.FloatingTransform2D;
import model.utils.Util;

/**
 * Concrete implementation of {@link Model} interface. Used composition to delegate operations to
 * appropriate model objects.
 */
public class ModelImpl implements Model {
  private ImageModel imageModel;
  private Draw drawObj;

  public ModelImpl() {
    imageModel = new ImageModelImpl();
    drawObj = new DrawImpl();
  }

  @Override
  public Image applyTransform(float[][] transformArr, Image image) {
    ColorTransform colorTransform = new FloatingTransform2D(transformArr);
    Canvas duplicateCanvas = Util.duplicateCanvas(image.getData());
    return new Image2D(imageModel.applyTransformer(colorTransform, duplicateCanvas),
            image.getBaseImageColorType(), image.getBaseImageType());
  }

  @Override
  public Image applyFilter(float[][] filterArr, Image image) {
    ConvolutionalFilter2D convolutionalFilter2D = new SimpleProductFilter2D(filterArr);
    Canvas duplicateCanvas = Util.duplicateCanvas(image.getData());
    return new Image2D(imageModel.applyFilter(convolutionalFilter2D, duplicateCanvas),
            image.getBaseImageColorType(), image.getBaseImageType());
  }

  @Override
  public Image drawCheckerBoard(int boardSize, int squareSize) {
    return drawObj.drawCheckerBoard(boardSize, squareSize);
  }

  @Override
  public Image drawRainbow(int width, int height, int stripeSize, char orientation,
          boolean repeat) {
    return drawObj.drawRainbow(width, height, stripeSize, orientation, repeat);
  }

  @Override
  public Image drawFlag(FlagEnum countryName, int flagWidth) {
    return drawObj.drawFlag(countryName, flagWidth);
  }
}
