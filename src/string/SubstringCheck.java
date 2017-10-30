package string;

public class SubstringCheck extends SuffixTree{
	
	
	public SubstringCheck(String str) {
		super(str);
		// TODO Auto-generated constructor stub
	}
    public int travarseEdge(String str, int idx, int start, int end){
    
    	for (int k = start; k<=end && idx<str.length(); k++, idx++){
    		if(text.charAt(k)!=str.charAt(idx))
    			return -1; // no match
    	}
    	
    	if (idx == str.length())
    		return 1;  // match
    	
		return 0; // more characters to match
    	
    }
    
    public int doTraversal(Node n, String str, int idx){
    	if (n==null)
    		return -1;
    	int res = -1;
    	if(n.start !=-1) {
    		res = travarseEdge(str, idx, n.start, n.end.value);
    		
    		if (res !=0)
    			return res;
    			
    	}
    	
    	idx = idx+n.edgeLength();
    	if(n.children.containsKey(str.charAt(idx)))
    		return doTraversal(n.children.get(str.charAt(idx)), str, idx);
    	else{
    		return -1;
    	}
    	
    	
     }
    
    public void checkForSubString(String str){
    	int res = doTraversal(root, str, 0);
    	
    	if (res == 1){
    		System.out.println("pattern " + str + " is substring of " + text);
    	}else{
    		System.out.println("pattern " + str + " is NOT substring of " + text);
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubstringCheck suffixtree = new SubstringCheck("THIS IS A TEST TEXT$");
		suffixtree.buildSuffixTree();
		
		suffixtree.checkForSubString("IS A");
	}

}
