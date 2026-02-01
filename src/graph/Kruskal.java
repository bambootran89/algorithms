package graph;
/**
 * Kruskal's Minimum Spanning Tree algorithm.
 *
 * @author Algorithms Collection
 */


import java.util.ArrayList;
import java.util.PriorityQueue;


public class Kruskal {
  private class Edge implements Comparable<Edge>{
    int u, v;
    Double d;
    public Edge(int _u, int _v, Double _d) {
      // TODO Auto-generated constructor stub
      u = _u;
      v = _v;
      d = _d;
    }
    
    @Override
    public int compareTo(Edge e) {
      if (this.d > e.d)
        return 1;
      else if (this.d < e.d)
        return -1;
      else
        return 0;
    }
  
  }
  
  ArrayList<ArrayList<Double>> w;
  
  int find(int[] C, int x){
    return (C[x] == x)? x: ( C[x] = find(C, C[x]) ) ;
    
  }  
  double Kruskal(){
    
    
    int n = w.size();
    double weight = 0;
    
    int[] C = new int[n];
    int[] R = new int[n];
    
    for (int i =0; i< n; i++) {
      C[i] = i;
      R[i] = 0;
    }
    
    ArrayList<Edge> T = new ArrayList<>();
    PriorityQueue<Edge> E = new PriorityQueue<>();
    
    for (int i =0; i< n; i++)
      for (int j = i+1;  j < n; j++)
        if(w.get(i).get(j)>=0){
          Edge e = new Edge(i, j, w.get(i).get(j));
          E.add(e);
        }
    
    while (T.size() < n-1 && !E.isEmpty()){
      
      Edge cur = E.poll();
      int uc = find(C, cur.u);
      int vc = find(C, cur.v);
      
      if(uc!=vc){
        T.add(cur);
        weight+=cur.d;
        if(R[uc] > R[vc]) C[vc] = uc;
        else if (R[vc] > R[uc]) C[uc] = C[vc];
        else {
          C[vc] = uc;
          R[uc]++;
        }
       }
      
    }
    
    return weight;
  }
  
  
}
