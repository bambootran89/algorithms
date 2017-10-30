package trees;

import java.util.ArrayList;
import java.util.List;

public abstract class Heap {
	List<Integer> heap = new ArrayList<Integer>();
	
	
	int left(int i){
		return i*2+1;
	}
	int right(int i){
		return i*2+2;
	}
	int parent(int i){
		return (i-1)/2;
	}
	void swap(int i, int j){
		int tmp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, tmp);
	}
	
	abstract void heapify(int i);
	
	public void insert(int data){
		heap.add(data);
		int i = heap.size()-1;
		decreaseKey(heap.size()-1, data);
	}
	public int pop(){
		if (heap.isEmpty()){
			return Integer.MIN_VALUE<getType()? Integer.MIN_VALUE: Integer.MAX_VALUE;
		}
		int root = heap.get(0);
		swap(0,heap.size()-1);
		heap.remove(heap.size()-1);
		heapify(0);
		return root;
		
	}
	public void modify(int index, int new_value){
		heap.set(index, new_value);
		swap(index, 0);
		heapify(0);
	}
	
	public void deleteKey(int index){
		if (index < 0 || index >=heap.size())
			return;
		decreaseKey(index, Integer.MAX_VALUE==getType()? Integer.MAX_VALUE:Integer.MIN_VALUE);
		pop();
	}
	
	 abstract void decreaseKey(int index, int new_value);
	 abstract int getType();
	
}
