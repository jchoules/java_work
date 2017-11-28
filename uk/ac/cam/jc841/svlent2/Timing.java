package uk.ac.cam.jc841.svlent2;
import java.util.Arrays;

public class Timing {
  public static void main(String[] args) {
    int[] a1 = {774, 884, 407, 850, 485, 13, 563, 510, 216, 550, 46, 850, 58, 700, 446, 265, 648, 446, 943, 786, 148, 929, 773, 339, 424, 219, 390, 120, 282, 992, 871, 620, 379, 657, 781, 100, 498, 572, 184, 310, 181, 869, 843, 466, 177, 123, 771, 754, 963, 223, 136, 403, 942, 474, 114, 221, 13, 418, 97, 334, 21, 813, 240, 465, 1000, 643, 193, 411, 825, 705, 776, 891, 577, 229, 100, 740, 764, 624, 676, 988, 676, 441, 630, 108, 881, 234, 213, 376, 996, 623, 827, 258, 996, 417, 510, 922, 357, 579, 677, 591};
    int[] a2 = a1.clone();
    int[] a3 = a1.clone();

    CompetitionSort cs = new CompetitionSort();

    long hsStart = System.nanoTime();
    QuickSort.quickSort(a2);
    long hsEnd = System.nanoTime();
    System.out.println("Heapsort: " + (hsEnd - hsStart));

    long csStart = System.nanoTime();
    cs.Sort(a1);
    long csEnd = System.nanoTime();
    System.out.println("Introsort: " + (csEnd - csStart));

    long qsStart = System.nanoTime();
    HeapSort.heapSort(a3);
    long qsEnd = System.nanoTime();
    System.out.println("Quicksort: " + (qsEnd - qsStart));

    System.out.println(Arrays.toString(a1));
  }
}
