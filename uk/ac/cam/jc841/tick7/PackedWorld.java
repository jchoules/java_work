package uk.ac.cam.jc841.tick7;

public class PackedWorld extends WorldImpl {
  private long cells;

  public PackedWorld() {
    super(8, 8);
    cells = 0;
  }

  protected PackedWorld(PackedWorld prev) {
    super(prev);
    cells = 0;
  }

  public boolean getCell(int col, int row) {
    if (col < 0 || col >= getWidth() || row < 0 || row >= getHeight()) {
      return false;
    } else {
      int position = col + row * 8;
      return PackedLong.get(cells, position);
    }
  }

  public void setCell(int col, int row, boolean alive) {
    if (col < 0 || col >= getWidth() || row < 0 || row >= getHeight()) {
      return;
    } else {
      int position = col + row * 8;
      cells = PackedLong.set(cells, position, alive);
    }
  }

  protected PackedWorld nextGeneration() {
    PackedWorld world = new PackedWorld(this);

    for (int row = 0; row < getHeight(); row++) {
      for (int col = 0; col < getWidth(); col++) {
        boolean nextCell = computeCell(col, row);
        world.setCell(col, row, nextCell);
      }
    }
    return world;
  }
}
