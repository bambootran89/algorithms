package Optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PushRelabelMaximumFlow {
	private class Edge{
		int from, to;
		long cap, flow;
		int index;
		public Edge(int from_, int t_, long c_, long flow_,int idx_){
			this.from = from_;
			this.to = t_;
			this.cap = c_;
			this.flow = flow_;
			this.index = idx_;
		}
	}
	
	int N;
	ArrayList<ArrayList<Edge>> G;
	
	Long[] excess;
	Integer[] dist, count;
	Boolean[] active;
	
	Queue<Integer> Q;
	
	public PushRelabelMaximumFlow(int N_){
		this.N = N_;
	
		G = new ArrayList<>();
		
		for (int i = 0; i<N; i++)
			G.add(new ArrayList<>());
		
		excess = new Long[N];
		
		active = new Boolean[N];
		dist =   new Integer[N];
		count =  new Integer[N*2];
		
		Arrays.fill(active,false);
		Arrays.fill(dist, 0);
		Arrays.fill(count, 0); 
		Arrays.fill(excess, (long)0);
		
		
		Q =      new LinkedList<>();
	}
	
	void addEdge(int from, int to,  long cap){
		G.get(from).add( new Edge(from, to, cap, 0, G.get(to).size()) );
		if(from == to) 
			G.get(from).get(G.get(from).size() - 1).index++;
		G.get(to).add(new Edge(to, from,0,0,G.get(from).size() - 1));
	}
	
	void enqueue(int v) {
		if (!active[v]  && excess[v] > 0){
			active[v] = true;
			Q.add(v);
		}
	}
	
	void push(Edge e){
		Long tmp = excess[e.from];
		if (tmp > (e.cap - e.flow) )
			tmp = (e.cap - e.flow);
		
		if(dist[e.from] <= dist[e.to] || tmp == 0 ) return;
		
		e.flow +=tmp;
		G.get(e.to).get(e.index).flow -=tmp;
		excess[e.to] +=  tmp;
		excess[e.from] -= tmp;
		enqueue(e.to);
	}
	
	void gap(int k){
		for(int v = 0; v< N ; v++){
			
			if(dist[v] < k) continue;
			
			count[dist[v]]--;
			dist[v] = N+1 > dist[v]? N+1: dist[v];
			count[dist[v]]++;
			
			enqueue(v);
		}
	}
	
	void relabel(int v){
		dist[v] = 2*N;
		for (Edge e: G.get(v)){
			if(e.cap - e.flow >0) {
				int H = dist[v];
				dist[v] =  H < dist[e.to] +1? H: dist[e.to] +1 ;
			}
		}
		count[dist[v]]++;
		enqueue(v);
	}
	
	void discharge(int v ){
		for (int i = 0; excess[v] > 0 && i < G.get(v).size(); i++)
			push(G.get(v).get(i));
		if (excess[v] > 0){
			if(count[dist[v]] == 1){
				gap(dist[v]);
			}else{
				relabel(v);
			}
		}
	}
	
	long getMaxFlow(int s, int t){
		count[0] = N-1;
		count[N] = 1;
		
		dist[s] = N;
		
		active[s] = active[t] = true;
		
		for (int i = 0; i<G.get(s).size(); i++){
			excess[s] +=  G.get(s).get(i).cap;
			push(G.get(s).get(i));
		}
		System.out.println("e "+ excess[s]);
		while(!Q.isEmpty()) {
			int v = Q.poll();
			active[v] =  false;
			discharge(v);
		}
		
		long totalFlow = 0;
		for (int i = 0; i<G.get(s).size(); i++){
			totalFlow+=G.get(s).get(i).flow;
		}
		return totalFlow;
	}
	
	
	public static void main(String[] args){
		PushRelabelMaximumFlow mf = new PushRelabelMaximumFlow(6);
		
		mf.addEdge(0, 1, 15);
		mf.addEdge(0, 3, 4);
		mf.addEdge(1, 2, 12);
		mf.addEdge(2, 5, 7);
		mf.addEdge(2, 3, 3);
		mf.addEdge(3, 4, 10);
		mf.addEdge(4,1,5);
		mf.addEdge(4, 5, 10);
		
//		mf.addEdge(0, 1, 10);
//		mf.addEdge(0, 2, 10);
//		mf.addEdge(1, 2, 2);
//		mf.addEdge(1, 3, 4);
//		mf.addEdge(1, 4, 8);
//		mf.addEdge(2, 4, 9);
//		mf.addEdge(3, 5, 10);
//		mf.addEdge(4, 3, 6);
//		mf.addEdge(4, 5, 10);
		
		System.out.println("MaxFlow(0, 5) = " + mf.getMaxFlow(0, 5) );
	}
	
	
}
