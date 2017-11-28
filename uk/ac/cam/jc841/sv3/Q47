package uk.ac.cam.jc841.sv3;
import java.util.*;
import java.io.*;

public class PairSorter {
  private static class Pair implements Comparable<Pair> {
    private int first;
    private int second;

    public Pair(int f, int s) {
      first = f;
      second = s;
    }

    public int getFirst() {
      return first;
    }
    public int getSecond() {
      return second;
    }

    public int compareTo(Pair p) {
      int firstComparison = first - p.first;
      if (firstComparison == 0) {
        return second - p.second;
      } else {
        return firstComparison;
      }
    }
  }

  public static void main(String[] args) throws FileNotFoundException, IOException {
    BufferedReader input = new BufferedReader(new FileReader("numbers.csv"));
    List<Pair> pairs = new ArrayList<Pair>();
    String line;
    while ((line = input.readLine()) != null) {
      String[] numbers = line.split(",");
      pairs.add(new Pair(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));
    }
    input.close();

    Collections.sort(pairs);
    for (Pair p : pairs) {
      System.out.println(p.getFirst() + "," + p.getSecond());
    }
  }
}
