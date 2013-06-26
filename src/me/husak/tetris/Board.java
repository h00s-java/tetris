package me.husak.tetris;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private Block[][] blocks = new Block[HEIGHT][WIDTH];
  private Tetromino currentTetromino, nextTetromino;

  private static final int HEIGHT = 22;
  private static final int WIDTH = 10;

  public Tetromino getCurrentTetromino() {
    return currentTetromino;
  }

  public void spawnTetromino() {
    if (nextTetromino == null) {
      nextTetromino = createRandomTetromino();
      currentTetromino = createRandomTetromino();
    }
    else {
      currentTetromino = nextTetromino;
      nextTetromino = createRandomTetromino();
    }
    currentTetromino.setPosition(WIDTH / 2 - 1, HEIGHT - 1);
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

  @Override
  public String toString() {
    return "";
  }
}
