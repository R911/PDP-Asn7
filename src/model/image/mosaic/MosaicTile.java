package model.image.mosaic;

import java.util.List;

/**
 * Interface representing a tile in a mosaic image. A tile is simply a collection of pixels in a
 * Mosaic image that are distinctively linked by some common characteristic (such as pixels
 * belonging to the same seed's cluster).
 */
interface MosaicTile {
  /**
   * Adds a new pixel to the list of pixels in the tile.
   *
   * @param pixel pixel to be added.
   */
  void addPixel(MosaicPixel pixel);

  /**
   * Returns the number of pixels in the tile.
   *
   * @return number of pixels in the tile.
   */
  int getPixelCount();

  /**
   * Returns a list of all pixels in the tile.
   *
   * @return pixel list.
   */
  List<MosaicPixel> getPixels();
}
