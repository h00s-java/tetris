package me.husak.tetris;

import java.awt.*;

public class Block extends Point {
  private Color color_;

  public Block(int x, int y, Color color) {
    super(x, y);
    color_ = color;
  }

  public Color getColor() {
    return color_;
  }

  public void setColor(Color color) {
    color_ = color;
  }
}
