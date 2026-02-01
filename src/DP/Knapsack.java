package DP;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 0/1 Knapsack Problem solution using Dynamic Programming.
 *
 * <p>
 * Given a knapsack of capacity S and a list of items with weights and values,
 * find the maximum
 * value that can be obtained by selecting items that fit in the knapsack.
 *
 * <p>
 * Time Complexity: O(n * S) where n is the number of items and S is the
 * knapsack capacity
 * Space Complexity: O(n * S)
 *
 * @author Algorithms Collection
 */
public class Knapsack {

  /**
   * Solves the 0/1 Knapsack problem using dynamic programming.
   *
   * @param n the number of items
   * @param S the knapsack capacity
   * @param s array of item weights
   * @param v array of item values
   * @return the maximum value that can be obtained
   *
   *         <p>
   *         dp[i][j] represents the maximum value obtainable using items 0...i-1
   *         with capacity j
   *         For each item, we decide whether to include it (if it fits) or
   *         exclude it.
   */
  public double knapsack(int n, int S, int s[], double v[]) {

    /*
     * dp[i][j] is the maximun value that can be obtained
     * by using a subset of the items i ... n-1 (last n-i items) which weights at
     * most j pounds.
     * 1. add item i to the knapsack. In this case,
     * we need to choose a subset of the items i+1 ... n-1 that weights at most j -
     * si pounds.
     * Assuming we do that optimally, we'll obtain dp[i+1][j-si] value out of items
     * i+1 ... n-1, so the
     * total value will be vi + dp[i+1][j-si].
     * 2. Don't add item i to the knapstack, so we'll re-use the optimal solution
     * for items i+1 ... n-1
     * that weights at most j pounds. That answer is in dp[i+1][j]
     * 
     * we want to maximize our profits, so we'll choose the best possible outcome
     * dp[i][j] = max(dp[i+1][j], dp[i+1][j-si] + vi with j >= si)
     * 
     * the answer to our original problem can be found in dp[0][S]
     */

    /*
     * solving, this solution is pseudo-polynomia O(nS)
     * to implement effectively, dp[n][j] = 0
     */
    double[][] dp = new double[n + 1][S + 1];

    for (int i = n; i >= 0; i--)
      for (int j = 0; j <= S; j++)
        if (i == n)
          dp[i][j] = 0;
        else {
          double[] choices = new double[2];
          choices[0] = dp[i + 1][j];
          if (j >= s[i]) {
            choices[1] = dp[i + 1][j - s[i]] + v[i];
          }
          dp[i][j] = Double.max(choices[0], choices[1]);
        }
    /*
     * trace back.
     * to check i whether selected or not, we just check the change of dp[i][s] and
     * dp[i+1][s]
     * 
     */
    ArrayList<Integer> selects = new ArrayList<>();
    int ss = S;
    for (int i = 0; i < n; i++)
      if (dp[i][ss] != dp[i + 1][ss]) {
        ss -= s[i];
        selects.add(i);
      }
    System.out.println("items at indexes" + selects + " are selected");
    return dp[0][S];

  }

  public static void main(String[] args) {
    Knapsack kns = new Knapsack();

    // double v[] = new double[]{9,8,10,9,3};
    // int s[] = new int[]{4,3,5,6,2};
    // int S = 8;
    // int n = v.length;

    double v[] = new double[] { 100, 60, 120 };
    int s[] = new int[] { 20, 10, 30 };
    int S = 50;
    int n = v.length;

    System.out.println(kns.knapsack(n, S, s, v));

  }
}
