package model.utils;

import java.util.HashMap;
import java.util.Map;

import dto.color.BaseColor;
import dto.color.ColorImpl;

/**
 * Class for generating a hash map of rainbow colors in reverse VIBGYOR format from index 0 to 6.
 */
public class RainbowColors {

  private final static int[] RED = {255, 0, 0, 255};
  private final static int[] ORANGE = {255, 127, 0, 255};
  private final static int[] YELLOW = {255, 255, 0, 255};
  private final static int[] GREEN = {0, 255, 0, 255};
  private final static int[] BLUE = {0, 0, 255, 255};
  private final static int[] INDIGO = {46, 43, 95, 255};
  private final static int[] VIOLET = {139, 0, 255, 255};

  /**
   * This method creates a Hash Map containing colors in reverse VIBGYOR format.
   *
   * @return Map
   */
  public static Map<Integer, BaseColor> getRainbowMap() {
    Map<Integer, BaseColor> rainbowHM = new HashMap<>();

    rainbowHM.put(0, new ColorImpl(RED));
    rainbowHM.put(1, new ColorImpl(ORANGE));
    rainbowHM.put(2, new ColorImpl(YELLOW));
    rainbowHM.put(3, new ColorImpl(GREEN));
    rainbowHM.put(4, new ColorImpl(BLUE));
    rainbowHM.put(5, new ColorImpl(INDIGO));
    rainbowHM.put(6, new ColorImpl(VIOLET));

    return rainbowHM;
  }
}
