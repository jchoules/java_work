package uk.ac.cam.jc841.tick7;

import uk.ac.cam.acr31.life.World;

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

  public void initialise(World world) throws PatternFormatException {
    String[] rowStrings = cells.split(" ");

    for (int i = 0; i < rowStrings.length; i++) {
      char[] cellChars = rowStrings[i].toCharArray();
      for (int j = 0; j < cellChars.length; j++) {
        if (startCol + j >= world.getWidth() || startRow + i >= world.getHeight()) {
          throw new PatternFormatException("could not set cell at (" +
                                           (startCol + j) + ", " + (startRow + i) +
                                           "): world not large enough");
        } else {
          if (cellChars[j] == '1') {
            world.setCell(startCol + j, startRow + i, true);
          } else if (cellChars[j] == '0') {
            world.setCell(startCol + j, startRow + i, false);
          } else {
            throw new PatternFormatException("pattern may only contain 1s and 0s");
          }
        }
      }
    }
  }
}
