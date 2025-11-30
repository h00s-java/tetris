package me.husak.tetris;

import javafx.scene.paint.Color;

public final class TetriminoZ extends Tetrimino {
  public TetriminoZ(Point position) {
    super(new Block[] {
        new Block(-1, 1, Color.rgb(250, 30, 30)),
        new Block(0, 1, Color.rgb(250, 30, 30)),
        new Block(0, 0, Color.rgb(250, 30, 30)),
        new Block(1, 0, Color.rgb(250, 30, 30))
    },
        position,
        new Point[][] {
            { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) },
            { new Point(0, 0), new Point(1, 0), new Point(1, -1), new Point(0, 2), new Point(1, 2) },
            { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) },
            { new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(0, 2), new Point(-1, 2) },
        });
  }

  public TetriminoZ(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "Z: " + super.toString();
  }
}
