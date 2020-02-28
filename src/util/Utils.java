package util;

import java.awt.image.BufferedImage;

import dto.color.BaseColor;
import dto.image.Canvas;

/**
 * Utility class containing operations used in the Image processing application.
 */
public class Utils {

  /**
   * Converts a {@link dto.image.Image} to {@link java.awt.Image}.
   *
   * @param image the custom internal image representation of the application.
   * @return the awt image object.
   */
  public static BufferedImage convertImage(dto.image.Image image) {
    BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(),
            image.getBaseImageColorType());

    Canvas cvs = image.getData();

    for (int i = 0; i < cvs.getHeight(); i++) {
      for (int j = 0; j < cvs.getWidth(); j++) {
        bufferedImage.setRGB(j, i, convertToRGB(cvs.getPixel(i, j)));
      }
    }

    return bufferedImage;
  }

  /**
   * Converts our custom color representation to an aRGB integer.
   *
   * @param color object of BaseColor interface.
   * @return integer representation of the color in aRGB format.
   */
  public static int convertToRGB(BaseColor color) {
    if (color == null) {
      return 0x0;
    }

    int[] components = color.getComponents();
    return components[3] << 24 | components[0] << 16 | components[1] << 8 | components[2];
  }
}
