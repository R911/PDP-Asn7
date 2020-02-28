package model.image.filter;

import dto.color.BaseColor;

/**
 * Interface representing a convolutional 2D filter. A convolutional filter is one that utilizes
 * convolution to compute the new pixel value. A convolutional filter is also a square matrix,
 * i.e. the number of rows in the matrix is equal to the number of columns.
 */
public interface ConvolutionalFilter2D {

  /**
   * Return the width of the filter. This corresponds to the number of columns in a filter.
   *
   * @return the filter width.
   */
  int getWidth();

  /**
   * Return the height of the filter. This corresponds to the number of rows in a filter.
   *
   * @return the filter height.
   */
  int getHeight();

  /**
   * Performs convolution over the given data, provided as the argument, and returns the new
   * pixel value as the return value.
   *
   * @param data data over which the convolution is to be performed.
   * @return the value of the new pixel.
   */
  BaseColor getValue(BaseColor[][] data);
}
