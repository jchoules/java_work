package uk.ac.cam.jc841.alg1;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MaxHeap {
  private char heapName;
  private List<Character> heapContents;

  MaxHeap(char name) {
    heapName = name;
    heapContents = new ArrayList<Character>();
  }

  MaxHeap(char name, String str) {
    heapName = name;
    heapContents = new ArrayList<Character>();
    for (char c : str.toCharArray()) {
      heapContents.add(c);
    }
    heapify();
  }

  public void insert(char x) {
    heapContents.add(x);
    fixHeap();
  }

  public char getMax() {
    if (heapContents.isEmpty()) {
      return '_';
    } else {
      char max = heapContents.get(0);
      heapContents.set(0, heapContents.get(heapContents.size() - 1));
      heapContents.remove(heapContents.size() - 1);
      heapify(0);
      return max;
    }
  }

  private void heapify() {
    for (int k = heapContents.size() / 2 - 1; k >= 0; k--) {
      heapify(k);
    }
  }

  private void heapify(int iRoot) {
    if (isMaxHeap(iRoot)) {
      return;
    } else {
      int j = maxChildIndex(iRoot);

      Character temp = heapContents.get(iRoot);
      heapContents.set(iRoot, heapContents.get(j));
      heapContents.set(j, temp);

      heapify(j);
    }
  }

  private boolean isMaxHeap(int iRoot) {
    int iLeft = iRoot * 2 + 1;
    int iRight = iRoot * 2 + 2;

    return iLeft >= heapContents.size()
        || iRight >= heapContents.size() && heapContents.get(iRoot) >= heapContents.get(iLeft)
        || heapContents.get(iRoot) >= heapContents.get(iLeft) && heapContents.get(iRoot) >= heapContents.get(iRight);
  }

  private int maxChildIndex(int iRoot) {
    int iLeft = iRoot * 2 + 1;
    int iRight = iRoot * 2 + 2;

    if (iRight >= heapContents.size()) {
      return iLeft;
    } else {
      if (heapContents.get(iLeft) > heapContents.get(iRight)) {
        return iLeft;
      } else {
        return iRight;
      }
    }
  }

  private void fixHeap() {
    upHeap(heapContents.size() - 1);
  }

  private void upHeap(int i) {
    if (i == 0) {
      return;
    }

    int iParent = i / 2 + i % 2 - 1;
    if (heapContents.get(iParent) < heapContents.get(i)) {
      Character temp = heapContents.get(i);
      heapContents.set(i, heapContents.get(iParent));
      heapContents.set(iParent, temp);

      upHeap(iParent);
    }
  }

  public static void main(String[] args) {
    char c;
    MaxHeap h = new MaxHeap('h', "CAMBRIDGEALGORITHMS");
    c = h.getMax();
    System.out.println(c);
    h.insert('Z');
    h.insert('A');
    c = h.getMax();
    System.out.println(c);
    c = h.getMax();
    System.out.println(c);
    c = h.getMax();
    System.out.println(c);
    c = h.getMax();
    System.out.println(c);
    c = h.getMax();
    System.out.println(c);
  }
}
