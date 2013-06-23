package me.husak.tetris;

public class LTetromino extends Tetromino {
  public LTetromino(Point position) {
    super(new Point[] {
        new Point(-1,1),
        new Point(-1,0),
        new Point(0,0),
        new Point(1,0)
    }, position);
  }

  public LTetromino(int x, int y) {
    this(new Point(x, y));
  }
}
