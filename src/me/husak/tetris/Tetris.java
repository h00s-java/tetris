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
    System.out.println(board.toString());
    board.addShape(new LShape(2, 1));
    board.addShape(new LShape(3, 2));
    System.out.println(board.toString());
  }
}
