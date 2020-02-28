package view.components;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import enums.FlagEnum;
import view.actions.Action;
import view.actions.ActionListener;

import static view.constants.ViewConstants.CHECKERBOARD_DIALOG_BOARD_SIZE_LABEL;
import static view.constants.ViewConstants.CHECKERBOARD_DIALOG_SQUARE_SIZE_LABEL;
import static view.constants.ViewConstants.CHECKERBOARD_INPUT_ERROR_MSG;
import static view.constants.ViewConstants.DIALOG_NAME;
import static view.constants.ViewConstants.DIALOG_SAVE_LOCATION;
import static view.constants.ViewConstants.DRAW_MENU;
import static view.constants.ViewConstants.DRAW_MENU_CHECKERBOARD;
import static view.constants.ViewConstants.DRAW_MENU_FLAG;
import static view.constants.ViewConstants.DRAW_MENU_RAINBOW;
import static view.constants.ViewConstants.FILE_MENU;
import static view.constants.ViewConstants.FILE_MENU_OPEN;
import static view.constants.ViewConstants.FILE_MENU_SAVE;
import static view.constants.ViewConstants.FLAG_DIALOG_COUNTRY_LABEL;
import static view.constants.ViewConstants.FLAG_DIALOG_WIDTH_LABEL;
import static view.constants.ViewConstants.FLAG_INPUT_ERROR_MSG;
import static view.constants.ViewConstants.IMAGE_MENU;
import static view.constants.ViewConstants.IMAGE_MENU_BLUR;
import static view.constants.ViewConstants.IMAGE_MENU_DITHER;
import static view.constants.ViewConstants.IMAGE_MENU_GREYSCALE;
import static view.constants.ViewConstants.IMAGE_MENU_MOSAIC;
import static view.constants.ViewConstants.IMAGE_MENU_SEPIA;
import static view.constants.ViewConstants.IMAGE_MENU_SHARPEN;
import static view.constants.ViewConstants.MENU_INPUT_ERROR;
import static view.constants.ViewConstants.MOSAIC_DIALOG_SEED_COUNT_LABEL;
import static view.constants.ViewConstants.MOSAIC_INPUT_ERROR_MSG;
import static view.constants.ViewConstants.RAINBOW_DIALOG_HEIGHT_LABEL;
import static view.constants.ViewConstants.RAINBOW_DIALOG_ORIENTATION_LABEL;
import static view.constants.ViewConstants.RAINBOW_DIALOG_REPEAT_LABEL;
import static view.constants.ViewConstants.RAINBOW_DIALOG_STRIP_SIZE_LABEL;
import static view.constants.ViewConstants.RAINBOW_DIALOG_WIDTH_LABEL;
import static view.constants.ViewConstants.RAINBOW_INPUT_ERROR_MSG;

/**
 * Custom implementation of a menu bar for image processing application using javax.swing
 * components. The class encapsulates all the operations regarding building the menu options, as
 * well as responding to the events that occur when the user interacts with the menu options.
 *
 * <p>Any class interested in the menu events must register a {@link ActionListener} via the
 * {@link #addActionListener(ActionListener)} method. The implementation will forward the actions
 * to the listeners in a sequential manner in the order in which they were added.
 * </p>
 */
public class ImageProcessingMenuBar implements MenuBar {
  private List<ActionListener> listeners;
  private java.awt.event.ActionListener actionListener;
  private JMenuBar menuBar;
  private ImageFilePicker imageFilePicker;

  /**
   * Instantiates a new menu bar object, with all menu options for image processing application.
   */
  public ImageProcessingMenuBar() {
    // create a menu bar.
    this.menuBar = new JMenuBar();
    this.listeners = new ArrayList<>();
    this.imageFilePicker = new ImageFilePicker();

    // initialize action listener.
    this.actionListener = createActionListener();

    // initialize all the individual menus and their sub-menus.
    menuBar.add(createFileMenu());
    menuBar.add(createImageMenu());
    menuBar.add(createDrawMenu());
  }

  @Override
  public void addActionListener(ActionListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Action listener cannot be null!");
    }
    this.listeners.add(listener);
  }

  @Override
  public JMenuBar getMenuBar() {
    return this.menuBar;
  }

  private JMenu createFileMenu() {
    JMenu fileMenu = new JMenu(FILE_MENU);

    // add options for opening and saving image, along with listeners.
    JMenuItem openMenuItem = createJMenuItem(FILE_MENU_OPEN, FILE_MENU_OPEN, KeyEvent.VK_O);
    JMenuItem saveMenuItem = createJMenuItem(FILE_MENU_SAVE, FILE_MENU_SAVE, KeyEvent.VK_S);

    fileMenu.add(openMenuItem);
    fileMenu.addSeparator();
    fileMenu.add(saveMenuItem);

    return fileMenu;
  }

  private JMenu createImageMenu() {
    JMenu imageMenu = new JMenu(IMAGE_MENU);

    // add options for all image operations.
    JMenuItem blur = createJMenuItem(IMAGE_MENU_BLUR, IMAGE_MENU_BLUR, KeyEvent.VK_B);
    JMenuItem sharpen = createJMenuItem(IMAGE_MENU_SHARPEN, IMAGE_MENU_SHARPEN, KeyEvent.VK_R);
    JMenuItem sepia = createJMenuItem(IMAGE_MENU_SEPIA, IMAGE_MENU_SEPIA, KeyEvent.VK_E);
    JMenuItem greyScale = createJMenuItem(IMAGE_MENU_GREYSCALE, IMAGE_MENU_GREYSCALE,
            KeyEvent.VK_G);
    JMenuItem dither = createJMenuItem(IMAGE_MENU_DITHER, IMAGE_MENU_DITHER, KeyEvent.VK_D);
    JMenuItem mosaic = createJMenuItem(IMAGE_MENU_MOSAIC, IMAGE_MENU_MOSAIC, KeyEvent.VK_M);

    imageMenu.add(blur);
    imageMenu.addSeparator();
    imageMenu.add(sharpen);
    imageMenu.addSeparator();
    imageMenu.add(sepia);
    imageMenu.addSeparator();
    imageMenu.add(greyScale);
    imageMenu.addSeparator();
    imageMenu.add(dither);
    imageMenu.addSeparator();
    imageMenu.add(mosaic);

    return imageMenu;
  }

  private JMenu createDrawMenu() {
    JMenu drawMenu = new JMenu(DRAW_MENU);
    JMenuItem rainbow = createJMenuItem(DRAW_MENU_RAINBOW, DRAW_MENU_RAINBOW, KeyEvent.VK_A);
    JMenuItem checkerboard = createJMenuItem(DRAW_MENU_CHECKERBOARD, DRAW_MENU_CHECKERBOARD,
            KeyEvent.VK_C);
    JMenuItem flag = createJMenuItem(DRAW_MENU_FLAG, DRAW_MENU_FLAG, KeyEvent.VK_F);

    drawMenu.add(rainbow);
    drawMenu.addSeparator();
    drawMenu.add(checkerboard);
    drawMenu.addSeparator();
    drawMenu.add(flag);

    return drawMenu;
  }

  private JMenuItem createJMenuItem(String text, String action, int mnemonic) {
    JMenuItem menuItem = new JMenuItem(text);
    menuItem.setActionCommand(action);
    menuItem.setMnemonic(mnemonic);
    menuItem.addActionListener(this.actionListener);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(mnemonic, InputEvent.ALT_DOWN_MASK));

    return menuItem;
  }

  private java.awt.event.ActionListener createActionListener() {
    return actionEvent -> {
      Action action;

      try {
        switch (actionEvent.getActionCommand()) {
          case FILE_MENU_OPEN:
            action = handleFileOpen();
            break;
          case FILE_MENU_SAVE:
            action = handleFileSave();
            break;
          case IMAGE_MENU_BLUR:
          case IMAGE_MENU_SHARPEN:
          case IMAGE_MENU_SEPIA:
          case IMAGE_MENU_GREYSCALE:
          case IMAGE_MENU_DITHER:
            action = new Action(actionEvent.getActionCommand(), null);
            break;
          case IMAGE_MENU_MOSAIC:
            action = handleMosaic();
            break;
          case DRAW_MENU_RAINBOW:
            action = handleDrawRainbow();
            break;
          case DRAW_MENU_CHECKERBOARD:
            action = handleDrawCheckerboard();
            break;
          case DRAW_MENU_FLAG:
            action = handleDrawFlag();
            break;
          default:
            throw new IllegalArgumentException("Unsupported menu operation!");
        }
      } catch (IllegalArgumentException e) {
        action = createErrorAction(e);
      }

      if (action != null) {
        Action finalAction = action;
        listeners.forEach(listener -> listener.onActionPerformed(finalAction));
      }
    };
  }

  private Action handleFileOpen() {
    // show file picker.
    String chosenFile = imageFilePicker.showOpenDialog(null);
    List<String> metaData = new ArrayList<>();
    if (chosenFile != null) {
      metaData.add(chosenFile);
      return new Action(FILE_MENU_OPEN, metaData);
    } else {
      return null;
    }
  }

  private Action handleFileSave() {
    // show file picker.
    String saveFile = imageFilePicker.showSaveDialog(null);
    List<String> metaData = new ArrayList<>();
    if (saveFile != null) {
      metaData.add(saveFile);
      return new Action(FILE_MENU_SAVE, metaData);
    } else {
      return null;
    }
  }

  private Action handleMosaic() {
    List<JLabel> labels = new ArrayList<>();
    List<JComponent> widgets = new ArrayList<>();

    JLabel inputLabel = new JLabel(MOSAIC_DIALOG_SEED_COUNT_LABEL);
    JTextField inputField = new JTextField(20);

    labels.add(inputLabel);
    widgets.add(inputField);

    DialogImpl dialog = new DialogImpl(DIALOG_NAME, labels, widgets);
    dialog.makeVisible();

    // collect input.
    String userInputStatus = dialog.getUserInputStatus();
    if (userInputStatus != null && userInputStatus.equals(DialogImpl.USER_INPUT_SUBMIT)) {
      String seedCount = inputField.getText();
      if (isValidInteger(seedCount, true)) {
        List<String> metadata = Collections.singletonList(seedCount);
        return new Action(IMAGE_MENU_MOSAIC, metadata);
      } else {
        throw new IllegalArgumentException(MOSAIC_INPUT_ERROR_MSG);
      }
    }

    return null;
  }

  private Action handleDrawRainbow() {
    List<JLabel> labels = new ArrayList<>();
    List<JComponent> widgets = new ArrayList<>();

    // construct dialog for rainbow.
    String[] labelArr = new String[]{RAINBOW_DIALOG_WIDTH_LABEL, RAINBOW_DIALOG_HEIGHT_LABEL,
                                     RAINBOW_DIALOG_STRIP_SIZE_LABEL,
                                     RAINBOW_DIALOG_ORIENTATION_LABEL, RAINBOW_DIALOG_REPEAT_LABEL};

    for (String label : labelArr) {
      labels.add(new JLabel(label));
      widgets.add(new JTextField(20));
    }

    labels.add(new JLabel(DIALOG_SAVE_LOCATION));
    widgets.add(new FilePickerWidget());

    DialogImpl dialog = new DialogImpl(DIALOG_NAME, labels, widgets);
    dialog.makeVisible();

    String userInputStatus = dialog.getUserInputStatus();
    if (userInputStatus != null && userInputStatus.equals(DialogImpl.USER_INPUT_SUBMIT)) {
      List<String> metadata = new ArrayList<>();

      String input = ((JTextField) widgets.get(0)).getText();
      if (isValidInteger(input, true)) {
        metadata.add(input);
      }

      input = ((JTextField) widgets.get(1)).getText();
      if (isValidInteger(input, true)) {
        metadata.add(input);
      }

      input = ((JTextField) widgets.get(2)).getText();
      if (isValidInteger(input, true)) {
        metadata.add(input);
      }

      input = ((JTextField) widgets.get(3)).getText();
      if (isValidString(input)) {
        metadata.add(input);
      }

      input = ((JTextField) widgets.get(4)).getText();
      if (isValidString(input)) {
        metadata.add(input);
      }

      Object object = widgets.get(5).getClientProperty(FilePickerWidget.FILE_PATH_KEY);
      if (object != null && isValidString(object.toString())) {
        metadata.add(object.toString());
      }

      if (metadata.size() != 6) {
        throw new IllegalArgumentException(RAINBOW_INPUT_ERROR_MSG);
      }

      return new Action(DRAW_MENU_RAINBOW, metadata);
    }

    return null;
  }

  private Action handleDrawCheckerboard() {
    List<JLabel> labels = new ArrayList<>();
    List<JComponent> widgets = new ArrayList<>();

    labels.add(new JLabel(CHECKERBOARD_DIALOG_BOARD_SIZE_LABEL));
    widgets.add(new JTextField(20));
    labels.add(new JLabel(CHECKERBOARD_DIALOG_SQUARE_SIZE_LABEL));
    widgets.add(new JTextField(20));
    labels.add(new JLabel(DIALOG_SAVE_LOCATION));
    widgets.add(new FilePickerWidget());

    DialogImpl dialog = new DialogImpl(DIALOG_NAME, labels, widgets);
    dialog.makeVisible();

    String userInputStatus = dialog.getUserInputStatus();
    if (userInputStatus != null && userInputStatus.equals(DialogImpl.USER_INPUT_SUBMIT)) {
      List<String> metadata = new ArrayList<>();

      String input = ((JTextField) widgets.get(0)).getText();
      if (isValidInteger(input, true)) {
        metadata.add(input);
      }

      input = ((JTextField) widgets.get(1)).getText();
      if (isValidInteger(input, true)) {
        metadata.add(input);
      }

      Object object = widgets.get(2).getClientProperty(FilePickerWidget.FILE_PATH_KEY);
      if (object != null && isValidString(object.toString())) {
        metadata.add(object.toString());
      }

      if (metadata.size() != 3) {
        throw new IllegalArgumentException(CHECKERBOARD_INPUT_ERROR_MSG);
      }

      return new Action(DRAW_MENU_CHECKERBOARD, metadata);
    }

    return null;
  }

  private Action handleDrawFlag() {
    List<JLabel> labels = new ArrayList<>();
    List<JComponent> widgets = new ArrayList<>();

    // create combo box containing names of supported Flags.
    FlagEnum[] flagEnums = FlagEnum.values();
    String[] flags = new String[FlagEnum.values().length];
    for (int i = 0; i < flagEnums.length; i++) {
      flags[i] = flagEnums[i].name();
    }
    JComboBox<String> comboBox = new JComboBox<>(flags);
    comboBox.setEditable(false);

    // add the labels and widgets for dialog box.
    labels.add(new JLabel(FLAG_DIALOG_COUNTRY_LABEL));
    widgets.add(comboBox);
    labels.add(new JLabel(FLAG_DIALOG_WIDTH_LABEL));
    widgets.add(new JTextField(20));
    labels.add(new JLabel(DIALOG_SAVE_LOCATION));
    widgets.add(new FilePickerWidget());

    DialogImpl dialog = new DialogImpl(DIALOG_NAME, labels, widgets);
    dialog.makeVisible();

    String userInputStatus = dialog.getUserInputStatus();
    if (userInputStatus != null && userInputStatus.equals(DialogImpl.USER_INPUT_SUBMIT)) {
      List<String> metadata = new ArrayList<>();

      int selectedIndex = ((JComboBox) widgets.get(0)).getSelectedIndex();
      if (selectedIndex >= 0 && selectedIndex < flags.length) {
        String chosenCountry = flags[selectedIndex];
        metadata.add(chosenCountry);
      }

      String input = ((JTextField) widgets.get(1)).getText();
      if (isValidInteger(input, true)) {
        metadata.add(input);
      }

      Object object = widgets.get(2).getClientProperty(FilePickerWidget.FILE_PATH_KEY);
      if (object != null && isValidString(object.toString())) {
        metadata.add(object.toString());
      }

      if (metadata.size() != 3) {
        throw new IllegalArgumentException(FLAG_INPUT_ERROR_MSG);
      }

      return new Action(DRAW_MENU_FLAG, metadata);
    }

    return null;
  }

  private Action createErrorAction(Throwable e) {
    return new Action(MENU_INPUT_ERROR, Collections.singletonList(e.getMessage()));
  }

  private boolean isValidString(String string) {
    return string != null && string.length() != 0;
  }

  private boolean isValidInteger(String string, boolean enforceNonNegative) {
    try {
      int intVal = Integer.parseInt(string);
      if (enforceNonNegative) {
        return intVal > 0;
      }
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}