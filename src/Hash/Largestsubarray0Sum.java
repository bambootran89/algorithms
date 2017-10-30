package Hash;

import java.util.HashMap;
import java.util.Map;

public class Largestsubarray0Sum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {15, -2, 2, -8, 1, 7, 10, 23};
		findSubs(arr);
	}
	
	public static void findSubs(int arr[]){
			
		Map<Integer, Integer> hash = new HashMap<>();
		int sum=0;
		int ending_index = -1;
		int max_len = 0;
				
		for (int i=0; i<arr.length; i++){
			sum+=arr[i];
			
			if (sum == 0 ){
				max_len = i+1;
				ending_index = i;
			}
			
			if(hash.containsKey(sum)){
				if(max_len < i-hash.get(sum)){
					{
	                    max_len = i - hash.get(sum);
	                    ending_index = i;
	                }
				}			
					
			}else{
				hash.put(sum, i);
			}
		}
		int end = ending_index - max_len + 1;
		System.out.println(end + " to " + ending_index);
	}

}
