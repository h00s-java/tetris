package me.husak.tetris;

public class Tetromino {
  private Block[] blocks;
  private Point position;

  public Tetromino(Block[] blocks, Point position) {
    this.blocks = blocks;
    this.position = position;
  }

  public Tetromino rotateClockwise() {
    for (Block block : blocks) {
      block.rotateClockwise();
    }
    return this;
  }

  public Tetromino rotateCounterClockwise() {
    for (Block block : blocks) {
      block.rotateCounterClockwise();
    }
    return this;
  }

  public Point getPosition() {
    return position;
  }

  public Tetromino setPosition(Point position) {
    this.position = position;
    return this;
  }

  public Tetromino setPosition(int x, int y) {
    position.setCoordinates(x, y);
    return this;
  }

  public Tetromino offsetPosition(int x, int y) {
    position.setCoordinates(x + position.getX(), y + position.getY());
    return this;
  }

  @Override
  public String toString() {
    String output = "[";
    for (Block block : blocks) {
      output += block.toString() + ",";
    }
    return output + " @" + position.toString() + "]";
  }                                               

}
