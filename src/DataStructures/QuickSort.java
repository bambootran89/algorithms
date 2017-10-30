package DataStructures;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort {
	ArrayList<Integer> arr;
	public QuickSort(ArrayList<Integer> arr_) {
		// TODO Auto-generated constructor stub
		arr = arr_;
		
	}
	
	void swap(int i , int j){
		if (i==j){
			return;
		}
		int tmp = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, tmp);
		
	}
	
	int partition(int low, int high){
		int pivot = arr.get(high);
		int i = low - 1;
		for (int j = low; j<= high; j++){
			if(arr.get(j) <= pivot){
				i++;
				swap(i,j);
			}
			
		}
		
        return i;		
	}
	
	void quicksort(int low, int high){
		if (low < high){
			int pi = partition(low, high);
			quicksort(low, pi-1);
			quicksort(pi+1, high);
		}
		
	}
}
