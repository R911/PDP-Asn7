package model.draw;

import dto.image.Image;

/**
 * Interface representing classes which are capable of drawing up an image given the requirements.
 * The nature of what is drawn is not fixed: the only requirement is that the implementations
 * should emit an Image object representing what was drawn.
 */
public interface Pattern {
  /**
   * Method for supporting drawing of arbitrary images.
   *
   * @return image representing the outcome of the drawing operation.
   */
  Image draw();
}
