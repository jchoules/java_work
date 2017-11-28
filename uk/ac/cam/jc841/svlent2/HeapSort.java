package uk.ac.cam.jc841.svlent2;
import java.util.Arrays;

public class HeapSort {
  public static void heapSort(int[] a) {
    // Only elements from 0 included to floor(END/2) excluded represent nodes
    // with children.

    // Start from the lowest subtrees and work up so that when a given
    // subtree comes to be heapified, its children will already satisfy the
    // max-heap property.
    for (int k = a.length / 2 - 1; k >= 0; k--) {
      heapify(a, a.length, k);
    }

    // Since we have a max-heap, each succesive element to be extracted will
    // need to go in the next position from the END. We need not go all the
    // way down to 1, since at that stage the heap portion of the list
    // contains only one element, the smallest in the array, which will be in
    // its correct position anyway.
    for (int k = a.length; k > 1; k--) {
      // Owing to the way the heap is constructed, its largest element (the
      // root) can be found at the front of the array and swapped in to the
      // appropriate position.
      int temp = a[0];
      a[0] = a[k - 1];
      a[k - 1] = temp;

      // Removal of the root may break the max-heap property, so fix up the
      // heap.
      heapify(a, k - 1, 0);
    }
  }

  private static void heapify(int[] a, int iEnd, int iRoot) {
    // It is assumed that both of the children of the node represented by
    // a[iRoot] already satisfy the max-heap property.

    // If the node represented by a[iRoot] itself satisfies the max-heap
    // property...
    if (isMaxHeap(a, iRoot, iEnd)) {
      // ...then there is nothing to do.
      return;
    } else {  // Otherwise...
      // ...get the index, j, of the largest existing child of the node
      // represented by a[iRoot]
      int j = maxChildIndex(a, iRoot, iEnd);

      // exchange a[iRoot] with a[j]: now the node represented by the new
      // a[iRoot] satisfies the max-heap property
      int temp = a[iRoot];
      a[iRoot] = a[j];
      a[j] = temp;

      // the node represented by the new a[j] might no longer satisfy the
      // max-heap property, so fix it up.
      heapify(a, iEnd, j);
    }
  }

  private static boolean isMaxHeap(int[] a, int iRoot, int iEnd) {
    int iLeft = iRoot * 2 + 1;
    int iRight = iRoot * 2 + 2;

    return iLeft >= iEnd // no children
        || iRight >= iEnd && a[iRoot] >= a[iLeft] // one child
        || a[iRoot] >= a[iLeft] && a[iRoot] >= a[iRight]; // both children
  }

  private static int maxChildIndex(int[] a, int iRoot, int iEnd) {
    int iLeft = iRoot * 2 + 1;
    int iRight = iRoot * 2 + 2;

    // This function is guaranteed never to be called with an iRoot such that
    // the node represented by a[iRoot] has no children.

    // If the node represented by a[iRoot] has no right child...
    if (iRight >= iEnd) {
      // ...then the left (and only) child is the maximum.
      return iLeft;
    } else { // Otherwise...
      // ... determine which of the two children is greater. The right child
      // is favoured in the event of a tie, since, in an almost full binary
      // tree, the right subtrees will be slightly smaller on average than
      // the left subtrees, and thus a little cheaper to heapify.
      if (a[iLeft] > a[iRight]) {
        return iLeft;
      } else {
        return iRight;
      }
    }
  }

  public static void main(String[] args) {
    int[] t1 = {6};
    heapSort(t1);
    System.out.println(Arrays.toString(t1));

    int[] t2 = {1,1,1,1,1};
    heapSort(t2);
    System.out.println(Arrays.toString(t2));

    int[] t3 = {5,4,4,3,2,1,2};
    heapSort(t3);
    System.out.println(Arrays.toString(t3));

    int[] t4 = {3,1,2,1,1,4,5,4,4};
    heapSort(t4);
    System.out.println(Arrays.toString(t4));

    int[] t5 = {7,8,9,10,11,12,13};
    heapSort(t5);
    System.out.println(Arrays.toString(t5));
  }
}
