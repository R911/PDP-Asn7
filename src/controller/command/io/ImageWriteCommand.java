package controller.command.io;

import java.io.IOException;
import java.util.Scanner;

import controller.command.WriteCommand;
import dto.image.Image;
import io.ImageWriter;

/**
 * Concrete implementation of {@link WriteCommand}, this class writes an image to the system file
 * path provided.
 */
public class ImageWriteCommand implements WriteCommand {
  private Scanner scanner;

  /**
   * Constructor for creating an {@link ImageWriteCommand}.
   *
   * @param scanner for reading user input
   */
  public ImageWriteCommand(Scanner scanner) {
    this.scanner = scanner;
  }

  /**
   * This method reads the user input to get the system file path and then writes the image to
   * that path.
   *
   * @param image to be written/saved.
   * @throws IOException if unable to save image.
   */
  @Override
  public void execute(Image image) throws IOException {
    String outputPath = scanner.next();
    boolean success = ImageWriter.writeImage(image, outputPath);
    if (!success) {
      throw new IOException("Failed to write image!");
    }
  }
}
