package uk.ac.cam.jc841.urop;

public class Line {
  private final int x1;
  private final int y1;
  private final int x2;
  private final int y2;

  public Line(int xStart, int yStart, int xEnd, int yEnd) {
    x1 = xStart;
    y1 = yStart;
    x2 = xEnd;
    y2 = yEnd;
  }

  public int getX1() {
    return x1;
  }

  public int getY1() {
    return y1;
  }

  public int getX2() {
    return x2;
  }

  public int getY2() {
    return y2;
  }
}
