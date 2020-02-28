package view.actions;

/**
 * The listener interface for receiving action events. The class that is interested in processing
 * an action event implements this interface, and the object created with that class is registered
 * with a component, using the component's addActionListener method.
 */
public interface ActionListener {
  /**
   * Invoked when an action occurs.
   *
   * @param action object representing the action that was performed, and containing the
   *         relevant
   *         data.
   */
  void onActionPerformed(Action action);
}
