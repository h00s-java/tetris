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

    Shape shape = new Shape(new Point[] {new Point(-1,0), new Point(0,0), new Point(0,1), new Point(1,0)});
    System.out.println(shape);
    shape.rotateClockwise();
    System.out.println(shape);
  }
}
