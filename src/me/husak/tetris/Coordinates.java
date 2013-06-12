package me.husak.tetris;

public class Coordinates {
  private int x_;
  private int y_;

  public Coordinates(int x, int y) {
    setValues(x, y);
  }

  public void setX(int x) {
    x_ = x;
  }

  public void setY(int y) {
    y_ = y;
  }

  public int getX() {
    return x_;
  }

  public int getY() {
    return y_;
  }

  public void setValues(int x, int y) {
    setX(x);
    setY(y);
  }

  @Override
  public String toString() {
    return x_ + "," + y_;
  }

}
