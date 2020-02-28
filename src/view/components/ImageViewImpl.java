package view.components;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 * Implementation of the {@link ImageView} interface. This class provides a scrollable image
 * view, with the image in the center of the component, and vertical and horizontal scroll bars
 * appear if the image dimensions are larger than the viewport.
 */
public class ImageViewImpl implements ImageView {
  private JLabel imageLabel;
  private JScrollPane scrollableImagePane;

  /**
   * Instantiates an empty image view, along with the scroll component.
   */
  public ImageViewImpl() {
    this.imageLabel = new JLabel();
    this.imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    this.scrollableImagePane = new JScrollPane(imageLabel);
    scrollableImagePane.setHorizontalScrollBarPolicy(
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollableImagePane.setVerticalScrollBarPolicy(
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
  }

  @Override
  public void setImage(Image image) {
    this.imageLabel.setIcon(new ImageIcon(image));
  }

  /**
   * Returns the scroll pane that holds the image.
   *
   * @return scroll pane holding the image.
   */
  @Override
  public JComponent getImageView() {
    return this.scrollableImagePane;
  }
}
