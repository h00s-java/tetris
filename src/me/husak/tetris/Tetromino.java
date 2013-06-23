package me.husak.tetris;

public class Tetromino {
  private Point[] points_;
  private Point position_;

  public Tetromino(Point[] points, Point position) {
    points_ = points;
    position_ = position;
  }

  public Tetromino rotateClockwise() {
    for (Point point : points_) {
      point.rotateClockwise();
    }
    return this;
  }

  public Tetromino rotateCounterClockwise() {
    for (Point point : points_) {
      point.rotateCounterClockwise();
    }
    return this;
  }

  public Point getPosition() {
    return position_;
  }

  public Tetromino setPosition(Point position) {
    position_ = position;
    return this;
  }

  public Tetromino setPosition(int x, int y) {
    position_.setCoordinates(x, y);
    return this;
  }

  @Override
  public String toString() {
    String output = "[";
    for (Point point : points_) {
      output += point.toString() + ",";
    }
    return output + " @" + position_.toString() + "]";
  }                                               

}
