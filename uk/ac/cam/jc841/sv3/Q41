package uk.ac.cam.jc841.sv3;
import java.util.*;

public class CollectionArrayList<E> extends AbstractList<E> implements List<E> {
  // Type erasure prevents us from instantiating an array of Es, so we use
  // Object[] instead. We rely on Java's type checking to stop consumers of our
  // class from creating CollectionArrayLists with heterogeneous types.
  private Object[] contents;
  private int length;
  private static int expansionAmount = 10;

  public CollectionArrayList() {
    contents = new Object[0];
    length = 0;
  }

  // We use a wildcard to allow CollectionArrayList<E> to contain instances of
  // subtypes of E, not just instances of E itself.
  public CollectionArrayList(Collection<? extends E> c) {
    contents = c.toArray();
    length = c.size();
  }

  private void expand() {
    Object[] newContents = new Object[contents.length + expansionAmount];
    for (int i = 0; i < length; i++) {
      newContents[i] = contents[i];
    }
    contents = newContents;
  }

  private void checkBounds(int n) throws IndexOutOfBoundsException {
    if (n >= length || n < 0) {
      throw new IndexOutOfBoundsException();
    }
  }

  // If we're adding something to the list, we should allow the index to be one
  //  higher than the list's current highest index (namely length - 1).
  private void checkAddBounds(int n) throws IndexOutOfBoundsException {
    if (n > length || n < 0) {
      throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public E get(int n) throws IndexOutOfBoundsException {
    checkBounds(n);
    return (E)contents[n];
  }

  @Override
  public E set(int n, E x) throws IndexOutOfBoundsException {
    checkBounds(n);
    E oldX = get(n);
    contents[n] = x;
    return oldX;
  }

  @Override
  public void add(int n, E x) throws IndexOutOfBoundsException {
    checkAddBounds(n);
    if (!(length < contents.length)) {
      expand();
    }

    for (int i = length - 1; i >= n; i--) {
      contents[i + 1] = contents[i];
    }

    contents[n] = x;
    length++;
  }

  @Override
  public E remove(int n) throws IndexOutOfBoundsException {
    checkBounds(n);
    E removed = get(n);

    for (int i = n; i < length - 1; i++) {
      contents[i] = contents[i + 1];
    }

    length--;
    return removed;
  }

  @Override
  public int size() {
    return length;
  }

  public static void main(String[] args) {
    CollectionArrayList<String> test = new CollectionArrayList();

    // We can use AbstractList's add() method, which builds off our own.
    test.add("aardvark");
    test.add("beaver");
    test.add("cuckoo");
    test.add("dingo");

    System.out.println(test.get(1));

    System.out.println(test.set(2, "chaffinch"));
    System.out.println(test.get(2));

    System.out.println(test.size());
    System.out.println(test.remove(1));
    System.out.println(test.size());

    test.add(2, "bison");
    System.out.println(test.size());

    System.out.println("List before sorting:");
    for (String s : test) {
      System.out.println(s);
    }

    Collections.sort(test);
    System.out.println("List after sorting:");
    for (String s : test) {
      System.out.println(s);
    }
  }
}
