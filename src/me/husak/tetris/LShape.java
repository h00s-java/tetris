package me.husak.tetris;

public class LShape extends Shape {
  public LShape() {
    super(new Point[] {
        new Point(0,1),
        new Point(0,0),
        new Point(0,-1),
        new Point(1,-1)
    });
  }
}
