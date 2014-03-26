package me.husak.tetris;

import java.awt.*;

public class TTetrimino extends Tetrimino {
  public TTetrimino(Point position) {
    super(new Block[]{
            new Block(-1, 0, Color.PINK),
            new Block(0, 0, Color.PINK),
            new Block(1, 0, Color.PINK),
            new Block(0, 1, Color.PINK)
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

  public TTetrimino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "T: " + super.toString();
  }

}
