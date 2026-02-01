package tests;

/**
 * Test suite for tree data structures.
 *
 * @author Algorithms Collection
 */

import java.util.ArrayList;
import java.util.Random;
import trees.*;

public class TestTree {
  public static void main(String[] args) {
    java.util.List<Integer> list = new ArrayList<Integer>();
    Random rand = new Random();
    Tree bst = new BST();
    Tree avl = new AVL();

    for (int i = 0; i < 200; i++) {
      Integer num = rand.nextInt(1000);
      list.add(num);
      bst.insert(num);
      avl.insert(num);
    }
    System.out.println(list);
    for (int i : list) {
      System.out.println("is " + i + " in BST? " + bst.search(i));
      System.out.println("is " + i + " in AVL? " + avl.search(i));
    }
    int del1 = list.get(100);
    int del2 = list.get(101);
    System.out.println("will delete " + del1 + " and " + del2);
    bst.delete(del1);
    bst.delete(del2);
    avl.delete(del1);
    avl.delete(del2);
    System.out.println("is " + del1 + " in BST? " + bst.search(del1));
    System.out.println("is " + del2 + " in BST? " + bst.search(del2));
    System.out.println("is " + del1 + " in AVL? " + avl.search(del1));
    System.out.println("is " + del2 + " in AVL? " + avl.search(del2));
    System.out.println("BST:heigh is " + bst.findheigh());
    System.out.println("AVL:heigh is " + avl.findheigh());
    System.out.println("AVL:heigh is " + avl.findheigh());

    testSegmentTree();

  }

  static void testSegmentTree() {
    System.out.print("Testing Segment Tree...");
    int[] arr = { 1, 3, 5, 7, 9, 11 };
    int n = arr.length;
    SegmentTree st = new SegmentTree();
    st.constructST(arr, n);

    // Verify sum of range [1, 3] = 3+5+7 = 15
    int sum = st.getSum(n, 1, 3);
    if (sum == 15) {
      System.out.println(" PASSED");
    } else {
      System.out.println(" X FAILED (Expected 15, got " + sum + ")");
    }
  }
}
