package graph;
/**
 * Strongly Connected Components algorithm.
 *
 * @author Algorithms Collection
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class StrongConnectedConponents {
  private class Edge {
    int e;
    int nxt;
    public Edge(int e_, int nxt_){
      e = e_;
      nxt = nxt_;
    }
    public String toString() { 
           return "(" + e + "," +  nxt +  ")";
        } 
  }
  
  int V,E;
  ArrayList<Edge> e, er;
  ArrayList<Integer> sp, spr;
  Stack<Integer> stk;
  
  int group_count;
  ArrayList<Boolean> v;
  
  HashMap<Integer, ArrayList<Integer>> groups;
  
  void add_edge(int v1, int v2){
    e.add( new Edge(v2, sp.get(v1)) );
    E++;
    sp.set(v1, E);
    er.add( new Edge(v1, spr.get(v2)) );
    spr.set(v2, E);
    
  }
  
  void fill_forward(int x){
    v.set(x, true);
    for (int i = sp.get(x); i !=-1; i=e.get(i).nxt)
      if (v.get(e.get(i).e) == false) {
        System.out.println(x + " --> " + e.get(i).e );
        fill_forward(e.get(i).e);
      }
    
    stk.push(x);
    
  }
  
  void fill_backward(int x){
    v.set(x, false);
    
    if(groups.containsKey(group_count))
      groups.get(group_count).add(x);
    else
      groups.put(group_count, new ArrayList<>(Arrays.asList(x)));
    
    for (int i = spr.get(x); i !=-1; i=er.get(i).nxt)
      if (v.get(er.get(i).e) == true) 
        fill_backward(er.get(i).e);
  }
  
  void SCC() {
    int i = 0;
    stk = new Stack<>();
    groups = new HashMap<>();
    
    
    v = new ArrayList<>(Arrays.asList(new Boolean[V]));
    Collections.fill(v, false);
    
    for (i = 0; i<V; i++)
      if (v.get(i) == false)
        fill_forward(i);
    
    System.out.println("stk is : " + stk.toString());
    group_count = 0;
    
    
    while (!stk.isEmpty()) {
      i = stk.pop();
      if(v.get(i) == true) {
        group_count++;
        fill_backward(i);
      }
        
    }
    
    System.out.println("groups: " + groups.toString());
  }
  
  public void input() throws FileNotFoundException{
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(new File("data\\scc"));
      
      int N = scanner.nextInt();
      V = scanner.nextInt();
      E = -1;
      sp = new ArrayList<>(Arrays.asList(new Integer[V]));
    Collections.fill(sp, -1);
    spr = new ArrayList<>(Arrays.asList(new Integer[V]));
    Collections.fill(spr, -1);
    e = new ArrayList<>();
    er = new ArrayList<>();
    
      
      for (int i = 0; i< N; i++){
        
        int v1 = scanner.nextInt();
        int M  = scanner.nextInt();
        for (int j = 0; j < M; j++){
          int v2 = scanner.nextInt();
          add_edge(v1, v2);
        }
      }
      
      System.out.println("Edges: " + e);
      System.out.println("revered Edges: " + er);
      System.out.println("sp: " + sp);
      System.out.println("epr: " + spr);
      
  }
  public static void main(String[] args) throws FileNotFoundException {
    StrongConnectedConponents scc = new StrongConnectedConponents();
    scc.input();
    scc.SCC();
  }
}
