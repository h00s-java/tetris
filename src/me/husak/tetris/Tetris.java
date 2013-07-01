package me.husak.tetris;

import javax.swing.*;

public class Tetris {
  private JPanel tetrisPanel;

  public static void main(String[] args) {
    JFrame frame = new JFrame("Tetris");
    frame.setContentPane(new Tetris().tetrisPanel);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

    Board board = new Board();
    board.spawnTetromino();
    System.out.println(board.getCurrentTetromino().toString());
    board.getCurrentTetromino().rotateClockwise();
    System.out.println(board.getCurrentTetromino().toString());
    Tetromino novi = new Tetromino(board.getCurrentTetromino());
    System.out.println(novi.toString());
    novi.rotateClockwise();
    System.out.println(novi.toString());
    System.out.println(board.getCurrentTetromino().toString());
  }
}
