package me.husak.tetris;

import java.awt.*;

public class STetromino extends Tetromino {
  public STetromino(Point position) {
    super(new Block[] {
        new Block(-1,0, Color.GREEN),
        new Block(0,0, Color.GREEN),
        new Block(0,1, Color.GREEN),
        new Block(1,1, Color.GREEN)
    }, position);
  }

  public STetromino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "S: " + super.toString();
  }
}
