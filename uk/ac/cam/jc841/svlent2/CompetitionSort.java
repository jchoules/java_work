package uk.ac.cam.jc841.svlent2;
import java.util.Arrays;

// An implementation of introsort, short for 'introspective sort'.
// Introsort was originally devised by Dave Musser in 1997 for use in the
// C++ Standard Library.

// Starts by using quicksort, then switches to heapsort below a certain
// depth of recursion, calculated as 2*floor(log_2(len(a))).

// Heapsort and quicksort implementations are both shamelessly cribbed from
// my answers to earlier exercises, with minor modifications.

// Crude benchmarking indicates roughly a 10X performance improvement over
// pure quicksort or pure heapsort (or at least, my implementations
// thereof).
public class CompetitionSort implements Sorter {
  public void Sort(int[] array) {
    introSort(array);
  }

  private void introSort(int[] array) {
    if (array.length == 0) {
      return;
    }

    int limit = 2 * (int)(Math.log(array.length) / Math.log(2));
    introSortAux(array, 0, array.length, limit);
  }

  private void introSortAux(int[] array, int iBegin, int iEnd, int limit) {
    if (iEnd - iBegin == 0) {
      return;
    }

    if (limit == 0) {
      heapSort(array, iBegin, iEnd);
      return;
    }

    int pivot = array[iBegin];
    int iLeft = iBegin + 1;
    int iRight = iEnd;

    while (iLeft < iRight) {
      while (iLeft < iEnd && array[iLeft] <= pivot) {
        iLeft++;
      }

      while (array[iRight - 1] > pivot) {
        iRight--;
      }

      if (iLeft < iRight) {
        int temp = array[iLeft];
        array[iLeft] = array[iRight - 1];
        array[iRight - 1] = temp;

        iLeft++;
        iRight--;
      }
    }

    int temp = array[iBegin];
    array[iBegin] = array[iLeft - 1];
    array[iLeft - 1] = temp;

    introSortAux(array, iBegin, iLeft - 1, limit - 1);
    introSortAux(array, iRight, iEnd, limit - 1);
  }

  public void heapSort(int[] array, int iBegin, int iEnd) {
    for (int k = iBegin + (iEnd - iBegin) / 2 - 1; k >= iBegin; k--) {
      heapify(array, iBegin, iEnd, k);
    }

    for (int k = iEnd; k > iBegin + 1; k--) {
      int temp = array[iBegin];
      array[iBegin] = array[k - 1];
      array[k - 1] = temp;

      heapify(array, iBegin, k - 1, iBegin);
    }
  }

  private void heapify(int[] array, int iBegin, int iEnd, int iRoot) {
    if (isMaxHeap(array, iRoot, iBegin, iEnd)) {
      return;
    } else {
      int j = maxChildIndex(array, iRoot, iBegin, iEnd);

      int temp = array[iRoot];
      array[iRoot] = array[j];
      array[j] = temp;

      heapify(array, iBegin, iEnd, j);
    }
  }

  private boolean isMaxHeap(int[] array, int iRoot, int iBegin,
    int iEnd) {

    int iLeft = iBegin + (iRoot - iBegin) * 2 + 1;
    int iRight = iBegin + (iRoot - iBegin) * 2 + 2;

    return iLeft >= iEnd
        || iRight >= iEnd && array[iRoot] >= array[iLeft]
        || array[iRoot] >= array[iLeft] && array[iRoot] >= array[iRight];
  }

  private int maxChildIndex(int[] array, int iRoot, int iBegin,
    int iEnd) {

    int iLeft = iBegin + (iRoot - iBegin) * 2 + 1;
    int iRight = iBegin + (iRoot - iBegin) * 2 + 2;

    if (iRight >= iEnd) {
      return iLeft;
    } else {
      if (array[iLeft] > array[iRight]) {
        return iLeft;
      } else {
        return iRight;
      }
    }
  }

  public String GetID() {
    return "jc841";
  }

  public static void main(String[] args) {
    CompetitionSort cs = new CompetitionSort();

    int[] t1 = {6};
    cs.Sort(t1);
    System.out.println(Arrays.toString(t1));

    int[] t2 = {1,1,1,1,1};
    cs.Sort(t2);
    System.out.println(Arrays.toString(t2));

    int[] t3 = {5,4,4,3,2,1,2};
    cs.Sort(t3);
    System.out.println(Arrays.toString(t3));

    int[] t4 = {3,1,2,1,1,4,5,4,4};
    cs.Sort(t4);
    System.out.println(Arrays.toString(t4));

    int[] t5 = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    cs.Sort(t5);
    System.out.println(Arrays.toString(t5));

    int[] t6 = {407, 762, 368, 575, 111, 207, 785, 917, 443, 798, 819, 359, 189, 801, 372, 597, 897, 237, 959, 414, 777, 30, 847, 651, 285, 717, 32, 381, 529, 62, 235, 210, 880, 51, 606, 917, 74, 951, 68, 433};
    cs.Sort(t6);
    System.out.println(Arrays.toString(t6));
  }
}
