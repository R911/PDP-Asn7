package view.components;

import java.awt.Component;

/**
 * Interface representing a GUI component which gives the user functionality to open/save files
 * from the user's file system. The type of files supported depends on the implementation. Also,
 * the interface represents file pickers that support selecting a single file.
 */
public interface FilePicker {

  /**
   * Pops up an "Open File" file chooser dialog. Note that the text that appears in the approve
   * button is determined by the L&F (look and feel).
   *
   * @param parent the parent component of the dialog, can be null.
   * @return the complete file path of the chosen file, or null if the user chose nothing.
   */
  String showOpenDialog(Component parent);

  /**
   * Pops up a "Save File" file chooser dialog. Note that the text that appears in the approve
   * button is determined by the L&F.
   *
   * @param parent the parent component of the dialog, can be null.
   * @return the complete file path of the chosen file to save, or null if the user chose nothing.
   */
  String showSaveDialog(Component parent);
}
