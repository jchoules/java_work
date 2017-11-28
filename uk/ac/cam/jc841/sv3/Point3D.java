package uk.ac.cam.jc841.sv3;

public class Point3D implements Comparable<Point3D> {
  private final double mX;
  private final double mY;
  private final double mZ;

  public Point3D(double x, double y, double z) {
    mX = x;
    mY = y;
    mZ = z;
  }

  public double getX() {
    return mX;
  }
  public double getY() {
    return mY;
  }
  public double getZ() {
    return mZ;
  }

  public int compareTo(Point3D p) {
    if (mZ > p.mZ) {
      return 1;
    } else if (mZ < p.mZ) {
      return -1;
    } else if (mY > p.mY) {
      return 1;
    } else if (mY < p.mY) {
      return -1;
    } else if (mX > p.mX) {
      return 1;
    } else if (mX < p.mX) {
      return -1;
    } else {
      return 0;
    }
  }

  public static void main(String[] args) {
    Point3D p1 = new Point3D(2.0, 5.4, -3.0);
    Point3D p2 = new Point3D(4.5, 0.3, -3.0);
    Point3D p3 = new Point3D(2.0, 5.0, 19.3);
    Point3D p4 = new Point3D(2.0, 5.0, -3.0);

    System.out.println(p1.compareTo(p2));
    System.out.println(p2.compareTo(p1));
    System.out.println(p1.compareTo(p3));
    System.out.println(p1.compareTo(p4));
  }
}
