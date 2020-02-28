package view.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Widget that combines a button with a text field for taking file path input from users. The
 * button, when clicked, opens a file save dialog which can be used to take file inputs.
 */
public class FilePickerWidget extends JPanel {

  /**
   * Key to use to retrieve the file path in the getClientProperty method.
   */
  public static final String FILE_PATH_KEY = "FilePath";
  private ImageFilePicker imageFilePicker;

  /**
   * Instantiates a new widget, with an empty text view and a button to open the file picker.
   */
  public FilePickerWidget() {
    this.imageFilePicker = new ImageFilePicker();

    JTextField textField = new JTextField(20);
    textField.setEditable(false);

    JButton button = new JButton("...");
    button.addActionListener(e -> {
      // show file picker dialog.
      String chosenFile = imageFilePicker.showSaveDialog(null);
      if (chosenFile != null) {
        textField.setText(chosenFile);
        putClientProperty(FILE_PATH_KEY, chosenFile);
      }
    });

    setLayout(new GridBagLayout());

    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.VERTICAL;
    constraints.gridx = 1;
    constraints.gridy = 0;
    add(button, constraints);

    constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 0;
    constraints.gridy = 0;
    add(textField, constraints);
  }
}
