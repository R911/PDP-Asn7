package view;

import java.awt.Image;

import controller.Features;

/**
 * Interface representing the view of the Image processing application. This class provides
 * methods to start the view UI, as well as operations to set the {@link Features} object for
 * invoking the actual image processing operations and getting the results.
 */
public interface View {

  /**
   * Creates a view on a separate event dispatch thread and makes it visible.
   */
  void start();

  /**
   * Sets the image which could be a result of being loaded by the user via the filesystem, or be
   * the result of image processing operations requested by the user.
   *
   * @param image current image.
   */
  void setImage(Image image);

  /**
   * Method for the clients to display an error message on the UI via a swing JDialog. The error
   * message displayed is provided by the caller.
   *
   * @param errorMsg error message.
   */
  void displayError(String errorMsg);

  /**
   * Method for clients to display an informational message on the UI via a swing JDialog.
   *
   * @param message info message.
   */
  void displayMessage(String message);

  /**
   * Sets the feature object using which the view can request image processing operations
   * requested by the user on the UI.
   *
   * @param features an object which provides the image processing features needed by the
   *         image processing application.
   */
  void setFeatures(Features features);
}
