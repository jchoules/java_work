package uk.ac.cam.jc841.sv1;

public class TryFinally {
  public static int x() {
    try {
      int y = 7;
      return y;
    }
    finally {
      y = 3;
    }
  }

  public static void main(String[] args) {
    System.out.println(x());
  }
}
