package model.image.transform;

import dto.color.BaseColor;

/**
 * Implementation of {@link ColorTransform}. Specifically, the class implements a linear color
 * transformation.
 */
public class FloatingTransform2D implements ColorTransform {

  private float[][] transformer;

  /**
   * Instantiates a new transform object given the floating point 2d array.
   *
   * @param transformer array used by the tranformer to perform color tranformation.
   */
  public FloatingTransform2D(float[][] transformer) {
    if (transformer == null || transformer.length == 0) {
      throw new IllegalStateException("Input transformer cannot be null/empty");
    }

    for (float[] row : transformer) {
      if (row.length != transformer.length) {
        throw new IllegalArgumentException("Input transformer needs to be a square");
      }
    }

    this.transformer = transformer;
  }


  @Override
  public BaseColor transform(BaseColor input) {
    if (input == null) {
      throw new IllegalArgumentException("input cannot be null");
    }

    if (transformer.length != input.getNumComponents()) {
      throw new IllegalArgumentException("Size of the transform not the same as the number of "
              + "channels in the color!");
    }

    int[] cArray = input.getComponents();
    int[] newCArray = new int[input.getNumComponents()];

    for (int i = 0; i < transformer.length; i++) {
      float newCValue = 0;
      for (int j = 0; j < transformer[i].length; j++) {
        newCValue += transformer[i][j] * (float) cArray[j];
      }
      newCArray[i] = Math.round(newCValue);
    }

    return input.createColorWithoutAlpha(newCArray);
  }
}
