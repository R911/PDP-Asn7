package controller.command;

import java.io.IOException;

import dto.image.Image;

/**
 * Interface for reading an image.
 */
public interface ReadCommand {
  /**
   * This method reads an image from various sources.
   *
   * @return Image
   * @throws IOException during reading an image.
   */
  Image execute() throws IOException;
}
