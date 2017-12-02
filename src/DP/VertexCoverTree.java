package DP;

import java.util.ArrayList;
import java.util.Arrays;

class Node{
	int v;
	ArrayList<Node> children;
	int[] V;
	public Node(int v, ArrayList<Node> c){
		this.v = v;
		this.children = c;
		V = new int[3];
		Arrays.fill(V, -1);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "("+v+ ")";
	}
	
}
public class VertexCoverTree {

	
	/*  
	 * 1. subproblems: v belongs to V and y {YES,NO, MAYBE}
	 * size of smallest vertex cover S in subtree rooted at v 
	 * such that [v belongs to S?] = y (unconstrained if y = MAYBE)
	 * 2. guess: Does MAYBE = YES or NO?
	 * 3. Recurrence:
	 * V(v,MAYBE) = min{V(v,YES), V(v,NO)}
	 * V(v, YES)  = 1 + sum(V(c, MAYBE) for c in v.children )
	 * V(v,NO)    = sum(V(c, YES) for c in v.children)
	 * 4. DP time O(V)
	 * 5. original problem O(root, MAYBE)
	 * 
	 * 0: MAYBE
	 * 1: YES
	 * 2: NO
	 */
	ArrayList<Node> vertexCover = new ArrayList<>();
	
	int vCover(Node root, int option){
		
		int result = -1;
		//System.out.println(root + " option = " + option);
		
		if(root==null)
			result = 0;
		else if(root.children.size() == 0){
			if (option == 1) // v is selected
				result = 1;
			else
				result = 0;
		}
		else if (root.V[option] >=0)
			result = root.V[option];
		else if(option == 0){
			int yes = vCover(root, 1);
			int no = vCover(root, 2);
			if (yes>no){
				result = no;
			}else{
				result = yes;
				vertexCover.add(root);
			}
			
		}else if(option == 1){
			int sum = 1;
			for (Node child: root.children){
				sum+=vCover(child, 0);
			}
			result =  sum;
		}else if(option ==2) {
			int sum = 0;
			for (Node child: root.children){
				sum+=vCover(child, 1);
			}
			result =  sum;
		}
		
		root.V[option] = result;
		
		return result;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* building a tree
		 */
		Node n40 = new Node(40, new ArrayList<>());
		Node n70 = new Node(70, new ArrayList<>());
		Node n80 = new Node(80, new ArrayList<>());
		Node n60 = new Node(60, new ArrayList<>());
	
		Node n50 = new Node(50, new ArrayList<Node>( Arrays.asList(n70,n80)));
		Node n30 = new Node(30, new ArrayList<Node>( Arrays.asList(n60)));
		Node n20 = new Node(20, new ArrayList<Node>( Arrays.asList(n40,n50)));
		Node n10 = new Node(10, new ArrayList<Node>( Arrays.asList(n20,n30)));
			
		VertexCoverTree vCT =  new VertexCoverTree();
		System.out.println("min vCover is " + vCT.vCover(n10, 0));
		
		System.out.println("These vertices are " + vCT.vertexCover);

	}

}
