package me.husak.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BoardPanel extends JPanel implements ActionListener {
  private Board board;

  public BoardPanel(Tetris parent) {
    setFocusable(true);
    board = new Board(this);
    addKeyListener(new TAdapter());
  }

  public void paint(Graphics g) {
    super.paint(g);
    for (Block block : board.getCurrentTetromino().getBlocks()) {
      drawBlock(block, g);
    }
  }

  int blockWidth() { return (int) getSize().getWidth() / Board.WIDTH; }
  int blockHeight() { return (int) getSize().getHeight() / Board.HEIGHT; }

  private void drawBlock(Block block, Graphics g) {
    g.setColor(block.getColor());
    g.fillRect(block.getX() * blockWidth(), (Board.HEIGHT * blockHeight()) - (block.getY() * (blockHeight()) + blockHeight()), blockWidth(), blockHeight());
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  class TAdapter extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
      int keycode = e.getKeyCode();

      switch (keycode) {
        case KeyEvent.VK_LEFT:
          board.moveCurrentTetrominoLeft();
          repaint();
          break;
        case KeyEvent.VK_RIGHT:
          board.moveCurrentTetrominoRight();
          repaint();
          break;
        case KeyEvent.VK_DOWN:
          board.moveCurrentTetrominoDown();
          repaint();
          break;
      }
    }
  }


}