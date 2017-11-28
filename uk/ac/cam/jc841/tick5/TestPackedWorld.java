package uk.ac.cam.jc841.tick5;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.io.PrintWriter;
import java.awt.Graphics;

public class TestPackedWorld implements World {

  private int generation;
  private int width;
  private int height;
  private long cells;

  public TestPackedWorld() {
    width = 8;
    height = 8;
    generation = 0;
    cells = 0;
  }

  protected TestPackedWorld(TestPackedWorld prev) {
    width = 8;
    height = 8;
    generation = prev.generation + 1;
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

  public int getWidth()  {
    return width;
  }

  public int getHeight()  {
    return height;
  }

  public int getGeneration()  {
    return generation;
  }

  public int getPopulation()  { return 0; }

  public void print(Writer w)  {
    PrintWriter pw = new PrintWriter(w);

    pw.println("-");
    for (int row = 0; row < getHeight(); row++) {
      for (int col = 0; col < getWidth(); col++) {
        pw.print(getCell(col, row) ? "#" : "_");
      }
      pw.println();
    }
    pw.flush();
  }

  public void draw(Graphics g, int width, int height)  { /*Leave empty*/ }

  private TestPackedWorld nextGeneration() {
    TestPackedWorld world = new TestPackedWorld(this);

    for (int row = 0; row < getHeight(); row++) {
      for (int col = 0; col < getWidth(); col++) {
        boolean nextCell = computeCell(col, row);
        world.setCell(col, row, nextCell);
      }
    }
    return world;
  }

  public World nextGeneration(int log2StepSize)  {
    TestPackedWorld world = this;
    for (int i = 0; i < 1 << log2StepSize; i++) {
      world = world.nextGeneration();
    }
    return world;
  }

  private int countNeighbours(int col, int row) {
    int count = 0;
    for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
      for (int colOffset = -1; colOffset <= 1; colOffset++) {
        if (!(rowOffset == 0 && colOffset == 0) && getCell(col + colOffset, row + rowOffset)) {
          count++;
        }
      }
    }
    return count;
  }

  private boolean computeCell(int col, int row) {
    boolean liveCell = getCell(col, row);
    int neighbours = countNeighbours(col, row);
    boolean nextCell = false;

    if (neighbours < 2) {
      nextCell = false;
    }
    if (liveCell && (neighbours == 2 || neighbours == 3)) {
      nextCell = true;
    }
    if (liveCell && neighbours > 3) {
      nextCell = false;
    }
    if (!liveCell && neighbours == 3) {
      nextCell = true;
    }

    return nextCell;
  }
}
