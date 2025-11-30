package me.husak.tetris;

import javafx.scene.paint.Color;

public final class TetriminoT extends Tetrimino {
  public TetriminoT(Point position) {
    super(new Block[] {
        new Block(-1, 0, Color.rgb(216, 56, 203)),
        new Block(0, 0, Color.rgb(216, 56, 203)),
        new Block(1, 0, Color.rgb(216, 56, 203)),
        new Block(0, 1, Color.rgb(216, 56, 203))
    },
        position,
        new Point[][] {
            { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) },
            { new Point(0, 0), new Point(1, 0), new Point(1, -1), new Point(0, 2), new Point(1, 2) },
            { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) },
            { new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(0, 2), new Point(-1, 2) },
        });
  }

  public TetriminoT(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "T: " + super.toString();
  }
}
