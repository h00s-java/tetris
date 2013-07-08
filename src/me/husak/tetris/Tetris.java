package me.husak.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Tetris extends JFrame implements ActionListener {
  private Board board;
  private BoardPanel boardPanel;
  private JLabel statusBar;
  private Timer timer;

  public Tetris() {
    statusBar = new JLabel("0");
    add(statusBar, BorderLayout.NORTH);

    board = new Board();
    boardPanel = new BoardPanel(board);
    add(boardPanel);

    initUI();
    setFocusable(true);

    addKeyListener(new TAdapter());
    timer = new Timer(1000, this);
    timer.start();
  }

  private void initUI() {
    statusBar.setOpaque(true);
    statusBar.setBackground(Color.BLACK);
    statusBar.setForeground(Color.WHITE);

    boardPanel.setBackground(Color.BLACK);

    setSize(200, 480 + statusBar.getHeight());
    setTitle("Tetris");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  private void showClearedLines() {
    statusBar.setText(Integer.toString(board.getClearedLines()));
  }

  private void checkGameState() {
    if (!board.isValid()) {
      timer.stop();
    }
  }

  class TAdapter extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
      int keycode = e.getKeyCode();

      switch (keycode) {
        case KeyEvent.VK_LEFT:
          board.moveCurrentTetrominoLeft();
          boardPanel.repaint();
          break;
        case KeyEvent.VK_RIGHT:
          board.moveCurrentTetrominoRight();
          boardPanel.repaint();
          break;
        case KeyEvent.VK_UP:
          board.rotateCurrentTetromino();
          boardPanel.repaint();
          break;
        case KeyEvent.VK_DOWN:
          board.moveCurrentTetrominoDown();
          showClearedLines();
          boardPanel.repaint();
          break;
        case KeyEvent.VK_SPACE:
          board.dropCurrentTetrominoDown();
          showClearedLines();
          boardPanel.repaint();
          break;
        case KeyEvent.VK_P:
          if (timer.isRunning()) {
            timer.stop();
          } else {
            timer.start();
          }
          break;
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    board.moveCurrentTetrominoDown();
    showClearedLines();
    checkGameState();
    boardPanel.repaint();
  }

  public static void main(String[] args) {
    Tetris tetris = new Tetris();
    tetris.setLocationRelativeTo(null);
    tetris.setVisible(true);
  }

}
