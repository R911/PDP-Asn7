package io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.Utils;
import dto.image.Image;

/**
 * This class provides ways to store an image.
 */
public class ImageWriter {

  /**
   * This method stores an image to the file path provided.
   *
   * @param image to store
   * @param filePath to store to
   * @throws IOException if unable to store image.
   */
  public static boolean writeImage(Image image, String filePath) throws IOException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null!");
    }

    if (filePath == null || filePath.trim().length() == 0) {
      throw new IllegalArgumentException("Path cannot be null/empty!");
    }

    BufferedImage response = Utils.convertImage(image);

    return ImageIO.write(response, image.getBaseImageType(), new File(filePath));
  }

  /**
   * Method to store the image in the OS temporary directory.
   *
   * @param image image to be stored.
   * @param imageFormat file extension of the image.
   * @return true if the image was written successfully, false otherwise.
   * @throws IOException if an IO exception occurs.
   */
  public static boolean writeImageToTemp(Image image, String imageFormat) throws IOException {
    String outPutPath = File.createTempFile("Draw_", imageFormat).getAbsolutePath();
    return ImageWriter.writeImage(image, outPutPath);
  }
}
