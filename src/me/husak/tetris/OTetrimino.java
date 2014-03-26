package me.husak.tetris;

import java.awt.*;

public class OTetrimino extends Tetrimino {
  public OTetrimino(Point position) {
    super(new Block[]{
            new Block(0, 0, Color.YELLOW),
            new Block(0, 1, Color.YELLOW),
            new Block(1, 0, Color.YELLOW),
            new Block(1, 1, Color.YELLOW)
        },
        position,
        new Point[][]{
            {new Point(0, 0)},
            {new Point(0, -1)},
            {new Point(-1, -1)},
            {new Point(-1, 0)},
        }
    );
  }

  public OTetrimino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "O: " + super.toString();
  }
}
