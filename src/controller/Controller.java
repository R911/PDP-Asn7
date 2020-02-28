package controller;

import java.io.IOException;

/**
 * Controller object for the image processing application. This interface provides the methods
 * that a client application can call to perform image processing actions.
 */
public interface Controller {

  /**
   * Starts the controller. The method returns when the controller is done processing the input
   * or if there is an exception. While the commands are being processed, the method blocks.
   *
   * @throws IOException if there is an IO exception while processing commands.
   */
  void start() throws IOException;
}
