package me.husak.tetris;

import java.awt.*;

public class ITetrimino extends Tetrimino {
  public ITetrimino(Point position) {
    super(new Block[]{
            new Block(-1, 0, Color.CYAN),
            new Block(0, 0, Color.CYAN),
            new Block(1, 0, Color.CYAN),
            new Block(2, 0, Color.CYAN)
        },
        position,
        new Point[][]{
            {new Point(0, 0), new Point(-1, 0), new Point(2, 0), new Point(-1, 0), new Point(2, 0)},
            {new Point(-1, 0), new Point(0, 0), new Point(0, 0), new Point(0, 1), new Point(0, -2)},
            {new Point(-1, 1), new Point(1, 1), new Point(-2, 1), new Point(1, 0), new Point(-2, 0)},
            {new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, -1), new Point(0, 2)},
        }
    );
  }

  public ITetrimino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "I: " + super.toString();
  }
}
