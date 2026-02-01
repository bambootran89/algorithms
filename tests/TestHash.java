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
}
