package view.components;

/**
 * Interface representing a general purpose dialog meant to take input from the user via the GUI.
 */
public interface Dialog {

  /**
   * Make the dialog visible. The behavior regarding the dialog's position on the screen is
   * dictated by the implementing class. However, calling this method should ensure that the
   * dialog indeed becomes visible.
   */
  void makeVisible();

  /**
   * Return a string representing the user input. The exact value returned by this method depends
   * on the implementation.
   *
   * @return string representation of the user input.
   */
  String getUserInputStatus();
}
