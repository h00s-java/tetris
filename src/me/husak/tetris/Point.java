package me.husak.tetris;

public class Point {
  private Coordinates coordinates_;

  public Point(Coordinates coordinates) {
    coordinates_ = coordinates;
  }

  public Point(int x, int y) {
    coordinates_ = new Coordinates(x, y);
  }

  public Coordinates getCoordinates() {
    return coordinates_;
  }

  public void setCoordinates(Coordinates coordinates) {
    coordinates_ = coordinates;
  }

  public void rotateClockwise() {
    coordinates_.setValues(coordinates_.getY(), -1 * coordinates_.getX());
  }

  public void rotateCounterClockwise() {
    coordinates_.setValues(-1 * coordinates_.getY(), coordinates_.getX());
  }

  @Override
  public String toString() {
    return coordinates_.toString();
  }

}
