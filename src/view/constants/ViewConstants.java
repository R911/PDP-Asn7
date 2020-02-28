package view.constants;

/**
 * Class for parking all of the constants used throughout the view implementation of the Image
 * processing application.
 */
public class ViewConstants {
  public static final String FILE_MENU = "File";
  public static final String IMAGE_MENU = "Image";
  public static final String DRAW_MENU = "Draw";
  public static final String FILE_MENU_OPEN = "Open";
  public static final String FILE_MENU_SAVE = "Save";
  public static final String IMAGE_MENU_BLUR = "Blur";
  public static final String IMAGE_MENU_SHARPEN = "Sharpen";
  public static final String IMAGE_MENU_SEPIA = "Sepia";
  public static final String IMAGE_MENU_GREYSCALE = "Greyscale";
  public static final String IMAGE_MENU_DITHER = "Dither";
  public static final String IMAGE_MENU_MOSAIC = "Mosaic";
  public static final String DRAW_MENU_RAINBOW = "Rainbow";
  public static final String DRAW_MENU_CHECKERBOARD = "Checkerboard";
  public static final String DRAW_MENU_FLAG = "Flag";
  public static final String MENU_INPUT_ERROR = "MenuInputError";
  public static final String MENU_INPUT_ERROR_MSG = "Invalid input detected!\n";

  // common dialog constants.
  public static final String DIALOG_NAME = "Input";
  public static final String DIALOG_SAVE_LOCATION = "Save location: ";

  // mosaic dialog constants.
  public static final String MOSAIC_DIALOG_SEED_COUNT_LABEL = "Seed count: ";
  public static final String MOSAIC_INPUT_ERROR_MSG = MENU_INPUT_ERROR_MSG
          + "1. Seed count must be a positive integer.";

  // rainbow dialog constants.
  public static final String RAINBOW_DIALOG_WIDTH_LABEL = "Width: ";
  public static final String RAINBOW_DIALOG_HEIGHT_LABEL = "Height: ";
  public static final String RAINBOW_DIALOG_STRIP_SIZE_LABEL = "Strip size: ";
  public static final String RAINBOW_DIALOG_ORIENTATION_LABEL = "Orientation: ";
  public static final String RAINBOW_DIALOG_REPEAT_LABEL = "Repeat: ";
  public static final String RAINBOW_INPUT_ERROR_MSG = MENU_INPUT_ERROR_MSG
          + "1. Rainbow width, height and strip size must be positive integers.\n"
          + "2. File path must be a non-null string.";

  // checkerboard dialog constants.
  public static final String CHECKERBOARD_DIALOG_BOARD_SIZE_LABEL = "Board size: ";
  public static final String CHECKERBOARD_DIALOG_SQUARE_SIZE_LABEL = "Square size: ";
  public static final String CHECKERBOARD_INPUT_ERROR_MSG = MENU_INPUT_ERROR_MSG
          + "1. Checkerboard size and square size must be positive integers.\n"
          + "2. File path must be a non-null string.";

  // flag dialog constants.
  public static final String FLAG_DIALOG_COUNTRY_LABEL = "Country name: ";
  public static final String FLAG_DIALOG_WIDTH_LABEL = "Flag width: ";
  public static final String FLAG_INPUT_ERROR_MSG = MENU_INPUT_ERROR_MSG
          + "1. Flag width must be a positive integer.\n"
          + "2. File path must be a non-null string.";

  // DialogImpl constants.
  public static final String DIALOG_SUBMIT_BUTTON = "Submit";
  public static final String DIALOG_CANCEL_BUTTON = "Cancel";
}
