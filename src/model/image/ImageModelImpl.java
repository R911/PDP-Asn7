package model.image;

import dto.color.BaseColor;
import dto.color.ColorImpl;
import dto.image.Canvas;
import model.image.filter.ConvolutionalFilter2D;
import model.image.transform.ColorTransform;

/**
 * Concrete implementation of {@link ImageModel} interface. This class provides stateless
 * operations on the input image, as defined by the {@link ImageModel} interface.
 */
public class ImageModelImpl implements ImageModel {

  /**
   * This method applies an {@link ConvolutionalFilter2D} on an image. It iterates over the
   * entire image pixel by pixel. For each pixel it finds all the values of pixels surrounding
   * it based upon the size of the filter. Then it uses that to calculate the new value of each
   * of the channels of the pixel.
   *
   * @param filter2D to apply.
   * @param canvas data to apply the filter onto.
   * @return canvas containing filtered data.
   */
  @Override
  public Canvas applyFilter(ConvolutionalFilter2D filter2D, Canvas canvas) {
    if (filter2D == null) {
      throw new IllegalArgumentException("Filter cannot be null");
    }

    if (canvas == null) {
      throw new IllegalArgumentException("Image data cannot be null");
    }

    int filterWidth = filter2D.getWidth();
    int filterHeight = filter2D.getHeight();
    int filterCenter = filterHeight / 2;
    Canvas filteredImageData = new Canvas(canvas.getHeight(), canvas.getWidth());
    int[] zeroColor = new int[]{0, 0, 0, 255};

    for (int imageRow = 0; imageRow < canvas.getHeight(); imageRow++) {
      for (int imageColumn = 0; imageColumn < canvas.getWidth(); imageColumn++) {
        BaseColor[][] pixelData = new BaseColor[filterHeight][filterWidth];
        for (int filterRow = 0; filterRow < filterHeight; filterRow++) {
          for (int filterColumn = 0; filterColumn < filterWidth; filterColumn++) {

            int indexY = imageRow + filterRow - filterCenter;
            int indexX = imageColumn + filterColumn - filterCenter;
            if (filteredImageData.isWithinBounds(indexY, indexX)) {
              pixelData[filterRow][filterColumn] = canvas.getPixel(indexY, indexX);
            } else {
              pixelData[filterRow][filterColumn] = new ColorImpl(zeroColor);
            }
          }
        }

        BaseColor filteredColor = filter2D.getValue(pixelData);
        filteredImageData.setPixel(imageRow, imageColumn, filteredColor);
      }
    }

    return filteredImageData;
  }

  /**
   * This method applies the color transform on an image by iterating over pixel by pixel and
   * calculating the dot product of the color transformer and the pixel channels. The number of
   * components in the color transforms must be less than equal to the number of channels (color)
   * in the image.
   *
   * @param colorTransformer to be applied.
   * @param canvas input data.
   * @return canvas containing transformed data.
   */
  @Override
  public Canvas applyTransformer(ColorTransform colorTransformer, Canvas canvas) {
    if (colorTransformer == null) {
      throw new IllegalArgumentException("Color transformer cannot be null");
    }

    if (canvas == null) {
      throw new IllegalArgumentException("Image data cannot be null");
    }

    Canvas newImageData = new Canvas(canvas.getHeight(), canvas.getWidth());

    for (int i = 0; i < canvas.getHeight(); i++) {
      for (int j = 0; j < canvas.getWidth(); j++) {
        BaseColor pixel = canvas.getPixel(i, j);
        BaseColor newPixel = colorTransformer.transform(pixel);
        newImageData.setPixel(i, j, newPixel);
      }
    }

    return newImageData;
  }
}
