package model.image.mosaic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import dto.color.BaseColor;
import dto.image.Canvas;

import static model.utils.Util.duplicateCanvas;

/**
 * Concrete implementation of the {@link Mosaic} interface. This class performs the mosaic
 * implementation as follows:
 * <ul>
 *   <li>It starts off by choosing a set of points in the image (called seeds) randomly.</li>
 *   <li>Each pixel in the image is then paired to the seed that is closest to it (by distance).
 *   This creates a cluster of pixels for each seed.</li>
 *   <li>Then the color of each pixel in the image is replaced with the average color of its
 *   cluster.</li>
 * </ul>
 */
public class MosaicImpl implements Mosaic {
  private int imageWidth;
  private int imageHeight;
  private Canvas originalCanvas;
  private int seedCount;
  private HashMap<MosaicPixel, MosaicTile> centerTileMap;

  /**
   * Instantiates a new MosaicImpl object.
   *
   * @param canvas Canvas holding the data of the image on which the mosaic effect is to be
   *         applied.
   * @param seedCount number of random points (seeds) to use in the mosaic effect.
   */
  public MosaicImpl(Canvas canvas, int seedCount) {
    this.originalCanvas = canvas;
    this.seedCount = seedCount;
    this.imageWidth = canvas.getWidth();
    this.imageHeight = canvas.getHeight();
    this.centerTileMap = new HashMap<>();

    selectRandomSeeds();
  }

  /**
   * Helper method to compute the random seeds in the image, and to compute the map of seed
   * positions to the grid numbers, which will help speed up the process of finding the closest
   * cluster for each pixel while performing mosaic transformation.
   */
  private void selectRandomSeeds() {
    Random random = new Random(100);

    for (int i = 0; i < seedCount; i++) {
      int x = random.nextInt(imageWidth);
      int y = random.nextInt(imageHeight);

      MosaicPixel center = new MosaicPixel(x, y, originalCanvas.getPixel(y, x));
      MosaicTile tile = new SimpleMosaicTile();
      tile.addPixel(center);
      centerTileMap.put(center, tile);
    }
  }

  @Override
  public Canvas applyMosaic() {
    Set<MosaicPixel> seeds = centerTileMap.keySet();
    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        // compute distance from each seed pixel.
        double minDistance = Double.MAX_VALUE;
        MosaicPixel assignedSeed = null;
        for (MosaicPixel seed : seeds) {
          double distance = computePixelDistance(seed.getX(), seed.getY(), j, i);

          if (distance < minDistance) {
            minDistance = distance;
            assignedSeed = seed;
          }
        }

        if (assignedSeed == null) {
          throw new IllegalStateException("Could not find seed for pixel!");
        } else {
          centerTileMap.get(assignedSeed).addPixel(new MosaicPixel(j, i,
                  originalCanvas.getPixel(i, j)));
        }
      }
    }

    // compute average color of each of the clusters.
    Set<Map.Entry<MosaicPixel, MosaicTile>> entrySet = centerTileMap.entrySet();
    for (Map.Entry<MosaicPixel, MosaicTile> entry : entrySet) {
      MosaicTile tile = entry.getValue();
      MosaicPixel center = entry.getKey();
      BaseColor centerColor = center.getColor();
      BaseColor newColor = centerColor.createColorWithoutAlpha(
              computeAverageColor(tile, centerColor.getNumComponents()));
      setNewColorForTile(newColor, tile);
    }

    // copy data from all tiles onto a new output canvas.
    Canvas newCanvas = duplicateCanvas(originalCanvas);
    for (MosaicTile tile : centerTileMap.values()) {
      for (MosaicPixel pixel : tile.getPixels()) {
        newCanvas.setPixel(pixel.getY(), pixel.getX(), pixel.getColor());
      }
    }

    return newCanvas;
  }

  /**
   * Helper method to compute euclidean distance between 2 2D points.
   *
   * @param x1 x coordinate of point 1.
   * @param y1 y coordinate of point 1.
   * @param x2 x coordinate of point 2.
   * @param y2 y coordinate of point 2.
   * @return distance between the two points.
   */
  private double computePixelDistance(int x1, int y1, int x2, int y2) {
    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
  }

  /**
   * Helper method to compute average color of a mosaic tile.
   *
   * @param tile tile containing clustered pixels.
   * @param numComponents number of components in the color.
   * @return new component color array.
   */
  private int[] computeAverageColor(MosaicTile tile, int numComponents) {
    int[] componentSums = new int[numComponents];
    int pixelCount = tile.getPixelCount();

    tile.getPixels().forEach(pixel -> {
      int[] pixelComponents = pixel.getColor().getComponents();
      for (int i = 0; i < numComponents; i++) {
        componentSums[i] += pixelComponents[i];
      }
    });

    int[] averageComponentValues = new int[numComponents];
    for (int i = 0; i < numComponents; i++) {
      averageComponentValues[i] = Math.round((float) componentSums[i] / pixelCount);
    }

    return averageComponentValues;
  }

  /**
   * Iterate through all the pixels in a mosaic tile and set the color of each pixel to new color.
   *
   * @param newColor new color of the pixels.
   * @param tile tile for which the color change is to be performed.
   */
  private void setNewColorForTile(BaseColor newColor, MosaicTile tile) {
    tile.getPixels().forEach(pixel -> pixel.setColor(newColor));
  }
}
