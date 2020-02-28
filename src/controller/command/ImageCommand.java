package controller.command;

import dto.image.Image;
import model.ExtendedModel;

/**
 * Interface for an image command used to either process or draw an image.
 */
public interface ImageCommand {
  /**
   * This method executes a model to produce an image.
   *
   * @param m model to be applied
   * @return modified/created image.
   */
  Image execute(ExtendedModel m);
}
