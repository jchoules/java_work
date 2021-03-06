package uk.ac.cam.jc841.tick4;

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

  public String getFormatString() {
    return (getName() + ":"
          + getAuthor() + ":"
          + getWidth() + ":"
          + getHeight() + ":"
          + getStartCol() + ":"
          + getStartRow() + ":"
          + getCells());
  }

  public Pattern(String format) throws PatternFormatException {
    String[] formatPieces = format.split(":");

    if (formatPieces.length < 7) {
      throw new PatternFormatException("not enough fields (" + formatPieces.length + " rather than 7)");
    }

    name = formatPieces[0];
    author = formatPieces[1];

    try {
      width = Integer.parseInt(formatPieces[2]);
    } catch (NumberFormatException error) {
      throw new PatternFormatException("width of world is not a positive integer");
    }

    if (width < 1) {
      throw new PatternFormatException("width of world is not a positive integer");
    }

    try {
      height = Integer.parseInt(formatPieces[3]);
    } catch (NumberFormatException error) {
      throw new PatternFormatException("height of world is not a positive integer");
    }

    if (height < 1) {
      throw new PatternFormatException("height of world is not a positive integer");
    }

    try {
      startCol = Integer.parseInt(formatPieces[4]);
    } catch (NumberFormatException error) {
      throw new PatternFormatException("column offset of pattern is not a nonnegative integer");
    }

    if (startCol < 0) {
      throw new PatternFormatException("column offset of pattern is not a nonnegative integer");
    }

    try {
      startRow = Integer.parseInt(formatPieces[5]);
    } catch (NumberFormatException error) {
      throw new PatternFormatException("row offset of pattern is not a nonnegative integer");
    }

    if (startRow < 0) {
      throw new PatternFormatException("row offset of pattern is not a nonnegative integer");
    }

    cells = formatPieces[6];
  }

  public void initialise(boolean[][] world) throws PatternFormatException {
    String[] rowStrings = cells.split(" ");

    boolean[][] tempWorld = new boolean[world.length][world[0].length];

    for (int i = 0; i < tempWorld.length; i++) {
      for (int j = 0; j < tempWorld[i].length; j++) {
        tempWorld[i][j] = false;
      }
    }

    for (int i = 0; i < rowStrings.length; i++) {
      char[] cellChars = rowStrings[i].toCharArray();
      for (int j = 0; j < cellChars.length; j++) {
        try {
          if (cellChars[j] == '1') {
            tempWorld[startRow + i][startCol + j] = true;
          } else if (cellChars[j] == '0') {
            tempWorld[startRow + i][startCol + j] = false;
          } else {
            throw new PatternFormatException("pattern may only contain 1s and 0s");
          }
        } catch (ArrayIndexOutOfBoundsException error) {
          throw new PatternFormatException("could not set cell at (" + (startCol + j) + ", " + (startRow + i) + "): world not large enough");
        }
      }
    }

    for (int i = 0; i < world.length; i++) {
      for (int j = 0; j < world[i].length; j++) {
        world[i][j] = tempWorld[i][j];;
      }
    }
  }
}
