package uk.ac.cam.jc841.tick3;

public class Pattern {
  private String name;
  private String author;
  private int width;
  private int height;
  private int startCol;
  private int startRow;
  private String cells;

  public String getName() {
    return name;
  }
  public String getAuthor() {
    return author;
  }
  public int getWidth() {
    return width;
  }
  public int getHeight() {
    return height;
  }
  public int getStartCol() {
    return startCol;
  }
  public int getStartRow() {
    return startRow;
  }
  public String getCells() {
    return cells;
  }

  public Pattern(String format) {
    String[] formatPieces = format.split(":");

    name = formatPieces[0];
    author = formatPieces[1];
    width = Integer.parseInt(formatPieces[2]);
    height = Integer.parseInt(formatPieces[3]);
    startCol = Integer.parseInt(formatPieces[4]);
    startRow = Integer.parseInt(formatPieces[5]);
    cells = formatPieces[6];
  }

  public void initialise(boolean[][] world) {
    String[] rowStrings = cells.split(" ");

    // First reset the world to all falses:
    for (int i = 0; i < world.length; i++) {
      for (int j = 0; j < world[i].length; j++) {
        world[i][j] = false;
      }
    }

    for (int i = 0; i < rowStrings.length; i++) {
      char[] cellChars = rowStrings[i].toCharArray();
      for (int j = 0; j < cellChars.length; j++) {
        world[startRow+i][startCol+j] = (cellChars[j] == '1');
      }
    }
  }
}
