package me.husak.tetris;

import java.awt.*;

public class OTetromino extends Tetromino {
  public OTetromino(Point position) {
    super(new Block[] {
        new Block(0,0, Color.YELLOW),
        new Block(0,1, Color.YELLOW),
        new Block(1,0, Color.YELLOW),
        new Block(1,1, Color.YELLOW)
    }, position);
  }

  public OTetromino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "O: " + super.toString();
  }
}
