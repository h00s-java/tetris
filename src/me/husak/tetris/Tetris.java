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

    boardPanel = new BoardPanel(null);
    add(boardPanel);

    initUI();
    setFocusable(true);

    addKeyListener(new TAdapter());
    timer = new Timer(1000, this);
    initGame();
  }

  private void initUI() {
    statusBar.setOpaque(true);
    statusBar.setBackground(Color.BLACK);
    statusBar.setForeground(Color.WHITE);

    boardPanel.setBackground(Color.BLACK);

    boardPanel.setSize(202, 467);
    setSize(boardPanel.getWidth(), boardPanel.getHeight() + statusBar.getHeight());
    setTitle("Tetris");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  private void start() {
    timer.start();
  }

  private void pause() {
    if (timer.isRunning()) {
      timer.stop();
    } else {
      timer.start();
    }
  }

  private void initGame() {
    board = new Board();
    boardPanel.setBoard(board);
    start();
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
          break;
        case KeyEvent.VK_RIGHT:
          board.moveCurrentTetrominoRight();
          break;
        case KeyEvent.VK_UP:
          board.rotateCurrentTetromino();
          break;
        case KeyEvent.VK_DOWN:
          board.moveCurrentTetrominoDown();
          showClearedLines();
          break;
        case KeyEvent.VK_SPACE:
          board.dropCurrentTetrominoDown();
          showClearedLines();
          break;
        case KeyEvent.VK_R:
          int option_restart = JOptionPane.showConfirmDialog(null, "Želite li ponovno pokrenuti igru?", "Ponovo?", JOptionPane.YES_NO_OPTION);
          if (option_restart == JOptionPane.YES_OPTION) {
            initGame();
          }
          break;
        case KeyEvent.VK_P:
          pause();
          break;
        case KeyEvent.VK_ESCAPE:
          int option_exit = JOptionPane.showConfirmDialog(null, "Želite li završiti igru?", "Zatvoriti?", JOptionPane.YES_NO_OPTION);
          if (option_exit == JOptionPane.YES_OPTION) {
            System.exit(0);
          }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    board.moveCurrentTetrominoDown();
    showClearedLines();
    checkGameState();
  }

  public static void main(String[] args) {
    Tetris tetris = new Tetris();
    tetris.setLocationRelativeTo(null);
    tetris.setVisible(true);
  }

}
