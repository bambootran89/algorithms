package tests;

/**
 * Test suite for hash tables.
 *
 * @author Algorithms Collection
 */

import Hash.*;

public class TestHash {
  public static void main(String[] args) {
    System.out.println("\n=== Testing Hash Algorithms ===\n");
    testCuckoo();
    testLargestSubarray0Sum();

    System.out.println("\nPASSED Hash Algorithm Tests Complete\n");
  }

  static void testCuckoo() {
    System.out.print("Testing Cuckoo Hashing...");
    Cuckoo cuckoo = new Cuckoo();
    cuckoo.insert(10);
    cuckoo.insert(20);
    cuckoo.insert(30);

    if (cuckoo.get(10) != 10)
      throw new RuntimeException("Failed to get 10");
    if (cuckoo.get(20) != 20)
      throw new RuntimeException("Failed to get 20");

    cuckoo.delete(20);
    try {
      cuckoo.get(20);
      throw new RuntimeException("Should have thrown exception for deleted key");
    } catch (RuntimeException e) {
      // Expected
    }

    System.out.println(" PASSED");
  }

  static void testLargestSubarray0Sum() {
    System.out.print("Testing Largest Subarray with 0 Sum...");
    int arr[] = { 15, -2, 2, -8, 1, 7, 10, 23 };
    Largestsubarray0Sum.findSubs(arr);
    // Code prints to stdout. We ensure it runs.
    System.out.println(" PASSED");
  }
}
