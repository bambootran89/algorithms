package string;

public class GeneralizedSuffixTree extends SuffixTree {

	public GeneralizedSuffixTree(String str) {
		super(str);
		// TODO Auto-generated constructor stub
	}
	private void print(int i, int j){ 
    	int k;
    	for (k = i; k<=j && text.charAt(k) != '#'; k++)
    		System.out.print(text.charAt(k));
    	if (k<=j){
    		System.out.print('#');
    	}
    }
    
    private void setSuffixIndexByDFS(Node n, int labelHeight){
    	if (n== null)
    		return;
    	if (n.start!=-1) {
    		//print(n.start, n.end.value);
    	}
    	int leaf = 1;
    	for (Character key: n.children.keySet()){
    		if (leaf == 1 && n.start!=1){
    			//System.out.println("[" + n.suffixIndex + "]");
    		}
    		
    		leaf = 0;
    		setSuffixIndexByDFS(n.children.get(key), labelHeight+n.children.get(key).edgeLength());
    		
    	}
    	
    	if(leaf == 1){
    		for (int i = n.start; i<=n.end.value; i++)
				if(text.charAt(i) == '#')
					n.end = new EndObject(i);
    		
    		n.suffixIndex = size - labelHeight;
    		//System.out.println("[" + n.suffixIndex + "]");
    	}
    }
    
    private void printSuffixesByDFS(Node n){
    	if (n== null)
    		return;
    	for (Character key: n.children.keySet()){
    		
    		printSuffixesByDFS(n.children.get(key));
    		
    	}
    	
    	if(n.suffixIndex >-1){
    		int i = text.length()-1;
    		if (text.charAt(n.end.value) == '#')
    			i = n.end.value;
    		print(n.suffixIndex, i);
    		System.out.println("[" + n.suffixIndex + "]");
    	}

    }
    public void buildSuffixTree(){
		size = text.length();
		
		rootEnd = new EndObject(-1);
		root = new Node(-1, rootEnd);
		activeNode = root;
		for (int i = 0; i<size; i++){
			extendSuffixTree(i);
		}
		
		int labelHeight = 0;
		setSuffixIndexByDFS(root, labelHeight);
		//System.out.println("-------------------");
		//printSuffixesByDFS(root);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "xabxa#babxba$";
		GeneralizedSuffixTree suffixTree = new GeneralizedSuffixTree(str);
		suffixTree.buildSuffixTree();
	}

}
