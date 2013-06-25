package me.husak.tetris;

import java.awt.*;

public class JTetromino extends Tetromino {
  public JTetromino(Point position) {
    super(new Block[] {
        new Block(-1,1, Color.BLUE),
        new Block(-1,0, Color.BLUE),
        new Block(0,0, Color.BLUE),
        new Block(1,0, Color.BLUE)
    }, position);
  }

  public JTetromino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "J: " + super.toString();
  }
}
