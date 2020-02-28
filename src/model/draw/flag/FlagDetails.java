package model.draw.flag;

import java.util.List;

import model.draw.drawable.Drawable;

/**
 * Interface representing the proportions of a flag. Any flag is made up of components of the
 * lowest possible dimensions, which we chose to designate as the proportions of the flag. Given
 * the proportions, a larger flag of any dimension can be easily created by scaling each
 * component of the proportional model.
 */
public interface FlagDetails {
  /**
   * Returns a list of drawable shapes that the flag is made of.
   *
   * @return the list of shapes constituting the flag.
   */
  List<Drawable> getDrawables();

  /**
   * Add a shape to the flag proportional model.
   *
   * @param drawable the drawable shape to add.
   */
  void addDrawable(Drawable drawable);

  /**
   * Returns the base width of the flag, i.e. the lowest possible width of the flag. The flag
   * cannot be drawn any smaller than the minimum width as the number of pixels would become
   * fractional.
   *
   * @return the base width of the flag.
   */
  int getBaseWidth();

  /**
   * Returns the base height of the flag, i.e. the lowest possible height of the flag. The flag
   * cannot be drawn any smaller than the minimum height as the number of pixels in the components
   * would become fractional (there is no such thing as half a pixel).
   *
   * @return the base width of the flag.
   */
  int getBaseHeight();
}
