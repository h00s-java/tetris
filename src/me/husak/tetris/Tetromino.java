package me.husak.tetris;

public class Tetromino {
  private Block[] blocks_;
  private Point position_;

  public Tetromino(Block[] blocks, Point position) {
    blocks_ = blocks;
    position_ = position;
  }

  public Tetromino rotateClockwise() {
    for (Block block : blocks_) {
      block.rotateClockwise();
    }
    return this;
  }

  public Tetromino rotateCounterClockwise() {
    for (Block block : blocks_) {
      block.rotateCounterClockwise();
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

  public Tetromino offsetPosition(int x, int y) {
    position_.setCoordinates(x + position_.getX(), y + position_.getY());
    return this;
  }

  @Override
  public String toString() {
    String output = "[";
    for (Block block : blocks_) {
      output += block.toString() + ",";
    }
    return output + " @" + position_.toString() + "]";
  }                                               

}
