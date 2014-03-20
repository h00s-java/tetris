package me.husak.tetris;

public class Tetromino {
  private Block[] blocks;
  private Point[][] offsetData;
  private Point position;
  private int rotationState;

  public Tetromino(Block[] blocks, Point position, Point[][] offsetData) {
    this.blocks = blocks;
    this.position = position;
    this.offsetData = offsetData;
    this.rotationState = 0;
  }

  public Tetromino(Tetromino tetromino) {
    this.blocks = new Block[tetromino.getBlocks().length];
    for (int i = 0; i < tetromino.getBlocks().length; i++) {
      this.blocks[i] = new Block(tetromino.getBlocks()[i]);
    }
    this.position = new Point(tetromino.position);
    this.offsetData = new Point[tetromino.getOffsetData().length][];
    for (int i = 0; i < tetromino.getOffsetData().length; i++) {
      this.offsetData[i] = new Point[tetromino.getOffsetData()[i].length];
      for (int j = 0; j < tetromino.getOffsetData()[i].length; j++) {
        this.offsetData[i][j] = new Point(tetromino.getOffsetData()[i][j]);
      }
    }
    this.rotationState = tetromino.getRotationState();
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

  public Tetromino offsetPosition(Point position) {
    Tetromino tetromino = new Tetromino(this);
    for (Block block : tetromino.getBlocks()) {
      block.addPoint(position);
    }
    tetromino.position.addPoint(position);
    return tetromino;
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

  public Tetromino[] rotateClockwise() {
    Tetromino tetromino = new Tetromino(this);
    for (Block block : tetromino.getBlocks()) {
      block.subtractPoint(position);
      block.rotateClockwise();
      block.addPoint(position);
    }
    tetromino.rotationState = (tetromino.rotationState + 1) % 4;
    Tetromino[] rotatedTetrominos = new Tetromino[offsetData[0].length];
    for (int i = 0; i < rotatedTetrominos.length; i++) {
      rotatedTetrominos[i] = tetromino.offsetPosition(new Point(offsetData[rotationState][i]).subtractPoint(offsetData[tetromino.rotationState][i]));
    }
    return rotatedTetrominos;
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
