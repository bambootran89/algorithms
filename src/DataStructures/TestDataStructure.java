package DataStructures;

import java.util.ArrayList;
import java.util.Arrays;

class BinaryIndexedTreeTest {
	ArrayList<Integer> freq = new ArrayList<Integer>(Arrays.asList(2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9));
	
	
	BinaryIndexedTreeTest(){
		BinaryIndexedTree bitTree = new BinaryIndexedTree();
		bitTree.constructBITree(freq);
		System.out.println("\nSum of elements in arr[0..5] is " + bitTree.getSum(5));
		freq.set(3, freq.get(3)+6);
		bitTree.updateBIT(3, 6);
		System.out.println("\nSum of elements in arr[0..5] after update is " + bitTree.getSum(5));
		
	}
	
}
class SegmentTreeTest{
	int arr[] = {1, 3, 5, 7, 9, 11};
	int n = arr.length ;
	SegmentTreeTest(){
		SegmentTree segTree = new SegmentTree();
		segTree.constructST(arr,n);
		System.out.println("Sum of values in given range = " + segTree.getSum(n, 1, 3));
		segTree.updateValue(arr, n, 1, 10);
		System.out.println("Updated sum of values in given range = " + segTree.getSum(n, 1, 3));
		
	}
	
}

class MinHeapTest{
	MinHeap minHeap = new MinHeap();
	public MinHeapTest() {
		// TODO Auto-generated constructor stub
		
		minHeap.insertKey(3);
		minHeap.insertKey(2);
		minHeap.insertKey(1);
		minHeap.insertKey(15);
		minHeap.insertKey(5);
		minHeap.insertKey(4);
		minHeap.insertKey(45); 		
		System.out.println( minHeap.extractMin() + " " + minHeap.getMin() +" " );
		
		minHeap.decreaseKey(2, 1);
		System.out.println(minHeap.getMin());
	}
	
}



class MaxHeapTest{
	MaxHeap maxHeap = new MaxHeap();
	public MaxHeapTest() {
		// TODO Auto-generated constructor stub
		
		maxHeap.insertKey(3);
		maxHeap.insertKey(2);
		maxHeap.insertKey(1);
		maxHeap.insertKey(15);
		maxHeap.insertKey(5);
		maxHeap.insertKey(4);
		maxHeap.insertKey(45); 		
		System.out.println( maxHeap.extractMax() + " " + maxHeap.getMax() +" " );
		
		maxHeap.decreaseKey(2, 100);
		System.out.println(maxHeap.getMax());
	}
	
}


class QuickSortTest {
	QuickSort qSort;
	public QuickSortTest() {
		// TODO Auto-generated constructor stub
		ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(100,2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9));
		qSort = new QuickSort(arr);
		qSort.quicksort(0,arr.size()-1);
		System.out.println(arr);
		
	}
	
}


class MergeSortTest {
	MergeSort mergeSort;
	public MergeSortTest() {
		// TODO Auto-generated constructor stub
		ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(100,2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9));
		mergeSort = new MergeSort();
		mergeSort.mergesort(arr);
		System.out.println(mergeSort.mergesort(arr));
		
	}
	
}


public class TestDataStructure {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BinaryIndexedTreeTest();
		new SegmentTreeTest();
		new MinHeapTest();
		new MaxHeapTest();
		new QuickSortTest();
		new MergeSortTest();
		
	}

}
