package me.husak.tetris;

import java.util.Random;

public class RandomGenerator {
  private final Tetrimino[] bag = new Tetrimino[BAG_SIZE];
  private int currentIndex;

  private static final int SET_SIZE = 7;
  private static final int BAG_SIZE = 14;

  public RandomGenerator() {
    currentIndex = -1;
    randomizeSetAt(0);
  }

  private void initializeSetAt(int index) {
    // I, J, L, O, S, T, Z
    bag[index++] = new ITetrimino(0, 0);
    bag[index++] = new JTetrimino(0, 0);
    bag[index++] = new LTetrimino(0, 0);
    bag[index++] = new OTetrimino(0, 0);
    bag[index++] = new STetrimino(0, 0);
    bag[index++] = new TTetrimino(0, 0);
    bag[index] = new ZTetrimino(0, 0);
  }

  private void randomizeSetAt(int index) {
    final Random random = new Random();
    final int maxIndex = index + SET_SIZE;
    int randomIndex;
    Tetrimino temp;

    initializeSetAt(index);
    for (int i = maxIndex - 1; i > index; i--) {
      randomIndex = random.nextInt(i - index) + index;
      temp = bag[randomIndex];
      bag[randomIndex] = bag[i];
      bag[i] = temp;
    }
    for (int i = index; i < maxIndex; i++) {
      randomIndex = random.nextInt(maxIndex - i) + i;
      temp = bag[randomIndex];
      bag[randomIndex] = bag[i];
      bag[i] = temp;
    }
  }

  public Tetrimino nextTetrimino() {
    currentIndex = (currentIndex + 1) % BAG_SIZE;
    if (currentIndex % SET_SIZE == 0) {
      randomizeSetAt((currentIndex + SET_SIZE) % BAG_SIZE);
    }
    return bag[currentIndex];
  }
}
