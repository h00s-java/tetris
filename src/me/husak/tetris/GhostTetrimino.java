package me.husak.tetris;

import java.awt.*;

public class GhostTetrimino extends Tetrimino {

  public GhostTetrimino(Tetrimino tetrimino) {
    super(tetrimino);
    for (Block block : blocks) {
      final float hsbVals[] = Color.RGBtoHSB(block.getColor().getRed(), block.getColor().getGreen(), block.getColor().getBlue(), null);
      block.setColor(Color.getHSBColor(hsbVals[0], hsbVals[1], 0.25f * hsbVals[2]));
    }
  }

}
