package me.husak.tetris;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {

  private JLabel statusBar;
  private BoardPanel boardPanel;

  public Tetris() {
    statusBar = new JLabel(" 0");
    add(statusBar, BorderLayout.NORTH);
    boardPanel = new BoardPanel(this);
    add(boardPanel);
    //board.start();

    initUI();
  }

  private void initUI() {
    statusBar.setOpaque(true);
    statusBar.setBackground(Color.BLACK);
    statusBar.setForeground(Color.WHITE);

    boardPanel.setBackground(Color.BLACK);

    setSize(200, 470 + statusBar.getHeight());
    //boardPanel.setSize(200, 440);
    setTitle("Tetris");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public JLabel getStatusBar() {
    return statusBar;
  }

  public BoardPanel getBoardPanel() {
    return boardPanel;
  }

  public static void main(String[] args) {
    Tetris tetris = new Tetris();
    tetris.setLocationRelativeTo(null);
    tetris.setVisible(true);
  }
}
