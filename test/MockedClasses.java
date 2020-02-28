import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import controller.Controller;
import controller.ExtendedControllerImpl;
import controller.Features;
import dto.image.Canvas;
import dto.image.Image;
import dto.image.Image2D;
import enums.FlagEnum;
import model.ExtendedModel;
import model.ExtendedModelImpl;
import view.View;
import view.ViewImpl;

/**
 * JUNIT Mocking Test class for MVC structure.
 */
public class MockedClasses {

  /**
   * Test cases for mocked view.
   */
  @Test
  public void testMockedView() {
    StringBuilder stringBuilder = new StringBuilder();

    ExtendedModel mockedModel = new ExtendedModelImpl();
    View view = new MockedView(stringBuilder);

    StringBuilder testBuilder = new StringBuilder();

    ExtendedControllerImpl extendedController = new ExtendedControllerImpl(view, mockedModel);

    extendedController.start();

    testBuilder.append("View Set Features Called").append("\n");

    testBuilder.append("View Start Called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.blur();

    testBuilder.append("View Display Error Called with message: ").append(
            "Image not loaded, cannot perform operation").append(
            "\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.sepia();

    testBuilder.append("View Display Error Called with message: ").append(
            "Image not loaded, cannot perform operation").append(
            "\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.loadImage("/tmp/Img1.jpg");

    testBuilder.append("View Set Image Called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.sharpen();

    testBuilder.append("View Set Image Called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.greyScale();

    testBuilder.append("View Set Image Called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.sepia();

    testBuilder.append("View Set Image Called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.dither();

    testBuilder.append("View Set Image Called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.mosaic(200);

    testBuilder.append("View Set Image Called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.checkerBoard(100, 10, "abc");

    testBuilder.append("View Display Message Called with message: ").append("Saved image at: abc")
            .append(
                    "\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.flag("Switzerland", 100, "555");

    testBuilder.append("View Display Message Called with message: ").append("Saved image at: "
            + "555").append(
            "\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.rainbow(100, 200, 10, 'H', false, "rain");

    testBuilder.append("View Display Message Called with message: ").append("Saved image at: "
            + "rain").append(
            "\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

  }

  /**
   * Test for mocking the model.
   */
  @Test
  public void testMockedModel() {
    StringBuilder stringBuilder = new StringBuilder();

    ExtendedModel mockedModel = new MockedModel(stringBuilder);
    View view = new ViewImpl();

    StringBuilder testBuilder = new StringBuilder();

    Image image = new Image2D(new Canvas(10, 10), 3, "Mocked");

    ExtendedControllerImpl extendedController = new ExtendedControllerImpl(view, mockedModel);

    extendedController.start();

    extendedController.loadImage("/tmp/Img1.jpg");

    extendedController.blur();

    testBuilder.append("Model Filter called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.sharpen();

    testBuilder.append("Model Filter called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.greyScale();

    testBuilder.append("Model Transform called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.sepia();

    testBuilder.append("Model Transform called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.dither();

    testBuilder.append("Model Dither called").append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.mosaic(200);

    testBuilder.append("Model Mosaic called with seed count: ").append(200).append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.checkerBoard(100, 10, "abc");

    testBuilder.append("Model Draw checkerboard called with board size: ").append(100)
            .append(" ").append("square ").append("size: ").append(10).append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.flag("Switzerland", 100, "555");

    testBuilder.append("Model Draw Flag called for country: ").append("SWITZERLAND")
            .append(" flag width: ").append(100).append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

    extendedController.rainbow(100, 200, 10, 'H', false, "rain");

    testBuilder.append("Model Draw rainbow called with width: ").append(100).append(
            " height: ").append(200).append("stripe size: ").append(10).append(
            " orientation: ").append('H').append(" repeat: ").append(false).append("\n");

    Assert.assertEquals(testBuilder.toString(), stringBuilder.toString());

  }

  /**
   * Mock implementation of the controller and feature interfaces.
   */
  public static class MockedController implements Controller, Features {

    private StringBuilder stringBuilder;
    private View view;
    private ExtendedModel extendedModel;

    MockedController(View view, ExtendedModel model, StringBuilder sb) {
      this.stringBuilder = sb;
      this.view = view;
      this.extendedModel = model;
    }

    @Override
    public void start() throws IOException {
      stringBuilder.append("Controller start method called").append("\n");
      view.setFeatures(this);
      view.start();
    }

    @Override
    public void blur() {
      stringBuilder.append("Controller blur feature called").append("\n");
    }

    @Override
    public void sharpen() {
      stringBuilder.append("Controller sharpen feature called").append("\n");
    }

    @Override
    public void greyScale() {
      stringBuilder.append("Controller grey scale feature called").append("\n");
    }

    @Override
    public void sepia() {
      stringBuilder.append("Controller sepia feature called").append("\n");
    }

    @Override
    public void dither() {
      stringBuilder.append("Controller dither feature called").append("\n");
    }

    @Override
    public void mosaic(int seedCount) {
      stringBuilder.append("Controller mosaic feature called with seed count: ").append(seedCount)
              .append("\n");
    }

    @Override
    public void checkerBoard(int boardSize, int squareSize, String filePath) {
      stringBuilder.append("Controller Draw checkerboard called with board size: ").append(
              boardSize).append(" square size: ").append(squareSize).append(" file path: ").append(
              filePath).append("\n");
    }

    @Override
    public void rainbow(int width, int height, int stripeSize, char orientation, boolean repeat,
            String filepath) {
      stringBuilder.append("Controller Draw rainbow called with width: ").append(width).append(
              " height: ").append(height).append("stripe size: ").append(stripeSize).append(
              " orientation: ").append(orientation).append(" repeat: ").append(repeat).append(
              " file path: ").append(filepath).append("\n");
    }

    @Override
    public void flag(String country, int flagWidth, String filePath) {
      stringBuilder.append("Controller Draw Flag called for country: ").append(country).append(
              " flag width: ").append(flagWidth).append(" file path: ").append(filePath).append(
              "\n");
    }

    @Override
    public void loadImage(String filePath) {
      stringBuilder.append("Controller load image feature called with file path: ").append(
              filePath).append("\n");
    }

    @Override
    public void saveImage(String filePath) {
      stringBuilder.append("Controller save image feature called with file path: ").append(
              filePath).append("\n");
    }
  }

  /**
   * Mock implementation of the extended model interface.
   */
  public static class MockedModel implements ExtendedModel {

    private StringBuilder stringBuilder;

    MockedModel(StringBuilder sb) {
      this.stringBuilder = sb;
    }

    @Override
    public Image applyDither(float[][] ditherKernel, Image image) {
      Image image1 = new Image2D(new Canvas(10, 10), 0, "Dither");
      stringBuilder.append("Model Dither called").append("\n");
      return image1;
    }

    @Override
    public Image applyMosaic(int seedCount, Image image) {
      Image image1 = new Image2D(new Canvas(10, 10), 0, "Mosaic");
      stringBuilder.append("Model Mosaic called with seed count: ").append(seedCount).append("\n");
      return image1;
    }

    @Override
    public Image applyTransform(float[][] transformArr, Image image) {
      Image image1 = new Image2D(new Canvas(10, 10), 0, "Transform");
      stringBuilder.append("Model Transform called").append("\n");
      return image1;
    }

    @Override
    public Image applyFilter(float[][] filterArr, Image image) {
      Image image1 = new Image2D(new Canvas(10, 10), 0, "Filter");
      stringBuilder.append("Model Filter called").append("\n");
      return image1;
    }

    @Override
    public Image drawCheckerBoard(int boardSize, int squareSize) {
      Image image1 = new Image2D(new Canvas(10, 10), 0, "Checkerboard");
      stringBuilder.append("Model Draw checkerboard called with board size: ").append(boardSize)
              .append(" ").append("square ").append("size: ").append(squareSize).append("\n");
      return image1;
    }

    @Override
    public Image drawRainbow(int width, int height, int stripeSize, char orientation,
            boolean repeat) {
      Image image1 = new Image2D(new Canvas(10, 10), 0, "Rainbow");
      stringBuilder.append("Model Draw rainbow called with width: ").append(width).append(
              " height: ").append(height).append("stripe size: ").append(stripeSize).append(
              " orientation: ").append(orientation).append(" repeat: ").append(repeat).append("\n");
      return image1;
    }

    @Override
    public Image drawFlag(FlagEnum countryName, int flagWidth) {
      Image image1 = new Image2D(new Canvas(10, 10), 0, "Flag");
      stringBuilder.append("Model Draw Flag called for country: ").append(countryName.name())
              .append(" flag width: ").append(flagWidth).append("\n");
      return image1;
    }
  }

  /**
   * Mock implementation of the view interface.
   */
  public static class MockedView implements View {

    private StringBuilder stringBuilder;

    MockedView(StringBuilder sb) {
      this.stringBuilder = sb;
    }

    @Override
    public void start() {
      stringBuilder.append("View Start Called").append("\n");
    }

    @Override
    public void setImage(java.awt.Image image) {
      stringBuilder.append("View Set Image Called").append("\n");
    }

    @Override
    public void displayError(String errorMsg) {
      stringBuilder.append("View Display Error Called with message: ").append(errorMsg).append(
              "\n");
    }

    @Override
    public void displayMessage(String message) {
      stringBuilder.append("View Display Message Called with message: ").append(message).append(
              "\n");
    }

    @Override
    public void setFeatures(Features features) {
      stringBuilder.append("View Set Features Called").append("\n");
    }
  }

}
