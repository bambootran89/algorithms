package DP;
/**
 * K-sum problem variants.
 *
 * @author Algorithms Collection
 */


import java.util.ArrayList;

public class Ksum {
  
  private class Pair{
    int first;
    ArrayList<Integer> second;
    public Pair(int f, ArrayList<Integer> s){
      first = f;
      second = s;
    }
    @Override
    public String toString() {
      return "(" + first + "," + second.toString() + ")";
    }
  }
  
  Pair ksum(int n,int K,int S,int s[]){
    
    /* a further restriction of the subset sum problem is that the backpack has K pockets,
     *  and you can fit exactly one gold bar in a pocket.
     *  dp[i][j][k] is true if there is a k-element subset of the gold bars i ... n-1 
     *  that weights exactly j pounds. dp[i][j][k] is computed considering all possible values of di 
     *  (the decision at step i, which is whether or not to include bar i).
     *  
     *  1. add bar i to the knapsack. In this case, we need to choose a k-1 bar subset of the bars 
     *  i+1 ... n-1 that weights exactly j - si pounds. dp[i+1][j-si][k-1] indecates whether such a subset exists.
     *  
     *  2. don't add item i to the knapsack. in this case, the solution rests on a k-bar subset of the bars i+1 ... n-1 
     *  that weights exactly j pounds. the answer of whether such a subset exists is in dp[i+1][j][k].
     *  
     *  dp[i][j][k] =  max(dp[i+1][j][k], dp[i+1][j-si][k-1] if j>=si and k>0)
     * 
     */
    
    int[][][] dp= new int[n+1][S+1][K+1];
    
    /*
     * Solve problem
     */
    
    for (int i = n; i>=0; i--){
      for (int j = 0; j<=S; j++){
        for (int k = 0; k<=K; k++){
          if(i == n){
            if(j == 0 && k == 0){
              dp[i][j][k] = 1;
            }else{
              dp[i][j][k] = 0;
            }
          }else{
            int[] choices = new int[2];
            choices[0] = dp[i+1][j][k];
            if(k>0 && j>=s[i]){
              choices[1] = dp[i+1][j-s[i]][k-1];
            }
            
            dp[i][j][k] = Integer.max(choices[0], choices[1]);
            
          }
          
        }
      }
        
    }
    
    /*
     * Trace back
     */
    ArrayList<Integer> selects = new ArrayList<>();
    if(dp[0][S][K] == 0)
      return new Pair(dp[0][S][K], selects );
    
    int kk = K;
    int ss = S;
    for (int i = 1; i<=n; i++){
      if(dp[i][ss][kk] == 0){
        selects.add(s[i-1]);
        ss-=s[i-1];
        kk--;
      }
    }
    
    return new Pair(dp[0][S][K], selects );
  }
  
  
  public static void main(String[] args) {
    Ksum ksum = new Ksum();
    
    int[] s = new int[]{9,12,33,5,0,7};
    int n = s.length;
    int S = 17;
    int K = 2;
    
    System.out.println("Being able to find a subset " + K + "-bars whose "
        + "weights add up to exactly " + S +" ? " + ksum.ksum(n, K, S, s) );
  }

}
