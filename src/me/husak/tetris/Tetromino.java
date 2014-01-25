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

  public Tetromino moveLeft() {
    Tetromino tetromino = new Tetromino(this);
    for (Block block : tetromino.getBlocks()) {
      block.setX(block.getX() - 1);
    }
    tetromino.getPosition().setCoordinates(tetromino.getPosition().getX() - 1, tetromino.getPosition().getY());
    return tetromino;
  }

  public Tetromino moveRight() {
    Tetromino tetromino = new Tetromino(this);
    for (Block block : tetromino.getBlocks()) {
      block.setX(block.getX() + 1);
    }
    tetromino.getPosition().setCoordinates(tetromino.getPosition().getX() + 1, tetromino.getPosition().getY());
    return tetromino;
  }

  public Tetromino moveDown() {
    Tetromino tetromino = new Tetromino(this);
    for (Block block : tetromino.getBlocks()) {
      block.setY(block.getY() - 1);
    }
    tetromino.getPosition().setCoordinates(tetromino.getPosition().getX(), tetromino.getPosition().getY() - 1);
    return tetromino;
  }

  public Tetromino rotateClockwise() {
    Tetromino tetromino = new Tetromino(this);
    for (Block block : tetromino.getBlocks()) {
      block.subtractPoint(position);
      block.rotateClockwise();
      block.addPoint(position);
    }
    return tetromino;
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
