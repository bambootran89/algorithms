package trees;

/**
 * Binary Search Tree (BST) implementation.
 *
 * <p>A binary tree where each node's left subtree contains only nodes 
 * with values less than the
 * node, and the right subtree contains only nodes with values greater than the node.
 *
 * <p>Time Complexity:
 * - Average: O(log n) for search, insert, delete
 * - Worst case: O(n) if tree is skewed
 *
 * <p>Space Complexity: O(h) where h is the height of tree
 *
 * @author Algorithms Collection
 */
public class BST implements Tree {
  /** Node class representing each element in the BST. */
  protected class Node {
    protected int data;
    protected Node left, right;

    /**
     * Initializes a Node with the given data.
     *
     * @param d the data value for this node
     */
    public Node(int d) {
      this.data = d;
      this.left = null;
      this.right = null;
    }
  }

  protected Node root;

  /** Initializes an empty BST. */
  public BST() {
    root = null;
  }

  /**
   * Deletes a node with the given data from the BST.
   *
   * @param data the value to delete
   * @throws RuntimeException if the data is not found
   */
  public void delete(int data) {
    root = delete(root, data);
  }

  /**
   * Helper method to recursively delete a node.
   *
   * @param p the current node
   * @param data the value to delete
   * @return the updated subtree
   */
  protected Node delete(Node p, int data) {
    if (p == null)
      throw new RuntimeException("can not delete ... ");
    else if (data < p.data)
      p.left = delete(p.left,data);
    else if (data > p.data)
      p.right = delete(p.right,data);
    else{
      if (p.left == null)
        return p.right;
      else if (p.right == null)
        return p.left;
      else{
        Node pp = p.left;
        while(pp.right != null) pp = pp.right;
        p.data = pp.data;
        p.left = delete(p.left,p.data);    
      }
    }
    return p;    
  }
  
  protected boolean search(Node p, int data){
    if (p == null)
      return false;
    else if (p.data == data)
      return true;
    else if (data<p.data)
      return search(p.left, data);
    else 
      return search(p.right, data);
  }
  
  /**
   * Searches for a value in the BST.
   *
   * @param data the value to search for
   * @return true if found, false otherwise
   */
  public boolean search(int data){
    return search(root,data);
  }

  /**
   * Helper method to recursively insert a node.
   *
   * @param p the current node
   * @param data the value to insert
   * @return the updated subtree
   */
  protected Node insert(Node p, int data){
    if (p == null)
      return new Node(data);
    if (data < p.data)
      p.left =  insert(p.left,data);
    else if (data > p.data)
      p.right =  insert(p.right, data);
    else 
      return p;
    return p;  
  }

  /**
   * Inserts a value into the BST.
   *
   * @param data the value to insert
   */
  public void insert(int data){
    root = insert(root,data);
  }
  protected int count,max;
  
  public int findheigh(){
    max = 0;
    count = 0;
    findheigh(root);
    return max-1;
  }
  
  protected void findheigh(Node p){
    if (p == null)
      return;
    count+=1;
    if (max < count){
      max = count;
    }
    findheigh(p.left);
    if (max < count){
      max = count;
    }
    findheigh(p.right);
    count-=1;  
  }
  
  
}
