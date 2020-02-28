package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import controller.Features;
import util.VoidFunction;
import view.components.Frame;
import view.components.ImageProcessingFrame;
import view.components.ImageView;
import view.components.ImageViewImpl;
import view.components.ImageProcessingMenuBar;
import view.components.MenuBar;
import view.utils.ViewWorker;

import static view.constants.ViewConstants.DRAW_MENU_CHECKERBOARD;
import static view.constants.ViewConstants.DRAW_MENU_FLAG;
import static view.constants.ViewConstants.DRAW_MENU_RAINBOW;
import static view.constants.ViewConstants.FILE_MENU_OPEN;
import static view.constants.ViewConstants.FILE_MENU_SAVE;
import static view.constants.ViewConstants.IMAGE_MENU_BLUR;
import static view.constants.ViewConstants.IMAGE_MENU_DITHER;
import static view.constants.ViewConstants.IMAGE_MENU_GREYSCALE;
import static view.constants.ViewConstants.IMAGE_MENU_MOSAIC;
import static view.constants.ViewConstants.IMAGE_MENU_SEPIA;
import static view.constants.ViewConstants.IMAGE_MENU_SHARPEN;
import static view.constants.ViewConstants.MENU_INPUT_ERROR;

/**
 * Implementation of the {@link View} interface. This view implementation provides all the
 * operations needed by the image processing UI. Also, any user action is carried out by the use
 * of background threads. So, the UI remains functional while the operations are carried out
 * asynchronously, and the results are displayed when available.
 */
public class ViewImpl implements View {
  private Frame applicationUI;
  private ImageView imageView;
  private MenuBar menuBar;
  private Features features;

  @Override
  public void start() {
    if (features == null) {
      throw new IllegalStateException("Features not set. Cannot start view!");
    }

    SwingUtilities.invokeLater(() -> {
      menuBar = new ImageProcessingMenuBar();
      menuBar.addActionListener(action -> {
        // call the controller.
        List<String> metadata = action.getMetadata();
        switch (action.getActionCommand()) {
          case FILE_MENU_OPEN:
            scheduleBackgroundJob(() -> features.loadImage(action.getMetadata().get(0)));
            break;
          case FILE_MENU_SAVE:
            scheduleBackgroundJob(() -> features.saveImage(action.getMetadata().get(0)));
            break;
          case IMAGE_MENU_BLUR:
            scheduleBackgroundJob(() -> features.blur());
            break;
          case IMAGE_MENU_SHARPEN:
            scheduleBackgroundJob(() -> features.sharpen());
            break;
          case IMAGE_MENU_SEPIA:
            scheduleBackgroundJob(() -> features.sepia());
            break;
          case IMAGE_MENU_GREYSCALE:
            scheduleBackgroundJob(() -> features.greyScale());
            break;
          case IMAGE_MENU_DITHER:
            scheduleBackgroundJob(() -> features.dither());
            break;
          case IMAGE_MENU_MOSAIC:
            scheduleBackgroundJob(
                    () -> features.mosaic(Integer.parseInt(action.getMetadata().get(0))));
            break;
          case DRAW_MENU_RAINBOW:
            scheduleBackgroundJob(() -> features.rainbow(Integer.parseInt(metadata.get(0)),
                    Integer.parseInt(metadata.get(1)), Integer.parseInt(metadata.get(2)),
                    metadata.get(3).charAt(0), Boolean.parseBoolean(metadata.get(4)),
                    metadata.get(5)));
            break;
          case DRAW_MENU_CHECKERBOARD:
            scheduleBackgroundJob(() -> features.checkerBoard(Integer.parseInt(metadata.get(0)),
                    Integer.parseInt(metadata.get(1)), metadata.get(2)));
            break;
          case DRAW_MENU_FLAG:
            scheduleBackgroundJob(() -> features
                    .flag(metadata.get(0), Integer.parseInt(metadata.get(1)), metadata.get(2)));
            break;
          case MENU_INPUT_ERROR:
            displayError(action.getMetadata().get(0));
            break;
          default:
            throw new IllegalArgumentException("Unsupported menu operation!");
        }
      });

      imageView = new ImageViewImpl();
      applicationUI = new ImageProcessingFrame();
      applicationUI.addMenuBar(menuBar);
      applicationUI.addImageView(imageView);

      applicationUI.getFrame().setVisible(true);
    });
  }

  @Override
  public void setImage(Image image) {
    // always run on event dispatch thread.
    SwingUtilities.invokeLater(() -> imageView.setImage(image));
  }

  @Override
  public void displayError(String errorMsg) {
    SwingUtilities.invokeLater(() -> createMessageDialog("Error!", errorMsg).setVisible(true));
  }

  @Override
  public void displayMessage(String message) {
    SwingUtilities.invokeLater(() -> createMessageDialog("Info", message).setVisible(true));
  }

  @Override
  public void setFeatures(Features features) {
    this.features = features;
  }

  /**
   * Helper method to schedule the jobs on background threads.
   *
   * @param runnable an object of {@link Void} runnable.
   */
  private void scheduleBackgroundJob(VoidFunction runnable) {
    new ViewWorker(aVoid -> {
      runnable.run();
      return null;
    }).execute();
  }

  /**
   * Helper method to create a dialog with the given title and message.
   *
   * @param title title of the dialog.
   * @param message message of the dialog.
   * @return the dialog object.
   */
  private Dialog createMessageDialog(String title, String message) {
    JTextArea textArea = new JTextArea(message);
    textArea.setEditable(false);
    textArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    textArea.setBackground(new Color(238, 238, 238, 255));

    JDialog dialog = new JDialog(applicationUI.getFrame());
    dialog.setTitle(title);
    dialog.setLayout(new BorderLayout());
    dialog.add(textArea, BorderLayout.CENTER);
    dialog.pack();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    dialog.setLocation((screenSize.width) / 2 - dialog.getWidth() / 2,
            (screenSize.height) / 2 - dialog.getHeight() / 2);
    dialog.setVisible(true);

    return dialog;
  }
}
