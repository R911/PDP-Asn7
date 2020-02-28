package model.draw.pattern;

import java.util.ArrayList;
import java.util.List;

import dto.color.BaseColor;
import dto.color.ColorImpl;
import dto.image.Canvas;
import dto.image.Image;
import dto.image.Image2D;
import model.draw.Pattern;
import model.draw.drawable.Drawable;
import model.draw.drawable.Rectangle;

/**
 * Implementation of the {@link Pattern} interface which supports drawing a checkerboard pattern,
 * as defined by https://en.wikipedia.org/wiki/Checkerboard.
 */
public class CheckerBoard implements Pattern {

  private Image checkerboard;
  private int outerSquareSize;
  private int innerSquareSize;
  private BaseColor checkerBoardColor;
  private BaseColor boardColor;

  /**
   * Instantiates a new checkerboard given the size of the board and the size of the square.
   *
   * @param outerSquareSize size of the board.
   * @param innerSquareSize size of the square.
   */
  public CheckerBoard(int outerSquareSize, int innerSquareSize) {
    if (outerSquareSize % innerSquareSize > 0) {
      throw new IllegalArgumentException("Checkerboard sizes must be proportional!");
    }
    if (outerSquareSize / innerSquareSize < 2) {
      throw new IllegalArgumentException("At least four squares must be present!");
    }

    this.innerSquareSize = innerSquareSize;
    this.outerSquareSize = outerSquareSize;
    this.checkerboard = new Image2D(new Canvas(outerSquareSize, outerSquareSize), 3, "png");

    int[] blackColor = {0, 0, 0, 255};
    this.checkerBoardColor = new ColorImpl(blackColor);

    int[] whiteColor = {255, 255, 255, 255};
    this.boardColor = new ColorImpl(whiteColor);
  }

  @Override
  public Image draw() {
    int numberOfSquaresInARow = outerSquareSize / innerSquareSize;
    List<Drawable> drawables = new ArrayList<>();

    int xOrigin = outerSquareSize - innerSquareSize;
    int yOrigin = 0;

    for (int i = 0; i < numberOfSquaresInARow; i++) {
      for (int j = 0; j < numberOfSquaresInARow; j++) {
        BaseColor color;
        if ((i + j) % 2 == 0) {
          color = checkerBoardColor;
        } else {
          color = boardColor;
        }

        drawables.add(createSquare((xOrigin - i * innerSquareSize),
                (yOrigin + j * innerSquareSize), innerSquareSize, color));
      }
    }

    Canvas canvas = this.checkerboard.getData();
    for (Drawable drawable : drawables) {
      drawable.draw(canvas);
    }

    return this.checkerboard;
  }

  private Drawable createSquare(int originX, int originY, int side, BaseColor color) {
    Rectangle rectangle = new Rectangle(originX, originY, color);
    rectangle.setWidth(side);
    rectangle.setHeight(side);
    return rectangle;
  }
}
