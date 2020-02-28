package view.utils;

import java.util.function.Function;

import javax.swing.SwingWorker;

/**
 * Extension of the {@link SwingWorker} abstract class for scheduling long running UI jobs on
 * background threads instead of the main event dispatch thread.
 */
public class ViewWorker extends SwingWorker<Void, Void> {
  private Function<Void, Void> function;

  /**
   * Instantiates a new ViewWorker with the provided function.
   *
   * @param function function to run on the background thread.
   */
  public ViewWorker(Function<Void, Void> function) {
    this.function = function;
  }

  @Override
  protected Void doInBackground() {
    this.function.apply(null);
    return null;
  }
}
