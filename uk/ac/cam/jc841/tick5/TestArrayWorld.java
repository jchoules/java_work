package uk.ac.cam.jc841.tick5;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.io.PrintWriter;
import java.awt.Graphics;

public class TestArrayWorld implements World {

  private int generation;
  private int width;
  private int height;
  private boolean[][] cells;

  public TestArrayWorld(int w, int h) {
    width = w;
    height = h;
    generation = 0;
    cells = new boolean[height][width];
  }

  protected TestArrayWorld(TestArrayWorld prev) {
    width = prev.width;
    height = prev.height;
    generation = prev.generation + 1;
    cells = new boolean[height][width];
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

  private TestArrayWorld nextGeneration() {
    TestArrayWorld world = new TestArrayWorld(this);

    for (int row = 0; row < getHeight(); row++) {
      for (int col = 0; col < getWidth(); col++) {
        boolean nextCell = computeCell(col, row);
        world.setCell(col, row, nextCell);
      }
    }
    return world;
  }

  public World nextGeneration(int log2StepSize)  {
    TestArrayWorld world = this;
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
