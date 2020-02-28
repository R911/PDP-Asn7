package controller.command.io;

import java.io.IOException;
import java.util.Scanner;

import controller.command.ReadCommand;
import dto.image.Image;
import enums.ImageFileEnums;
import io.ImageReader;

/**
 * Concrete implementation of {@link ReadCommand}, this class reads an image from either a URL or
 * a file path.
 */
public class ImageReadCommand implements ReadCommand {
  private Scanner scanner;

  /**
   * Constructor for creating an {@link ImageReadCommand}.
   *
   * @param scanner for reading user input
   */
  public ImageReadCommand(Scanner scanner) {
    this.scanner = scanner;
  }

  /**
   * This method reads the path given by user and based upon the path type (url/file), it loads
   * that image from that path.
   *
   * @return Image
   * @throws IOException for read errors
   */
  @Override
  public Image execute() throws IOException {
    String path = scanner.next();

    if (path.startsWith("http://")) {
      return ImageReader.readImage(ImageFileEnums.URL, path);
    } else {
      return ImageReader.readImage(ImageFileEnums.FILE, path);
    }
  }
}
