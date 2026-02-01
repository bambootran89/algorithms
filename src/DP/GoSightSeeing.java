package DP;
/**
 * Sightseeing tour optimization.
 *
 * @author Algorithms Collection
 */


import java.util.ArrayList;
import java.util.Arrays;

public class GoSightSeeing {
  /* The problem:
   * 
   * Find out the best plan to visit as many cities as possible 
   * the constrain is that time to complete the trip have to be not greater than Tf.
   * Time to go sight seeing at one city is constant Ts.
   * There is one way bus route from 1->2-> ... -> N. 
   * Each bus route at each city is ruled by 3-tuple (Si,Fi, Di) 
   * Si is start time of the first bus.
   * Fi is the interval time between 2 buses 
   * Di is duration the bus need to travel from i to i+1
   * 
   * xth-bus will departure at Si+(x-1)Fi
   * 
   */
  
  int Tf;
  int Ts;
  
  public GoSightSeeing(int tf, int ts){
    Tf = tf;
    Ts = ts;
  }
    
  
  int nextTime(int t, int s, int f, int d){
    if (s>= t) 
      return s +d;
    else
      return t + ((s - t)%f+f)%f + d;
  }
  
  int goSightSeeing(int n, int[] S, int[] F, int[] D){
    
    /*  
     *  dp[i][j] is minimum time can be archived when we'r standing at i 
     *  and already visited j cities.
     *  
     *  duration can be classified into groups: 
     *  moving time from i to i+1: Di
     *  visisting time: Si if visiting city i else 0
     *  waiting bus time
     *   
     *  I call nextTime(t,s,f,d) is arrival time at i+1:
     *  t is current time at i
     *  s is start time of the first bus
     *  f is interval time among two bus
     *  d is duration for traval from i to i+1
     *   
     *  if s>t, nextTime = s+d
     *  if s<t, nextTime = t + ((s - t)%f+f)%f + d
     *  
     *  1. if don't go sightseeing at i, dp[i][j] = nextTime(dp[i-1][j], S[i], F[i], D[i])
     *  2. if go sight seeing at i, dp[i][j] = nextTime(dp[i-1][j-1]  +Ts, S[i], F[i], D[i])
     *  
     *  dp[0][0] = 0
     *  
     *  the solution is maxi subject to dp[n-1][i]<=Tf.
     */
    
    int[][]  dp = new int[n][n];
    for(int i =0 ; i< n; i++)
      Arrays.fill(dp, Tf+1); //infinite value
    
    /* note: city 1 is the first place to visit
     *       city n is the last place ( time to go sightseeing don't include)
     *       just evaluate city 1,2, ..., n-1
     * 
     */
    dp[0][0] = 0; 
    for (int i  = 1; i<n; i++)
      for (int j = 0; j<=i; j++){
        dp[i][j] = nextTime(dp[i-1][j],S[i],F[i],D[i]);
        if(j!=0) dp[i][j] = Integer.min(dp[i][j], nextTime(dp[i-1][j-1] +Ts ,S[i],F[i],D[i]) );
      }
        
    int max_v = -1;
    for (int r=0; r< n; r++){
      if (dp[n-1][r] <= Tf){
        max_v = r;
      }
    }
    
    /* back track
     * just basing on dp[][]. 
     * at i: to locate whether or not go sightseeing at i-1.
     * just by checking dp[i][j] equals dp[i-1][j] or dp[i-1][j-1] 
     */
    int[] visit_city = new int[n]; 
    int j = max_v;
    for(int i = n-1; i>0; i--){
      if (dp[i][j] == nextTime(dp[i-1][j],S[i],F[i],D[i])){
        visit_city[i] = 0;
      }else{
        visit_city[i] = 1;
        j--;
      }
    }
    
    System.out.print("which city will be visited:");
    for (int i = 1; i<visit_city.length; i++){
      if (visit_city[i] == 1)
        System.out.print(" " + i);
    }
    
    System.out.println("");
    return max_v;
  }
  
  
  public static void main(String[] args) {
        
  }

}
