package Optimization;
/**
 * Global minimum cut algorithm.
 *
 * @author Algorithms Collection
 */


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

    @Override
    public String toString() {
      return "(" + first + "," + second + ")"; 
    }
    
    
  }
  
  public GlobalMinCut(int N){
    weights = new ArrayList<>();
    for (int i =0 ; i< N; i++){
      ArrayList<Integer> w_edges = new ArrayList<>(Arrays.asList(new Integer[N]));
      Collections.fill(w_edges, 0);
      weights.add(w_edges);
    }
  
  }
  
  void addEdge(int i, int j, int w){
    weights.get(i).set(j, w);
    weights.get(j).set(i, w);
  }
  
  ArrayList<ArrayList<Integer>> weights;
  
  public Pair getMinCut(){
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
  
  public static void main(String[] args){
    GlobalMinCut gmc = new GlobalMinCut(8);
    
    gmc.addEdge(0, 1, 2);
    gmc.addEdge(0, 4, 3);
    gmc.addEdge(4, 1, 2);
    gmc.addEdge(4, 5, 3);
    gmc.addEdge(1, 5, 2);
    gmc.addEdge(1, 2, 3);
    gmc.addEdge(5, 6, 1);
    gmc.addEdge(2, 6, 2);
    gmc.addEdge(2, 3, 4);
    gmc.addEdge(6, 7, 3);
    gmc.addEdge(6, 3, 2);
    gmc.addEdge(3, 7, 2);
    
    System.out.println(gmc.getMinCut());
    
  }
  
}
