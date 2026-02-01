package string;
/**
 * Test suite for suffix trees.
 *
 * @author Algorithms Collection
 */


public class TestSuffixTree {

  public static void main(String[] args) {
    String str = "abcabxabcd$";
    SuffixTree suffixTree = new SuffixTree(str);
    suffixTree.buildSuffixTree();
    
    LongestPalindromicSubstring lps = new LongestPalindromicSubstring("cabbaabb");
    lps.buildSuffixTree();
    lps.getLongestPalindromicSubstring();
    
  }

}
