package Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SmallestRangeKList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
		arr.add( new ArrayList<>( Arrays.asList(4, 7, 9, 12, 15) ) );
		arr.add( new ArrayList<>( Arrays.asList(0, 8, 10, 14, 20) ) );
		arr.add( new ArrayList<>( Arrays.asList(6, 12, 16, 30, 50) ) );
		moreBetterApproachfindSmallestRange(arr);
		return;
		
	}

	private static void moreBetterApproachfindSmallestRange(ArrayList<ArrayList<Integer>> arr) {
		class Node implements Comparable<Node>{
			public int element;
			public int i;
			public int j;
					
			public Node(int e, int i_, int j_){
				element = e;
				i = i_;
				j = j_;
			}

			@Override
			public int compareTo(Node arg0) {
				// TODO Auto-generated method stub
				return this.element - arg0.element;
			}
		}
		
		PriorityQueue<Node> queue=new PriorityQueue<Node>();  
		
		int range = Integer.MAX_VALUE;
	    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	    int start=0, end=0;
	    
	    for (int i = 0; i<arr.size(); i++){
	    	Node n = new Node(arr.get(i).get(0), i, 1);
	    	if (n.element > max){
	    		max = n.element;
	    	}
	    	queue.add(n);
	    }
	    
	    while (true){
	    	// Get the minimum element and store it in output
	    	Node root = queue.poll();
	    	// update min
	    	min = root.element;
	    	// update range
	    	if (range > max - min + 1)
	        {
	            range = max - min + 1;
	            start = min;
	            end = max;
	        }
	    	// Find the next element that will replace current
	        // root of heap. The next element belongs to same
	        // list as the current root.
	        if (root.j < arr.get(root.i).size())
	        {
	            root.element = arr.get(root.i).get(root.j);
	            root.j += 1;
	
	            // update max element
	            if (root.element > max)
	                max = root.element;
	        }else
	        	break;
	        // Replace root with next element of list
	        queue.add(root);
	    	
	    }
	    System.out.println("The smallest range is " + start + " , " + end);	
	}

	private static void findSmallestRange(ArrayList<ArrayList<Integer>> arr) {
		
		int i,minval,maxval,minrange,minel=0,maxel=0,flag,minind;
		
		ArrayList<Integer> ptr = new ArrayList<>();
		
		for(i = 0;i <= arr.size();i++) 
	        ptr.add(0);
		
		minrange = Integer.MAX_VALUE;
		while (true){
			  minind = -1; 
	          minval = Integer.MAX_VALUE;
	          maxval = Integer.MIN_VALUE;
	          flag = 0;
	          System.out.println("ptr = " + ptr);
	          for (i = 0; i< arr.size(); i++){
	        	  // if every element of list[i] is traversed then break the loop
	        	  if(ptr.get(i) == arr.get(i).size()){ 
	        		  flag = 1;
	        		  break;
	        	  }
	        	  
	        	// find minimum value among all the list elements pointing by the ptr[] array
	        	  if(ptr.get(i) < arr.get(i).size()  && arr.get(i).get(ptr.get(i)) < minval){
	        		  minind = i;
	        		  minval = arr.get(i).get(ptr.get(i));
	        	  }
	        	// find maximum value among all the list elements pointing by the ptr[] array
	        	  if(ptr.get(i) < arr.get(i).size()  && arr.get(i).get(ptr.get(i)) > maxval){
	        		  maxval = arr.get(i).get(ptr.get(i));
	        	  }
	          }
	          
	          if (flag==1){
	        	  break;
	          }
	          
	          ptr.set(minind, ptr.get(minind) +1);
	         //updating the minrange
	          
	          if( (maxval - minval) < minrange ){
	        	  minel = minval;
	        	  maxel = maxval;
	        	  minrange = maxel - minel;
	          }
		}
		
		System.out.println("The smallest range is " + minel + " , " + maxel);
	}
	
	

}
