package Optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class DinicBlockMaxFlow {
	private class Edge {
		int u,v;
		long cap, flow;
		public Edge(int u_, int v_, long cap_){
			this.u = u_;
			this.v = v_;
			this.cap = cap_;
			this.flow = 0;
		}
	}
	int N;
	ArrayList<Edge> E;
	ArrayList<ArrayList<Integer>> g;
	ArrayList<Integer> d;
	
	public DinicBlockMaxFlow(int N_){
		this.N = N_;
		E = new ArrayList<>();
		g = new ArrayList<>();
		for (int i = 0; i< N; i++)
			g.add(new ArrayList<>());
		d = new ArrayList<>(Arrays.asList(new Integer[N]));
		
	}
	
	void addEdge(int u, int v, long cap){
		if(u!=v){
			E.add(new Edge(u, v, cap));
			g.get(u).add(E.size() - 1);
			E.add(new Edge(v, u,0));
			g.get(v).add(E.size() - 1);
			
		}
	}

	boolean BFS(int S, int T) {
		Queue<Integer> q = new LinkedList<>();
		q.add(S);
		Collections.fill(d, N+1);
		
		d.set(S, 0);
		while(!q.isEmpty()){
			int u = q.poll();
			if (u == T)
				break;
			for (int k:g.get(u)){
				Edge e = E.get(k);
				if (e.flow < e.cap && d.get(e.v) > d.get(e.u) + 1){
					d.set(e.v, d.get(e.u) + 1);
					q.add(e.v);	
				}
			}
		}
		
		return d.get(T) !=N+1;
	}
	
	long DFS(int u, int T, long flow){
		if (u==T || flow  == 0 )
			return flow;
		for (int k: g.get(u)){
			Edge e = E.get(k);
			Edge oe = E.get(k + 1);
			if(d.get(e.v) == d.get(e.u) +1){
				
				//find minimum flow from u to t
				long tmp = e.cap - e.flow;
				if (flow !=-1 && tmp > flow) 
					tmp = flow;
				
				long pushed = DFS(e.v,T,tmp);
				if(pushed>0){
					e.flow +=pushed;
					oe.flow -=pushed;
					return pushed;	
				}
				
			}
		}		
		return 0;
	}
	
	long maxFlow(int S, int T){
		long total = 0;
		while(BFS(S,T)){			
			while(true){
				long flow = DFS(S, T, -1);
				if (flow == 0)
					break;
				total +=flow;
			}
		}
		
		return total;
	}
	
	
	public static void main(String[] args){
		DinicBlockMaxFlow mf = new DinicBlockMaxFlow(6);

//		mf.addEdge(0, 1, 10);
//		mf.addEdge(0, 2, 10);
//		mf.addEdge(1, 2, 2);
//		mf.addEdge(1, 3, 4);
//		mf.addEdge(1, 4, 8);
//		mf.addEdge(2, 4, 9);
//		mf.addEdge(3, 5, 10);
//		mf.addEdge(4, 3, 6);
//		mf.addEdge(4, 5, 10);

		mf.addEdge(0, 1, 15);
		mf.addEdge(0, 3, 4);
		mf.addEdge(1, 2, 12);
		mf.addEdge(2, 5, 7);
		mf.addEdge(2, 3, 3);
		mf.addEdge(3, 4, 10);
		mf.addEdge(4,1,5);
		mf.addEdge(4, 5, 10);
		System.out.println("MaxFlow(0, 5) = " + mf.maxFlow(0, 5) );
		
	}
	
}
