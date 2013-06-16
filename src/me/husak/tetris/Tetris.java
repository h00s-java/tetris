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

    LShape shape = new LShape();
    System.out.println(shape.minY() + "," + shape.minX());
    shape.rotateClockwise();
    System.out.println(shape.minY() + "," + shape.minX());
    shape.rotateClockwise();
    System.out.println(shape.minY() + "," + shape.minX());
    shape.rotateClockwise();
    System.out.println(shape.minY() + "," + shape.minX());
  }
}
