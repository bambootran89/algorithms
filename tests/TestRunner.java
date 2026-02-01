package tests;

/**
 * Master Test Runner for all algorithm tests.
 *
 * Orchestrates execution of all 10 test categories and provides unified
 * reporting.
 *
 * @author Algorithms Collection
 */
public class TestRunner {

  private static int totalPassed = 0;
  private static int totalFailed = 0;
  private static int categoryCount = 0;

  /**
   * Main entry point - runs all test suites.
   *
   * @param args command line arguments (unused)
   */
  public static void main(String[] args) {
    System.out.println("\n" + repeatString("=", 50));
    System.out.println("Java Algorithms - Comprehensive Test Suite");
    System.out.println(repeatString("=", 50) + "\n");

    // Run all test categories
    runTestCategory("TestTree", "Trees (BST & AVL)");
    runTestCategory("TestHeap", "Heaps (MaxHeap & MinHeap)");
    runTestCategory("TestHash", "Hash Tables");
    runTestCategory("TestList", "Linked Lists");

    runTestCategory("TestDP", "Dynamic Programming");
    runTestCategory("TestGraph", "Graph Algorithms");
    runTestCategory("TestString", "String Algorithms");
    runTestCategory("TestSorts", "Sorting Algorithms");
    runTestCategory("TestOptimization", "Optimization Algorithms");

    // Print summary
    printSummary();
  }

  /**
   * Helper method to repeat a string.
   */
  private static String repeatString(String s, int count) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) {
      sb.append(s);
    }
    return sb.toString();
  }

  /**
   * Runs a single test category.
   *
   * @param testClass    the test class name
   * @param categoryName the human-readable category name
   */
  private static void runTestCategory(String testClass, String categoryName) {
    categoryCount++;
    System.out.printf("Running: %s%n", categoryName);

    try {
      // Create an instance of the test class
      Class<?> clazz = Class.forName("tests." + testClass);
      Object testInstance = clazz.getDeclaredConstructor().newInstance();

      // Invoke main method (static)
      String[] args = {};
      clazz.getMethod("main", String[].class).invoke(null, (Object) args);

      System.out.println("PASSED\n");
      totalPassed++;
    } catch (Exception e) {
      System.out.println("✗ FAILED: " + e.getMessage() + "\n");
      totalFailed++;
    }
  }

  /**
   * Prints the test execution summary.
   */
  private static void printSummary() {
    System.out.println(repeatString("=", 50));
    System.out.println("Test Summary");
    System.out.println(repeatString("=", 50));
    System.out.printf("Passed: %d%n", totalPassed);
    System.out.printf("Failed: %d%n", totalFailed);
    System.out.printf("Total:  %d%n", categoryCount);
    System.out.println();

    if (totalFailed == 0) {
      System.out.println("PASSED All tests passed!");
    } else {
      System.out.println("✗ Some tests failed");
    }
    System.out.println();
  }
}
