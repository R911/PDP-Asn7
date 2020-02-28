package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import controller.command.draw.DrawCommand;
import controller.command.io.ImageReadCommand;
import controller.command.io.ImageWriteCommand;
import controller.command.process.ImageProcessingCommand;
import dto.image.Image;
import io.ImageWriter;
import model.ExtendedModel;

/**
 * Simple command based implementation of the {@link Controller} interface. The controller takes
 * input from the provided input stream and processes them until the stream is empty. The
 * controller expects the stream to contain character data (i.e. passing a binary stream to this
 * controller is probably a bad idea). The input stream should contain a sequence of image
 * processing commands to be executed one after another.
 */
public class ControllerImpl implements Controller {
  private static final String DRAW_COMMAND = "draw";
  private static final String IMAGE_COMMAND = "process";
  private static final String READ = "read";
  private static final String WRITE = "write";
  private static final String QUIT = "q";
  private Scanner scanner;
  private Image image;
  private ExtendedModel model;

  /**
   * Instantiates the controller object with the provided input stream.
   *
   * @param inputStream stream representing a sequence of commands.
   * @param model The model object using which the commands would be executed. The model
   *         contains the actual logic for implementing the image processing commands.
   */
  public ControllerImpl(InputStream inputStream, ExtendedModel model) {
    this.scanner = new Scanner(inputStream);
    this.model = model;
  }

  @Override
  public void start() throws IOException {
    while (this.scanner.hasNext()) {
      String command = scanner.next();

      switch (command) {
        case DRAW_COMMAND:
          Image drawImage = new DrawCommand(scanner).execute(model);
          ImageWriter.writeImageToTemp(drawImage, ".png");
          break;
        case IMAGE_COMMAND:
          if (image == null) {
            throw new IllegalStateException("Image is null. Kindly load an image before running "
                    + "the image command");
          }
          this.image = new ImageProcessingCommand(scanner, image).execute(model);
          break;
        case READ:
          this.image = new ImageReadCommand(scanner).execute();
          break;
        case WRITE:
          new ImageWriteCommand(scanner).execute(this.image);
          this.image = null;
          break;
        case QUIT:
          break;
        default:
          throw new IllegalArgumentException("Program terminated due to invalid command!");
      }
    }
  }
}
