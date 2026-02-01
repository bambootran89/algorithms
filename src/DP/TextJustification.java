package DP;
/**
 * Text justification problem.
 *
 * @author Algorithms Collection
 */


import java.util.Arrays;

public class TextJustification {

  /* split text into "good" lines
   * define badness(i,j) for single line of words[i:j]
   * e.g. cost of a line = (Number of extra spaces in the line)^2
   * Total Cost = Sum of costs for all lines
   * goal: split words into lines to min(sum of badness)
   */
  
  int M = 15;
  int INF = Integer.MAX_VALUE/2;
  
  public TextJustification(int m){
    M = m;
  }
  int justification(int n, int w[]){
    /* 
     * 1. subproblem = min badness for suffix words[i:]
     * number of subproblem is O(n) where n is number of words
     * 2. guessing = where to end first line, say [i:j]
     * number of choises = n - 1 = O(n)
     * 3. recurrence: dp[i] = min(badness(i,j) + dp[j] for j in range(i+1, n+1))
     * dp[n] = 0 -> time per subproblem = O(n) 
     * 4. order: for i = n, n-1, ... 1, 0
     * total time = O(n^2)
     * 5. solution dp[0]
     */
    
    int dp[] = new int[n+1];
    
    int p[]  = new int[n+1];
    
    int[][] badness = new int[n][n+1];
    
    /* calculate extra spaces in a single line,
     * if words from word number i to j are placed in a single line
     */
    
    for (int i = 0; i<n; i++){
      badness[i][i] = M;
      for (int j = i+1; j<=n; j++){
        int space = 0;
        if(j-i > 1)
          space = 1;
        badness[i][j] = badness[i][j-1] -  w[j-1] - space;
      }
    }
    
    
    /* calculate badness
     */
    
    for (int i = 0; i<n; i++){
      for (int j = i+1; j<= n; j++){
        if(badness[i][j] < 0)
          badness[i][j] = INF;
        else if (j == n && badness[i][j] >=0)
          badness[i][j] = 0;
        else{
          badness[i][j] = badness[i][j]*badness[i][j];
        }
      }
    }
    
    
    /* solve problem
     * recurrence: dp[i] = min(badness(i,j) + dp[j] for j in range(i+1, n+1))
       * dp[n] = 0 -> time per subproblem = O(n) 
     */
    dp[n] = 0;
    for (int i = n-1; i>=0; i--){
      dp[i] = INF;
      for (int j = i+1; j <=n; j++){
        int b = badness[i][j]+ dp[j];
        if (b<dp[i]){
          dp[i] = b;
          p[i] = j;
        }
      }
        
    }
    
    /*  back track basing on p
     * 
     */
    int line = 0;
    for(int i = 0;i!=n ;  ){
      System.out.println("Line " + line + ": from index " + i +" to " + (p[i] - 1));
      i = p[i];
      line++;
    }
    return dp[0];
  }
  
  public static void main(String[] args) {
    TextJustification TJ = new TextJustification(15);
    /*
     *  If you are connected but behind a firewall check that Firefox has permission to access the Web
     */
    int[] w = new int[]{2, 3, 3, 9, 3, 6, 1, 8, 5, 4, 7, 3, 10, 2, 6, 3, 3};
    int n = w.length;
    System.out.println("Optimal cost : " + TJ.justification(n, w));
  }

}
