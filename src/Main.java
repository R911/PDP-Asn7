import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import controller.Controller;
import controller.ControllerImpl;
import controller.ExtendedControllerImpl;
import model.ExtendedModel;
import model.ExtendedModelImpl;
import view.View;
import view.ViewImpl;

/**
 * Starting point for the program.
 */
public class Main {

  /**
   * Main class for running all the model implementations.
   *
   * @param args arguments.
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 1 && args[0] != null) {
      if (args[0].equalsIgnoreCase("in")) {
        handleCLIInput();
      } else if (args[0].equalsIgnoreCase("-interactive")) {
        handleInteractiveInput();
      } else {
        exitWithErrorMsg();
      }
    } else if (args.length == 2 && args[0] != null && args[1] != null) {
      if (args[0].equalsIgnoreCase("-script")) {
        handleScriptInput(args[1]);
      } else {
        exitWithErrorMsg();
      }
    } else {
      exitWithErrorMsg();
    }
  }

  private static void handleInteractiveInput() throws IOException {
    ExtendedModel model = new ExtendedModelImpl();
    View view = new ViewImpl();
    Controller controller = new ExtendedControllerImpl(view, model);
    controller.start();
  }

  private static void handleScriptInput(String scriptFilePath) throws IOException {
    File file = new File(scriptFilePath);
    InputStream inputStream = new FileInputStream(file);
    ExtendedModel model = new ExtendedModelImpl();
    Controller controller = new ControllerImpl(inputStream, model);
    controller.start();
  }

  private static void handleCLIInput() throws IOException {
    InputStream inputStream = System.in;
    ExtendedModel model = new ExtendedModelImpl();
    Controller controller = new ControllerImpl(inputStream, model);
    controller.start();
  }

  private static void exitWithErrorMsg() {
    String errorMsg = "Invalid arguments. Supported arguments are:\n"
            + "1. -script <path-to-script>\n"
            + "2. -interactive";
    System.out.println(errorMsg);
    System.exit(1);
  }
}
