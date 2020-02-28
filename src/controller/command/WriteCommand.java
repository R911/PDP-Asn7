package controller.command;

import java.io.IOException;

import dto.image.Image;

/**
 * This image writes(saves) an image provided to it.
 */
public interface WriteCommand {
  /**
   * This method writes an image provided to it.
   *
   * @param image to be written
   * @throws IOException while writing an image.
   */
  void execute(Image image) throws IOException;
}
