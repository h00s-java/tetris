package me.husak.tetris;

import java.awt.*;

public class ZTetromino extends Tetromino {
  public ZTetromino(Point position) {
    super(new Block[] {
        new Block(-1,1, Color.RED),
        new Block(0,1, Color.RED),
        new Block(0,0, Color.RED),
        new Block(1,0, Color.RED)
    }, position);
  }

  public ZTetromino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "Z: " + super.toString();
  }
}
