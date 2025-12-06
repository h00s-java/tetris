package me.husak.tetris;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

public class Game extends Application {
  private Board board;
  private TetrisView tetrisView;

  private boolean isPaused = false;
  private long lastUpdate = 0;

  @Override
  public void start(Stage stage) {
    BorderPane root = new BorderPane();
    root.setStyle("-fx-background-color: black;");

    tetrisView = new TetrisView();
    root.setCenter(tetrisView);

    initGame();

    Scene scene = new Scene(root, 600, 700);
    scene.setOnKeyPressed(this::handleInput);

    scene.widthProperty().addListener(e -> tetrisView.paint(board));
    scene.heightProperty().addListener(e -> tetrisView.paint(board));

    stage.setTitle("Tetris FX");
    stage.setScene(scene);
    stage.show();

    startGameLoop();
  }

  private void initGame() {
    board = new Board();
    isPaused = false;
    board.setBoardChangeListener(new Board.BoardChangeListener() {
      @Override
      public void onBoardChange() {
      }

      @Override
      public void onLineCleared(int row) {
        tetrisView.explodeLine(row);
      }
    });
    // tetrisView.paint(board);
  }

  private void startGameLoop() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        if (isPaused) return;

        if (now - lastUpdate >= 1_000_000_000) {
          board.moveCurrentTetriminoDown();
          checkGameState();
          lastUpdate = now;
        }

        tetrisView.paint(board);
      }
    };
    timer.start();
  }

  private void checkGameState() {
    if (!board.isValid()) {
      isPaused = true;
      // TODO: Game over text
    }
  }

  private void handleInput(KeyEvent event) {
    if (event.getCode() == KeyCode.P) {
      isPaused = !isPaused;
      tetrisView.paint(board);
      return;
    }

    if (!isPaused && board.isValid()) {
      switch (event.getCode()) {
        case LEFT:
        case NUMPAD4:
          board.moveCurrentTetriminoLeft();
          break;
        case RIGHT:
        case NUMPAD6:
          board.moveCurrentTetriminoRight();
          break;
        case UP:
        case NUMPAD9:
          board.rotateCurrentTetriminoClockwise();
          break;
        case NUMPAD7:
          board.rotateCurrentTetriminoCounterClockwise();
          break;
        case DOWN:
        case NUMPAD5:
          board.moveCurrentTetriminoDown();
          break;
        case SPACE:
          board.dropCurrentTetriminoDown();
          break;
        default:
          break;
      }
    }

    switch (event.getCode()) {
      case R:
        confirmRestart();
        break;
      case ESCAPE:
        confirmExit();
        break;
      default:
        break;
    }
    tetrisView.paint(board);
  }

  private void confirmRestart() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Restart?");
    alert.setHeaderText(null);
    alert.setContentText("Would you like to restart the game?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      initGame();
    }
  }

  private void confirmExit() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Exit?");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to exit?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      Platform.exit();
      System.exit(0);
    }
  }
}