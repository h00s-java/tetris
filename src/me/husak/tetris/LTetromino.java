package me.husak.tetris;

import java.awt.*;

public class LTetromino extends Tetromino {
  public LTetromino(Point position) {
    super(new Block[]{
            new Block(-1, 0, Color.ORANGE),
            new Block(0, 0, Color.ORANGE),
            new Block(1, 0, Color.ORANGE),
            new Block(1, 1, Color.ORANGE)
        },
        position,
        new Point[][]{
            {new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)},
            {new Point(0, 0), new Point(1, 0), new Point(1, -1), new Point(0, 2), new Point(1, 2)},
            {new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)},
            {new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(0, 2), new Point(-1, 2)},
        }
    );
  }

  public LTetromino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "L: " + super.toString();
  }
}
