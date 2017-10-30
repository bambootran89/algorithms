package DataStructures;

import java.util.ArrayList;

public class BinaryIndexedTree {
	ArrayList<Integer> biTree;
	
	public int getSum(int index){
		
		int sum = 0;
		index = index+1;
		while(index>0) {
			
			sum +=biTree.get(index);
			index -= index&(-index);
		}
		return sum;
	}
	
	public void updateBIT(int index, int val){
		
		index = index +1;
		int n = biTree.size();
		while(index<=n){
			biTree.set(index, biTree.get(index)+val);
			index += index & (-index);	
		}
		
	}
	
	public void constructBITree(ArrayList<Integer> arr){
		int n = arr.size();
		biTree = new ArrayList<Integer>();
		for ( int i = 0; i<=n; i++){
			biTree.add(0);			
		}
		for ( int i =0; i<n; i++){
			updateBIT(i, arr.get(i));
			
		}
		
	}
	
}

