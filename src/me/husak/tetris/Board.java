package me.husak.tetris;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private Block[][] blocks_ = new Block[getWidth()][getHeight()];
  private Tetromino currentTetromino_, nextTetromino_;

  public Tetromino getCurrentTetromino() {
    return currentTetromino_;
  }

  public int getWidth() {
    return 10;
  }

  public int getHeight() {
    return 22;
  }

  public void spawnTetromino() {
    if (nextTetromino_ == null) {
      nextTetromino_ = createRandomTetromino();
      currentTetromino_ = createRandomTetromino();
    }
    else {
      currentTetromino_ = nextTetromino_;
      nextTetromino_ = createRandomTetromino();
    }
    currentTetromino_.setPosition(getWidth() / 2 - 1, getHeight() - 1);
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
