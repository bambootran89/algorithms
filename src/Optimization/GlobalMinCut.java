package Optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GlobalMinCut {
	private class Pair{
		int first;
		ArrayList<Integer> second;
		
		public Pair(int f, ArrayList<Integer> s){
			first = f;
			second = s;
		}
	}
	
	public Pair getMinCut(ArrayList<ArrayList<Integer>> weights){
		int N =  weights.size();
		
		ArrayList<Boolean> used = new ArrayList<>(Arrays.asList(new Boolean[N]));
		Collections.fill(used, false);
		
		ArrayList<Integer> cut =  new ArrayList<>();
		ArrayList<Integer> best_cut = new ArrayList<>();
		
		int best_weight = -1;
		
		for(int phase = N - 1; phase >=0; phase--){
			ArrayList<Integer> w = (ArrayList<Integer>) weights.get(0).clone();
			ArrayList<Boolean> added = (ArrayList<Boolean>) used.clone();
			int prev, last = 0;
			for (int i = 0; i < phase; i++){ 
				prev = last;
				last = -1;
				for(int j = 1; j < N; j++)
					//MaxStickiness V\T
					if(!added.get(j) && (last == -1 || w.get(j) > w.get(last)))
						last = j;
				
				if (i == phase - 1){
					for(int j =0 ; j < N; j++)
						weights.get(prev).set(j, weights.get(prev).get(j) + weights.get(last).get(j));
					for(int j = 0; j< N; j++)
						weights.get(j).set(prev, weights.get(prev).get(j));
					
					used.set(last, true);
					cut.add(last);
					if(best_weight == -1 || w.get(last) < best_weight){
						best_cut = (ArrayList<Integer>) cut.clone();
						best_weight = w.get(last);
					}
				}else{
					for(int j = 0; j< N; j++)
						w.set(j, w.get(j) + weights.get(last).get(j));
					added.set(last, true);
				}
			}
		}
		
		return new Pair(best_weight,best_cut);
	}
	
	
}
