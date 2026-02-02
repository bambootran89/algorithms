package DP;
/**
 * Longest Common Subsequence problem.
 *
 * @author Algorithms Collection
 */


import java.util.ArrayList;
import java.util.Arrays;

public class LCS {
  /* The problem
   * LCS Problem Statement: Given two sequences, 
   * find the length of longest subsequence present in both of them.
   * A subsequence is a sequence that appears in the same relative order, 
   * but not necessarily contiguous
   */
  
  /* the solution 
   * optimal substructure:
   * Let the input sequences be X[0..m-1] and Y[0..n-1] of lengths m and n respectively
   * let L(X[0..m-1], Y[0..n-1]) be the length of LCS of the two sequences X and Y
   * Following is the '''recursive definition''' of L(X[0..m-1], Y[0..n-1]):
   * if X[m-1] == Y[n-1],  L(X[0..m-1], Y[0..n-1]) = 1 + L(X[0..m-2], Y[0..n-2])
   * if X[m-1] != Y[n-1],  
   * L(X[0..m-1], Y[0..n-1]) = max(L(X[0..m-1], Y[0..n-2]), L(X[0..m-2], Y[0..n-1]))
   */
  
  private char[] s1, s2;
  private int[][] L;
  
  
  public LCS(String s1, String s2){
    this.s1 = s1.toCharArray();
    this.s2 = s2.toCharArray();
    L = new int[s1.length()+1][s2.length()+1];
    for (int[] row : L)
        Arrays.fill(row, 0);
  }
  
  private int lcs(char[] x, char[] y,int m, int n){
  
    for (int i = 0 ; i<= m; i++){
      for (int j = 0 ; j <= n; j++)
      {
        if (i == 0 || j == 0)
          L[i][j] = 0;
        else if (x[i-1] == y[j-1]){
          L[i][j] = L[i-1][j-1] +1;
        } else{
          L[i][j] = Integer.max(L[i-1][j], L[i][j-1]);
        }
      }
    }
    
    return L[m][n];
  }
  
  public int lcs(){
    
    int l = this.lcs(s1, s2, s1.length, s2.length);
    
    /* Traceback approach
     * The actual subsequences are deduced in a "traceback" procedure 
     * that follows the arrows backwards,
     *  starting from the last cell in the table.
     *  When the length decreases, the sequences must have had a common element.
     */
    
    
    int nrow = s1.length; 
    int ncol = s2.length;
    int r = nrow;
    
    ArrayList<Integer> change = new ArrayList<>();
    for (int c = ncol; c>0; c--){
      if (L[r][c] != L[r][c-1]){
        change.add(c);
        r--;
      }
    }
    System.out.print("One of the largest charactor sequences is: ");
    for (int i = change.size()-1; i >=0; i--)
      System.out.print(s2[change.get(i) -1]);  
    return l;
  }
  public static void main(String[] args) {
    String s1 = "The opening screen of the sampling simulation displays " 
        + "all 100 animals in the population";
      String s2 = "You can select between a random sample and a stratified sample " 
          + "directly below the population and then generate a sample of ten animals";
    LCS lcs = new LCS(s1,s2);
      System.out.println("\nlcs is:" + lcs.lcs());
  }

}
