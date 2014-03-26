package me.husak.tetris;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
  private Board board;
  private int offsetX, offsetY;
  private int blockSize;

  public BoardPanel(Board board) {
    this.board = board;
  }

  public void paint(Graphics g) {
    super.paint(g);
    int blockSizeX = (int) getSize().getHeight() / Board.HEIGHT;
    int blockSizeY = (int) getSize().getWidth() / Board.WIDTH;
    blockSize = (blockSizeX > blockSizeY) ? blockSizeY : blockSizeX;
    offsetX = ((int) getSize().getWidth() - (Board.WIDTH * blockSize)) / 2;
    offsetY = ((int) getSize().getHeight() - (Board.HEIGHT * blockSize)) / 2;
    paintBoard(g);
    for (Block block : board.getCurrentTetrimino().getBlocks()) {
      paintBlock(block, g);
    }
  }

  private void paintBlock(Block block, Graphics g) {
    g.setColor(block.getColor());
    g.fillRect(block.getX() * blockSize + offsetX, (Board.HEIGHT * blockSize) - (block.getY() * (blockSize) + blockSize) + offsetY, blockSize, blockSize);
  }

  private void paintBoard(Graphics g) {
    g.setColor(Color.DARK_GRAY);
    for (int i = 0; i <= Board.WIDTH; i++) {
      g.drawLine(i * blockSize + offsetX, offsetY, i * blockSize + offsetX, blockSize * Board.HEIGHT + offsetY);
    }
    for (int i = 0; i <= Board.HEIGHT; i++) {
      g.drawLine(offsetX, i * blockSize + offsetY, blockSize * Board.WIDTH + offsetX, i * blockSize + offsetY);
    }

    for (int i = 0; i < Board.HEIGHT; i++) {
      for (int j = 0; j < Board.WIDTH; j++) {
        if (board.getBlocks()[i][j] != null) {
          g.setColor(board.getBlocks()[i][j].getColor());
          g.fillRect(j * blockSize + offsetX, (Board.HEIGHT * blockSize) - (i * (blockSize) + blockSize) + offsetY, blockSize, blockSize);
        }
      }
    }
  }

  public void setBoard(Board board) {
    this.board = board;
    this.board.setBoardChangeListener(new Board.BoardChangeListener() {
      @Override
      public void onBoardChangeListener() {
        repaint();
      }
    });
  }

}
