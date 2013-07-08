package me.husak.tetris;

public class Board {
  private Block[][] blocks = new Block[HEIGHT][WIDTH];
  private Tetromino currentTetromino, nextTetromino;
  private BoardPanel panel;

  public static final int HEIGHT = 22;
  public static final int WIDTH = 10;

  public Board(BoardPanel boardPanel) {
    this.panel = boardPanel;
    spawnTetromino();
  }

  public boolean isValidHorizontalPosition(Tetromino tetromino) {
    for (Block block : tetromino.getBlocks()) {
      if (!isValidHorizontalPosition(block)) {
        return false;
      }
    }
    return true;
  }

  private boolean isValidHorizontalPosition(Block block) {
    return !((block.getX() < 0) ||
        (block.getX() > (WIDTH - 1)) ||
        (blocks[block.getY()][block.getX()] != null));
  }

  public boolean isValidVerticalPosition(Tetromino tetromino) {
    for (Block block : tetromino.getBlocks()) {
      if (!isValidVerticalPosition(block)) {
        return false;
      }
    }
    return true;
  }

  private boolean isValidVerticalPosition(Block block) {
    return !((block.getY() < 0) ||
        (block.getY() > (HEIGHT - 1)) ||
        (blocks[block.getY()][block.getX()] != null));
  }

  private void place(Tetromino tetromino) {
    for (Block block : tetromino.getBlocks()) {
      blocks[block.getY()][block.getX()] = block;
    }
  }

  private boolean isLineFull(Block[] blocks) {
    for (Block block : blocks) {
      if (block == null) {
        return false;
      }
    }
    return true;
  }

  private void clearLines() {
    for (int i = 0; i < HEIGHT; i++) {
      if (isLineFull(blocks[i])) {
        for (int j = i; j < (HEIGHT - 1); j++) {
          blocks[j] = blocks[j + 1];
        }
        i--;
        blocks[HEIGHT - 1] = new Block[WIDTH];
      }
    }
  }

  public void moveCurrentTetrominoLeft() {
    Tetromino tetromino = currentTetromino.moveLeft();
    if (isValidHorizontalPosition(tetromino)) {
      currentTetromino = tetromino;
    }
  }

  public void moveCurrentTetrominoRight() {
    Tetromino tetromino = currentTetromino.moveRight();
    if (isValidHorizontalPosition(tetromino)) {
      currentTetromino = tetromino;
    }
  }

  public boolean moveCurrentTetrominoDown() {
    Tetromino tetromino = currentTetromino.moveDown();
    if (isValidVerticalPosition(tetromino)) {
      currentTetromino = tetromino;
      return true;
    } else {
      place(currentTetromino);
      spawnTetromino();
      return false;
    }
  }

  public boolean rotateCurrentTetromino() {
    Tetromino tetromino = currentTetromino.rotateClockwise();
    if (isValidVerticalPosition(tetromino) && isValidHorizontalPosition(tetromino)) {
      currentTetromino = tetromino;
      return true;
    } else {
      return false;
    }
  }

  public void dropCurrentTetrominoDown() {
    while (moveCurrentTetrominoDown());
  }

  public void spawnTetromino() {
    if (nextTetromino == null) {
      nextTetromino = createRandomTetromino();
    } else {
      clearLines();
    }
    currentTetromino = nextTetromino;
    nextTetromino = createRandomTetromino();
    currentTetromino.setPosition(WIDTH / 2 - 1, HEIGHT - 2);
  }

  private Tetromino createRandomTetromino() {
    int random = (int) (Math.random() * 7);
    switch (random) {
      case 0:
        return new ITetromino(0, 0);
      case 1:
        return new OTetromino(0, 0);
      case 2:
        return new TTetromino(0, 0);
      case 3:
        return new STetromino(0, 0);
      case 4:
        return new ZTetromino(0, 0);
      case 5:
        return new JTetromino(0, 0);
      case 6:
        return new LTetromino(0, 0);
    }
    return null;
  }

  public Tetromino getCurrentTetromino() {
    return currentTetromino;
  }

  public Block[][] getBlocks() {
    return blocks;
  }

  @Override
  public String toString() {
    return "";
  }
}
