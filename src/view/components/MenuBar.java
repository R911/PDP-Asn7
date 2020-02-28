package view.components;

import javax.swing.JMenuBar;

import view.actions.ActionListener;

/**
 * Interface representing a menu bar that can be attached to any top level swing window.
 *
 * <p>This class provides functionality to attach listeners which will be invoked if any menu
 * item is clicked/activated by the user via the UI.</p>
 */
public interface MenuBar {

  /**
   * Adds the provided listener object as the action listener, which is notified whenever a menu
   * item is clicked/activated.
   *
   * @param listener listener to be added.
   */
  void addActionListener(ActionListener listener);

  /**
   * Returns the underlying JMenuBar for use with other swing components.
   *
   * @return the JMenuBar object used to make the menu.
   */
  JMenuBar getMenuBar();
}
