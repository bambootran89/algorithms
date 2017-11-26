package DP;

import java.util.Stack;

public class LargestSumContiguousSubarray {
	int maxSubArraySum(int a[]){
		/* this code can be written shorter and more effectively
		 * but i'd like to separate two tasks in DP:
		 * 1. solve problem
		 * 2. trace back  (trying formulate for al most DP problem)
		 */
		
		/* Solving
		 * 
		 */
		int[] cur_max = new int[a.length];
		
		cur_max[0] = a[0];
		int max_save_index = 0;
		for (int i = 1 ; i< a.length; i++){
			cur_max[i] = a[i]> cur_max[i-1]+a[i]? a[i]: cur_max[i-1]+a[i];
			if (cur_max[max_save_index]<cur_max[i]){
				max_save_index = i;
			}
			
		}
		
		/* trace back
		 * zero/negative value + sum <= sum so it will the start point of the largest contiguous subarray.
		 * so when value cur_max[i] changes to zero or negative, it will break the largest Contiguous Subarray
		 */
		
		Stack<Integer> stack = new Stack<>();
		
		for (int i = max_save_index; i>0; i--){
			if(cur_max[i]<=0)
				break;
			stack.push(a[i]);
		}
		System.out.print("Contiguous Subarray having largest Sum: ");
		while(!stack.isEmpty()){
			System.out.print("   "+ stack.pop());
		}
		return cur_max[max_save_index];

	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] a = {-2,4, -3,4, -1, -2, 1, 5, -3};
		LargestSumContiguousSubarray lsc = new LargestSumContiguousSubarray();
		
        System.out.println("\nMaximum contiguous sum is " +
        		lsc.maxSubArraySum(a));
	}
	

}
