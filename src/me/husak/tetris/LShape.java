package me.husak.tetris;

public class LShape extends Shape {
  public LShape(Point position) {
    super(new Point[] {
        new Point(0,1),
        new Point(0,0),
        new Point(0,-1),
        new Point(1,-1)
    }, position);
  }

  public LShape(int x, int y) {
    this(new Point(x, y));
  }
}
