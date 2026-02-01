package string;
/**
 * Longest repeated substring.
 *
 * @author Algorithms Collection
 */


public class LongestRepeatedSubstring extends SuffixTree{

  public LongestRepeatedSubstring(String str) {
    super(str);
    // TODO Auto-generated constructor stub
  }
    int maxHeight;
    int substringStartIndex;
    
    void getLongestRepeatedSubstring()
    {
      maxHeight = 0;
      substringStartIndex = 0;
      doTraversal(root, 0);
      System.out.println("Longest Repeated Substring in " + text +  " is: " + text.substring(substringStartIndex,substringStartIndex+ maxHeight));
    
    }
  private void doTraversal(Node n, int labelHeight) {
    
    if(n==null)
      return;  
    if(n.suffixIndex == -1) { // if it is internal node
      for (Character key: n.children.keySet()){
        doTraversal(n.children.get(key), labelHeight+n.children.get(key).edgeLength());
      }
    }else if (n.suffixIndex > -1 && ( maxHeight < labelHeight - n.edgeLength()) ){
      maxHeight  = labelHeight - n.edgeLength();
      substringStartIndex = n.suffixIndex;
    }
  }
  public static void main(String[] args) {
    LongestRepeatedSubstring suffixtree = new LongestRepeatedSubstring("GEEKSFORGEEKS$");
    suffixtree.buildSuffixTree();
    suffixtree.getLongestRepeatedSubstring();
  }

}
