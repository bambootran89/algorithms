package string;

public class TestSuffixTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abcabxabcd$";
		SuffixTree suffixTree = new SuffixTree(str);
		suffixTree.buildSuffixTree();
		
		LongestPalindromicSubstring lps = new LongestPalindromicSubstring("cabbaabb");
		lps.buildSuffixTree();
		lps.getLongestPalindromicSubstring();
		
	}

}
