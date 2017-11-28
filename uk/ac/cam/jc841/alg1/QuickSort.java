package uk.ac.cam.jc841.alg1;
import java.util.Arrays;

public class QuickSort {
  public static void quickSort(int[] a) {
    quickSortAux(a, 0, a.length);
  }

  private static void quickSortAux(int[] a, int iBegin, int iEnd) {
    if (iEnd - iBegin == 0) {
      return;
    }

    int pivot = a[iBegin];
    int iLeft = iBegin + 1;
    int iRight = iEnd;

    while (iLeft < iRight) {
      while (iLeft < iEnd && a[iLeft] <= pivot) {
        iLeft++;
      }

      while (a[iRight - 1] > pivot) {
        iRight--;
      }

      if (iLeft < iRight) {
        int temp = a[iLeft];
        a[iLeft] = a[iRight - 1];
        a[iRight - 1] = temp;

        iLeft++;
        iRight--;
      }
    }

    int temp = a[iBegin];
    a[iBegin] = a[iLeft - 1];
    a[iLeft - 1] = temp;

    quickSortAux(a, iBegin, iLeft - 1);
    quickSortAux(a, iRight, iEnd);
  }

  public static void main(String[] args) {
    int[] t1 = {6};
    quickSort(t1);
    System.out.println(Arrays.toString(t1));

    int[] t2 = {1,1,1,1,1};
    quickSort(t2);
    System.out.println(Arrays.toString(t2));

    int[] t3 = {5,4,4,3,2,1,2};
    quickSort(t3);
    System.out.println(Arrays.toString(t3));

    int[] t4 = {3,1,2,1,1,4,5,4,4};
    quickSort(t4);
    System.out.println(Arrays.toString(t4));
  }
}
