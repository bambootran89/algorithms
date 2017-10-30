package DataStructures;

import javax.management.RuntimeErrorException;

public class BinaryTree {
	
	private class Node{
		private int data;
		private Node left, right;
		public Node(int data, Node l, Node r){
			this.data = data;
			right = r;
			left = l;
		}
		
		public Node(int data){
			this(data,null,null);
			
		}
	}
	
	private Node root;
	public BinaryTree(){
		root = null;
	}
	public void insert(int data){
		root  =  insert(root,data);
	}
	
	private Node insert(Node p, int data){
		
		if(p == null){
			return new Node(data);
		}
		if(p.data >= data){
			p.left = insert(p.left, data);
		}else{
			p.right = insert(p.right, data);
		}
		return p;
	}
	
	public boolean search(int data){
		return search(root,data);
	}
	
	private boolean search(Node p, int data){
		if (p == null){
			return false;
		}
		else if (p.data==data){
			return true;
		}else if(p.data < data){
			return search(p.right,data);
		}else{
			return search(p.left, data);
		}
	}
	public void delete(int data){
		root = delete(root, data);
	}
	
	private Node delete(Node p, int data){
		if (p == null) throw new RuntimeException("cannot delete...");
		else if (p.data > data) {
			p.left = delete(p.left, data);
		}else if(p.data < data){
			p.right = delete(p.right, data);
		}else{
			if (p.left == null) return p.right;
			else if (p.right == null) return p.left;
			else{
				p.data =  retrieveData(p.left);
				p.left =delete(p.left, p.data);
				
			}
		}
		
		return p;
	}
	private int retrieveData(Node p){
		
		while(p.right!=null) p=p.right;
		return p.data;
	}
	
}

