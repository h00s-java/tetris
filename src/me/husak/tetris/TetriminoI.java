package me.husak.tetris;

import java.awt.*;

public final class TetriminoI extends Tetrimino {
  public TetriminoI(Point position) {
    super(new Block[] {
        new Block(-1, 0, new Color(29, 183, 237)),
        new Block(0, 0, new Color(29, 183, 237)),
        new Block(1, 0, new Color(29, 183, 237)),
        new Block(2, 0, new Color(29, 183, 237))
    },
        position,
        new Point[][] {
            { new Point(0, 0), new Point(-1, 0), new Point(2, 0), new Point(-1, 0), new Point(2, 0) },
            { new Point(-1, 0), new Point(0, 0), new Point(0, 0), new Point(0, 1), new Point(0, -2) },
            { new Point(-1, 1), new Point(1, 1), new Point(-2, 1), new Point(1, 0), new Point(-2, 0) },
            { new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, -1), new Point(0, 2) },
        });
  }

  public TetriminoI(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "I: " + super.toString();
  }
}
