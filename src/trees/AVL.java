package trees;


public class AVL implements Tree{
	
	protected class Node {
		protected int data;
		protected Node left, right;
		protected int heigh ;
	    public Node(int d){
	    	this.data = d;
	    	this.left = null;
	    	this.right = null;	
	    	heigh = 1;
	    }
	}
	protected Node root;
	public AVL(){
		root = null;
	}
	
	private int height(Node node){
		if (node == null){
			return 0;
		}
		return node.heigh;
	}
	private int max(int a, int b){
		return (a>b)?a:b;
		
	}
	private Node left_rotate(Node x){
		Node y = x.right;
		Node T =  y.left;	
		
		y.left = x;
		x.right = T;
		
		x.heigh = max(height(x.left), height(x.right)) +1;
		y.heigh = max(height(y.left), height(y.right)) +1;
		
		return y;
		
	}
	private Node right_rotate(Node y){
		Node x = y.left;
		Node T = x.right;
		
		x.right = y;
		y.left = T;
		
		
		y.heigh = max(height(y.left), height(y.right)) +1;
		x.heigh = max(height(x.left), height(x.right)) +1;
		
		return x;
				
	}
	
	private int getBalance(Node node){
		if (node == null)
			return 0;
		return height(node.left) - height( node.right);
	}
	
	protected Node insert(Node node, int data){
		
		if (node == null)
			return new Node(data);
		if (data < node.data)
			node.left =  insert(node.left,data);
		else if (data > node.data)
			node.right =  insert(node.right, data);
		else 
			return node;
		node.heigh = max(height(node.left), height(node.right)) + 1;
		
		int balance = getBalance(node);
		
		if (balance > 1 &&  data < node.left.data) // left-left
			return right_rotate(node);
		if (balance <-1 &&  data > node.right.data) // right-right
			return left_rotate(node);
		
		if (balance > 1 && data > node.left.data) {// left-right
			node.left = left_rotate(node.left);
			return right_rotate(node);
		}
		if (balance < -1 && data < node.right.data) {//right -left
			node.right = right_rotate(node.right);
			return left_rotate(node);
		}	
		
		return node;
	}
	public void insert(int data){
		root = insert(root,data);
	}
	

	@Override
	public void delete(int data) {
		// TODO Auto-generated method stub
		root = delete(root,data);
	}
	
	protected Node delete(Node node, int data){
		if (node == null)
			return null;
		else if (data < node.data)
			node.left = delete(node.left,data);
		else if (data > node.data)
			node.right = delete(node.right,data);
		else{
			if (node.left == null || node.right == null){
				Node temp = null;
				if (node.left == null)
					temp =  node.right;
				else if (node.right == null)
					temp = node.left;
				
				node = temp;
			}
			else{
				Node pp = node.right;
				while(pp.left != null) pp = pp.left;
				node.data = pp.data;
				node.right = delete(node.right,node.data);		
			}
		}
		
		if (node == null)
			return node;
		node.heigh = max(height(node.left), height(node.right))+1;
		
		
		int balance = getBalance(node);
		
		if (balance > 1 &&  getBalance(node.left) >=0) // left-left
			return right_rotate(node);
		if (balance <-1 &&  getBalance(node.right) <=0) // right-right
			return left_rotate(node);
		
		if (balance > 1 && getBalance(node.left) < 0) {// left-right
			node.left = left_rotate(node.left);
			return right_rotate(node);
		}
		if (balance < -1 && getBalance(node.right) > 0) {//right -left
			node.right = right_rotate(node.right);
			return left_rotate(node);
		}	
		
		return node;		
	}
	
	@Override
	public int findheigh() {
		// TODO Auto-generated method stub
		return root.heigh;
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
	
	@Override
	public boolean search(int data) {
		return search(root,data);
	}
	
}
