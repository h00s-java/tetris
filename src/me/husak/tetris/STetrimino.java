package me.husak.tetris;

import java.awt.*;

public final class STetrimino extends Tetrimino {
  public STetrimino(Point position) {
    super(new Block[] {
        new Block(-1, 0, new Color(75, 216, 56)),
        new Block(0, 0, new Color(75, 216, 56)),
        new Block(0, 1, new Color(75, 216, 56)),
        new Block(1, 1, new Color(75, 216, 56))
    },
        position,
        new Point[][] {
            { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) },
            { new Point(0, 0), new Point(1, 0), new Point(1, -1), new Point(0, 2), new Point(1, 2) },
            { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) },
            { new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(0, 2), new Point(-1, 2) },
        });
  }

  public STetrimino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "S: " + super.toString();
  }
}
