package me.husak.tetris;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TetrisView extends Pane {

  private final GraphicsContext gcBg;
  private final GraphicsContext gcGame;
  private final GraphicsContext gcEffect;

  // Dimensions
  private double blockSize;
  private double boardX, boardY;
  private double boardWidth, boardHeight;
  private double lastWidth, lastHeight;

  // Assets / Constants
  private static final Color BG_COLOR_DARK = Color.rgb(10, 15, 40);
  private static final Color BG_COLOR_LIGHT = Color.rgb(20, 25, 60);
  private static final Color BORDER_GLOW = Color.rgb(0, 240, 255);
  private static final Font HUD_FONT = Font.font("Monospaced", FontWeight.BOLD, 16);
  private static final Font VALUE_FONT = Font.font("Monospaced", FontWeight.BOLD, 22);

  // Particle System
  private final List<Particle> particles = new ArrayList<>();
  private final Random random = new Random();

  public TetrisView() {
    Canvas backgroundLayer = new Canvas(800, 800);
    Canvas gameLayer = new Canvas(800, 800);
    Canvas effectLayer = new Canvas(800, 800);

    // Bind sizes to the Pane
    backgroundLayer.widthProperty().bind(this.widthProperty());
    backgroundLayer.heightProperty().bind(this.heightProperty());
    gameLayer.widthProperty().bind(this.widthProperty());
    gameLayer.heightProperty().bind(this.heightProperty());
    effectLayer.widthProperty().bind(this.widthProperty());
    effectLayer.heightProperty().bind(this.heightProperty());

    // Get Contexts
    gcBg = backgroundLayer.getGraphicsContext2D();
    gcGame = gameLayer.getGraphicsContext2D();
    gcEffect = effectLayer.getGraphicsContext2D();

    // Add visual flair
    gameLayer.setEffect(new Bloom(0.5)); // Make the blocks glow
    effectLayer.setEffect(new Bloom(0.8)); // Make explosions really bright
    effectLayer.setMouseTransparent(true); // Let clicks pass through if needed

    getChildren().addAll(backgroundLayer, gameLayer, effectLayer);
  }

  /**
   * Main render loop called from Game.java
   */
  public void paint(Board board) {
    double currentW = getWidth();
    double currentH = getHeight();

    // 1. Handle Resize / Static Layer
    // Only redraw the background if the window size actually changed
    if (currentW != lastWidth || currentH != lastHeight) {
      lastWidth = currentW;
      lastHeight = currentH;
      calculateDimensions();
      drawStaticLayer(board);
    }

    // 2. Render Game State (Always)
    drawDynamicLayer(board);

    // 3. Render Effects (Always)
    drawEffectsLayer();
  }

  // ==========================================
  // LAYER 1: STATIC (Background, Grid, Text Labels)
  // ==========================================

  private void drawStaticLayer(Board board) {
    gcBg.clearRect(0, 0, getWidth(), getHeight());

    // Background Gradient
    RadialGradient gradient = new RadialGradient(0, 0, getWidth() / 2, getHeight() / 2, getHeight(), false, CycleMethod.NO_CYCLE, new Stop(0, BG_COLOR_LIGHT), new Stop(1, BG_COLOR_DARK));
    gcBg.setFill(gradient);
    gcBg.fillRect(0, 0, getWidth(), getHeight());

    // Board Background
    gcBg.setFill(Color.rgb(0, 0, 0, 0.5));
    gcBg.fillRect(boardX, boardY, boardWidth, boardHeight);

    // Grid Lines
    gcBg.setStroke(Color.rgb(255, 255, 255, 0.05));
    gcBg.setLineWidth(1);

    for (int i = 0; i <= Board.WIDTH; i++) {
      double x = boardX + i * blockSize;
      gcBg.strokeLine(x, boardY, x, boardY + boardHeight);
    }

    for (int i = 0; i <= Board.HEIGHT; i++) {
      double y = boardY + i * blockSize;
      gcBg.strokeLine(boardX, y, boardX + boardWidth, y);
    }

    // Glowing Border
    gcBg.save();
    gcBg.setStroke(BORDER_GLOW);
    gcBg.setLineWidth(3);
    gcBg.setEffect(new GaussianBlur(3)); // Soft glow on the border
    gcBg.strokeRect(boardX - 2, boardY - 2, boardWidth + 4, boardHeight + 4);
    gcBg.restore();

    // HUD Static Elements (Labels and Panels)
    drawStaticHUD();
  }

  private void drawStaticHUD() {
    gcBg.setFill(Color.WHITE);
    gcBg.setTextAlign(TextAlignment.CENTER);

    double leftCenterX = boardX / 2;
    double rightCenterX = boardX + boardWidth + (getWidth() - (boardX + boardWidth)) / 2;

    // HOLD Section
    drawLabel(gcBg, leftCenterX, boardY + 20, "HOLD");
    drawPanelBackground(gcBg, leftCenterX, boardY + 35, 4);

    // STATS Section
    double statsY = boardY + boardHeight - 150;
    drawLabel(gcBg, leftCenterX, statsY, "LINES");
    drawLabel(gcBg, leftCenterX, statsY + 60, "LEVEL");
    drawLabel(gcBg, leftCenterX, statsY + 120, "SCORE");

    // NEXT Section
    drawLabel(gcBg, rightCenterX, boardY + 20, "NEXT");
    drawPanelBackground(gcBg, rightCenterX, boardY + 35, 12); // Taller for next pieces
  }

  // ==========================================
  // LAYER 2: DYNAMIC (Blocks, Pieces, Scores)
  // ==========================================

  private void drawDynamicLayer(Board board) {
    gcGame.clearRect(0, 0, getWidth(), getHeight());

    // Draw the placed blocks
    for (int i = 0; i < Board.HEIGHT; i++) {
      for (int j = 0; j < Board.WIDTH; j++) {
        Block b = board.getBlocks()[i][j];
        if (b != null) {
          drawBlock(gcGame, b.getX(), b.getY(), b.getColor(), 1.0);
        }
      }
    }

    // Draw Ghost
    if (board.getGhostTetrimino() != null) {
      drawTetrimino(gcGame, board.getGhostTetrimino(), 0.2);
    }

    // Draw Current
    if (board.getCurrentTetrimino() != null) {
      drawTetrimino(gcGame, board.getCurrentTetrimino(), 1.0);
    }

    // Draw Dynamic Text (Scores)
    drawDynamicHUD(board);
  }

  private void drawDynamicHUD(Board board) {
    gcGame.setTextAlign(TextAlignment.CENTER);
    double leftCenterX = boardX / 2;
    double statsY = boardY + boardHeight - 150;

    drawValue(gcGame, leftCenterX, statsY + 25, String.valueOf(board.getClearedLines()));
    drawValue(gcGame, leftCenterX, statsY + 85, String.valueOf(board.getClearedLines() / 10)); // Example Level calc
    drawValue(gcGame, leftCenterX, statsY + 145, String.valueOf(board.getClearedLines() * 100)); // Example Score calc
  }

  // ==========================================
  // LAYER 3: EFFECTS (Particles)
  // ==========================================

  private void drawEffectsLayer() {
    gcEffect.clearRect(0, 0, getWidth(), getHeight());

    Iterator<Particle> it = particles.iterator();
    while (it.hasNext()) {
      Particle p = it.next();
      p.update();
      if (!p.isAlive()) {
        it.remove();
      } else {
        p.draw(gcEffect);
      }
    }
  }

  /**
   * Call this method when a line is cleared to trigger an explosion.
   *
   * @param rowY The logic Y coordinate (0-21) of the cleared line.
   */
  public void explodeLine(int rowY) {
    // Calculate visual Y position
    double screenY = boardY + (boardHeight) - (rowY * blockSize + blockSize) + (blockSize / 2);

    for (int i = 0; i < Board.WIDTH; i++) {
      double screenX = boardX + (i * blockSize) + (blockSize / 2);
      spawnExplosion(screenX, screenY, Color.CYAN); // Or use the block's color if you can access it
    }
  }

  private void spawnExplosion(double x, double y, Color color) {
    int particleCount = 10;
    for (int i = 0; i < particleCount; i++) {
      particles.add(new Particle(x, y, color));
    }
  }

  // ==========================================
  // HELPERS & MATH
  // ==========================================

  private void calculateDimensions() {
    double w = getWidth();
    double h = getHeight();

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

  private void drawPanelBackground(GraphicsContext gc, double centerX, double y, double heightInBlocks) {
    double w = blockSize * 4.5;
    double h = blockSize * heightInBlocks;
    gc.setFill(Color.rgb(0, 0, 0, 0.3));
    gc.fillRoundRect(centerX - w / 2, y, w, h, 10, 10);
  }

  private void drawTetrimino(GraphicsContext gc, Tetrimino tetrimino, double brightness) {
    Color baseColor = tetrimino.getBlocks()[0].getColor();
    Color drawColor = (brightness < 1.0) ? baseColor.deriveColor(0, 1, brightness, 1) : baseColor;

    for (Block block : tetrimino.getBlocks()) {
      drawBlock(gc, block.getX(), block.getY(), drawColor, brightness);
    }
  }

  private void drawBlock(GraphicsContext gc, int x, int y, Color color, double brightness) {
    double xPos = boardX + (x * blockSize);
    double yPos = boardY + (boardHeight) - (y * blockSize + blockSize);
    drawBlockAbsolute(gc, xPos, yPos, color);
  }

  private void drawBlockAbsolute(GraphicsContext gc, double x, double y, Color color) {
    // --- Colors ---
    // Create lighter and darker versions of the base color for 3D effect
    Color light = color.brighter().brighter();
    Color medium = color;
    Color dark = color.darker();
    Color ultraDark = color.darker().darker();
    Color outline = Color.rgb(30, 30, 30); // A dark grey for the block border

    // --- Dimensions ---
    // Calculate sizes relative to the block size
    double bevel = blockSize * 0.125; // The width of the outer rim
    double innerX = x + bevel;
    double innerY = y + bevel;
    double innerSize = blockSize - 2 * bevel;
    double innerBevel = bevel * 0.6; // The width of the inner "button" bevel

    // --- 1. Outer Bevel (Rim) ---
    // Draws the four sides of the outer rim with mitered corners.
    // Top and Left are light (lit from top-left).
    // Bottom and Right are dark (in shadow).

    // Top (light)
    gc.setFill(light);
    gc.fillPolygon(new double[]{x, x + blockSize, x + blockSize - bevel, x + bevel},
        new double[]{y, y, y + bevel, y + bevel}, 4);
    // Left (light)
    gc.fillPolygon(new double[]{x, x + bevel, x + bevel, x},
        new double[]{y, y + bevel, y + blockSize - bevel, y + blockSize}, 4);
    // Bottom (dark)
    gc.setFill(dark);
    gc.fillPolygon(new double[]{x, x + blockSize, x + blockSize - bevel, x + bevel},
        new double[]{y + blockSize, y + blockSize, y + blockSize - bevel, y + blockSize - bevel}, 4);
    // Right (dark)
    gc.fillPolygon(new double[]{x + blockSize, x + blockSize - bevel, x + blockSize - bevel, x + blockSize},
        new double[]{y, y + bevel, y + blockSize - bevel, y + blockSize}, 4);

    // --- 2. Inner Button Bevel ---
    // Draws the beveled edge of the inner square "button".
    // We use ultraDark for the shadow here to make it pop more.

    // Top (light)
    gc.setFill(light);
    gc.fillPolygon(new double[]{innerX, innerX + innerSize, innerX + innerSize - innerBevel, innerX + innerBevel},
        new double[]{innerY, innerY, innerY + innerBevel, innerY + innerBevel}, 4);
    // Left (light)
    gc.fillPolygon(new double[]{innerX, innerX + innerBevel, innerX + innerBevel, innerX},
        new double[]{innerY, innerY + innerBevel, innerY + innerSize - innerBevel, innerY + innerSize}, 4);
    // Bottom (ultra dark)
    gc.setFill(ultraDark);
    gc.fillPolygon(new double[]{innerX, innerX + innerSize, innerX + innerSize - innerBevel, innerX + innerBevel},
        new double[]{innerY + innerSize, innerY + innerSize, innerY + innerSize - innerBevel, innerY + innerSize - innerBevel}, 4);
    // Right (ultra dark)
    gc.fillPolygon(new double[]{innerX + innerSize, innerX + innerSize - innerBevel, innerX + innerSize - innerBevel, innerX + innerSize},
        new double[]{innerY, innerY + innerBevel, innerY + innerSize - innerBevel, innerY + innerSize}, 4);

    // --- 3. Inner Center Face ---
    // Fills the very center of the button with the base color.
    gc.setFill(medium);
    gc.fillRect(innerX + innerBevel, innerY + innerBevel, innerSize - 2 * innerBevel, innerSize - 2 * innerBevel);

    // --- 4. Final Outline ---
    // Draws a crisp dark border around the entire block.
    gc.setStroke(outline);
    gc.setLineWidth(1);
    gc.strokeRect(x, y, blockSize, blockSize);
  }

  private void drawLabel(GraphicsContext gc, double x, double y, String text) {
    gc.setFont(HUD_FONT);
    gc.setFill(Color.LIGHTGRAY);
    gc.fillText(text, x, y);
  }

  private void drawValue(GraphicsContext gc, double x, double y, String text) {
    gc.setFont(VALUE_FONT);
    gc.setFill(Color.WHITE);
    gc.fillText(text, x, y);
  }

  // ==========================================
  // INTERNAL PARTICLE CLASS
  // ==========================================

  private class Particle {
    double x, y;
    double dx, dy;
    double life;
    Color color;

    Particle(double x, double y, Color color) {
      this.x = x;
      this.y = y;
      this.color = color;
      this.life = 1.0;

      // Random velocity spread
      double speed = blockSize * 0.1;
      this.dx = (random.nextDouble() - 0.5) * speed;
      this.dy = (random.nextDouble() - 0.5) * speed;
    }

    void update() {
      x += dx;
      y += dy;
      life -= 0.05; // Fade out speed
    }

    boolean isAlive() {
      return life > 0;
    }

    void draw(GraphicsContext gc) {
      gc.setGlobalAlpha(life);
      gc.setFill(color);
      // Particle size based on remaining life
      double size = (blockSize * 0.3) * life;
      gc.fillOval(x, y, size, size);
      gc.setGlobalAlpha(1.0);
    }
  }
}