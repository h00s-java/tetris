package me.husak.tetris;

public class Tetrimino {
  private Block[] blocks;
  private Point[][] offsetData;
  private Point position;
  private int rotationState;

  public Tetrimino(Block[] blocks, Point position, Point[][] offsetData) {
    this.blocks = blocks;
    this.position = position;
    this.offsetData = offsetData;
    this.rotationState = 0;
  }

  public Tetrimino(Tetrimino tetrimino) {
    this.blocks = new Block[tetrimino.getBlocks().length];
    for (int i = 0; i < tetrimino.getBlocks().length; i++) {
      this.blocks[i] = new Block(tetrimino.getBlocks()[i]);
    }
    this.position = new Point(tetrimino.position);
    this.offsetData = new Point[tetrimino.getOffsetData().length][];
    for (int i = 0; i < tetrimino.getOffsetData().length; i++) {
      this.offsetData[i] = new Point[tetrimino.getOffsetData()[i].length];
      for (int j = 0; j < tetrimino.getOffsetData()[i].length; j++) {
        this.offsetData[i][j] = new Point(tetrimino.getOffsetData()[i][j]);
      }
    }
    this.rotationState = tetrimino.getRotationState();
  }

  public Block[] getBlocks() {
    return blocks;
  }

  public Point getPosition() {
    return position;
  }

  public Point[][] getOffsetData() {
    return offsetData;
  }

  public int getRotationState() {
    return rotationState;
  }

  public Tetrimino setPosition(Point position) {
    for (Block block : blocks) {
      block.subtractPoint(this.position);
      block.addPoint(position);
    }
    this.position = position;
    return this;
  }

  public Tetrimino setPosition(int x, int y) {
    setPosition(new Point(x, y));
    return this;
  }

  public Tetrimino offsetPosition(Point position) {
    Tetrimino tetrimino = new Tetrimino(this);
    for (Block block : tetrimino.getBlocks()) {
      block.addPoint(position);
    }
    tetrimino.position.addPoint(position);
    return tetrimino;
  }

  public Tetrimino moveLeft() {
    Tetrimino tetrimino = new Tetrimino(this);
    for (Block block : tetrimino.getBlocks()) {
      block.setX(block.getX() - 1);
    }
    tetrimino.getPosition().setCoordinates(tetrimino.getPosition().getX() - 1, tetrimino.getPosition().getY());
    return tetrimino;
  }

  public Tetrimino moveRight() {
    Tetrimino tetrimino = new Tetrimino(this);
    for (Block block : tetrimino.getBlocks()) {
      block.setX(block.getX() + 1);
    }
    tetrimino.getPosition().setCoordinates(tetrimino.getPosition().getX() + 1, tetrimino.getPosition().getY());
    return tetrimino;
  }

  public Tetrimino moveDown() {
    Tetrimino tetrimino = new Tetrimino(this);
    for (Block block : tetrimino.getBlocks()) {
      block.setY(block.getY() - 1);
    }
    tetrimino.getPosition().setCoordinates(tetrimino.getPosition().getX(), tetrimino.getPosition().getY() - 1);
    return tetrimino;
  }

  private Tetrimino[] rotate(boolean clockwise) {
    Tetrimino tetrimino = new Tetrimino(this);
    for (Block block : tetrimino.getBlocks()) {
      block.subtractPoint(position);
      if (clockwise) {
        block.rotateClockwise();
      } else {
        block.rotateCounterClockwise();
      }
      block.addPoint(position);
    }
    tetrimino.rotationState = (clockwise) ? (tetrimino.rotationState + 1) % 4 : (tetrimino.rotationState + 3) % 4;
    Tetrimino[] rotatedTetriminos = new Tetrimino[offsetData[0].length];
    for (int i = 0; i < rotatedTetriminos.length; i++) {
      rotatedTetriminos[i] = tetrimino.offsetPosition(new Point(offsetData[rotationState][i]).subtractPoint(offsetData[tetrimino.rotationState][i]));
    }
    return rotatedTetriminos;
  }

  public Tetrimino[] rotateClockwise() {
    return rotate(true);
  }

  public Tetrimino[] rotateCounterClockwise() {
    return rotate(false);
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
