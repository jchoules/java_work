package uk.ac.cam.jc841.tick7;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.io.PrintWriter;
import java.awt.Graphics;
import java.awt.Color;
//TODO: insert other appropriate "import" statements here

public abstract class WorldImpl implements World {

  private int width;
  private int height;
  private int generation;

  protected WorldImpl(int width, int height) {
    this.width = width;
    this.height = height;
    this.generation = 0;
  }

  protected WorldImpl(WorldImpl prev) {
    this.width = prev.width;
    this.height = prev.height;
    this.generation = prev.generation + 1;
  }

  public int getWidth() { return this.width; }

  public int getHeight() { return this.height; }

  public int getGeneration() { return this.generation; }

  public int getPopulation() { return 0; }

  protected String getCellAsString(int col,int row) {
    return getCell(col,row) ? "#" : "_";
  }

  protected Color getCellAsColour(int col,int row) {
    return getCell(col,row) ? Color.BLACK : Color.WHITE;
  }
  public void draw(Graphics g,int width, int height) {
    int worldWidth = getWidth();
    int worldHeight = getHeight();

    double colScale = (double)width/(double)worldWidth;
    double rowScale = (double)height/(double)worldHeight;

    for(int col=0; col<worldWidth; ++col) {
      for(int row=0; row<worldHeight; ++row) {
        int colPos = (int)(col*colScale);
        int rowPos = (int)(row*rowScale);
        int nextCol = (int)((col+1)*colScale);
        int nextRow = (int)((row+1)*rowScale);

        if (g.hitClip(colPos,rowPos,nextCol-colPos,nextRow-rowPos)) {
          g.setColor(getCellAsColour(col, row));
          g.fillRect(colPos,rowPos,nextCol-colPos,nextRow-rowPos);
        }
      }
    }
  }

  public World nextGeneration(int log2StepSize) {
    WorldImpl world = this;
    for (int i = 0; i < 1 << log2StepSize; i++) {
      world = world.nextGeneration();
    }
    return world;
  }

  public void print(Writer w) {
    PrintWriter pw = new PrintWriter(w);

    pw.println("-");
    for (int row = 0; row < getHeight(); row++) {
      for (int col = 0; col < getWidth(); col++) {
        pw.print(getCellAsString(col, row));
      }
      pw.println();
    }
    pw.flush();
  }

  protected int countNeighbours(int col, int row) {
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

  protected boolean computeCell(int col, int row) {
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

  public abstract boolean getCell(int col,int row);

  public abstract void setCell(int col, int row, boolean alive);

  protected abstract WorldImpl nextGeneration();
}

