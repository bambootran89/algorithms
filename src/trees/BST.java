package trees;

public class BST implements Tree {
	protected class Node {
		protected int data;
		protected Node left, right;
	    public Node(int d){
	    	this.data = d;
	    	this.left = null;
	    	this.right = null;	
	    }
	}
	
	protected Node root;
	public BST(){
		root = null;
	}
	
	public void delete(int data){
		root = delete(root,data);
	}
	
	protected Node delete(Node p, int data){
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
	
	public boolean  search(int data){
		return search(root,data);
	}
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
