package controller.command.process;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

import controller.command.ImageCommand;
import dto.image.Image;
import enums.DitherEnum;
import enums.FilterEnum;
import enums.TransformEnum;
import factory.dither.DitherKernelFactory;
import factory.filter.FilterFactory;
import factory.transform.TransformFactory;
import model.ExtendedModel;

/**
 * Concrete implementation of {@link ImageCommand}, this class executes various image processing
 * commands on an input image.
 */
public class ImageProcessingCommand implements ImageCommand {
  private static final String COMMAND_BLUR = "blur";
  private static final String COMMAND_SHARPEN = "sharpen";
  private static final String COMMAND_GREY_SCALE = "greyscale";
  private static final String COMMAND_SEPIA = "sepia";
  private static final String COMMAND_DITHER = "dither";
  private static final String COMMAND_JJN_DITHER = "jjn_dither";
  private static final String COMMAND_MOSAIC = "mosaic";
  private Scanner scanner;
  private HashMap<String, Function<Scanner, ImageCommand>> commands;

  /**
   * Constructor for creating an {@link ImageProcessingCommand}.
   *
   * @param scanner for user inputs.
   * @param image to be processed.
   */
  public ImageProcessingCommand(Scanner scanner, Image image) {
    this.scanner = scanner;
    this.commands = new HashMap<>();

    commands.put(COMMAND_BLUR, a -> new Blur(image));
    commands.put(COMMAND_SHARPEN, a -> new Sharpen(image));
    commands.put(COMMAND_SEPIA, a -> new Sepia(image));
    commands.put(COMMAND_GREY_SCALE, a -> new GreyScale(image));
    commands.put(COMMAND_DITHER, a -> new FSDither(image));
    commands.put(COMMAND_JJN_DITHER, a -> new JJNDither(image));
    commands.put(COMMAND_MOSAIC, a -> new Mosaic(a.nextInt(), image));
  }

  /**
   * This method executes the image command.
   *
   * @param m model to process image.
   * @return processed Image.
   */
  @Override
  public Image execute(ExtendedModel m) {
    String commandStr = this.scanner.next().trim().toLowerCase();
    if (!commands.containsKey(commandStr)) {
      throw new IllegalArgumentException("Kindly enter a valid image processing command!");
    }

    return commands.get(commandStr).apply(scanner).execute(m);
  }

  /**
   * Class for producing a blur effect on an image.
   */
  private static class Blur implements ImageCommand {
    private Image image;

    /**
     * Constructor for producing a Blur image.
     *
     * @param image to be blurred.
     */
    Blur(Image image) {
      this.image = image;
    }

    /**
     * This method executes the blur command on the image.
     *
     * @param m model for processing image
     * @return blurred image
     */
    @Override
    public Image execute(ExtendedModel m) {
      return m.applyFilter(FilterFactory.getFilter(FilterEnum.BLUR), this.image);
    }
  }

  /**
   * Class for sharpening an image.
   */
  private static class Sharpen implements ImageCommand {
    private Image image;

    /**
     * Constructor for sharpening an image.
     *
     * @param image to be sharpened.
     */
    Sharpen(Image image) {
      this.image = image;
    }

    /**
     * This method executes a sharpen command on the image.
     *
     * @param m model for sharpening image
     * @return sharpened image
     */
    @Override
    public Image execute(ExtendedModel m) {
      return m.applyFilter(FilterFactory.getFilter(FilterEnum.SHARPEN), this.image);
    }
  }

  /**
   * Class for adding a sepia transformation on an image.
   */
  private static class Sepia implements ImageCommand {
    private Image image;

    /**
     * Constructor for adding a sepia effect.
     *
     * @param image to be applied the sepia effect on.
     */
    Sepia(Image image) {
      this.image = image;
    }

    /**
     * This method executes the sepia effect on an image.
     *
     * @param m model for applying sepia effect
     * @return sepia image.
     */
    @Override
    public Image execute(ExtendedModel m) {
      return m.applyTransform(TransformFactory.getTransformer(TransformEnum.SEPIA), this.image);
    }
  }

  /**
   * Class for applying greyscale transformation on an image.
   */
  private static class GreyScale implements ImageCommand {
    private Image image;

    /**
     * Constructor for applying a greyscale transformation.
     *
     * @param image to be grey scaled
     */
    GreyScale(Image image) {
      this.image = image;
    }

    /**
     * This method executes the greyscale command on an image.
     *
     * @param m model for grey scaling an image
     * @return grey scaled image
     */
    @Override
    public Image execute(ExtendedModel m) {
      return m.applyTransform(TransformFactory.getTransformer(TransformEnum.GREYSCALE), this.image);
    }
  }

  /**
   * Class for applying Floyd Steinberg dithering on an image.
   */
  private static class FSDither implements ImageCommand {
    private Image image;

    /**
     * Constructor for applying Floyd Steinberg dithering.
     *
     * @param image to be dithered.
     */
    FSDither(Image image) {
      this.image = image;
    }

    /**
     * This method executes Floyd Steinberg dithering on an image.
     *
     * @param m model for dithering
     * @return dithered image
     */
    @Override
    public Image execute(ExtendedModel m) {
      return m.applyDither(DitherKernelFactory.getDitherKernel(DitherEnum.FS_DITHER), this.image);
    }
  }

  /**
   * Class for applying Jarvis, Judice and Ninke dithering on image.
   */
  private static class JJNDither implements ImageCommand {
    private Image image;

    /**
     * Constructor for creating Jarvis, Judice and Ninke dithering.
     *
     * @param image to be dithered.
     */
    JJNDither(Image image) {
      this.image = image;
    }

    /**
     * This method executes JJN dithering on an image.
     *
     * @param m model for dithering
     * @return dithered image.
     */
    @Override
    public Image execute(ExtendedModel m) {
      return m.applyDither(DitherKernelFactory.getDitherKernel(DitherEnum.JJN_DITHER), this.image);
    }
  }

  /**
   * Class for converting an image into a mosaic.
   */
  private static class Mosaic implements ImageCommand {
    private Image image;
    private int seedCount;

    /**
     * Constructor for creating a mosaic.
     *
     * @param seedCount of the mosaic
     * @param image converted to mosaic
     */
    Mosaic(int seedCount, Image image) {
      this.image = image;
      this.seedCount = seedCount;
    }

    /**
     * This method converts an image to mosaic.
     *
     * @param m model for applying mosaic
     * @return mosaic image
     */
    @Override
    public Image execute(ExtendedModel m) {
      return m.applyMosaic(seedCount, image);
    }
  }
}