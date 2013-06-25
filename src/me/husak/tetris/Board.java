package me.husak.tetris;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private Block[][] blocks_ = new Block[getWidth()][getHeight()];
  private Tetromino currentTetromino_;

  private Tetromino getCurrentTetromino() {
    return currentTetromino_;
  }

  public int getWidth() {
    return 10;
  }

  public int getHeight() {
    return 22;
  }

  @Override
  public String toString() {
    return "";
  }
}
