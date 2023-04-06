package me.husak.tetris;

import java.awt.*;

public final class TetriminoL extends Tetrimino {
  public TetriminoL(Point position) {
    super(new Block[] {
        new Block(-1, 0, new Color(255, 115, 18)),
        new Block(0, 0, new Color(255, 115, 18)),
        new Block(1, 0, new Color(255, 115, 18)),
        new Block(1, 1, new Color(255, 115, 18))
    },
        position,
        new Point[][] {
            { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) },
            { new Point(0, 0), new Point(1, 0), new Point(1, -1), new Point(0, 2), new Point(1, 2) },
            { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) },
            { new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(0, 2), new Point(-1, 2) },
        });
  }

  public TetriminoL(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "L: " + super.toString();
  }
}
