package view.components;

import java.awt.Image;

import javax.swing.JComponent;

/**
 * Interface representing a container capable of holding an image and displaying it on the UI.
 */
public interface ImageView {

  /**
   * Set the image being displayed on the UI.
   *
   * @param image AWT image object to be displayed.
   */
  void setImage(Image image);

  /**
   * Returns the JComponent object which is holding the image in the UI. Having the return type
   * as JComponent gives the implementer the flexibility to display the image on the UI using any
   * type of javax.swing components.
   *
   * @return the component holding the image.
   */
  JComponent getImageView();
}
