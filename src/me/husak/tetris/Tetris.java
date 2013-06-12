package me.husak.tetris;

import javax.swing.*;

public class Tetris {
    private JPanel pnlTetris;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setContentPane(new Tetris().pnlTetris);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
