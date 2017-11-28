package uk.ac.cam.jc841.tick6;

public class ArrayWorld extends WorldImpl {
  private boolean[][] cells;

  public ArrayWorld(int w, int h) {
    super(w, h);
    cells = new boolean[getHeight()][getWidth()];
  }

  protected ArrayWorld(ArrayWorld prev) {
    super(prev);
    cells = new boolean[getHeight()][getWidth()];
  }

  public boolean getCell(int col, int row) {
    if (col < 0 || col >= getWidth() || row < 0 || row >= getHeight()) {
      return false;
    } else {
      return cells[row][col];
    }
  }

  public void setCell(int col, int row, boolean alive) {
    if (col < 0 || col >= getWidth() || row < 0 || row >= getHeight()) {
      return;
    } else {
      cells[row][col] = alive;
    }
  }

  protected ArrayWorld nextGeneration() {
    ArrayWorld world = new ArrayWorld(this);

    for (int row = 0; row < getHeight(); row++) {
      for (int col = 0; col < getWidth(); col++) {
        boolean nextCell = computeCell(col, row);
        world.setCell(col, row, nextCell);
      }
    }
    return world;
  }
}
