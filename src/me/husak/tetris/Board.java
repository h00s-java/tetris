package me.husak.tetris;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private List<Tetromino> tetrominos_ = new ArrayList<Tetromino>();
  private Tetromino currentTetromino_;
  private int width_ = 10;
  private int height_ = 22;

  private Tetromino getCurrentTetromino() {
    return currentTetromino_;
  }

  public Board addTetromino(Tetromino Tetromino) {
    currentTetromino_ = Tetromino;
    tetrominos_.add(Tetromino);
    return this;
  }

  @Override
  public String toString() {
    return "Tetrominos count: " + tetrominos_.size();
  }

}
