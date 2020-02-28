package view.components;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import view.constants.ViewConstants;

/**
 * Class representing a template based Dialog implementation. The template for this dialog
 * implementation is a 2 column grid, where the first column is an object of JLabel, whereas the
 * object in the second column is of type JComponent. This provides flexibility of adding
 * different types of widgets to the dialog box. Also, the layout of the dialog is ideal for
 * creating forms meant to take input from the user. Finally, this class adds a submit and a
 * cancel button after the components provided by the user. These buttons can be used to
 * submit/cancel the input operation respectively.
 *
 * <p>This represents a modal dialog, which blocks any input to the application until it is
 * closed/dismissed. A ramification of this design is that the any thread that calls
 * {@link #makeVisible()} will block until the dialog is closed or dismissed.</p>
 */
public class DialogImpl implements Dialog {
  public static final String USER_INPUT_SUBMIT = "Submit";
  public static final String USER_INPUT_CANCEL = "Cancel";
  private JDialog dialog;
  private String userInputStatus;
  private List<JLabel> labels;
  private List<JComponent> widgets;

  /**
   * Instantiates a new Dialog object. The widgets provided by the user are placed in a JPanel
   * that uses GridBoxLayout as its layout manager. The constructor assumes a one-to-one mapping
   * between the JLabels and the widgets, i.e. for each label, a corresponding widget should be
   * provided. A label and its corresponding JComponent widget are arranged horizontally next to
   * each other, and each new label is on a new line.
   *
   * @param title title for the dialog.
   * @param labels list of JLabel objects.
   * @param widgets list of JComponent objects for use next to the labels.
   */
  public DialogImpl(String title, List<JLabel> labels, List<JComponent> widgets) {
    if (labels.size() != widgets.size()) {
      throw new IllegalArgumentException("Label and widget lists should be of the same size!");
    }

    this.dialog = new JDialog();
    this.labels = labels;
    this.widgets = widgets;

    dialog.setModal(true);
    dialog.setTitle(title);
    dialog.setLayout(new BorderLayout());
    dialog.setResizable(false);
    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    JPanel uiPanel = createPanel();
    addButtons(uiPanel);
    uiPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

    dialog.add(uiPanel, BorderLayout.PAGE_START);
    dialog.pack();

    // center the dialog relative to the screen.
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    dialog.setLocation((screenSize.width) / 2 - dialog.getWidth() / 2,
            (screenSize.height) / 2 - dialog.getHeight() / 2);

    this.userInputStatus = null;
  }

  /**
   * Make the dialog visible.
   *
   * <p>Since this class represents a modal dialog, this method will block until the dialog is
   * either dismissed, or the dialog or closed by clicking the submit button.</p>
   */
  @Override
  public void makeVisible() {
    this.dialog.setVisible(true);
  }

  /**
   * Returns whether the user chose to submit the input via the dialog, or dismissed the dialog
   * by pressing cancel.
   *
   * <p>The specific return values are as follows:
   * <ul>
   *   <li>{@link #USER_INPUT_SUBMIT} if the user pressed the submit button.</li>
   *   <li>{@link #USER_INPUT_CANCEL} if the user pressed the cancel button.</li>
   * </ul>
   * </p>
   *
   * @return string representing the user input.
   */
  @Override
  public String getUserInputStatus() {
    return this.userInputStatus;
  }

  /**
   * Helper method for creating the panel for dialog content.
   *
   * @return created panel.
   */
  private JPanel createPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.EAST;

    for (int i = 0; i < labels.size(); i++) {
      constraints.gridwidth = GridBagConstraints.RELATIVE;
      constraints.fill = GridBagConstraints.NONE;
      constraints.weightx = 0;
      panel.add(labels.get(i), constraints);

      constraints.gridwidth = GridBagConstraints.REMAINDER;
      constraints.fill = GridBagConstraints.HORIZONTAL;
      panel.add(widgets.get(i), constraints);
    }

    return panel;
  }

  /**
   * Helper method for adding the submit and cancel buttons onto the dialog.
   *
   * @param panel JPanel consisting of the other user input widgets.
   */
  private void addButtons(JPanel panel) {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    JButton submitButton = new JButton(ViewConstants.DIALOG_SUBMIT_BUTTON);
    submitButton.addActionListener(e -> {
      userInputStatus = USER_INPUT_SUBMIT;
      dialog.dispose();
    });

    JButton cancelButton = new JButton(ViewConstants.DIALOG_CANCEL_BUTTON);
    cancelButton.addActionListener(e -> {
      userInputStatus = USER_INPUT_CANCEL;
      dialog.dispose();
    });

    buttonPanel.add(submitButton);
    buttonPanel.add(cancelButton);

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.NONE;
    constraints.ipady = 0;
    constraints.weighty = 1.0;
    constraints.anchor = GridBagConstraints.LAST_LINE_END;
    constraints.insets = new Insets(10, 0, 0, 0);
    constraints.gridx = 1;
    constraints.gridwidth = 1;
    constraints.gridy = labels.size(); // setting the y grid position to be below all input labels.
    panel.add(buttonPanel, constraints);
  }
}
