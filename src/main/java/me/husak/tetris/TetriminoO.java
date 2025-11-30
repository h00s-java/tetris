package me.husak.tetris;

import javafx.scene.paint.Color;

public final class TetriminoO extends Tetrimino {
  public TetriminoO(Point position) {
    super(new Block[] {
        new Block(0, 0, Color.rgb(255, 196, 42)),
        new Block(0, 1, Color.rgb(255, 196, 42)),
        new Block(1, 0, Color.rgb(255, 196, 42)),
        new Block(1, 1, Color.rgb(255, 196, 42))
    },
        position,
        new Point[][] {
            { new Point(0, 0) },
            { new Point(0, -1) },
            { new Point(-1, -1) },
            { new Point(-1, 0) },
        });
  }

  public TetriminoO(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "O: " + super.toString();
  }
}
