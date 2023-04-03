package me.husak.tetris;

public class Point {
  private int[] coordinates;

  public Point() {
    coordinates = new int[2];
  }

  public Point(int x, int y) {
    this();
    setX(x);
    setY(y);
  }

  public Point(Point point) {
    this(point.getX(), point.getY());
  }

  public int getX() {
    return coordinates[0];
  }

  public int getY() {
    return coordinates[1];
  }

  public Point setX(int x) {
    coordinates[0] = x;
    return this;
  }

  public Point setY(int y) {
    coordinates[1] = y;
    return this;
  }

  public int[] getCoordinates() {
    return coordinates;
  }

  public Point setCoordinates(int x, int y) {
    setX(x);
    return setY(y);
  }

  public Point addPoint(Point point) {
    setCoordinates(getX() + point.getX(), getY() + point.getY());
    return this;
  }

  public Point subtractPoint(Point point) {
    setCoordinates(getX() - point.getX(), getY() - point.getY());
    return this;
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
