package me.husak.tetris;

import java.awt.*;

public class TTetromino extends Tetromino {
  public TTetromino(Point position) {
    super(new Block[] {
        new Block(-1,0, Color.PINK),
        new Block(0,0, Color.PINK),
        new Block(1,0, Color.PINK),
        new Block(0,1, Color.PINK)
    }, position);
  }

  public TTetromino(int x, int y) {
    this(new Point(x, y));
  }

  @Override
  public String toString() {
    return "T: " + super.toString();
  }

}
