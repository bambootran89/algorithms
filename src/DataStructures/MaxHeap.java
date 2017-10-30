package DataStructures;

import java.util.ArrayList;

public class MaxHeap {
      ArrayList<Integer> heap = new ArrayList();
      
      
      void swap(int i, int j){
    	  int tmp =  heap.get(i);
    	  heap.set(i, heap.get(j));
    	  heap.set(j, tmp);
      }
      
      int parent(int i) {
    	  return (i-1)/2;
  
      }
      int left(int i){
    	  return 2*i +1;
      }
      
      int right(int i){
    	  return 2*i +2;
      }
      
      
      int getMax(){
    	  
    	  if (heap.isEmpty()){
    		  return Integer.MIN_VALUE;
    	  }
    	  return heap.get(0);
      }
      
      void decreaseKey(int i, int new_val){
    	  heap.set(i, new_val);
    	  while(i!=0 && heap.get(parent(i)) < heap.get(i)){
    		  swap(i, parent(i));
    		  i = parent(i);
    	  }
      }
      
  	void deleteKey(int i){
  		if ( i < 0 || i>= heap.size())
  			return;
  		decreaseKey(i, Integer.MAX_VALUE);
  		extractMax();
  	}
   int extractMax() {
		// TODO Auto-generated method stub
		if (heap.isEmpty()){
			return Integer.MIN_VALUE;
		}
		int root = heap.get(0);
		
		heap.remove(0);
		
		maxHeapify(0);
		return root;
	}

	private void maxHeapify(int i) {
		// TODO Auto-generated method stub
		if (i < 0 || i >= heap.size())
			return;
		
		int l = left(i);
		int r = right(i);
		int max = i;
		
		if (l < heap.size() && heap.get(l) > heap.get(max)) {
			max = l;
		}
		if( r < heap.size() && heap.get(r) > heap.get(max)) {
			max = r;
		}
		if (i != max ) {
			swap(i, max);
			maxHeapify(max);
			
		}
	}

	public void insertKey(int value){
  		 heap.add(value);
  		 int i = heap.size() -1;
  		 while(i!=0 && heap.get(parent(i)) < heap.get(i)){
  			 swap(i, parent(i));
  			 i = parent(i);
  			 
  		 }
  	}
}
