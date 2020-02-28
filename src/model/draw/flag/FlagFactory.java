package model.draw.flag;

import dto.color.BaseColor;
import dto.color.ColorImpl;
import enums.FlagEnum;
import model.draw.drawable.Drawable;
import model.draw.drawable.Rectangle;

/**
 * Helper factory for creating flags.
 */
class FlagFactory {
  /**
   * This method provides flags in a base format.
   *
   * @param flagEnum type of flag required.
   * @return base flag.
   */
  static FlagDetails getFlag(FlagEnum flagEnum) {
    switch (flagEnum) {
      case FRANCE:
        return createFlagOfFrance();
      case GREECE:
        return createFlagOfGreece();
      case SWITZERLAND:
        return createFlagOfSwitzerland();
      default:
        throw new IllegalArgumentException("Unsupported flag!");
    }
  }

  /**
   * Method to create base flag of France.
   *
   * @return flag of France.
   */
  private static FlagDetails createFlagOfFrance() {
    FlagDetails flag = new FlagDetailsImpl(100, 1.5);

    BaseColor blue = new ColorImpl(new int[]{0, 85, 164, 255});
    BaseColor white = new ColorImpl(new int[]{255, 255, 255, 255});
    BaseColor red = new ColorImpl(new int[]{239, 65, 53, 255});

    flag.addDrawable(createRectangle(0, 0, 30, flag.getBaseHeight(), blue));
    flag.addDrawable(createRectangle(30, 0, 33, flag.getBaseHeight(), white));
    flag.addDrawable(createRectangle(63, 0, 37, flag.getBaseHeight(), red));

    return flag;
  }

  /**
   * Method to create base flag of Greece.
   *
   * @return flag of Greece.
   */
  private static FlagDetails createFlagOfGreece() {
    FlagDetails flag = new FlagDetailsImpl(27, 1.5);
    BaseColor blue = new ColorImpl(new int[]{0, 91, 174, 255});
    BaseColor white = new ColorImpl(new int[]{255, 255, 255, 255});

    for (int i = 0; i < 9; i++) {
      if (i % 2 == 0) {
        flag.addDrawable(createRectangle(0, 2 * i, 27, 2, blue));
      } else {
        flag.addDrawable(createRectangle(0, 2 * i, 27, 2, white));
      }
    }

    flag.addDrawable(createRectangle(0, 0, 4, 4, blue));
    flag.addDrawable(createRectangle(4, 0, 2, 4, white));
    flag.addDrawable(createRectangle(6, 0, 4, 4, blue));

    flag.addDrawable(createRectangle(0, 4, 10, 2, white));

    flag.addDrawable(createRectangle(0, 6, 4, 4, blue));
    flag.addDrawable(createRectangle(4, 6, 2, 4, white));
    flag.addDrawable(createRectangle(6, 6, 4, 4, blue));
    return flag;
  }

  /**
   * Method to create base flag of Switzerland.
   *
   * @return flag of Switzerland.
   */
  private static FlagDetailsImpl createFlagOfSwitzerland() {
    FlagDetailsImpl flag = new FlagDetailsImpl(32, 1);
    BaseColor red = new ColorImpl(new int[]{255, 0, 0, 255});
    BaseColor white = new ColorImpl(new int[]{255, 255, 255, 255});

    flag.addDrawable(createRectangle(0, 0, 32, flag.getBaseHeight(), red));

    flag.addDrawable(createRectangle(13, 6, 6, 7, white));
    flag.addDrawable(createRectangle(6, 13, 20, 6, white));
    flag.addDrawable(createRectangle(13, 19, 6, 7, white));
    return flag;
  }

  /**
   * Method to draw a rectangle of given dimensions.
   *
   * @param centerX of rectangle
   * @param centerY of rectangle
   * @param width of rectangle
   * @param height of rectangle
   * @param color of rectangle
   * @return Rectangle object.
   */
  private static Drawable createRectangle(int centerX, int centerY, int width, int height,
          BaseColor color) {
    Rectangle rectangle = new Rectangle(centerX, centerY, color);
    rectangle.setWidth(width);
    rectangle.setHeight(height);
    return rectangle;
  }
}
