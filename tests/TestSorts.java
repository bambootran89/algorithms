package tests;

import java.util.ArrayList;
import sorts.*;

/**
 * Test suite for Sorting algorithms.
 *
 * @author Algorithms Collection
 */
public class TestSorts {

  public static void main(String[] args) {
    System.out.println("\n=== Testing Sorting Algorithms ===\n");

    testMergeSort();
    testQuickSort();

    System.out.println("\nPASSED Sorting Algorithm Tests Complete\n");
  }

  /**
   * Tests the Merge Sort algorithm.
   */
  static void testMergeSort() {
    System.out.print("Testing Merge Sort...");
    ArrayList<Integer> arr = new ArrayList<>();
    arr.add(10);
    arr.add(7);
    arr.add(8);
    arr.add(9);
    arr.add(1);
    arr.add(5);

    MergeSort ms = new MergeSort();
    ArrayList<Integer> sorted = ms.mergesort(arr);

    assertSorted(sorted);
    System.out.println(" PASSED");
  }

  /**
   * Tests the Quick Sort algorithm.
   */
  static void testQuickSort() {
    System.out.print("Testing Quick Sort...");
    ArrayList<Integer> arr = new ArrayList<>();
    arr.add(10);
    arr.add(7);
    arr.add(8);
    arr.add(9);
    arr.add(1);
    arr.add(5);

    QuickSort qs = new QuickSort(arr);
    qs.quicksort(0, arr.size() - 1);

    assertSorted(arr);
    System.out.println(" PASSED");
  }

  private static void assertSorted(ArrayList<Integer> arr) {
    for (int i = 0; i < arr.size() - 1; i++) {
      if (arr.get(i) > arr.get(i + 1)) {
        throw new RuntimeException("Array not sorted: " + arr);
      }
    }
  }
}
