package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Implementation of the {@link Frame} interface. This class uses a BorderLayout as its layout
 * manager.
 */
public class ImageProcessingFrame implements Frame {
  private JFrame frame;

  /**
   * Instantiates an empty JFrame, and sets the size and layout manager for the same. The
   * underlying JFrame is made to be the same size as the display of the machine where it is
   * running. Thus, the JFrame is full-screen.
   */
  public ImageProcessingFrame() {
    this.frame = new JFrame();

    // initialize the top level container
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.frame.setPreferredSize(screenSize);
    this.frame.setLayout(new BorderLayout());
    this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.frame.setUndecorated(false);
    this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // pack
    this.frame.pack();
  }

  @Override
  public JFrame getFrame() {
    return this.frame;
  }

  @Override
  public void addMenuBar(MenuBar menuBar) {
    this.frame.setJMenuBar(menuBar.getMenuBar());
  }

  /**
   * Adds the image view to the JFrame. The image view is added to the center of the JFrame.
   *
   * @param imageView image view to be added.
   */
  @Override
  public void addImageView(ImageView imageView) {
    this.frame.add(imageView.getImageView(), BorderLayout.CENTER);
  }
}
