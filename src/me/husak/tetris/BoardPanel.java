package me.husak.tetris;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
  // TODO: add javaFX
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
    paintTetrimino(board.getGhostTetrimino(), 0.35f, g);
    paintTetrimino(board.getCurrentTetrimino(), 1.0f, g);
  }

  private void paintBlock(Block block, Graphics g) {
    final Color color = g.getColor();
    g.fillRect(block.getX() * blockSize + offsetX, (Board.HEIGHT * blockSize) - (block.getY() * (blockSize) + blockSize) + offsetY, blockSize, blockSize);

    g.setColor(color.brighter());
    g.drawLine(block.getX() * blockSize + offsetX,
        (Board.HEIGHT * blockSize) - (block.getY() * (blockSize) + blockSize) + offsetY + blockSize - 1,
        block.getX() * blockSize + offsetX,
        (Board.HEIGHT * blockSize) - (block.getY() * (blockSize) + blockSize) + offsetY);
    g.drawLine(block.getX() * blockSize + offsetX,
        (Board.HEIGHT * blockSize) - (block.getY() * (blockSize) + blockSize) + offsetY,
        block.getX() * blockSize + offsetX + blockSize - 1,
        (Board.HEIGHT * blockSize) - (block.getY() * (blockSize) + blockSize) + offsetY);

    g.setColor(color.darker());
    g.drawLine(block.getX() * blockSize + offsetX + 1,
        (Board.HEIGHT * blockSize) - (block.getY() * (blockSize) + blockSize) + offsetY + blockSize - 1,
        block.getX() * blockSize + offsetX + blockSize - 1,
        (Board.HEIGHT * blockSize) - (block.getY() * (blockSize) + blockSize) + offsetY + blockSize - 1);
    g.drawLine(block.getX() * blockSize + offsetX + blockSize - 1,
        (Board.HEIGHT * blockSize) - (block.getY() * (blockSize) + blockSize) + offsetY + blockSize - 1,
        block.getX() * blockSize + offsetX + blockSize - 1,
        (Board.HEIGHT * blockSize) - (block.getY() * (blockSize) + blockSize) + offsetY + 1);
    g.setColor(color);
  }

  private void paintTetrimino(Tetrimino tetrimino, float brightness, Graphics g) {
    // brightness in range [0, 1]
    if (brightness < 1.0f) {
      final float hsbVals[] = Color.RGBtoHSB(tetrimino.getBlocks()[0].getColor().getRed(), tetrimino.getBlocks()[0].getColor().getGreen(), tetrimino.getBlocks()[0].getColor().getBlue(), null);
      g.setColor(Color.getHSBColor(hsbVals[0], hsbVals[1], brightness * hsbVals[2]));
    } else {
      g.setColor(tetrimino.getBlocks()[0].getColor());
    }
    for (Block block : tetrimino.getBlocks()) {
      paintBlock(block, g);
    }
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
          paintBlock(board.getBlocks()[i][j], g);
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
