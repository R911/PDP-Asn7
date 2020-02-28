import java.io.File;
import java.io.IOException;
import java.util.Date;

import dto.image.Image;
import enums.FilterEnum;
import enums.FlagEnum;
import enums.ImageFileEnums;
import enums.TransformEnum;
import factory.filter.FilterFactory;
import factory.transform.TransformFactory;
import io.ImageReader;
import io.ImageWriter;
import model.Model;
import model.ModelImpl;

/**
 * Complementary class for running image processing operations supported by the model as a batch.
 *
 * <p>This code was part of Assignment 7 (when there was no controller). Now that the controller
 * has been added, the original {@link Main} has code to start the controller. However, this class
 * has been kept for the sake of showcasing backward compatibility in the Model design. This class
 * continues to use the original Model interface (from Assignment 7), and not the new
 * {@link model.ExtendedModel}. Also, this class serves as the reminder that the interface design
 * needs to be done in a way that allows existing code to run while making upgrades.
 * </p>
 */
public class BatchDriver {
  private static Model model;

  /**
   * Main class for running all the model implementations.
   *
   * @param args arguments. The file path of the image should be provided as one of the
   *         arguments in the args array.
   */
  public static void main(String[] args) {
    if (args.length != 1 || args[0] == null || args[0].length() == 0) {
      System.out.println("Please provide the path of the image file to run!");
      return;
    }

    model = new ModelImpl();
    String imageFilePath = args[0].trim();

    testTransform(imageFilePath, TransformEnum.GREYSCALE);
    testTransform(imageFilePath, TransformEnum.SEPIA);
    testFilter(imageFilePath, FilterEnum.BLUR);
    testFilter(imageFilePath, FilterEnum.SHARPEN);

    testCheckerBoard(20, 100);
    testCheckerBoard(40, 160);
    testCheckerBoard(80, 640);
    testRainbow(1000, 1000, 100, 'H', false);
    testRainbow(1000, 1000, 120, 'H', true);
    testRainbow(1000, 700, 100, 'H', true);
    testRainbow(1000, 1500, 50, 'H', true);
    testRainbow(1200, 1000, 100, 'V', false);
    testRainbow(1000, 1000, 100, 'V', true);
    testRainbow(700, 700, 100, 'V', true);

    testFlag(FlagEnum.SWITZERLAND, 640);
    testFlag(FlagEnum.SWITZERLAND, 1280);
    testFlag(FlagEnum.GREECE, 540);
    testFlag(FlagEnum.GREECE, 810);
    testFlag(FlagEnum.FRANCE, 1200);
    testFlag(FlagEnum.FRANCE, 600);
    testRainbow(1000, 1000, 100, 'H', false);
  }

  private static String getImageImageNameWithoutExtensionFromPath(String imageFilePath) {
    String[] imageArr = imageFilePath.split("/");
    String imageName = imageArr[imageArr.length - 1];
    String[] imageNameWithoutExtensionArr = imageName.split("\\.");
    return imageNameWithoutExtensionArr[0];
  }

  private static void testCheckerBoard(int innerSquareSide, int outerSquareSide) {
    Image image = model.drawCheckerBoard(outerSquareSide, innerSquareSide);
    writeImage(image, "I_" + innerSquareSide + "O_" + outerSquareSide,
            "Checkerboard");
  }

  private static void testRainbow(int width, int height, int stripSize, char orientation,
          boolean repeat) {
    Image image = model.drawRainbow(width, height, stripSize, orientation, repeat);
    writeImage(image, String.valueOf(orientation), "Rainbow");
  }

  private static void testFlag(FlagEnum flagType, int width) {
    Image image = model.drawFlag(flagType, width);
    writeImage(image, flagType.toString(), "Flag");
  }

  private static void testFilter(String input, FilterEnum filterEnum) {
    Image image = readImage(input);
    Image newImage = model.applyFilter(FilterFactory.getFilter(filterEnum), image);
    String imageName = getImageImageNameWithoutExtensionFromPath(input);
    writeImage(newImage, filterEnum.toString(), imageName);
  }

  private static void testTransform(String inputImgPath, TransformEnum transformEnum) {
    Image image = readImage(inputImgPath);
    Image newImage = model.applyTransform(TransformFactory.getTransformer(transformEnum), image);
    String imageName = getImageImageNameWithoutExtensionFromPath(inputImgPath);
    writeImage(newImage, transformEnum.toString(), imageName);
  }

  private static Image readImage(String filePath) {
    Image img = null;
    try {
      img = ImageReader.readImage(ImageFileEnums.FILE, filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return img;
  }

  private static void writeImage(Image img, String operation, String imageName) {
    Date date = new Date();
    String prefix = imageName + "_" + operation + "_" + date.getTime();
    String suffix = "." + img.getBaseImageType();

    try {
      System.out.println("Prefix: " + prefix + " suffix: " + suffix);
      String outputPath = File.createTempFile(prefix, suffix).getAbsolutePath();
      System.out.println("Writing image to: " + outputPath);
      ImageWriter.writeImage(img, outputPath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
