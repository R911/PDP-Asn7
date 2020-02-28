package view.components;

import javax.swing.JFrame;

/**
 * Interface representing a top level JFrame to be used by the Image processing application. The
 * frame offers functionality to set a menu bar and to populate an ImageView in the frame. The
 * layout characteristics of the frame depend on the implementation.
 *
 * <p>Specifically, the frame should have a menu bar and a container of type {@link ImageView}
 * for holding an image.
 * </p>
 */
public interface Frame {

  /**
   * Returns the underlying JFrame encapsulated by the Frame object.
   *
   * @return JFrame object.
   */
  JFrame getFrame();

  /**
   * Adds a {@link MenuBar} object as the menu bar for the JFrame.
   *
   * @param menuBar menu bar object.
   */
  void addMenuBar(MenuBar menuBar);

  /**
   * Adds the image view provided as the argument to the JFrame.
   *
   * @param imageView image view to be added.
   */
  void addImageView(ImageView imageView);
}
