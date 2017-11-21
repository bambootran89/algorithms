package Optimization;

import java.util.Arrays;

public class MinCostMatching {

	double minCostMatching(double[][] cost, int[] Lmate, int[] Rmate){
		int n = cost.length;
		//construct  dual feasible solution
		double[] u = new double[n];
		double[] v = new double[n];
		
		for(int i = 0; i< n; i++){
			u[i] = cost[i][0];
			for (int j = 0; j< n; j++)
				u[i] = Double.min(u[i], cost[i][j]);
		}
		
		for(int j = 0; j < n; j++){
			v[j] = cost[0][j] - u[0];
			for (int i = 0; i < n; i++)
				v[j] = Double.min(v[j], cost[i][j] - u[i]);
		}
		// construct primal solution satisfying complementary  slackness.
		
		
		Arrays.fill(Lmate, -1);
		
		Arrays.fill(Rmate, -1);
		
		int mated = 0;
		for(int i = 0; i< n; i++){
			for(int j = 0; j<n; j++){
				if(Rmate[j] != - 1)
					continue;
				if(Math.abs(cost[i][j] - u[i] - v[j]) < 1e-10){
					Lmate[i] = j;
					Rmate[j] = i;
					mated++;
					break;
				}
			}
		}
		
		double[] dist = new double[n];
		int[] dad = new int[n];
		int[] seen = new int[n];
		
		//repeat until primal solution is feasible
		while(mated < n){
			// find an unmatched left node
			int s = 0;
			while(Lmate[s] != -1) s++;
			//initialize dijkstra
			Arrays.fill(dad, -1);
			Arrays.fill(seen, 0);
			
			for(int k = 0; k< n; k++)
				dist[k] = cost[s][k] - u[s] - v[k];
			
			int j = 0;
			while(true){
				// find closest
				j = -1;
				for(int k = 0; k<n; k++){
					if(seen[k] == 1) continue;
					if(j == -1 || dist[k] < dist[j])
						j = k;
				}
				seen[j] = 1;
				//termination condition
				if(Rmate[j] == -1)
					break;
				//relax neighbors
				final int i = Rmate[j];
				for (int k = 0; k<n; k++){
					if(seen[k] ==1) continue;
					final double new_dist = dist[j] + cost[i][k] - u[i] - v[k];
					if(dist[k] > new_dist){
						dist[k] = new_dist;
						dad[k]  = j;
					}
				}
			}
			
			//update dual varialbes
			for (int k = 0; k< n; k++){
				if(k==j || seen[k] == 1) 
					continue;
				final int i = Rmate[k];
				v[k] +=dist[k] - dist[j];
				u[i] -=dist[k] - dist[j];
			}
			
			u[s] +=dist[j];
			// augment along path
			while(dad[j] >=0 ){
				final int d = dad[j];
				Rmate[j] = Rmate[d];
				Lmate[Rmate[j]] = j;
				j = d;
			}
			
			Rmate[j] = s;
			Lmate[s] = j;
			mated++;
				
		}
		
		double value = 0;
		for (int i = 0; i< n; i++)
			value+=cost[i][Lmate[i]];
		
		return value;
	}
	
	public static void main(String[] args){
		MinCostMatching mcm = new MinCostMatching();
		int[] Lmate = new int[2];
		int[] Rmate = new int[2];
		
		double INF = Double.MAX_VALUE/4;
		double[][] cost = new double[2][2];
		for (int i = 0; i< cost.length; i++)
			Arrays.fill(cost[i], INF);
		
		cost[0][0] = 2; 
		
		cost[0][1] = 3; 
		
		cost[1][0] = 5; 
		
		cost[1][1] = 7; 
		
		double value = mcm.minCostMatching(cost, Lmate, Rmate);
		
		System.out.println("value: " +value + "\nLmate: "+  Arrays.toString(Lmate) + "\nRmate: " + Arrays.toString(Rmate));
		for (int i = 0; i< Lmate.length; i++)
			System.out.println("Match: " + i + " to " + Lmate[i]);
		
	}
}
