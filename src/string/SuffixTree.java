package string;
/**
 * Suffix Tree for pattern matching.
 *
 * @author Algorithms Collection
 */


import java.util.LinkedHashMap;

public class SuffixTree {
  class EndObject{
    public int value;
    public EndObject() {
      value =-1;
    }
    public EndObject(int v) {
      value =v;
    }
  }
  
  class Node{
    
    LinkedHashMap<Character, Node> children;
    
    Node suffixLink;
    Integer start=null;
    EndObject end=null;
    int suffixIndex;
    
      public Node(int s, EndObject e){
      suffixLink = root;
      start = s;
      end = e;
      suffixIndex = -1;
        children = new LinkedHashMap<>();
      }
      int edgeLength(){
        if (end.value==-1 && start == -1)
          return 0;
        return end.value - start +1;
      }
  }
  
  String text;
  
  
  Node root = null;
  Node lastNewNode = null;
  Node activeNode = null;
  
  int activeEdge = -1;
  int activeLength = 0;
  
  int remainingSuffixCount =0;
  EndObject leafEnd = new EndObject();
  EndObject rootEnd = null;
  EndObject splitEnd = null;
  int size = -1;
  public SuffixTree(String str){
    text = str;
  }
  
  private boolean walkDown(Node currNode){
    if(activeLength >= currNode.edgeLength()){
      activeEdge +=currNode.edgeLength();
      activeLength -=currNode.edgeLength();
      activeNode = currNode;
      return true;
    }
    
    return false;
  }
  
  
  protected void extendSuffixTree(int pos){
    
    leafEnd.value = pos;
    remainingSuffixCount++;
    lastNewNode = null;
    
    while(remainingSuffixCount > 0){
      
      if (activeLength == 0) //APCFALZ
        activeEdge = pos;
      if (activeNode.children.containsKey(text.charAt(activeEdge))==false){
        activeNode.children.put(text.charAt(activeEdge), new Node(pos, leafEnd));
        
        if (lastNewNode != null){
          lastNewNode.suffixLink = activeNode;
          lastNewNode = null;
        }
      }
      else{
                
        Node next = activeNode.children.get(text.charAt(activeEdge));
        if (walkDown(next)){
          continue;  
        }
        
        if(text.charAt(next.start + activeLength) == text.charAt(pos)){
          if(lastNewNode !=null && activeNode!=root){
            lastNewNode.suffixLink = activeNode;
            lastNewNode = null;
          }
          
          activeLength++;
          break;
        }
        
        splitEnd = new EndObject(next.start+ activeLength - 1);
        Node split = new Node(next.start, splitEnd);
        activeNode.children.put(text.charAt(activeEdge), split);
        
        split.children.put(text.charAt(pos), new Node(pos, leafEnd));
        next.start += activeLength;
        split.children.put(text.charAt(next.start), next);
        
        if(lastNewNode !=null){
          lastNewNode.suffixLink = split;
        }
        
        lastNewNode = split;
        
      }
      
      remainingSuffixCount--;
      if (activeNode == root && activeLength > 0) //APCFER2C1
      {
        activeLength--;
        activeEdge = pos - remainingSuffixCount +1;
      }
      else if (activeNode != root) //APCFER2C2
      {
        activeNode = activeNode.suffixLink;
      }
      
    }
    
  }
  
  private void print(int i, int j){
    
    for (int k = i; k<=j; k++){
      System.out.print(text.charAt(k));
    }
  }
  
  private void setSuffixIndexByDFS(Node n, int labelHeight){
    if (n==null) 
      return;
    if (n.start !=-1) {
      //print(n.start, n.end.value);
    }
    
    int leaf = 1;
    
    for (Character key : n.children.keySet()){
      
      if(leaf == 1  && n.start!=-1) {
        //System.out.println(" ["+ n.suffixIndex +"]");
      }
      
      leaf = 0;
      
      setSuffixIndexByDFS(n.children.get(key), labelHeight + n.children.get(key).edgeLength());
      
    }
    
    if (leaf == 1){
      

      n.suffixIndex = size - labelHeight;
      //System.out.println(" ["+ n.suffixIndex + "]");
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
    
    
  }
  

}
