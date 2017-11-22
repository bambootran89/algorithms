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
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "(" + first + "," + second +")";
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
		
		for(int i = 0; i<N; i++){
			Arrays.fill(flow[i], (long)0);
			Arrays.fill(cap[i], (long)0);
			Arrays.fill(cost[i], (long)0);
		}
		
		found = new boolean[N];
		dist = new Long[N];
		pi = new Long[N];
		
		Arrays.fill(pi, (long)0);
		
		width = new Long[N];
		dad = new Pair[N];
	
	}
	
	void addEdge(int from, int to, long cap, long cost) {
		this.cap[from][to] = cap;
		this.cost[from][to] = cost;
		this.cap[to][from] = cap;
		this.cost[to][from] = cost;
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
				relax(s, k, flow[k][s]            ,-cost[k][s], -1);
				
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
		
//		MinCostMaxFlow mcmf = new MinCostMaxFlow(5);
//		mcmf.addEdge(1, 4, 10, 1 );
//		mcmf.addEdge(4, 1, 10, 1);
//		
//		mcmf.addEdge(1, 3, 10, 2);
//		mcmf.addEdge(3, 1, 10, 2);
//		
//		mcmf.addEdge(3, 4, 10, 5);
//		mcmf.addEdge(4, 3, 10, 5);
//		
//		mcmf.addEdge(1, 2, 10, 3);
//		mcmf.addEdge(2, 1, 10, 3);
//		
//		mcmf.addEdge(2, 4, 10, 4);
//		mcmf.addEdge(4, 2, 10, 4);
//		
//		mcmf.addEdge(0, 1, 20, 0);
//		
//		System.out.println(mcmf.getMaxFlow(0, 4));
		
		MinCostMaxFlow mcmf = new MinCostMaxFlow(8);
		mcmf.addEdge(0, 1, 3, 0);
		mcmf.addEdge(0, 2, 7, 0);
        mcmf.addEdge(1, 3, 7, 3);
        mcmf.addEdge(2, 3, 10, 6);
        mcmf.addEdge(2, 4, 4, 2);
        mcmf.addEdge(3, 5, 10, 4);
        mcmf.addEdge(3, 6, 2, 1);
        mcmf.addEdge(4, 6, 7, 3);
        mcmf.addEdge(5, 7, 4, 0);
        mcmf.addEdge(6, 7, 6, 0);
        System.out.println(mcmf.getMaxFlow(0, 7));
        
        System.out.println(mcmf.flow[0][1]);
        System.out.println(mcmf.flow[0][2]);
        
        System.out.println(mcmf.flow[5][7]);
        System.out.println(mcmf.flow[6][7]);
        
	}
	
	
}
