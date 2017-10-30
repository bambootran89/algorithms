package string;

public class SearchingAllPatterns extends SuffixTree {

	public SearchingAllPatterns(String str) {
		super(str);
		// TODO Auto-generated constructor stub
	}
	
	private int traverseEdge(String str, int idx, int start, int end){
		
		for (int k = start; k<=end && idx < str.length(); k++, idx++){
			if (str.charAt(idx)!= text.charAt(k)){
				return -1; // not match
			}
		}
		
		if (idx==str.length())
			return 1; // match
		
		return 0; // more characters to match
	}
	
	private int doTraversalToCountLeaf(Node n){
		
		if(n == null)
			return 0;
		if(n.suffixIndex >-1){
			System.out.println("Found at position " + n.suffixIndex );
			
			return 1;
		}
		int count = 0;
		for (Character key: n.children.keySet()){
			count+=doTraversalToCountLeaf(n.children.get(key));
		}	
		return count;
	}
	
	private int countLeaf(Node n){
		
		if (n==null)
			return 0;
		else
			return doTraversalToCountLeaf(n);
	}
	
	private int doTraverse(Node n, String str, int idx){
		if (n == null)
			return -1; // no match
		
		int res = -1;
		if (n.start !=-1){
			
			res = traverseEdge(str, idx, n.start, n.end.value);
			if(res == -1){
				return -1; // no match
			}
			if (res == 1) { // match
				if (n.suffixIndex >-1){
					System.out.println("Found at position " + n.suffixIndex);
					
				}else {
					System.out.println("substring count: " + countLeaf(n));
					
				}
				return 1; // match
			}
		}
		
		idx = idx + n.edgeLength();
	
		if (n.children.containsKey(str.charAt(idx)))
			return doTraverse(n.children.get(str.charAt(idx)), str, idx);
		
		else 
			return -1; // no match
	}
	
	public void checkForSubString(String str){
		int res = doTraverse(root, str, 0);
		if (res == 1){
    		System.out.println("pattern " + str + " is substring of " + text);
    	}else{
    		System.out.println("pattern " + str + " is NOT substring of " + text);
    	}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SearchingAllPatterns suffixtree = new SearchingAllPatterns("GEEKSFORGEEKS$");
		suffixtree.buildSuffixTree();
		suffixtree.checkForSubString("GEEKS");
	}

}
