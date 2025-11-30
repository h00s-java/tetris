module me.husak.tetris {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;

  opens me.husak.tetris to javafx.graphics, javafx.fxml;

  exports me.husak.tetris;
}