package model.image.filter;

import dto.color.BaseColor;

/**
 * Class representing a simple square filter of odd size, which can be applied to an image using
 * convolution. This class can be used to represent all such filters. The class supports
 * evaluation of the value of the new pixel given the pixel data of the image. It is the
 * responsibility of the caller to provide the correct pixel data of the correct dimensions.
 *
 * <p>The filter is applied to each of the channels present in the original image. So, if the
 * image has 3 color channels, the entire filter will be applied thrice to create three
 * new values.</p>
 */
public class SimpleProductFilter2D implements ConvolutionalFilter2D {

  private float[][] filter;
  private int width;
  private int height;

  /**
   * Initializes the filter with the provided matrix.
   *
   * @param filter filter matrix to use.
   */
  public SimpleProductFilter2D(float[][] filter) {
    // since convolutional filters are uniform, i.e. all rows have the same number of columns, we
    // need to check the input.
    if (filter == null || filter.length == 0) {
      throw new IllegalArgumentException("Filter cannot be null/empty");
    }

    int temp = -1;
    for (float[] arr : filter) {
      if (temp == -1) {
        temp = arr.length;
      } else {
        if (arr.length != temp) {
          throw new IllegalArgumentException("Input filter should have uniform dimensions!");
        }
      }
    }

    // check to ensure that the width and height are the same, and odd numbers.
    if (filter.length != temp || (filter.length % 2 == 0)) {
      throw new IllegalArgumentException();
    }

    this.width = temp;
    this.height = filter.length;
    this.filter = filter;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public BaseColor getValue(BaseColor[][] data) throws ArrayIndexOutOfBoundsException {
    // delayed fail implementation. For the sake of efficiency, we do not perform bounds checking,
    // and instead allow ArrayIndexOutOfBoundsException to be thrown.

    if (data == null) {
      throw new IllegalArgumentException("data cannot be null");
    }

    BaseColor sample = data[0][0];
    int numChannels = sample.getNumComponents();
    int[] newComponents = new int[numChannels];

    for (int k = 0; k < numChannels; k++) {
      double result = 0;
      for (int i = 0; i < filter.length; i++) {
        for (int j = 0; j < filter[i].length; j++) {
          result += filter[i][j] * data[i][j].getComponents()[k];
        }
      }

      newComponents[k] = (int) result;
    }

    return sample.createColorWithoutAlpha(newComponents);
  }
}
