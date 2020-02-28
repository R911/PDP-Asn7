package view.components;

import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Implementation of a file picker for opening/saving image files. Thus, the picker filters the
 * files, and only allows jpg, jpeg, png and tiff file extensions.
 *
 * <p>Also, the picker provides the functionality of remembering the last successful save/open
 * location, and any subsequent invocations will open the picker in that location.</p>
 */
public class ImageFilePicker implements FilePicker {
  private String lastOpenPath;
  private String lastSavePath;

  /**
   * Instantiates a new image file picker.
   */
  public ImageFilePicker() {
    this.lastOpenPath = null;
    this.lastSavePath = null;
  }

  @Override
  public String showOpenDialog(Component parent) {
    JFileChooser fileChooser;
    if (lastOpenPath != null) {
      fileChooser = new JFileChooser(lastOpenPath);
    } else {
      fileChooser = new JFileChooser();
    }

    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg",
            "png", "jpeg", "tiff");
    fileChooser.setFileFilter(filter);
    fileChooser.setMultiSelectionEnabled(false);
    fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);

    int returnVal = fileChooser.showOpenDialog(parent);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      lastOpenPath = fileChooser.getCurrentDirectory().getAbsolutePath();

      return fileChooser.getSelectedFile().getAbsolutePath();
    } else {
      return null;
    }
  }

  @Override
  public String showSaveDialog(Component parent) {
    JFileChooser saveFileChooser;
    if (lastSavePath != null) {
      saveFileChooser = new JFileChooser(lastSavePath);
    } else {
      saveFileChooser = new JFileChooser();
    }

    saveFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);

    int saveStatus = saveFileChooser.showSaveDialog(parent);
    if (saveStatus == JFileChooser.APPROVE_OPTION) {
      lastSavePath = saveFileChooser.getCurrentDirectory().getAbsolutePath();
      return saveFileChooser.getSelectedFile().getAbsolutePath();
    } else {
      return null;
    }
  }
}
