package io;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import dto.color.ColorImpl;
import dto.image.Canvas;
import dto.image.Image;
import dto.image.Image2D;
import enums.ImageFileEnums;

/**
 * This class various methods to read an image.
 */
public class ImageReader {
  private static BufferedImage readFromFile(String filePath) throws IOException {
    return ImageIO.read(new File((filePath)));
  }

  private static BufferedImage readFromURL(String urlString) throws IOException {
    URL url = new URL(urlString);
    return ImageIO.read(url);
  }

  private static int[] getRGBAColorFormat(int[] colors, int colorType) {
    if (colors.length == 3) {
      colors = new int[]{colors[0], colors[1], colors[2], 255};
    }
    //TODO add support for more colors
    return colors;
  }

  private static Image createImageObject(BufferedImage input, String imageType) {
    int imgHeight = input.getHeight();
    int imgWidth = input.getWidth();
    int imageColorType = input.getColorModel().getColorSpace().getType();
    Canvas cvs = new Canvas(imgHeight, imgWidth);
    Raster raster = input.getRaster();
    int numColors = raster.getNumBands();

    for (int i = 0; i < imgHeight; i++) {
      for (int j = 0; j < imgWidth; j++) {
        int[] colors = new int[numColors];
        raster.getPixel(j, i, colors);
        int[] rgbColors = getRGBAColorFormat(colors, imageColorType);
        cvs.setPixel(i, j, new ColorImpl(rgbColors));
      }
    }

    return new Image2D(cvs, imageColorType, imageType);
  }

  private static String getImageType(String imagePath) {

    String[] pathArray = imagePath.split("\\.");
    String imageType = "jpg";

    if (pathArray.length > 1) {
      imageType = pathArray[pathArray.length - 1];
    }

    if (!(imageType.equalsIgnoreCase("jpg") || imageType.equalsIgnoreCase("png") || imageType
            .equalsIgnoreCase("gif"))) {
      throw new IllegalStateException("Image type not supported");
    }
    return imageType;
  }

  /**
   * This method reads an image for various inputs provided.
   *
   * @param from where to read image from
   * @param path of image
   * @return image
   * @throws IOException for unable to read image.
   */
  public static Image readImage(ImageFileEnums from, String path) throws IOException {

    if (path == null || path.trim().length() == 0) {
      throw new IllegalArgumentException("Path cannot be null/empty");
    }

    BufferedImage bufferedImage;

    switch (from) {
      case URL: {
        bufferedImage = readFromURL(path);
        break;
      }
      case FILE: {
        bufferedImage = readFromFile(path);
        break;
      }
      default: {
        throw new IllegalArgumentException("Image input format not supported");
      }
    }


    return createImageObject(bufferedImage, getImageType(path));
  }

}
