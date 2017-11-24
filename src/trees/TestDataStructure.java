package trees;

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



public class TestDataStructure {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new SegmentTreeTest();
		
		
	}

}
