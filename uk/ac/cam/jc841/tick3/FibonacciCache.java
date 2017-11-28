package uk.ac.cam.jc841.tick3;
import java.util.Arrays;

public class FibonacciCache {
  public static long[] fib = new long[20];

  public static void store() {
    long previous = 0L;
    long current = 1L;
    long next;

    for (int i = 0; i < fib.length; i++) {
      fib[i] = current;
      next = previous + current;
      previous = current;
      current = next;
    }
  }

  public static void reset() {
    for (int i = 0; i < fib.length; i++) {
      fib[i] = 0;
    }
  }

  public static long get(int i) {
    if (i < 0 || i >= fib.length) {
      return -1L;
    } else {
      return fib[i];
    }
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(fib));
    store();
    System.out.println(Arrays.toString(fib));
    System.out.println(get(0));
    System.out.println(get(12));
    System.out.println(get(300));
    System.out.println(get(-1));
    reset();
    System.out.println(Arrays.toString(fib));
    System.out.println(get(0));
    System.out.println(get(12));
    System.out.println(get(300));
    System.out.println(get(-1));
  }
}
