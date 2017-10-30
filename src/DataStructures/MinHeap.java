package DataStructures;

import java.util.ArrayList;


public class MinHeap {
	ArrayList<Integer> heap = new ArrayList<>();
	
	int parent(int i) {
		return (i-1)/2;
	}
	int left(int i){
		return 2*i +1;
	}
	int right(int i){
		return 2*i +2;
	}
	
	public int getMin(){
		if (heap.isEmpty()){
			return Integer.MAX_VALUE;
		}
		return heap.get(0);
		
	}
	
	public int extractMin(){
		if (heap.isEmpty()){
			return Integer.MAX_VALUE;
			
		}
		
		int root = heap.get(0);
		heap.remove(0);
		minHeapify(0);
		return root;
		
	}
	
	public void decreaseKey(int i, int new_val){
		heap.set(i, new_val);
		while(i!=0 && heap.get(parent(i)) > heap.get(i)){
			 swap(i, parent(i));
			 i = parent(i);
			 
		 }
	}
	void deleteKey(int i){
		decreaseKey(i, Integer.MIN_VALUE);
		extractMin();
	}
	public void insertKey(int value){
		 heap.add(value);
		 int i = heap.size() -1;
		 while(i!=0 && heap.get(parent(i)) > heap.get(i)){
			 swap(i, parent(i));
			 i = parent(i);
			 
		 }
	}
	
	void minHeapify(int i){
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < heap.size() && heap.get(l) < heap.get(i)){
			smallest = l;
		}
		if (r < heap.size() && heap.get(r) < heap.get(smallest)){
			smallest = r;
		}
		if (smallest != i){
			swap(i, smallest);
			minHeapify(smallest);
		}
	}
	
	void swap(int i,int j){
		int tmp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, tmp);
		
	}
	
}
