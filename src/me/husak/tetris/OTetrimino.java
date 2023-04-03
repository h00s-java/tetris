package me.husak.tetris;

import java.awt.*;

public final class OTetrimino extends Tetrimino {
  public OTetrimino(Point position) {
    super(new Block[] {
        new Block(0, 0, new Color(255, 196, 42)),
        new Block(0, 1, new Color(255, 196, 42)),
        new Block(1, 0, new Color(255, 196, 42)),
        new Block(1, 1, new Color(255, 196, 42))
    },
        position,
        new Point[][] {
            { new Point(0, 0) },
            { new Point(0, -1) },
            { new Point(-1, -1) },
            { new Point(-1, 0) },
        });
  }

  public OTetrimino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "O: " + super.toString();
  }
}
