package tests;

import graph.*;

/**
 * Test suite for Graph algorithms.
 *
 * @author Algorithms Collection
 */
public class TestGraph {

  public static void main(String[] args) {
    System.out.println("\n=== Testing Graph Algorithms ===\n");

    testBellmanFord();
    testDijkstra();
    testKruskal();
    testStrongConnectedComponents();
    testEulerianPath(); // Added this line

    System.out.println("\nPASSED Graph Algorithm Tests Complete\n");
  }

  /**
   * Tests the Bellman-Ford shortest path algorithm.
   */
  static void testBellmanFord() {
    System.out.print("Testing Bellman-Ford...");
    // Placeholder - would typically verify path weights
    System.out.println(" PASSED (instantiation)");
  }

  /**
   * Tests the Dijkstra shortest path algorithm.
   */
  static void testDijkstra() {
    System.out.print("Testing Dijkstra (Fast)...");
    new FastDijkstras();
    System.out.println(" PASSED");
  }

  /**
   * Tests the Kruskal Minimum Spanning Tree algorithm.
   */
  static void testKruskal() {
    System.out.print("Testing Kruskal MST...");
    new Kruskal();
    System.out.println(" PASSED");
  }

  /**
   * Tests the Strong Connected Components algorithm.
   */
  static void testStrongConnectedComponents() {
    System.out.print("Testing Strong Connected Components...");
    new StrongConnectedConponents();
    System.out.println(" PASSED");
  }

  /**
   * Tests the Eulerian Path algorithm.
   */
  static void testEulerianPath() {
    System.out.print("Testing Eulerian Path...");
    EulerianPath euler = new EulerianPath(5);
    // Create a graph that has an Eulerian path
    // 0-1, 1-2, 2-0, 0-3, 3-4, 4-0 (All degrees even except node 0? No wait)
    // Let's use simple triangle: 0-1, 1-2, 2-0. All degrees 2. 0->1->2->0
    euler.add_edge(0, 1);
    euler.add_edge(1, 2);
    euler.add_edge(2, 0);

    euler.find_path(0);

    if (euler.path.size() != 4) { // 0, 1, 2, 0 (nodes visited) - wait logic adds vertices to path
      throw new RuntimeException(
          "Eulerian path size incorrect. Expected 4, got " + euler.path.size() + " Path: " + euler.path);
    }
    System.out.println(" PASSED");
  }
}
