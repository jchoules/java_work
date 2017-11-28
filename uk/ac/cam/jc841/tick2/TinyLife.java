package uk.ac.cam.jc841.tick2;

public class TinyLife {
  public static boolean getCell(long world, int col, int row) {
    if (col >= 0 && col < 8 && row >= 0 && row < 8) {
      int position = col + row * 8;
      return PackedLong.get(world, position);
    } else {
      return false;
    }
  }

  public static long setCell(long world, int col, int row, boolean value) {
    if (col >= 0 && col < 8 && row >= 0 && row < 8) {
      int position = col + row * 8;
      return PackedLong.set(world, position, value);
    } else {
      return world;
    }
  }

  public static int countNeighbours(long world, int col, int row) {
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

  public static boolean computeCell(long world, int col, int row) {
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

  public static long nextGeneration(long world) {
    long next = world;
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        boolean nextCell = computeCell(world, col, row);
        next = setCell(next, col, row, nextCell);
      }
    }
    return next;
  }

  public static void play(long world) throws Exception {
    int userResponse = 0;
    while (userResponse != 'q') {
      print(world);
      userResponse = System.in.read();
      world = nextGeneration(world);
    }
  }

  public static void print(long world) {
    System.out.println("-");
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        System.out.print(getCell(world, col, row) ? "#" : "_");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) throws Exception {
    play(Long.decode(args[0]));
  }
}
