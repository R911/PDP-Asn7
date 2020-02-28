package model.draw.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dto.color.BaseColor;
import dto.color.ColorImpl;
import dto.image.Canvas;
import dto.image.Image;
import dto.image.Image2D;
import model.draw.Pattern;
import model.draw.drawable.Drawable;
import model.draw.drawable.Rectangle;
import model.utils.RainbowColors;

/**
 * Implementation of {@link Pattern} interface which supports drawing of rainbow patterns. The
 * class has the following features:
 * <ul>
 *   <li>A rainbow can be drawn both horizontally and vertically.</li>
 *   <li>For dimensions where more than 7 colors can be fitted in the pattern, the class
 *   provides the option of repeat.</li>
 *   <li>If repeat is true, any extra space is filled with colors repeated in the same VIBGYOR
 *   pattern.</li>
 *   <li>If repeat is false, then there are exactly 7 stripes in the image with equal amount of
 *   whitespace left on both sides, depending on the orientation.</li>
 *   <li>If the space is less than what is required for 7 stripes, as many stripes as possible
 *   are fitted, where the last stripe may not be of the required dimension.</li>
 * </ul>
 */
public class Rainbow implements Pattern {
  private Image rainbow;
  private int width;
  private int height;
  private int stripeSize;
  private char orientation;
  private boolean repeat;
  private BaseColor emptySpace;
  private Map<Integer, BaseColor> colorMap;

  /**
   * Instantiates a new Rainbow pattern with the provided inputs.
   *
   * @param width horizontal dimension of the pattern.
   * @param height vertical dimension of the pattern.
   * @param stripeSize size of the stripe. It can be width or height depending on the
   *         orientation.
   * @param orientation 'H' for horizontal and 'V' for vertical.
   * @param repeat true if repetition should be for extra space, false otherwise.
   */
  public Rainbow(int width, int height, int stripeSize, char orientation, boolean repeat) {
    if (!(orientation == 'H' || orientation == 'h' || orientation == 'V' || orientation == 'v')) {
      throw new IllegalArgumentException("Orientation can only be Horizontal or Vertical");
    }

    this.width = width;
    this.height = height;
    this.stripeSize = stripeSize;
    this.orientation = orientation;
    this.repeat = repeat;
    this.rainbow = new Image2D(new Canvas(height, width), 3, "png");
    this.emptySpace = new ColorImpl(new int[]{255, 255, 255, 255});
    this.colorMap = RainbowColors.getRainbowMap();
  }

  private List<Drawable> drawHorizontalRainBow(int totalStripes, int spaceToLeave) {
    List<Drawable> drawables = new ArrayList<>();

    int yMin = spaceToLeave;

    if (spaceToLeave > 0) {
      drawables.add(createRectangle(0, 0, width, spaceToLeave, emptySpace));
    }

    for (int i = 0; i < totalStripes; i++) {
      if (yMin + stripeSize > height) {
        stripeSize = height - yMin;
      }
      drawables.add(createRectangle(0, yMin, width, stripeSize, colorMap.get(i % 7)));
      yMin += stripeSize;
    }

    if (spaceToLeave > 0) {
      drawables.add(createRectangle(0, yMin, width, spaceToLeave, emptySpace));
    }
    return drawables;
  }

  private List<Drawable> drawVerticalRainbow(int totalStripes, int spaceToLeave) {
    List<Drawable> drawables = new ArrayList<>();

    if (spaceToLeave > 0) {
      drawables.add(createRectangle(0, 0, spaceToLeave, height, emptySpace));
    }

    int xMin = spaceToLeave;

    for (int i = 0; i < totalStripes; i++) {
      if (xMin + stripeSize > width) {
        stripeSize = width - xMin;
      }

      drawables.add(createRectangle(xMin, 0, stripeSize, height, colorMap.get(i % 7)));
      xMin += stripeSize;
    }

    if (spaceToLeave > 0) {
      drawables.add(createRectangle(xMin, 0, spaceToLeave, height, emptySpace));
    }

    return drawables;
  }

  private int spaceToLeave(int totalStripes, int planePrimeValue) {
    int spaceToLeave = 0;
    if (!repeat && totalStripes == 7) {
      spaceToLeave = (planePrimeValue - (7 * stripeSize)) / 2;
    }
    return spaceToLeave;
  }

  private int totalStripes(int planePrimeValue) {
    int totalStripes;
    if (repeat) {
      totalStripes = planePrimeValue / stripeSize;
      if (planePrimeValue % stripeSize > 0) {
        totalStripes++;
      }
    } else {
      int totalFittingStrips = planePrimeValue / stripeSize;
      if (totalFittingStrips >= 7) {
        totalStripes = 7;
      } else {
        totalStripes = totalFittingStrips;
        if (planePrimeValue % stripeSize > 0) {
          totalStripes++;
        }
      }
    }

    return totalStripes;
  }

  private Drawable createRectangle(int originX, int originY, int width, int height,
          BaseColor color) {
    Rectangle rectangle = new Rectangle(originX, originY, color);
    rectangle.setWidth(width);
    rectangle.setHeight(height);
    return rectangle;
  }

  @Override
  public Image draw() {

    List<Drawable> drawables;

    int totalStripes;
    int spaceToLeave;

    if (orientation == 'H' || orientation == 'h') {
      totalStripes = totalStripes(height);
      spaceToLeave = spaceToLeave(totalStripes, height);
      drawables = drawHorizontalRainBow(totalStripes, spaceToLeave);
    } else {
      totalStripes = totalStripes(width);
      spaceToLeave = spaceToLeave(totalStripes, width);
      drawables = drawVerticalRainbow(totalStripes, spaceToLeave);
    }

    for (Drawable drawable : drawables) {
      drawable.draw(rainbow.getData());
    }

    return rainbow;
  }
}
