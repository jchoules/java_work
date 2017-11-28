package uk.ac.cam.jc841.sv3;
import java.util.*;

public class MarkBook {
  private Map<String, Integer> mMarks;
  private static Integer maximumMark = 100;

  public MarkBook(Map<String, Integer> marks) {
    mMarks = marks;
  }

  public void setMark(String name, Integer mark) {
    mMarks.put(name, mark);
  }

  public Integer getMark(String name) {
    return mMarks.get(name);
  }

  public List<String> allNames() {
    // Map.keySet() returns a Set, which is converted to a List via an array.
    List<String> names
      = Arrays.asList(mMarks.keySet().toArray(new String[0]));
    Collections.sort(names);
    return names;
  }

  private Integer minimumMark(int percent) {
    Integer minimumRank = mMarks.size() * percent / 100;
    // If the desired percentage is too small to contain anyone...
    if (minimumRank <= 0) {
      // ...set the minimum mark higher than the maximum.
      return maximumMark + 1;
    } else {
      // Same trick as was used in allNames(), since Map.values() returns a
      // Collection.
      List<Integer> marks
        = Arrays.asList(mMarks.values().toArray(new Integer[0]));
      Collections.sort(marks);
      // Index from the end since list is sorted in ascending order.
      // Note that allowing minimumRank <= 0 at this point would cause trouble.
      return marks.get(marks.size() - minimumRank);
    }
  }

  public List<String> topPercent(int percent) {
    // Clamp percentage between 0 and 100.
    percent = Math.max(percent, 0);
    percent = Math.min(percent, 100);
    Integer minimumMark = minimumMark(percent);
    List<String> names = new ArrayList<String>();
    for (Map.Entry<String, Integer> mark : mMarks.entrySet()) {
      if (mark.getValue() >= minimumMark) {
        names.add(mark.getKey());
      }
    }
    return names;
  }

  // Perhaps this method should be constrained to returning Integers, to be
  // consistent with the precision to which marks are actually stored.
  // Returning a Double is more mathematically correct, though.
  public Double medianMark() {
    List<Integer> marks
      = Arrays.asList(mMarks.values().toArray(new Integer[0]));
    Collections.sort(marks);
    if (marks.size() % 2 == 0) {
      return (marks.get(marks.size() / 2 - 1)
               + marks.get(marks.size() / 2)) / 2.0;
    } else {
      return marks.get(marks.size() / 2).doubleValue();
    }
  }

  public static void main(String[] args) {
    MarkBook markBook = new MarkBook(new HashMap<String, Integer>());
    // Conveniently, all the students have unique first names...
    // A more rigourous inplementation would have a Person or Name class with a
    // custom natural sort order (probably surname/forename).
    markBook.setMark("Alice", 76);
    markBook.setMark("Bob", 33);
    markBook.setMark("Aaron", 97);
    markBook.setMark("Zachary", 65);
    markBook.setMark("Mark", 42);

    System.out.println("Alphabetised names:");
    for (String name : markBook.allNames()) {
      System.out.println(name);
      System.out.println(markBook.getMark(name));
    }

    System.out.println("Top 65%:");
    for (String name : markBook.topPercent(65)) {
      System.out.println(name);
      System.out.println(markBook.getMark(name));
    }

    System.out.println("Median mark:");
    System.out.println(markBook.medianMark());
  }
}
