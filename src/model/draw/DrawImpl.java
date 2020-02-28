package model.draw;

import dto.image.Image;
import enums.FlagEnum;
import model.draw.flag.FlagModel;
import model.draw.pattern.CheckerBoard;
import model.draw.pattern.Rainbow;

/**
 * Concrete implementation of {@link Draw} interface. Uses composition to delegate the operations
 * to respective classes.
 */
public class DrawImpl implements Draw {

  @Override
  public Image drawRainbow(int width, int height, int stripeSize, char orientation,
          boolean repeat) {
    Pattern rainbow = new Rainbow(width, height, stripeSize, orientation, repeat);
    return rainbow.draw();
  }

  @Override
  public Image drawCheckerBoard(int outerSquareSide, int innerSquareSide) {
    Pattern cb = new CheckerBoard(outerSquareSide, innerSquareSide);
    return cb.draw();
  }

  @Override
  public Image drawFlag(FlagEnum countryName, int requiredWidth) {
    Pattern pattern = new FlagModel(countryName, requiredWidth);
    return pattern.draw();
  }
}
