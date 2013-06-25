package me.husak.tetris;

import java.awt.*;

public class ITetromino extends Tetromino {
  public ITetromino(Point position) {
    super(new Block[] {
        new Block(-1,0, Color.CYAN),
        new Block(0,0, Color.CYAN),
        new Block(1,0, Color.CYAN),
        new Block(2,0, Color.CYAN)
    }, position);
  }

  public ITetromino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "I: " + super.toString();
  }
}
