package me.husak.tetris;

public class Shape {
  private Point[] points_;
  private Point position_;

  public Shape(Point[] points, Point position) {
    points_ = points;
    position_ = position;
  }

  public Shape rotateClockwise() {
    for (Point point : points_) {
      point.rotateClockwise();
    }
    return this;
  }

  public Shape rotateCounterClockwise() {
    for (Point point : points_) {
      point.rotateCounterClockwise();
    }
    return this;
  }

  public Point getPosition() {
    return position_;
  }

  public Shape setPosition(Point position) {
    position_ = position;
    return this;
  }

  public Shape setPosition(int x, int y) {
    position_.setCoordinates(x, y);
    return this;
  }

  public int minY() {
    int y = points_[0].getCoordinates().getY();
    for (int i = 1; i < points_.length; i++) {
      if (points_[i].getCoordinates().getY() < y) {
        y = points_[i].getCoordinates().getY();
      }
    }
    return y;
  }

  public int minX() {
    int x = points_[0].getCoordinates().getX();
    for (int i = 1; i < points_.length; i++) {
      if (points_[i].getCoordinates().getX() < x) {
        x = points_[i].getCoordinates().getX();
      }
    }
    return x;
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
