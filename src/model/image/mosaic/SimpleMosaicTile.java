package model.image.mosaic;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of a MosaicTile. This class simply stores the pixels as is, in their
 * designated original positions.
 */
class SimpleMosaicTile implements MosaicTile {
  private List<MosaicPixel> pixels;

  /**
   * Instantiates a new empty mosaic tile object.
   */
  SimpleMosaicTile() {
    this.pixels = new ArrayList<>();
  }

  @Override
  public void addPixel(MosaicPixel pixel) {
    this.pixels.add(pixel);
  }

  @Override
  public int getPixelCount() {
    return this.pixels.size();
  }

  @Override
  public List<MosaicPixel> getPixels() {
    return this.pixels;
  }
}