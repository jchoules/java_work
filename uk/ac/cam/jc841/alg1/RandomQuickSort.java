package uk.ac.cam.jc841.alg1;
import java.util.Random;
import java.util.Arrays;

public class RandomQuickSort {
  public static void randomQuickSort(int[] a) {
    Random rand = new Random();
    randomQuickSortAux(a, 0, a.length, rand);
  }

  private static void randomQuickSortAux(int[] a, int iBegin, int iEnd, Random rand) {
    if (iEnd - iBegin == 0) {
      return;
    }

    int iPivot = rand.nextInt(iEnd - iBegin) + iBegin;
    int temp1 = a[iBegin];
    a[iBegin] = a[iPivot];
    a[iPivot] = temp1;

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

    int temp2 = a[iBegin];
    a[iBegin] = a[iLeft - 1];
    a[iLeft - 1] = temp2;

    randomQuickSortAux(a, iBegin, iLeft - 1, rand);
    randomQuickSortAux(a, iRight, iEnd, rand);
  }

  public static void main(String[] args) {
    int[] t1 = {6};
    randomQuickSort(t1);
    System.out.println(Arrays.toString(t1));

    int[] t2 = {1,1,1,1,1};
    randomQuickSort(t2);
    System.out.println(Arrays.toString(t2));

    int[] t3 = {5,4,4,3,2,1,2};
    randomQuickSort(t3);
    System.out.println(Arrays.toString(t3));

    int[] t4 = {3,1,2,1,1,4,5,4,4};
    randomQuickSort(t4);
    System.out.println(Arrays.toString(t4));
  }
}
