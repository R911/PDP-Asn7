package factory.dither;

import enums.DitherEnum;

/**
 * Factory for creating a dither kernel.
 */
public class DitherKernelFactory {
  private static float[][] getFloydSteinbergDither() {
    return new float[][]{{0.0f, 0.0f, 0.4375f}, {0.1875f, 0.3125f, 0.0625f}};
  }

  private static float[][] getJarvisJudiceNinkeDither() {
    return new float[][]{{0.0f, 0.0f, 0.0f, 0.1458f, 0.1042f}, {0.0625f, 0.1042f, 0.1458f
            , 0.1042f, 0.0625f}, {0.0208f, 0.0625f, 0.1042f, 0.0625f, 0.0208f}};
  }

  /**
   * This method provides a supported dither kernel.
   *
   * @param ditherType of kernel required.
   * @return dither kernel
   */
  public static float[][] getDitherKernel(DitherEnum ditherType) {
    switch (ditherType) {
      case FS_DITHER:
        return getFloydSteinbergDither();
      case JJN_DITHER:
        return getJarvisJudiceNinkeDither();
      default: {
        throw new IllegalArgumentException("Unsupported dither function!");
      }
    }
  }
}
