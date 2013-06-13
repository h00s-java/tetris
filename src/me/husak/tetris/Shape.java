package me.husak.tetris;

public class Shape {
  private Point[] points_;

  public Shape(Point[] points) {
    points_ = points;
  }

  public Shape rotateClockwise() {
    for (Point point : points_) {
      point.rotateClockwise();
    }
    return this;
  }

  public Shape rotateCounterClockwise() {
    for(Point point : points_) {
      point.rotateCounterClockwise();
    }
    return this;
  }

  @Override
  public String toString() {
    String output = "[";
    for (Point point : points_) {
      output += point.toString() + ",";
    }
    return output + "]";
  }

}
