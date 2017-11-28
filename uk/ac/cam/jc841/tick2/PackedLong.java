package uk.ac.cam.jc841.tick2;

public class PackedLong {

  public static boolean get(long packed, int position) {
    long check = packed >> position & 1;

    return (check == 1);
  }

  public static long set(long packed, int position, boolean value) {
    if (value) {
      long mask = 1L << position;
      packed |= mask;
    }
    else {
      long mask = ~(1L << position);
      packed &= mask;
    }
    return packed;
  }
}
