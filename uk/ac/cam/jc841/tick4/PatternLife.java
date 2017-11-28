package uk.ac.cam.jc841.tick4;

public class PatternLife {
  public static boolean getCell(boolean[][] world, int col, int row) {
    if (row < 0 || row >= world.length) {
      return false;
    } else if (col < 0 || col >= world[row].length) {
      return false;
    } else {
      return world[row][col];
    }
  }

  public static void setCell(boolean[][] world, int col, int row, boolean value) {
    if (row < 0 || row >= world.length) {
    } else if (col < 0 || col >= world[row].length) {
    } else {
      world[row][col] = value;
    }
  }

  public static int countNeighbours(boolean[][] world, int col, int row) {
    int count = 0;
    for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
      for (int colOffset = -1; colOffset <= 1; colOffset++) {
        if (!(rowOffset == 0 && colOffset == 0) && getCell(world, col + colOffset, row + rowOffset)) {
          count++;
        }
      }
    }
    return count;
  }

  public static boolean computeCell(boolean[][] world, int col, int row) {
    boolean liveCell = getCell(world, col, row);
    int neighbours = countNeighbours(world, col, row);
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

  public static boolean[][] nextGeneration(boolean[][] world) {
    boolean[][] next = new boolean[world.length][];
    for (int i = 0; i < world.length; i++) {
      next[i] = world[i].clone();
    }

    for (int row = 0; row < world.length; row++) {
      for (int col = 0; col < world[row].length; col++) {
        boolean nextCell = computeCell(world, col, row);
        setCell(next, col, row, nextCell);
      }
    }
    return next;
  }

  public static void play(boolean[][] world) throws Exception {
    int userResponse = 0;
    while (userResponse != 'q') {
      print(world);
      userResponse = System.in.read();
      world = nextGeneration(world);
    }
  }

  public static void print(boolean[][] world) {
    System.out.println("-");
    for (int row = 0; row < world.length; row++) {
      for (int col = 0; col < world[row].length; col++) {
        System.out.print(getCell(world, col, row) ? "#" : "_");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) throws Exception {
    try {
      Pattern p = new Pattern(args[0]);
      boolean[][] world = new boolean[p.getHeight()][p.getWidth()];
      p.initialise(world);
      play(world);
    } catch (ArrayIndexOutOfBoundsException error) {
      System.out.println("Error: need at least one argument");
    } catch (PatternFormatException error) {
      System.out.println("Error: " + error.getMessage());
    }
  }
}
