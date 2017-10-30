package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FastDijkstras {
    
	private class Pair implements Comparable<Pair>{
		Double first;
		Integer second;
		public Pair(Double f, int s){
			first = f;
			second = s;
		}
		
		@Override
		public int compareTo(Pair p) {
			// TODO Auto-generated method stub
			if (this.first < p.first)
				return -1;
			else if(this.first > p.first)
				return 1;
			else {
				if (this.second < p.second)
					return -1;
				else if(this.second > p.second)
					return 1;
				else return 0;
			}
		}
		
		public String toString() { 
	         return "(" + first.toString() + ", " +  second.toString() + ")";
	      } 
		
	}
	private ArrayList<Pair> edges[];
	private final Double INF = Double.MAX_VALUE;
	private int source;
	private int target;
	private int N;
	@SuppressWarnings("unchecked")
	public void input() throws FileNotFoundException{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(new File("data\\dijkstras"));
	    
	    N = scanner.nextInt();
	    source = scanner.nextInt();
	    target = scanner.nextInt();
	    edges = new ArrayList[N];
	    
	    for (int i = 0; i< N; i++) 
	    	edges[i] = new ArrayList<>();
	    
	    
	    for (int i = 0; i< N; i++){
	    	int M = scanner.nextInt();
	    	for (int j = 0; j < M; j++){
	    		int vertex; 
	    		Double dist;
	    		vertex = scanner.nextInt();
	    		dist = scanner.nextDouble();
	    		edges[i].add(new Pair(dist,vertex));
	    	}
	    }
	   
	
	}
	
	public void dijkstras(){
		PriorityQueue<Pair> Q = new PriorityQueue<>();
		ArrayList<Double> dist = new ArrayList<>(Arrays.asList(new Double[N]));
		Collections.fill(dist, INF);
		ArrayList<Integer> dad = new ArrayList<>(Arrays.asList(new Integer[N]));
		Collections.fill(dad, -1);
		
		Q.add(new Pair(0.0, source));
		dist.set(source, 0.0);
		
		while(!Q.isEmpty()){
			Pair p = Q.poll();
			int here = p.second;
			if (here == target)
				break;
			if(dist.get(here).equals(p.first) == false )
				continue;
			for (Pair nV: edges[here]){
				if(dist.get(here) + nV.first < dist.get(nV.second)){
					dist.set(nV.second, dist.get(here) + nV.first );
					dad.set(nV.second, here);
					Q.add(new Pair(dist.get(nV.second), nV.second ));
				}
			}
		}
		
		System.out.print("path from Source " + source + " to target " + target + " is: ");
		if (dist.get(target) < INF){
			for (int i = target; i!=-1; i = dad.get(i))
				System.out.print(i+ " ");
			System.out.println("");
		}
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		FastDijkstras dif = new FastDijkstras();
		dif.input();
		dif.dijkstras();
	}

}
