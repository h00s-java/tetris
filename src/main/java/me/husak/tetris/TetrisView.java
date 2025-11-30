package me.husak.tetris;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TetrisView extends Pane {

  private final Canvas canvas;
  private final GraphicsContext gc;

  private double blockSize;
  private double offsetX;
  private double offsetY;

  public TetrisView() {
    canvas = new Canvas(202, 467);
    gc = canvas.getGraphicsContext2D();
    getChildren().add(canvas);

    canvas.widthProperty().bind(this.widthProperty());
    canvas.heightProperty().bind(this.heightProperty());
  }

  public void paint(Board board) {
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

    calculateDimensions();

    drawGrid();

    for (int i = 0; i < Board.HEIGHT; i++) {
      for (int j = 0; j < Board.WIDTH; j++) {
        Block b = board.getBlocks()[i][j];
        if (b != null) {
          drawBlock(b, b.getColor());
        }
      }
    }

    if (board.getGhostTetrimino() != null) {
      drawTetrimino(board.getGhostTetrimino(), 0.35);
    }

    if (board.getCurrentTetrimino() != null) {
      drawTetrimino(board.getCurrentTetrimino(), 1.0);
    }
  }

  private void calculateDimensions() {
    double height = canvas.getHeight();
    double width = canvas.getWidth();

    double blockSizeX = height / Board.HEIGHT;
    double blockSizeY = width / Board.WIDTH;
    blockSize = Math.min(blockSizeX, blockSizeY);

    offsetX = (width - (Board.WIDTH * blockSize)) / 2;
    offsetY = (height - (Board.HEIGHT * blockSize)) / 2;
  }

  private void drawGrid() {
    gc.setStroke(Color.rgb(25, 25, 25));
    gc.setLineWidth(1);

    for (int i = 0; i <= Board.WIDTH; i++) {
      double x = i * blockSize + offsetX;
      gc.strokeLine(x, offsetY, x, blockSize * Board.HEIGHT + offsetY);
    }

    for (int i = 0; i <= Board.HEIGHT; i++) {
      double y = i * blockSize + offsetY;
      gc.strokeLine(offsetX, y, blockSize * Board.WIDTH + offsetX, y);
    }
  }

  private void drawTetrimino(Tetrimino tetrimino, double brightness) {
    Color baseColor = tetrimino.getBlocks()[0].getColor();
    Color drawColor = (brightness < 1.0) ? baseColor.deriveColor(0, 1, brightness, 1) : baseColor;

    for (Block block : tetrimino.getBlocks()) {
      drawBlock(block, drawColor);
    }
  }

  private void drawBlock(Block block, Color color) {
    double xPos = block.getX() * blockSize + offsetX;
    double yPos = (Board.HEIGHT * blockSize) - (block.getY() * blockSize + blockSize) + offsetY;

    gc.setFill(color);
    gc.fillRect(xPos, yPos, blockSize, blockSize);

    gc.setLineWidth(1);

    gc.setStroke(color.brighter());
    gc.strokeLine(xPos, yPos + blockSize - 1, xPos, yPos);
    gc.strokeLine(xPos, yPos, xPos + blockSize - 1, yPos);

    gc.setStroke(color.darker());
    gc.strokeLine(xPos + 1, yPos + blockSize - 1, xPos + blockSize - 1, yPos + blockSize - 1);
    gc.strokeLine(xPos + blockSize - 1, yPos + blockSize - 1, xPos + blockSize - 1, yPos + 1);
  }
}