package sorts;

import java.util.ArrayList;

public class MergeSort {
	ArrayList<Integer> mergesort(ArrayList<Integer> arr){
		int lengh = arr.size();
		if (lengh == 1)  
			return arr;
		ArrayList<Integer> l = new ArrayList<>();
		ArrayList<Integer> r = new ArrayList<>();
		
		for ( int i = 0; i< lengh; i++){
			if(i < lengh/2){
				l.add(arr.get(i));
			}else{
				r.add(arr.get(i));
			}
		}		
		l = mergesort(l); 
		r = mergesort(r); 
		return merge(l,r);
		
		
	}

	private ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
		ArrayList<Integer> mergeList = new ArrayList<>();
		
		int l = 0;
		int r = 0;
		
		while(l <= left.size()-1 && r <= right.size()-1){
			if(left.get(l) <= right.get(r)){
				mergeList.add(left.get(l));
				l++;
				
			}else {
				mergeList.add(right.get(r));
				r++;
				
			}
			
		}
		
		while(l<=left.size()-1){
			mergeList.add(left.get(l));
			l++;
		}
		while(r<=right.size()-1){
			mergeList.add(right.get(r));
			r++;
		}
		return mergeList;
	}
}
