package me.husak.tetris;

public class Tetromino {
  private Block[] blocks;
  private Point position;

  public Tetromino(Block[] blocks, Point position) {
    this.blocks = blocks;
    this.position = position;
  }

  public Tetromino(Tetromino tetromino) {
    this.blocks = new Block[tetromino.getBlocks().length];
    for (int i = 0; i < tetromino.getBlocks().length; i++) {
      this.blocks[i] = new Block(tetromino.getBlocks()[i]);
    }
    this.position = new Point(tetromino.position);
  }

  public Block[] getBlocks() {
    return blocks;
  }

  public Point getPosition() {
    return position;
  }

  public Tetromino setPosition(Point position) {
    for (Block block : blocks) {
      block.subtractPoint(this.position);
      block.addPoint(position);
    }
    this.position = position;
    return this;
  }

  public Tetromino setPosition(int x, int y) {
    setPosition(new Point(x, y));
    return this;
  }

  public Tetromino rotateClockwise() {
    for (Block block : blocks) {
      block.subtractPoint(position);
      block.rotateClockwise();
      block.addPoint(position);
    }
    return this;
  }

  public Tetromino rotateCounterClockwise() {
    for (Block block : blocks) {
      block.subtractPoint(position);
      block.rotateCounterClockwise();
      block.addPoint(position);
    }
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
