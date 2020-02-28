package factory.filter;

import enums.FilterEnum;

/**
 * Factory method for creating filters.
 */
public class FilterFactory {

  /**
   * Creates a blur filter of 3x3 size.
   *
   * @return blur filter.
   */
  private static float[][] getBlurFilter() {

    float baseValue = 1.0f / 4.0f;
    float firstNeighbour = baseValue / 2.0f;
    float secondNeighbour = firstNeighbour / 2.0f;

    return new float[][]{
            {secondNeighbour, firstNeighbour, secondNeighbour},
            {firstNeighbour, baseValue, firstNeighbour},
            {secondNeighbour, firstNeighbour, secondNeighbour}
    };
  }

  /**
   * Creates a sharpen filter of 5x5 type.
   *
   * @return sharpen filter
   */
  private static float[][] getSharpenFilter() {
    float baseValue = 1.0f;
    float firstLayer = 1.0f / 4.0f;
    float secondLayer = -1.0f / 8.0f;

    return new float[][]{
            {secondLayer, secondLayer, secondLayer, secondLayer, secondLayer},
            {secondLayer, firstLayer, firstLayer, firstLayer, secondLayer},
            {secondLayer, firstLayer, baseValue, firstLayer, secondLayer},
            {secondLayer, firstLayer, firstLayer, firstLayer, secondLayer},
            {secondLayer, secondLayer, secondLayer, secondLayer, secondLayer}
    };
  }

  /**
   * This method provides the filter based on the input provided.
   *
   * @param filterType required
   * @return filter
   */
  public static float[][] getFilter(FilterEnum filterType) {

    switch (filterType) {
      case BLUR: {
        return getBlurFilter();
      }
      case SHARPEN: {
        return getSharpenFilter();
      }
      default: {
        throw new IllegalArgumentException("Filter not supported");
      }
    }

  }
}
