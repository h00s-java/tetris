package me.husak.tetris;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private List<Shape> shapes_ = new ArrayList<Shape>();
  private Shape currentShape_;
  private int width_ = 10;
  private int height_ = 22;

  private Shape getCurrentShape() {
    return currentShape_;
  }

  public Board addShape(Shape shape) {
    currentShape_ = shape;
    shapes_.add(shape);
    return this;
  }

  @Override
  public String toString() {
    return "Shapes count: " + shapes_.size();
  }

}
