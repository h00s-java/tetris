package me.husak.tetris;

public class Point {
  private int[] coordinates_;

  public Point() {
    coordinates_ = new int[2];
  }

  public Point(int x, int y) {
    this();
    setX(x);
    setY(y);
  }

  public int getX() {
    return coordinates_[0];
  }

  public int getY() {
    return coordinates_[1];
  }

  public Point setX(int x) {
    coordinates_[0] = x;
    return this;
  }

  public Point setY(int y) {
    coordinates_[1] = y;
    return this;
  }

  public int[] getCoordinates() {
    return coordinates_;
  }

  public Point setCoordinates(int x, int y) {
    setX(x);
    return setY(y);
  }

  public Point rotateClockwise() {
    setCoordinates(getY(), -1 * getX());
    return this;
  }

  public Point rotateCounterClockwise() {
    setCoordinates(-1 * getY(), getX());
    return this;
  }

  @Override
  public String toString() {
    return "(" + getX() + "," + getY() + ")";
  }

}
