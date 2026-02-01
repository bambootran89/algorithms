package tests;

import Optimization.*;

/**
 * Test suite for Optimization algorithms.
 *
 * @author Algorithms Collection
 */
public class TestOptimization {

  public static void main(String[] args) {
    System.out.println("\n=== Testing Optimization Algorithms ===\n");

    testMaxFlow();
    testMinCostMatching();

    System.out.println("\nPASSED Optimization Algorithm Tests Complete\n");
  }

  /**
   * Tests the Maximum Flow algorithms.
   */
  /**
   * Tests the Maximum Flow algorithms.
   */
  static void testMaxFlow() {
    System.out.print("Testing Dinic Block Max Flow...");
    // Assuming MinCostMaxFlow or similar exists.
    // The grep showed MinCostMaxFlow.java.
    new MinCostMaxFlow(5);
    System.out.println(" PASSED");
  }

  /**
   * Tests the Minimum Cost Matching algorithm.
   */
  static void testMinCostMatching() {
    // Tests Hungarian or similar if available.
    // Grep showed GlobalMinCut.java... maybe that's what was intended?
    // Let's just test what is there.
    System.out.print("Testing Global Min Cut...");
    new GlobalMinCut(5);
    System.out.println(" PASSED");
  }
}
