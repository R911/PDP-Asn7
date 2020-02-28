package model.draw.flag;

import dto.image.Canvas;
import dto.image.Image;
import dto.image.Image2D;
import enums.FlagEnum;
import model.draw.Pattern;
import model.draw.drawable.Drawable;

/**
 * Class for supporting drawing of Flag onto images. Implements {@link Pattern} interface. The
 * implementation of the draw method creates a canvas and performs scaling of base flag
 * proportions based on the required size of the flag. Finally, the flag pixels are written into
 * an image and returned.
 */
public class FlagModel implements Pattern {

  private FlagDetails flagDetails;
  private int requiredWidth;

  /**
   * Instantiates a new model for drawing flag, given the country name and required width.
   *
   * @param countryName enum specifying the name of the country for which the flag is to be
   *         generated.
   * @param requiredWidth width(horizontal dimension) of the flag.
   */
  public FlagModel(FlagEnum countryName, int requiredWidth) {
    this.flagDetails = FlagFactory.getFlag(countryName);

    if (requiredWidth < flagDetails.getBaseWidth()) {
      throw new IllegalArgumentException("Required width cannot be less than base flag width!");
    }
    this.requiredWidth = requiredWidth;
  }

  @Override
  public Image draw() {
    if (flagDetails == null || flagDetails.getDrawables().size() == 0) {
      throw new IllegalArgumentException("Flag details cannot be null or empty!");
    }

    int scalingFactor = Math.round((float) requiredWidth / flagDetails.getBaseWidth());
    int actualHeight = flagDetails.getBaseHeight() * scalingFactor;
    int actualWidth = flagDetails.getBaseWidth() * scalingFactor;

    Canvas canvas = new Canvas(actualHeight, actualWidth);

    for (Drawable drawable : flagDetails.getDrawables()) {
      drawable.setWidth(drawable.getWidth() * scalingFactor);
      drawable.setCenterX(drawable.getCenterX() * scalingFactor);
      drawable.setCenterY(drawable.getCenterY() * scalingFactor);
      drawable.setHeight(drawable.getHeight() * scalingFactor);
      drawable.draw(canvas);
    }


    return new Image2D(canvas, 3, "png");
  }
}
