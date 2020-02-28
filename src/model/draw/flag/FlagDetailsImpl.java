package model.draw.flag;

import java.util.ArrayList;
import java.util.List;

import model.draw.drawable.Drawable;

/**
 * Concrete implementation of the {@link FlagDetails} interface. It supports building a flag
 * model by adding shapes. The class also performs basic bounds check to ensure that the shapes
 * added to the flag design do not exceed the bounds of the flag itself.
 */
public class FlagDetailsImpl implements FlagDetails {
  private int baseWidth;
  private int baseHeight;
  private List<Drawable> drawables;

  /**
   * Instantiates an object of which can be used to create a model of a flag.
   *
   * @param width the lowest possible width of the country's flag being built.
   * @param aspectRatio aspect ratio (width/height) of the flag.
   */
  FlagDetailsImpl(int width, double aspectRatio) {
    if (width <= 0 || aspectRatio <= 0) {
      throw new IllegalArgumentException("Flag width or aspect ratio cannot be non-positive!");
    }

    this.baseWidth = width;
    this.drawables = new ArrayList<>();

    double rawBaseWidth = baseWidth / aspectRatio;
    double fractionalPart = rawBaseWidth - (int) (rawBaseWidth);
    if (fractionalPart > 0.5 || fractionalPart == 0) {
      this.baseHeight = (int) Math.round(rawBaseWidth);
    } else {
      this.baseHeight = (int) Math.round(rawBaseWidth) + 1; // to negate rounding down.
    }
  }

  @Override
  public List<Drawable> getDrawables() {
    return this.drawables;
  }

  @Override
  public void addDrawable(Drawable drawable) {
    // basic bounds check to ensure the shapes do not go beyond the flag.
    if ((drawable.getCenterX() + drawable.getWidth() > this.baseWidth)
            || (drawable.getCenterY() + drawable.getHeight() > this.baseHeight)) {
      throw new IllegalArgumentException("Boundary of drawable cannot exceed flag bounds!");
    }

    this.drawables.add(drawable);
  }

  @Override
  public int getBaseWidth() {
    return baseWidth;
  }

  @Override
  public int getBaseHeight() {
    return baseHeight;
  }
}
