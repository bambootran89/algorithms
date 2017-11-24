package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class BellmanFord {
	private class Edge {
		int u;
		int v;
		double w;
		public Edge(int u_, int v_, double w_){
			u = u_;
			v = v_;
			w = w_;
		}
		
		public String toString() { 
	         return "(" + u + "," +  v + "," +  w + ")";
	      } 
	}
	
	private final Double INF = Double.MAX_VALUE;
	private int source;
	private int target;
	private int N;
	private ArrayList<Edge> edges;
	
	String input;
	
	public BellmanFord(String file){
		input = file;
	}
	
	@SuppressWarnings("unchecked")
	public void input() throws FileNotFoundException{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(new File(input));
	    
	    N = scanner.nextInt();
	    source = scanner.nextInt();
	    target = scanner.nextInt();
	    edges = new ArrayList<>();
	    
	    for (int i = 0; i< N; i++){
	    	int M = scanner.nextInt();
	    	for (int j = 0; j < M; j++){
	    		int vertex; 
	    		Double dist;
	    		vertex = scanner.nextInt();
	    		dist = scanner.nextDouble();
	    		edges.add(new Edge(i,vertex, dist));
	    	}
	    }
	    System.out.println("\nGraph: edges are " + edges);
	}
	
	public void SolveByBellmanFord(){
		//initialize
		ArrayList<Double> dist = new ArrayList<>(Arrays.asList(new Double[N]));
		Collections.fill(dist, INF);
		ArrayList<Integer> dad = new ArrayList<>(Arrays.asList(new Integer[N]));
		Collections.fill(dad, -1);
		dist.set(source, 0.0);
		
		for (int i = 0; i<N; i++){
			
			for (Edge edge: edges){
				if(dist.get(edge.v)  > (dist.get(edge.u) + edge.w)) {
					dist.set(edge.v , dist.get(edge.u) + edge.w);
					dad.set(edge.v, edge.u);
					
				}
			}
		}
		
		
		for (Edge edge: edges){
			if (dist.get(edge.v) > dist.get(edge.u) + edge.w){
				System.out.print("There is a negative-weight cycle exists: ");
				
				int size = 0;
				for (int i = edge.v; ; i = dad.get(i)){
					System.out.print(i);
					if (size > 0 && i == edge.v)
						break;
					System.out.print(" <- ");
					size++;
				}
				
				return;
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
		BellmanFord dif = new BellmanFord("data\\negativeCycleGraph");
		dif.input();
		dif.SolveByBellmanFord();
		
		dif = new BellmanFord("data\\dijkstras");
		dif.input();
		dif.SolveByBellmanFord();
		
	}
}
