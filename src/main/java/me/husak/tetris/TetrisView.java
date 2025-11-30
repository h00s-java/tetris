package me.husak.tetris;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class TetrisView extends Pane {

  private final Canvas canvas;
  private final GraphicsContext gc;

  private double blockSize;
  private double boardX, boardY;
  private double boardWidth, boardHeight;

  private static final Color BG_COLOR_DARK = Color.rgb(10, 15, 40);
  private static final Color BG_COLOR_LIGHT = Color.rgb(20, 25, 60);
  private static final Color BORDER_GLOW = Color.rgb(0, 240, 255);
  private static final Font HUD_FONT = Font.font("Monospaced", FontWeight.BOLD, 16);
  private static final Font VALUE_FONT = Font.font("Monospaced", FontWeight.BOLD, 22);

  public TetrisView() {
    canvas = new Canvas(800, 800);
    gc = canvas.getGraphicsContext2D();
    getChildren().add(canvas);

    canvas.widthProperty().bind(this.widthProperty());
    canvas.heightProperty().bind(this.heightProperty());
  }

  public void paint(Board board) {
    calculateDimensions();
    drawBackground();
    drawBoardBackground();
    drawGrid();
    drawHUD(board);
    drawBoardBlocks(board);
    drawGlowingBorder();
  }

  private void calculateDimensions() {
    double w = canvas.getWidth();
    double h = canvas.getHeight();

    double maxBoardH = h * 0.85;
    double maxBoardW = w * 0.50;

    double blockSizeH = maxBoardH / Board.HEIGHT;
    double blockSizeW = maxBoardW / Board.WIDTH;

    blockSize = Math.min(blockSizeH, blockSizeW);

    boardWidth = blockSize * Board.WIDTH;
    boardHeight = blockSize * Board.HEIGHT;

    boardX = (w - boardWidth) / 2;
    boardY = (h - boardHeight) / 2;
  }

  private void drawBackground() {
    RadialGradient gradient = new RadialGradient(
        0, 0,
        canvas.getWidth() / 2, canvas.getHeight() / 2,
        canvas.getHeight(),
        false,
        CycleMethod.NO_CYCLE,
        new Stop(0, BG_COLOR_LIGHT),
        new Stop(1, BG_COLOR_DARK)
    );
    gc.setFill(gradient);
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
  }

  private void drawBoardBackground() {
    gc.setFill(Color.rgb(0, 0, 0, 0.5));
    gc.fillRect(boardX, boardY, boardWidth, boardHeight);
  }

  private void drawGrid() {
    gc.setStroke(Color.rgb(255, 255, 255, 0.05));
    gc.setLineWidth(1);

    for (int i = 0; i <= Board.WIDTH; i++) {
      double x = boardX + i * blockSize;
      gc.strokeLine(x, boardY, x, boardY + boardHeight);
    }

    for (int i = 0; i <= Board.HEIGHT; i++) {
      double y = boardY + i * blockSize;
      gc.strokeLine(boardX, y, boardX + boardWidth, y);
    }
  }

  private void drawGlowingBorder() {
    gc.save();
    gc.setStroke(BORDER_GLOW);
    gc.setLineWidth(3);
    gc.setEffect(new Bloom(0.3));
    gc.strokeRect(boardX - 2, boardY - 2, boardWidth + 4, boardHeight + 4);
    gc.restore();
  }

  private void drawBoardBlocks(Board board) {
    for (int i = 0; i < Board.HEIGHT; i++) {
      for (int j = 0; j < Board.WIDTH; j++) {
        Block b = board.getBlocks()[i][j];
        if (b != null) {
          drawBlock(b.getX(), b.getY(), b.getColor(), 1.0);
        }
      }
    }

    if (board.getGhostTetrimino() != null) {
      drawTetrimino(board.getGhostTetrimino(), 0.2);
    }

    if (board.getCurrentTetrimino() != null) {
      drawTetrimino(board.getCurrentTetrimino(), 1.0);
    }
  }

  private void drawHUD(Board board) {
    gc.setFill(Color.WHITE);
    gc.setTextAlign(TextAlignment.CENTER);

    double leftCenterX = boardX / 2;
    double rightCenterX = boardX + boardWidth + (canvas.getWidth() - (boardX + boardWidth)) / 2;

    drawLabel(leftCenterX, boardY + 20, "HOLD");
    drawPanelBackground(leftCenterX, boardY + 35, 4);

    double statsY = boardY + boardHeight - 150;
    drawLabel(leftCenterX, statsY, "LINES");
    drawValue(leftCenterX, statsY + 25, String.valueOf(board.getClearedLines()));

    drawLabel(leftCenterX, statsY + 60, "LEVEL");
    drawValue(leftCenterX, statsY + 85, String.valueOf(board.getClearedLines() / 10));

    drawLabel(leftCenterX, statsY + 120, "SCORE");
    drawValue(leftCenterX, statsY + 145, String.valueOf(board.getClearedLines() * 100));

    drawLabel(rightCenterX, boardY + 20, "NEXT");
    drawPanelBackground(rightCenterX, boardY + 35, 12);
  }

  private void drawPanelBackground(double centerX, double y, double heightInBlocks) {
    double w = blockSize * 4.5;
    double h = blockSize * heightInBlocks;
    gc.setFill(Color.rgb(0, 0, 0, 0.3));
    gc.fillRoundRect(centerX - w / 2, y, w, h, 10, 10);
  }

  private void drawTetrimino(Tetrimino tetrimino, double brightness) {
    Color baseColor = tetrimino.getBlocks()[0].getColor();
    Color drawColor = (brightness < 1.0) ? baseColor.deriveColor(0, 1, brightness, 1) : baseColor;

    for (Block block : tetrimino.getBlocks()) {
      drawBlock(block.getX(), block.getY(), drawColor, brightness);
    }
  }

  private void drawPreviewPiece(double centerX, double centerY, Tetrimino piece) {
    Color color = piece.getBlocks()[0].getColor();
    for (Block block : piece.getBlocks()) {
      double x = centerX + (block.getX() * blockSize);
      double y = centerY - (block.getY() * blockSize);
      drawBlockAbsolute(x, y, color);
    }
  }

  private void drawBlock(int x, int y, Color color, double brightness) {
    double xPos = boardX + (x * blockSize);
    double yPos = boardY + (boardHeight) - (y * blockSize + blockSize);
    drawBlockAbsolute(xPos, yPos, color);
  }

  private void drawBlockAbsolute(double x, double y, Color color) {
    gc.setFill(color);
    gc.fillRect(x, y, blockSize, blockSize);

    double innerOffset = blockSize * 0.2;
    double innerSize = blockSize - (innerOffset * 2);

    gc.setStroke(color.brighter());
    gc.setLineWidth(blockSize * 0.1);
    gc.strokeRect(x + innerOffset, y + innerOffset, innerSize, innerSize);

    gc.setFill(Color.WHITE);
    double dotSize = blockSize * 0.15;
    gc.fillRect(x + innerOffset, y + innerOffset, dotSize, dotSize);

    gc.setStroke(color.darker());
    gc.setLineWidth(1);
    gc.strokeRect(x, y, blockSize, blockSize);
  }

  private void drawLabel(double x, double y, String text) {
    gc.setFont(HUD_FONT);
    gc.setFill(Color.LIGHTGRAY);
    gc.fillText(text, x, y);
  }

  private void drawValue(double x, double y, String text) {
    gc.setFont(VALUE_FONT);
    gc.setFill(Color.WHITE);
    gc.fillText(text, x, y);
  }
}