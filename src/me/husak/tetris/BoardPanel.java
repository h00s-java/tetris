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
    for (Block block : board.getCurrentTetromino().getBlocks()) {
      drawBlock(block, g);
    }
    drawBoard(g);
  }

  int blockWidth() {
    return (int) getSize().getWidth() / Board.WIDTH;
  }

  int blockHeight() {
    return (int) getSize().getHeight() / Board.HEIGHT;
  }

  private void drawBlock(Block block, Graphics g) {
    g.setColor(block.getColor());
    g.fillRect(block.getX() * blockWidth(), (Board.HEIGHT * blockHeight()) - (block.getY() * (blockHeight()) + blockHeight()), blockWidth(), blockHeight());
  }

  private void drawBoard(Graphics g) {
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
