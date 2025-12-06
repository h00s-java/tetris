package me.husak.tetris;

public class Board {
  // TODO: add ghost piece
  // TODO: add next piece
  private final Block[][] blocks = new Block[HEIGHT][WIDTH];
  private final RandomGenerator randomGenerator;
  private Tetrimino currentTetrimino, ghostTetrimino;
  private int clearedLines;
  private boolean valid;
  private BoardChangeListener boardChangeListener;

  public static final int HEIGHT = 22;
  public static final int WIDTH = 10;

  public Board() {
    clearedLines = 0;
    valid = true;
    randomGenerator = new RandomGenerator();
    spawnTetrimino();
  }

  public interface BoardChangeListener {
    void onBoardChange();
    void onLineCleared(int row);
  }

  public void setBoardChangeListener(BoardChangeListener l) {
    boardChangeListener = l;
  }

  public void notifyBoardChange() {
    if (boardChangeListener != null) {
      boardChangeListener.onBoardChange();
    }
  }

  private void notifyLineCleared(int row) {
    if (boardChangeListener != null) {
      boardChangeListener.onLineCleared(row);
    }
  }

  private boolean isValidHorizontalPosition(Tetrimino tetrimino) {
    for (Block block : tetrimino.getBlocks()) {
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

  private boolean isValidVerticalPosition(Tetrimino tetrimino) {
    for (Block block : tetrimino.getBlocks()) {
      if (!isValidVerticalPosition(block)) {
        return false;
      }
    }
    return true;
  }

  private boolean isValidVerticalPosition(Block block) {
    return !((block.getY() < 0 || block.getX() < 0) ||
        (block.getY() > (HEIGHT - 1) || block.getX() > (WIDTH - 1)) ||
        (blocks[block.getY()][block.getX()] != null));
  }

  private void place(Tetrimino tetrimino) {
    for (Block block : tetrimino.getBlocks()) {
      blocks[block.getY()][block.getX()] = block;
    }
  }

  private void clearLines() {
    for (int i = 0; i < HEIGHT; i++) {
      if (isLineFull(blocks[i])) { // if line is full
        notifyLineCleared(i);
        removeLineAt(i);
        i--; // return to position where was line that was removed
        clearedLines++;
      }
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

  private void removeLineAt(int index) {
    for (int j = index; j < (HEIGHT - 1); j++) { // move down all blocks on top of that line
      blocks[j] = blocks[j + 1];
      for (Block block : blocks[j]) { // update Y position on all block in that line (move down)
        if (block != null) {
          block.setY(block.getY() - 1);
        }
      }
    }
    blocks[HEIGHT - 1] = new Block[WIDTH]; // initialize new line on top
    notifyBoardChange();
  }

  private void updateGhostTetrimino() {
    ghostTetrimino = new Tetrimino(currentTetrimino);
    for (int i = ghostTetrimino.getPosition().getY() - 1; i >= 0; i--) {
      ghostTetrimino.setPosition(ghostTetrimino.getPosition().getX(), i);
      if (!isValidVerticalPosition(ghostTetrimino)) {
        ghostTetrimino.setPosition(ghostTetrimino.getPosition().getX(), i + 1);
        break;
      }
    }
  }

  public void moveCurrentTetriminoLeft() {
    final Tetrimino tetrimino = currentTetrimino.moveLeft();
    if (isValidHorizontalPosition(tetrimino)) {
      currentTetrimino = tetrimino;
      updateGhostTetrimino();
      notifyBoardChange();
    }
  }

  public void moveCurrentTetriminoRight() {
    final Tetrimino tetrimino = currentTetrimino.moveRight();
    if (isValidHorizontalPosition(tetrimino)) {
      currentTetrimino = tetrimino;
      updateGhostTetrimino();
      notifyBoardChange();
    }
  }

  public boolean moveCurrentTetriminoDown() {
    final Tetrimino tetrimino = currentTetrimino.moveDown();
    if (isValidVerticalPosition(tetrimino)) {
      currentTetrimino = tetrimino;
      notifyBoardChange();
      return true;
    } else {
      place(currentTetrimino);
      clearLines();
      spawnTetrimino();
      notifyBoardChange();
      if (!isValidVerticalPosition(currentTetrimino)) {
        valid = false;
      }
      return false;
    }
  }

  private boolean rotateCurrentTetrimino(boolean clockwise) {
    final Tetrimino tetriminos[] = (clockwise) ? currentTetrimino.rotateClockwise()
        : currentTetrimino.rotateCounterClockwise();
    for (Tetrimino tetrimino : tetriminos) {
      if (isValidVerticalPosition(tetrimino) && isValidHorizontalPosition(tetrimino)) {
        currentTetrimino = tetrimino;
        updateGhostTetrimino();
        notifyBoardChange();
        return true;
      }
    }
    return false;
  }

  public boolean rotateCurrentTetriminoClockwise() {
    return rotateCurrentTetrimino(true);
  }

  public boolean rotateCurrentTetriminoCounterClockwise() {
    return rotateCurrentTetrimino(false);
  }

  public void dropCurrentTetriminoDown() {
    while (moveCurrentTetriminoDown());
  }

  public void spawnTetrimino() {
    randomGenerator.advance();
    currentTetrimino = randomGenerator.peek(0);
    currentTetrimino.setPosition(WIDTH / 2 - 1, HEIGHT - 2);
    updateGhostTetrimino();
  }

  public Tetrimino getCurrentTetrimino() {
    return currentTetrimino;
  }

  public Tetrimino getGhostTetrimino() {
    return ghostTetrimino;
  }

  public Tetrimino getHoldTetrimino() {
    return null;
  }

  public Tetrimino getNextTetrimino(int lookahead) {
    return randomGenerator.peek(lookahead + 1);
  }

  public Block[][] getBlocks() {
    return blocks;
  }

  public boolean isValid() {
    return valid;
  }

  public int getClearedLines() {
    return clearedLines;
  }

  public int getScore() {
    return 0;
  }

  public int getLevel() {
    return 0;
  }

  public int getLines() {
    return getClearedLines();
  }

  @Override
  public String toString() {
    return "";
  }
}
