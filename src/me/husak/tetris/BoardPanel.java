package me.husak.tetris;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
  private Board board;

  public BoardPanel(Board board) {
    this.board = board;
  }

  public void paint(Graphics g) {
    super.paint(g);
    paintBoard(g);
    for (Block block : board.getCurrentTetromino().getBlocks()) {
      paintBlock(block, g);
    }
  }

  int blockWidth() {
    return (int) getSize().getWidth() / Board.WIDTH;
  }

  int blockHeight() {
    return (int) getSize().getHeight() / Board.HEIGHT;
  }

  private void paintBlock(Block block, Graphics g) {
    g.setColor(block.getColor());
    g.fillRect(block.getX() * blockWidth(), (Board.HEIGHT * blockHeight()) - (block.getY() * (blockHeight()) + blockHeight()), blockWidth(), blockHeight());
  }

  private void paintBoard(Graphics g) {
    g.setColor(Color.DARK_GRAY);
    for (int i = 0; i < Board.WIDTH; i++) {
      g.drawLine(i * blockWidth(), 0, i * blockWidth(), (int) getSize().getHeight());
    }
    for (int i = 0; i < Board.HEIGHT; i++) {
      g.drawLine(0, i * blockHeight(), (int) getSize().getWidth(), i * blockHeight());
    }

    for (int i = 0; i < Board.HEIGHT; i++) {
      for (int j = 0; j < Board.WIDTH; j++) {
        if (board.getBlocks()[i][j] != null) {
          g.setColor(board.getBlocks()[i][j].getColor());
          g.fillRect(j * blockWidth(), (Board.HEIGHT * blockHeight()) - (i * (blockHeight()) + blockHeight()), blockWidth(), blockHeight());
        }
      }
    }
  }

  public void setBoard(Board board) {
    this.board = board;
  }

}
