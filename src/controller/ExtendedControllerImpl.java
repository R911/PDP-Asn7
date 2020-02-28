package controller;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import dto.image.Image;
import enums.DitherEnum;
import enums.FilterEnum;
import enums.FlagEnum;
import enums.ImageFileEnums;
import enums.TransformEnum;
import factory.dither.DitherKernelFactory;
import factory.filter.FilterFactory;
import factory.transform.TransformFactory;
import io.ImageReader;
import io.ImageWriter;
import model.ExtendedModel;
import util.Utils;
import util.VoidFunction;
import view.View;

/**
 * Implementation of the {@link Features} and {@link Controller} interfaces.
 *
 * <p>This class handles all the incoming operations in a FIFO manner. Since the operations may
 * be triggered on multiple threads, the class prevents race conditions by using a queue of
 * operations which is handled in a synchronized manner.</p>
 *
 * <p>Also, if any errors are reported by the model or any other erroneous condition is conveyed
 * to the user via the view object.</p>
 */
public class ExtendedControllerImpl implements Features, Controller {

  private final String IMAGE_NOT_LOADED_ERROR_MSG = "Image not loaded, cannot perform operation";
  private ExtendedModel model;
  private Image image;
  private View view;
  private Queue<VoidFunction> queue;
  private boolean isJobRunning = false;

  /**
   * Instantiates a new feature controller object with an empty queue, given the model and view
   * objects.
   *
   * @param view view object.
   * @param model model object.
   */
  public ExtendedControllerImpl(View view, ExtendedModel model) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model or view cannot be null!");
    }

    this.model = model;
    this.view = view;
    this.queue = new ConcurrentLinkedQueue<>();
  }

  @Override
  public void blur() {
    if (image == null) {
      view.displayError(IMAGE_NOT_LOADED_ERROR_MSG);
      return;
    }

    queue.add(() -> {
      try {
        image = model.applyFilter(FilterFactory.getFilter(FilterEnum.BLUR), image);
        view.setImage(Utils.convertImage(image));
      } catch (Exception e) {
        view.displayError(e.getMessage());
      }
    });

    schedule();
  }

  @Override
  public void sharpen() {
    if (image == null) {
      view.displayError(IMAGE_NOT_LOADED_ERROR_MSG);
      return;
    }

    queue.add(() -> {
      try {
        image = model.applyFilter(FilterFactory.getFilter(FilterEnum.SHARPEN), image);
        view.setImage(Utils.convertImage(image));
      } catch (Exception e) {
        view.displayError(e.getMessage());
      }
    });

    schedule();
  }

  @Override
  public void greyScale() {
    if (image == null) {
      view.displayError(IMAGE_NOT_LOADED_ERROR_MSG);
      return;
    }

    queue.add(() -> {
      try {
        image = model.applyTransform(TransformFactory.getTransformer(TransformEnum.GREYSCALE),
                image);
        view.setImage(Utils.convertImage(image));
      } catch (Exception e) {
        view.displayError(e.getMessage());
      }
    });

    schedule();
  }

  @Override
  public void sepia() {
    if (image == null) {
      view.displayError(IMAGE_NOT_LOADED_ERROR_MSG);
      return;
    }

    queue.add(() -> {
      try {
        image = model.applyTransform(TransformFactory.getTransformer(TransformEnum.SEPIA), image);
        view.setImage(Utils.convertImage(image));
      } catch (Exception e) {
        view.displayError(e.getMessage());
      }
    });

    schedule();
  }

  @Override
  public void dither() {
    if (image == null) {
      view.displayError(IMAGE_NOT_LOADED_ERROR_MSG);
      return;
    }

    queue.add(() -> {
      try {
        image = model.applyDither(DitherKernelFactory.getDitherKernel(DitherEnum.FS_DITHER), image);
        view.setImage(Utils.convertImage(image));
      } catch (Exception e) {
        view.displayError(e.getMessage());
      }
    });

    schedule();
  }

  @Override
  public void mosaic(int seedCount) {
    if (image == null) {
      view.displayError(IMAGE_NOT_LOADED_ERROR_MSG);
      return;
    }

    queue.add(() -> {
      try {
        image = model.applyMosaic(seedCount, image);
        view.setImage(Utils.convertImage(image));
      } catch (Exception e) {
        view.displayError(e.getMessage());
      }
    });

    schedule();
  }

  @Override
  public void checkerBoard(int boardSize, int squareSize, String filePath) {
    queue.add(() -> {
      try {
        Image checkerBoard = model.drawCheckerBoard(boardSize, squareSize);
        save(checkerBoard, filePath);
      } catch (Exception e) {
        view.displayError(e.getMessage());
      }
    });

    schedule();
  }

  @Override
  public void rainbow(int width, int height, int stripeSize, char orientation, boolean repeat,
          String filepath) {
    queue.add(() -> {
      try {
        Image rainbow = model.drawRainbow(width, height, stripeSize, orientation, repeat);
        save(rainbow, filepath);
      } catch (Exception e) {
        view.displayError(e.getMessage());
      }
    });

    schedule();
  }

  @Override
  public void flag(String country, int flagWidth, String filePath) {
    queue.add(() -> {
      try {
        Image flag = model.drawFlag(FlagEnum.valueOf(country.trim().toUpperCase()), flagWidth);
        save(flag, filePath);
      } catch (IllegalArgumentException e) {
        view.displayError(e.getMessage());
      }
    });

    schedule();
  }

  @Override
  public void loadImage(String filePath) {
    queue.add(() -> {
      try {
        image = ImageReader.readImage(ImageFileEnums.FILE, filePath);
        view.setImage(Utils.convertImage(image));
      } catch (IOException e) {
        view.displayError(e.getMessage());
      }
    });

    schedule();
  }

  @Override
  public void saveImage(String filePath) {
    if (image == null) {
      view.displayError(IMAGE_NOT_LOADED_ERROR_MSG);
      return;
    }

    queue.add(() -> {
      try {
        save(image, filePath);
      } catch (Exception e) {
        view.displayError(e.getMessage());
      }
    });

    schedule();
  }

  /**
   * Helper method for scheduling jobs to run them in a FIFO order.
   */
  private synchronized void schedule() {
    if (!isJobRunning) {
      while (!queue.isEmpty()) {
        queue.remove().run();
      }

      isJobRunning = false;
    }
  }

  private void save(Image image, String filePath) {
    try {
      ImageWriter.writeImage(image, filePath);
      view.displayMessage(String.format("Saved image at: %s", filePath));
    } catch (IOException e) {
      view.displayError(e.getMessage());
    }
  }

  @Override
  public void start() {
    view.setFeatures(this);
    view.start();
  }
}
