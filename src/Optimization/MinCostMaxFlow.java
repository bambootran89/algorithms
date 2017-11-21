package Optimization;

import java.util.Arrays;

public class MinCostMaxFlow {
	private class Pair<T>{
		T first;
		T second;
		public Pair(T f, T s){
			first = f;
			second = s;
		}
	}
	
	int N;
	Long[][] cap, flow, cost;
	boolean[] found;
	Long[] dist, pi, width;
	Pair<Integer>[] dad;
	final long INF = Long.MAX_VALUE/4;
	
	public MinCostMaxFlow(int N_) {
		N = N_;
		cap = new Long[N][N];
		flow = new Long[N][N];
		cost = new Long[N][N];
		
		for(int i = 0; i<N; i++)
			Arrays.fill(flow[i], 0);
		
		found = new boolean[N];
		dist = new Long[N];
		pi = new Long[N];
		
		Arrays.fill(pi, 0);
		
		width = new Long[N];
		dad = new Pair[N];
	
	}
	
	void addEdge(int from, int to, long cap, long cost) {
		this.cap[from][to] = cap;
		this.cost[from][to] = cost;
	}
	
	void relax(int s, int k, long cap, long cost, int dir){
		long val = dist[s] + pi[s] - pi[k] + cost;
		if (cap>0 && val < dist[k]){
			dist[k] = val;
			dad[k] = new Pair<Integer>(s,dir);
			width[k] = cap<width[s]?cap:width[s];
		}
	}
	
	long dijkstra(int s, int t){
		Arrays.fill(found, false);
		Arrays.fill(dist, INF);
		Arrays.fill(width, (long)0);
		dist[s] = (long)0;
		width[s] = INF;
		
		while(s!=-1){
			int best = -1;
			found[s] = true;
			for(int k = 0; k< N; k++){
				if(found[k]) continue;
				relax(s, k, cap[s][k] - flow[s][k], cost[s][k], 1);
				relax(s, k, flow[s][k]            ,-cost[k][s], -1);
				
				if (best == -1 || dist[k] < dist[best]) 
					best = k;
			}
			s = best;
		}
		for (int k = 0; k < N; k++)
			pi[k] = Long.min(pi[k] + dist[k], INF);
		return width[t];
	}
	
	Pair<Long> getMaxFlow(int s, int t){
		long totFlow = 0;
		long totCost = 0;
		
		while(true){
			long amt = dijkstra(s, t);
			if (amt <=0 )
				break;
			totFlow +=amt;
			for (int x = t; x!=s; x = dad[x].first){
				if(dad[x].second == 1){
					flow[dad[x].first][x] +=amt;
					totCost +=amt * cost[dad[x].first][x];
				}else{
					flow[x][dad[x].first] -=amt;
					totCost -=amt * cost[x][dad[x].first];
				}
			}
				
		}
		return new Pair<Long>(totFlow, totCost);
	}
	
	public static void main(String[] args){
		//MinCostMaxFlow mcmf = new MinCostMaxFlow(6);
		//mcmf.addEdge(from, to, cap, cost);
		
	}
	
	
}
