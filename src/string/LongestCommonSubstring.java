package string;

import string.SuffixTree.Node;

public class LongestCommonSubstring extends GeneralizedSuffixTree{

	public LongestCommonSubstring(String s1, String s2) {
		
		super(s1+"#" + s2 + "$");
		// TODO Auto-generated constructor stub
		size1 = s1.length()+1;
	}
	String str1;
	String str2;
	
	int maxHeight;
    int substringStartIndex;
    int size1;
    
    public void getLongest2Substrings()
    {
    	maxHeight = 0;
    	substringStartIndex = 0;
    	doTraversal(root, 0);
    	if (maxHeight == 0)
    		System.out.println("No common substring");
    	else 
    		System.out.println("Longest Repeated Substring in " + text +  " is: " + text.substring(substringStartIndex,substringStartIndex+ maxHeight));
		
    }
	private int doTraversal(Node n, int labelHeight) {
		
		int res;
		if(n.suffixIndex == -1) { // if it is internal node
			for (Character key: n.children.keySet()){
				res = doTraversal(n.children.get(key), labelHeight+n.children.get(key).edgeLength());
				
				if (n.suffixIndex== -1)
					n.suffixIndex = res;
				else if( (n.suffixIndex == -2 && res == -3) ||
						(n.suffixIndex == -3 && res == -2) || (n.suffixIndex ==  -4) ) {
					n.suffixIndex = -4; // mark node as XY
					if(maxHeight < labelHeight){
						maxHeight = labelHeight;
						substringStartIndex = n.end.value - labelHeight+1;
					}
				}
				
			}
		}else if (n.suffixIndex>-1 && n.suffixIndex<size1)
			return -2; // mark node as X
		else if(n.suffixIndex >=size1)
			return -3; // mark node as Y
		
		return n.suffixIndex;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestCommonSubstring suffixtree = new LongestCommonSubstring("CuongTran", "Cuong");
		suffixtree.buildSuffixTree();
		suffixtree.getLongest2Substrings();
	}

}
