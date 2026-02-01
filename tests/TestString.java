package tests;

import string.*;

/**
 * Test suite for String algorithms.
 *
 * @author Algorithms Collection
 */
public class TestString {

  public static void main(String[] args) {
    System.out.println("\n=== Testing String Algorithms ===\n");

    testLongestPalindromicSubstring();
    testTrie();

    System.out.println("\nPASSED String Algorithm Tests Complete\n");
  }

  /**
   * Tests the Trie (Prefix Tree) data structure.
   */
  static void testTrie() {
    System.out.print("Testing Trie...");
    Trie trie = new Trie();
    trie.insert("hello");
    trie.insert("world");
    trie.insert("help");

    boolean foundHello = trie.search("hello");
    boolean foundHey = trie.search("hey");

    if (!foundHello)
      throw new RuntimeException("Should find 'hello' in trie");
    if (foundHey)
      throw new RuntimeException("Should not find 'hey' in trie");

    System.out.println(" PASSED");
  }

  /**
   * Tests the Longest Palindromic Substring algorithm.
   */
  static void testLongestPalindromicSubstring() {
    System.out.print("Testing Longest Palindromic Substring...");
    // Current implementation text format: str + "#" + reverse + "$" (Generalized
    // Suffix Tree approach?)
    // Let's test basic palindrome
    String input = "banana";
    LongestPalindromicSubstring lps = new LongestPalindromicSubstring(input);
    lps.buildSuffixTree();
    String result = lps.getLongestPalindromicSubstring();

    // "banana" -> "anana"
    if (!result.equals("anana")) {
      throw new RuntimeException("Expected 'anana', got '" + result + "'");
    }
    System.out.println(" PASSED");
  }

  /**
   * Tests the Longest Common Substring algorithm.
   */
  static void testLongestCommonSubstring() {
    System.out.print("Testing Longest Common Substring...");
    // S1: CuongTran, S2: Cuong
    // LCS: Cuong
    LongestCommonSubstring lcs = new LongestCommonSubstring("CuongTran", "Cuong");
    lcs.buildSuffixTree();
    String common = lcs.getLongest2Substrings();

    if (!common.equals("Cuong")) {
      throw new RuntimeException("Expected 'Cuong', got '" + common + "'");
    }
    System.out.println(" PASSED");
  }

  /**
   * Tests the Substring Check algorithm.
   */
  static void testSubstringCheck() {
    System.out.print("Testing Substring Check...");
    SubstringCheck checker = new SubstringCheck("THIS IS A TEST TEXT$");
    checker.buildSuffixTree();

    boolean found = checker.checkForSubString("IS A");
    boolean notFound = checker.checkForSubString("NOT FOUND");

    if (!found)
      throw new RuntimeException("Should have found 'IS A'");
    if (notFound)
      throw new RuntimeException("Should NOT have found 'NOT FOUND'");

    System.out.println(" PASSED");
  }
}
