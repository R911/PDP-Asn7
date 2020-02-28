package factory.transform;

import enums.TransformEnum;

/**
 * Factory for creating color transformation objects.
 */
public class TransformFactory {

  /**
   * This method creates a grey scale color transformation object.
   *
   * @return grey scale transformer.
   */
  private static float[][] getGreyScaleTransformer() {
    float redTransform = 0.2126f;
    float greenTransform = 0.7152f;
    float blueTransform = 0.0722f;

    return new float[][]{
            {redTransform, greenTransform, blueTransform},
            {redTransform, greenTransform, blueTransform},
            {redTransform, greenTransform, blueTransform}
    };
  }

  /**
   * This method creates a sepia color transformation object.
   *
   * @return sepia transformer.
   */
  private static float[][] getSepiaTransformer() {
    return new float[][]{
            {0.393f, 0.769f, 0.189f},
            {0.349f, 0.686f, 0.168f},
            {0.272f, 0.534f, 0.131f}
    };
  }

  /**
   * This method provides the color transformation requested.
   *
   * @param transformType type of color transformation.
   * @return color transformation object.
   */
  public static float[][] getTransformer(TransformEnum transformType) {
    switch (transformType) {
      case GREYSCALE:
        return getGreyScaleTransformer();
      case SEPIA:
        return getSepiaTransformer();
      default:
        throw new IllegalArgumentException("Transform not supported");
    }
  }
}
