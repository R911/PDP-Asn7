package controller.command.draw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.command.ImageCommand;
import dto.image.Image;
import enums.FlagEnum;
import model.ExtendedModel;

/**
 * Concrete implementation of {@link ImageCommand}, this class provides commands for drawing
 * various objects/patterns like rainbow, checkerboard, flags, etc.
 */
public class DrawCommand implements ImageCommand {
  private static final String COMMAND_CHECKERBOARD = "checkerboard";
  private static final String COMMAND_RAINBOW = "rainbow";
  private static final String COMMAND_FLAG = "flag";
  private Scanner scanner;
  private Map<String, Function<Scanner, ImageCommand>> commands;

  /**
   * Constructor for creating draw commands. This constructor creates a map of draw commands
   * currently supported by this program.
   *
   * @param scanner for user input.
   */
  public DrawCommand(Scanner scanner) {
    this.scanner = scanner;
    this.commands = new HashMap<>();

    commands.put(COMMAND_CHECKERBOARD, (a -> new CheckerBoard(a.nextInt(), a.nextInt())));

    commands.put(COMMAND_RAINBOW,
            a -> new Rainbow(a.nextInt(), a.nextInt(), a.nextInt(), a.next(".").charAt(0),
                    a.nextBoolean()));

    commands.put(COMMAND_FLAG, a -> new Flag(a.next(), a.nextInt()));
  }

  /**
   * This method executes the draw command to return the drawn image.
   *
   * @param m Model used to draw image.
   * @return resultant image.
   */
  @Override
  public Image execute(ExtendedModel m) {
    String command = scanner.next().trim().toLowerCase();
    if (!commands.containsKey(command)) {
      throw new IllegalArgumentException("Kindly provide a valid draw command!");
    }

    return commands.get(command).apply(this.scanner).execute(m);
  }

  /**
   * Private class for creating a Rainbow, it Implements an {@link ImageCommand}.
   */
  private static class Rainbow implements ImageCommand {
    private boolean repeat;
    private int width;
    private int height;
    private int stripeSize;
    private char orientation;

    /**
     * Constructor for creating a rainbow.
     *
     * @param width of image
     * @param height of image
     * @param stripeSize of rainbow
     * @param orientation horizontal/vertical
     * @param repeat of pattern required
     */
    Rainbow(int width, int height, int stripeSize, char orientation, boolean repeat) {
      this.width = width;
      this.height = height;
      this.stripeSize = stripeSize;
      this.orientation = orientation;
      this.repeat = repeat;
    }

    /**
     * This method draws a rainbow.
     *
     * @param m model used to dra rainbow
     * @return Image of rainbow
     */
    @Override
    public Image execute(ExtendedModel m) {
      return m.drawRainbow(width, height, stripeSize, orientation, repeat);
    }
  }

  /**
   * Private class for creating a checkerboard, this class implements an {@link ImageCommand}.
   */
  private static class CheckerBoard implements ImageCommand {
    private int boardSize;
    private int squareSize;

    /**
     * Constructor for creating a checkerboard.
     *
     * @param boardSize of checkerboard.
     * @param squareSize of checkerboard.
     */
    CheckerBoard(int boardSize, int squareSize) {
      this.boardSize = boardSize;
      this.squareSize = squareSize;
    }

    /**
     * This method draws a checkerboard.
     *
     * @param m Model to draw a checkerboard.
     * @return Image of checkerboard.
     */
    @Override
    public Image execute(ExtendedModel m) {
      return m.drawCheckerBoard(boardSize, squareSize);
    }
  }

  /**
   * Private class for drawing a flag. This class implements an {@link ImageCommand}.
   */
  private static class Flag implements ImageCommand {
    private int flagWidth;
    private FlagEnum flagType;

    /**
     * Constructor for creating a flag.
     *
     * @param flagType to be created.
     * @param flagWidth required.
     */
    Flag(String flagType, int flagWidth) {
      try {
        this.flagWidth = flagWidth;
        this.flagType = FlagEnum.valueOf(flagType.trim().toUpperCase());
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Unsupported flag type");
      }
    }

    /**
     * Method to draw a flag.
     *
     * @param m model for drawing a flag.
     * @return image of flag.
     */
    @Override
    public Image execute(ExtendedModel m) {
      return m.drawFlag(flagType, flagWidth);
    }
  }
}
